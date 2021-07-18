import cv2
import numpy as np
class Detector:
    def __init__(self):
        self.is_cpu=1
        self.protoFile = "C:\\ProgramData\\Anaconda3\\envs\\DarkflowTest\\openpose\\models\\pose\\coco\\pose_deploy_linevec.prototxt"
        self.weightsFile = "C:\\ProgramData\\Anaconda3\\envs\\DarkflowTest\\openpose\\models\\pose\\coco\\pose_iter_440000.caffemodel"
        self.net = cv2.dnn.readNetFromCaffe(self.protoFile, self.weightsFile)
        if self.is_cpu == 1:
            self.net.setPreferableBackend(cv2.dnn.DNN_TARGET_CPU)
        else:
            self.net.setPreferableBackend(cv2.dnn.DNN_BACKEND_CUDA)
            self.net.setPreferableTarget(cv2.dnn.DNN_TARGET_CUDA)

        self.nPoints = 9
        self.keypointsMapping = ['Nose', 'Neck', 'R-Sho', 'R-Elb', 'R-Wr', 'L-Sho', 'L-Elb', 'L-Wr', 'R-Hip', 'R-Knee', 'R-Ank', 'L-Hip', 'L-Knee', 'L-Ank', 'R-Eye', 'L-Eye', 'R-Ear', 'L-Ear']
        self.POSE_PAIRS = [[1,2], [1,5], [2,3], [3,4], [5,6], [6,7],
                  [1,8],  [1,11]]
        # index of pafs correspoding to the POSE_PAIRS
        # e.g for POSE_PAIR(1,2), the PAFs are located at indices (31,32) of output, Similarly, (1,5) -> (39,40) and so on.
        self.mapIdx = [[31,32], [39,40], [33,34], [35,36], [41,42], [43,44],
              [19,20], [25,26]]



    def getKeypoints(self,probMap, threshold=0.1):
        mapSmooth = cv2.GaussianBlur(probMap,(3,3),0,0)
        mapMask = np.uint8(mapSmooth>threshold)
        keypoints = []
        contours, _ = cv2.findContours(mapMask, cv2.RETR_TREE, cv2.CHAIN_APPROX_SIMPLE)
        #확률이 가장 높은 점 찾기
        for cnt in contours:
            blobMask = np.zeros(mapMask.shape)
            blobMask = cv2.fillConvexPoly(blobMask, cnt, 1)
            maskedProbMap = mapSmooth * blobMask
            _, maxVal, _, maxLoc = cv2.minMaxLoc(maskedProbMap)
            keypoints.append(maxLoc + (probMap[maxLoc[1], maxLoc[0]],))

        return keypoints

    # Find valid connections between the different joints of a all persons present
    def getValidPairs(self,output,frameWidth, frameHeight, detected_keypoints):
        valid_pairs = []
        invalid_pairs = []
        n_interp_samples = 10
        paf_score_th = 0.1
        conf_th = 0.7
        # loop for every POSE_PAIR
        for k in range(len(self.mapIdx)):
            # A->B constitute a limb
            pafA = output[0, self.mapIdx[k][0], :, :]
            pafB = output[0, self.mapIdx[k][1], :, :]
            pafA = cv2.resize(pafA, (frameWidth, frameHeight))
            pafB = cv2.resize(pafB, (frameWidth, frameHeight))

            # Find the keypoints for the first and second limb, 여기 수정햇음
            candA = self.POSE_PAIRS[k][0]
            if candA is 11:
                candA=candA-3
            else:
                candA = candA - 1
            candA=detected_keypoints[candA]

            candB = self.POSE_PAIRS[k][1]
            if candB is 11:
                candB=candB-3
            else:
                candB = candB - 1
            candB=detected_keypoints[candB]

            nA = len(candA)
            nB = len(candB)

            # If keypoints for the joint-pair is detected
            # check every joint in candA with every joint in candB
            # Calculate the distance vector between the two joints
            # Find the PAF values at a set of interpolated points between the joints
            # Use the above formula to compute a score to mark the connection valid

            if( nA != 0 and nB != 0):
                valid_pair = np.zeros((0,3))
                for i in range(nA):
                    max_j=-1
                    maxScore = -1
                    found = 0
                    for j in range(nB):
                        # Find d_ij
                        d_ij = np.subtract(candB[j][:2], candA[i][:2])
                        norm = np.linalg.norm(d_ij)
                        if norm:
                            d_ij = d_ij / norm
                        else:
                            continue
                        # Find p(u)
                        interp_coord = list(zip(np.linspace(candA[i][0], candB[j][0], num=n_interp_samples),
                                                np.linspace(candA[i][1], candB[j][1], num=n_interp_samples)))
                        # Find L(p(u))
                        paf_interp = []
                        for k in range(len(interp_coord)):
                            paf_interp.append([pafA[int(round(interp_coord[k][1])), int(round(interp_coord[k][0]))],
                                               pafB[int(round(interp_coord[k][1])), int(round(interp_coord[k][0]))] ])
                        # Find E
                        paf_scores = np.dot(paf_interp, d_ij)
                        avg_paf_score = sum(paf_scores)/len(paf_scores)

                        # Check if the connection is valid
                        # If the fraction of interpolated vectors aligned with PAF is higher then threshold -> Valid Pair
                        if ( len(np.where(paf_scores > paf_score_th)[0]) / n_interp_samples ) > conf_th :
                            if avg_paf_score > maxScore:
                                max_j = j
                                maxScore = avg_paf_score
                                found = 1
                    # Append the connection to the list
                    if found:
                        valid_pair = np.append(valid_pair, [[candA[i][3], candB[max_j][3], maxScore]], axis=0)

                # Append the detected connections to the global list
                valid_pairs.append(valid_pair)
            else: # If no keypoints are detected
                invalid_pairs.append(k)
                valid_pairs.append([])
        return valid_pairs, invalid_pairs



    # This function creates a list of keypoints belonging to each person
    # For each detected valid pair, it assigns the joint(s) to a person
    def getPersonwiseKeypoints(self,valid_pairs, invalid_pairs, keypoints_list):
        # the last number in each row is the overall score
        personwiseKeypoints = -1 * np.ones((0, 19))

        for k in range(len(self.mapIdx)):
            if k not in invalid_pairs:
                partAs = valid_pairs[k][:,0]
                partBs = valid_pairs[k][:,1]
                indexA, indexB = np.array(self.POSE_PAIRS[k])

                for i in range(len(valid_pairs[k])):
                    found = 0
                    person_idx = -1
                    for j in range(len(personwiseKeypoints)):
                        if personwiseKeypoints[j][indexA] == partAs[i]:
                            person_idx = j
                            found = 1
                            break

                    if found:
                        personwiseKeypoints[person_idx][indexB] = partBs[i]
                        personwiseKeypoints[person_idx][-1] += keypoints_list[partBs[i].astype(int), 2] + valid_pairs[k][i][2]

                    # if find no partA in the subset, create a new subset
                    elif not found and k < 17:
                        row = -1 * np.ones(19)
                        row[indexA] = partAs[i]
                        row[indexB] = partBs[i]
                        # add the keypoint_scores for the two keypoints and the paf_score
                        row[-1] = sum(keypoints_list[valid_pairs[k][i,:2].astype(int), 2]) + valid_pairs[k][i][2]
                        personwiseKeypoints = np.vstack([personwiseKeypoints, row])
        return personwiseKeypoints

    def detectBody(self,image1):
        frameWidth = image1.shape[1]
        frameHeight = image1.shape[0]

        # Fix the input Height and get the width according to the Aspect Ratio
        inHeight = 368
        inWidth = int((inHeight/frameHeight)*frameWidth)

        inpBlob = cv2.dnn.blobFromImage(image1, 1.0 / 255, (inWidth, inHeight),
                                  (0, 0, 0), swapRB=False, crop=False)

        self.net.setInput(inpBlob)
        output = self.net.forward()


        #찾은 관절들 좌표 저장
        detected_keypoints = []
        detected_keypoints_for_search = []
        keypoints_list = np.zeros((0,3))
        keypoint_id = 0
        threshold = 0.1

        for part in (1, 2, 3, 4, 5, 6, 7, 8, 11):
            probMap = output[0,part,:,:]
            probMap = cv2.resize(probMap, (image1.shape[1], image1.shape[0]))
            keypoints = self.getKeypoints(probMap, threshold)
            keypoints_with_id = []
            for i in range(len(keypoints)):
                keypoints_with_id.append(keypoints[i] + (keypoint_id,))
                keypoints_list = np.vstack([keypoints_list, keypoints[i]])
                keypoint_id += 1
            detected_keypoints.append(keypoints_with_id)

        for part in detected_keypoints:
            if len(part)==0:
                continue
            for point in part:
                detected_keypoints_for_search.append((point[0],point[1]))

        #관절들을 어울리게 이음
        valid_pairs, invalid_pairs = self.getValidPairs(output,frameWidth,frameHeight,detected_keypoints)
        #이거 잘 수정하면 인덱스가 아니라 좌표를 넣을 수 있게 만들어 볼까?
        personwiseKeypoints = self.getPersonwiseKeypoints(valid_pairs, invalid_pairs, keypoints_list)

        ret=[]

        for person in personwiseKeypoints:
            point_in_a_person=[]
            for index in (1, 2, 3, 4, 5, 6, 7, 8, 11):
                point=np.int32(person[index])
                if point == -1:
                    point_in_a_person.append(-1)
                    point_in_a_person.append(-1)
                else:
                    point_in_a_person.append(detected_keypoints_for_search[point][0])
                    point_in_a_person.append(detected_keypoints_for_search[point][1])
            ret.append(point_in_a_person)
        return ret

