import cv2
import numpy

class Detector:
    def __init__(self):
            #모델 불러오기, 경로 바꾸자.
            self.protoFile = "C:\\ProgramData\\Anaconda3\\envs\\DarkflowTest\\openpose\\models\\pose\\mpi\\pose_deploy_linevec.prototxt"
            self.weightsFile = "C:\\ProgramData\\Anaconda3\\envs\\DarkflowTest\\openpose\\models\\pose\\mpi\\pose_iter_160000.caffemodel"
            self.net = cv2.dnn.readNetFromCaffe(self.protoFile, self.weightsFile)
    def detectBody(self,image):
        imageHeight, imageWidth, _ = image.shape
        inpBlob = cv2.dnn.blobFromImage(image, 1.0 / 255, (imageWidth, imageHeight), (0, 0, 0), swapRB=False, crop=False)
        self.net.setInput(inpBlob)

        # 결과 받아오기
        output = self.net.forward()

        # output.shape[0] = 이미지 ID, [1] = 출력 맵의 높이, [2] = 너비
        H = output.shape[2]
        W = output.shape[3]
        print("이미지 ID : ", len(output[0]), ", H : ", output.shape[2], ", W : ", output.shape[3])  # 이미지 ID

        # 키포인트 검출시 이미지에 그려줌
        points = []
        # 각 부위마다 정한다.
        #"Head": 0, "Neck": 1, "RShoulder": 2, "RElbow": 3, "RWrist": 4,"LShoulder": 5, "LElbow": 6, "LWrist": 7, "RHip": 8, LHip": 11,"Chest": 14
        for i in (0, 1, 2, 3, 4, 5, 6, 7, 8, 11,  14):
            # 해당 신체부위 신뢰도 얻음.
            probMap = output[0, i, :, :]

            # global 최대값 찾기
            minVal, prob, minLoc, point = cv2.minMaxLoc(probMap)

            # 원래 이미지에 맞게 점 위치 변경
            x = (imageWidth * point[0]) / W
            y = (imageHeight * point[1]) / H

            # 키포인트 검출한 결과가 0.1보다 크면(검출한곳이 위 BODY_PARTS랑 맞는 부위면) points에 추가, 검출했는데 부위가 없으면 None으로
            # threadhold는 결과 보면서 수정
            if prob > 0.1:
                #이걸 적당한 형태로 reshape,9*2
                points.append((int(x),int(y)))
            else:
                points.append((None,None))

        points=numpy.array(points)
        points.reshape(1,-1)
        return points
