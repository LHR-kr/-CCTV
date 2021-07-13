import cv2
from queue import Queue
from objectDetector import CNN_Model
from detectBody import Detector
import detectAction

from flask import Response
from flask import Flask
from flask import render_template

class CCTV:
    def __init__(self):

        self.frame=None
        #시작 전에 바꿔주기
        self.ideal_num = {"total": 0, "chocopie": 0, "frenchpie": 0, "margaret": 0, "moncher": 0}
        self.real_num = {"total": 0, "chocopie": 0, "frenchpie": 0, "margaret": 0, "moncher": 0}
        self.deltanum=0
        self.frame_queue = Queue()

        self.camera = cv2.VideoCapture(0)
        self.width = (self.camera.get(cv2.CAP_PROP_FRAME_WIDTH))
        self.height = (self.camera.get(cv2.CAP_PROP_FRAME_HEIGHT))
        self.fps = self.camera.get(cv2.CAP_PROP_FPS)
        self.fourcc = cv2.VideoWriter_fourcc(*'DIVX')
        self.writer = cv2.VideoWriter("video.avi", self.fourcc, self.fps, (int(self.width), int(self.height)))

        self.CNN_model=CNN_Model()
        self.RNN_model=detectAction.build()
        self.body_detector=Detector() # 불러오기


    #메인 함수, 스트리밍 + ai 처리
    def run(self):
        app = Flask(__name__)
        def gen():
            while True:
                success, self.frame = self.camera.read()  # read the camera frame
                if not success:
                    break
                else:
                    ret, buffer = cv2.imencode('.jpg', self.frame)
                    encoded_frame = buffer.tobytes()
                    yield (b'--frame\r\n'
                           b'Content-Type: image/jpeg\r\n\r\n' + encoded_frame + b'\r\n\r\n')
                self.work()


        @app.route('/')
        def index():
            # rendering webpage
            return render_template('streamingWeb.html')

        @app.route('/video_feed')
        def video_feed():
            return Response(gen(),
                            mimetype='multipart/x-mixed-replace; boundary=frame')

        # defining server ip address and port
        app.run(host='0.0.0.0', port='8080', debug=False)


    def work(self):
        self.frame_queue.put(self.frame)

        det_ret=self.CNN_model.detect()
        if det_ret.get("human") == 0:
            #프레임만 처리하고 continue
            return
        # 이제 도난 감지 시작
        if self.update_num(dict=det_ret) == 1:
            #개수 바뀜
        else:
            #개수 안 바뀜



    def update_num(self,dict):
        num=list(zip(dict.values(),self.real_num.values()))
        for n in (1,2,3,4):
            if n[0] != n[1]:
                self.real_num.update(total=sum(dict.values()))
                return 1
        return 0


    def detect_action(self,frame,q):
        result=self.body_detector.detectBody(frame)
        result.append(result,)


if __name__=="__main__":
    cctv=CCTV()
    cctv.run()


















