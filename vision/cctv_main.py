import makeWeb
import cv2
from queue import Queue
from objectDetector import CNN_Model
from detectBody import Detector
import detectAction

class cctv:
    def __init__(self):

        self.frame

        #시작 전에 바꿔주기
        self.ideal_num = {"total": 0, "chocopie": 0, "frenchpie": 0, "margaret": 0, "moncher": 0}
        self.real_num = {"total": 0, "chocopie": 0, "frenchpie": 0, "margaret": 0, "moncher": 0}
        self.deltanum=0

        self.frame_queue = Queue()
        self.CNN_model=CNN_Model()
        self.RNN_model=detectAction.build()
        self.body_detector=Detector() # 불러오기

        self.camera = cv2.VideoCaputre(0)
        self.width = (self.camera.get(cv2.CAP_PROP_FRAME_WIDTH))
        self.height = (self.camera.get(cv2.CAP_PROP_FRAME_HEIGHT))
        self.fps = self.camera.get(cv2.CAP_PROP_FPS)
        self.fourcc = cv2.VideoWriter_fourcc(*'DIVX')
        self.writer = cv2.VideoWriter("video.avi", self.fourcc, self.fps, (int(self.width), int(self.height)))



    def run(self):
        while True:
            ret, self.frame=cv2.read()
            if not ret:
                break
            det_ret=self.CNN_model.detect()
            if det_ret.get("human") == 0:
                #프레임만 처리하고 continue
            # 이제 도난 감지 시작
            if self.update_num(dict=det_ret) == 1:
                #개수 바뀜
            else:
                #개수 안 바뀜
                #dddgit



    def update_num(self,dict):
        num=list(zip(dict.values(),self.real_num.values()))
        for n in (1,2,3,4)
            if n[0] != n[1]:
                self.real_num.update(total=sum(dict.values()))
                return 1
        return 0


    def detect_action(self,frame,q):
        result=self.body_detector.detectBody(frame)
        result.append(result,)





















