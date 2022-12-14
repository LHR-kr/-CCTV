import cv2
import math
from objectDetector import CNN_Model
from detectBody import Detector
from detectAction import LSTM_MODEL
from datetime import datetime


import requests
import json


import operator


# cctv 화면 절반 라인 조금 넘게 매대가 오도록 화면 앵글 설정
class ai_model:
    def __init__(self):

        self.frame = None
        self.delta_stock = {"chocopie": 0, "frenchpie": 0, "margaret": 0, "moncher": 0}
        # 시작 전에 개수 초기화 해야 함
        self.stock = {"total": 5, "chocopie": 1, "frenchpie": 2, "margaret": 0, "moncher": 2}
        self.frame_queue = []

        # 임의로 테스트용으로 함, 나중에 변경
        self.camera = cv2.VideoCapture("./video/a_.mp4")
        #self.camera = cv2.VideoCapture(0)

        self.camera.set(3, int(640))
        self.camera.set(4, int(480))

        self.width = (self.camera.get(cv2.CAP_PROP_FRAME_WIDTH))
        self.height = (self.camera.get(cv2.CAP_PROP_FRAME_HEIGHT))
        self.fps = self.camera.get(cv2.CAP_PROP_FPS)
        self.fourcc = cv2.VideoWriter_fourcc(*'DIVX')
        self.writer = cv2.VideoWriter("video.avi", self.fourcc, self.fps, (int(self.width), int(self.height)))

        self.CNN_model = CNN_Model()
        self.body_detector = Detector()  # 불러오기

        # 아이디를 키로, 중심좌표를 value로 가지는 딕셔너리
        self.center_points = {}
        self.id_count = 0
        # 고객이 가져간 물건들을 저장하는 딕셔너리, 이중 딕셔너리?
        self.pick_upped = {}
        # cnt가 0되면 화면에서 사라졌다고 판단
        self.cnt_for_finding_person = {}

        self.cnt = 15


        self.headers = {'Content-Type': 'application/json; chearset=utf-8'}
        self.video_headers = {'Content-Type': 'multipart/form-data; chearset=utf-8'}

        self.LSTM_model = LSTM_MODEL()



    # 스트리밍 처리 끝났고, ai 처리
    def work(self):
        # 프레임 처리
        ret, self.frame=self.camera.read()
        if not ret:
            print("cant read")
            return None

        self.frame_queue.append(self.frame)
        if len(self.frame_queue) > 60:
            self.frame_queue.pop(0)




        # CNN
        obj_det_ret, person_num, person_bounding_boxes = self.CNN_model.detect(self.frame)

        # 사람이 매장에서 나갔는지 검사
        cant_detected = self.person_update(person_bounding_boxes)
        for id in self.center_points.keys():
            print(str(id) + " : " + str(self.pick_upped[id]))

        for id in cant_detected:
            # cnt 센다
            if self.cnt_for_finding_person[id] > 0:
                self.cnt_for_finding_person[id] = self.cnt_for_finding_person[id] - 1
            else:
                # cnt 값이 0이면 더이상 매장에서 나간 걸로 판단
                self.center_points.pop(id)
                # pick upped는 딕셔너리
                pick_upped = self.pick_upped.pop(id)
                print(str(id) + " disappeared")
                if list(pick_upped.values()) == [0, 0, 0, 0]:
                    print("he didnt")
                else:
                    print("go out ")
                # 최근 결제 내역 가져와서 비교하기
                # 결제 내역과 pick_upped가 다르면 클립 저장하고 서버에 알리기
                # if :
                #     filename = self.writeClip()
                #     # 서버 전송
                #     data = open(filename, 'rb')
                #     # api 이름이랑 파일 포맷 나중에 수정
                #     data = {"filename": data}
                #     res = requests.post('http://3.238.53.109:8080/video/aaaa/save', data=data, headers=self.headers)
                #     print(str(res.status_code) + " | " + res.text)

        # 매장에 사람 없으면 다음 프레임으로
        if person_num > 0:
            # 사람이 있고 물건 개수 변함
            # 개수 달라졌는지 체크하고 달라졌으면 업데이트 후 1반환, 아니면 0 반환
            self.delta_stock = {"chocopie": 0, "frenchpie": 0, "margaret": 0, "moncher": 0}
            result_update_stock = self.update_stock(dict=obj_det_ret)
            if result_update_stock == 1:
                # res = requests.post('http://3.233.241.48:8080/stock/aaaa', data=json.dumps(self.stock),headers=self.headers)
                # print("재고 업데이트 결과 : "+res.text)
                self.cnt-=1
            elif self.update_stock(dict=obj_det_ret) == 2:
                self.cnt=15
                self.stock = obj_det_ret

            #만약 재고가 줄어든 상태가 15프레임 이상 지속되면 재고 사라진 걸로 판단.
            if self.cnt == 0:
                self.detectAction()
                self.cnt = 15
                self.stock.update(obj_det_ret)

        print("stock : " + str(self.stock))
        print("==============================================================================")
        ret, jpg = cv2.imencode('.jpg', self.frame)
        return jpg.tobytes()

    # 개수 달라졌느지 체크하고 달라졌으면 업데이트 후 1반환, 아니면 0 반환
    # 인식률 낮은게 문제
    def update_stock(self, dict):
        # 개수 변환
        ret = 0
        for name in ('chocopie', 'frenchpie', 'margaret', 'moncher'):
            if dict[name] < self.stock[name]:
                self.delta_stock[name] = self.delta_stock[name] + (self.stock[name] - dict[name])
                return 1
        else:
             return 2

    # 클립 작성 이상 없음
    def writeClip(self):
        file_name = "./clip/" + datetime.today().strftime("%Y%m%d%H%M%S") + ".avi"
        writer = cv2.VideoWriter(file_name, self.fourcc, self.fps, (int(self.width), int(self.height)))
        for frame in self.frame_queue:
            writer.write(frame)
        writer.release()
        return file_name

    def detectAction(self):
        print("detecting action")
        # [index][0]이 id다.
        sorted_center_points_list = sorted(self.center_points.items(), key=operator.itemgetter(1))
        sorted_center_points = {}
        for i in sorted_center_points_list:
            sorted_center_points[i[0]] = i[1]

        # 프레임 큐에 있는 프레임의 신체 좌표 추출
        joint_data = [[], [], []]
        # 끝에 40프레임만 입력
        for frame in self.frame_queue[-40:]:
            # body detector의 리턴값 모양은 [사람 수 <=3][좌표데이터]
            # 1920 * 1080 기준은로 나옴
            detect_body_result = self.body_detector.detectBody(frame)
            # 목 위치 기준 왼쪽부터 정렬
            detect_body_result.sort(key=lambda x: x[0])
            cnt = 0

            # 최대 사람 수 갱신

            for personal_data in detect_body_result:
                joint_data[cnt].append(personal_data)
                cnt += 1
            for i in range(cnt, 3):
                joint_data[i].append(
                    [-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1])

        # joint_data는 [사람 수<=3][frame 40][좌표데이터 22]이다
        # 추출한 좌표를 lstm에 입력하고 도난 판단

        #joint data는 1920*1080, sorted center는 화면 해상도
        detect_action_result = self.LSTM_model.predict(joint_data, sorted_center_points)
        for id in detect_action_result:
            if detect_action_result[id] == 1:  # 도난임
                print("*************clip****************")
                filename = self.writeClip()
                # 서버 전송
                file = {'file': open(filename, 'rb')}
                # api 이름이랑 파일 포맷 나중에 수정
                # res = requests.post('http://3.233.241.48:8080/video/aaaa/save', files=file)
                # print("클립 전송 결과 : "+str(res.status_code) + " | " + res.text)
            # 도난 아님
            elif detect_action_result[id] == 2:
                print("pick upped")
                # 물건 고른 거니까 개수 증가
                print(id)
                for name in ('chocopie', 'frenchpie', 'margaret', 'moncher'):
                     self.pick_upped[id][name]=self.pick_upped[id][name]+self.delta_stock[name]
            else:
                return

    #화면 해상도 기준
    def person_update(self, objects_rect):
        # 인자는 이차원 배열이다.
        # 아이디와 중심좌표 임시로 저장하는 이차원 배열
        objects_bbs_ids = []

        for rect in objects_rect:
            x, y, w, h = rect
            cx = (x + x + w) // 2
            cy = (y + y + h) // 2

            # Find out if that object was detected already
            same_object_detected = False
            # 이전 프레임에서 찾은 박스들의 좌표와 아이디와 비교
            # 새 프레임에 있지만 이전 프레임에 없는 경우 : 새로운 아이디로 등록
            # 새 프레임에 있고 이전 프레임에도 있는 경우 : 좌표 최신화, 이전 프레임 정보에서 pop
            # 새 프레임에서 없고 이전 프레임에 있는 경우 : 이전 프레임 정보에 남김
            items = self.center_points.items()
            for id, pt in items:
                # 두 좌표 사이의 거리 계산
                # 만약 업데이트 하면 이걸 변경
                # 거리계산이 아니라 특징 분석하는 걸로 바꿀 수 있다
                # 조건 식만 바꾸면 된다
                dist = math.hypot(cx - pt[0], cy - pt[1])
                # 거리가 일정 값 미만이면 같은 물체로 취급하여 아이디, 중심좌표 저장하고 이 물체에 대한 트랙킹 종료
                if dist < 50:
                    self.center_points.pop(id)
                    # 배열에 임시 저장
                    objects_bbs_ids.append([x, y, w, h, id])
                    same_object_detected = True
                    self.cnt_for_finding_person[id] = 20
                    break

            # 만약 이전 프레임에서 찾지 못했다면 새롭게 아이디 등록
            if same_object_detected is False:
                # 배열에 임시 저장
                objects_bbs_ids.append([x, y, w, h, self.id_count])
                # 이 사람의 장바구니
                self.pick_upped[self.id_count] = {"chocopie": 0, "frenchpie": 0, "margaret": 0, "moncher": 0}
                self.cnt_for_finding_person[self.id_count] = 4
                self.id_count += 1

        # Clean the dictionary by center points to remove IDS not used anymore
        # 새 아이디와 그 물체의 중심좌표 저장하는 딕셔너리
        new_center_points = {}
        # 이전 프레임에 있던 애들 + 새로운 애들 업데이트
        for obj_bb_id in objects_bbs_ids:
            x, y, w, h, object_id = obj_bb_id
            center = ((x + x + w) // 2, (y + y + h) // 2)
            new_center_points[object_id] = center

        # (이전 프레임에 있던 애들 + 새로운 애들 + 못 찾은 애들)
        new_center_points.update(self.center_points)

        # cant는 못 찾은 애들 id 모음
        cant_detected = list(self.center_points.keys())

        self.center_points = new_center_points.copy()
        print(str(self.center_points) + " " + str(cant_detected))
        return cant_detected


    def release(self):
        self.camera.release()
        self.writer.release()
















