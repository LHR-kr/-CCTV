import cv2
from objectDetector import CNN_Model
from detectBody import Detector
from detectAction import LSTM_MODEL

from datetime import datetime

from flask import Response
from flask import Flask
from flask import render_template

class CCTV:
    def __init__(self):

        self.frame=None
        #시작 전에 개수 초기화 해야 함
        self.ideal_num = {"total": 0, "chocopie": 0, "frenchpie": 0, "margaret": 0, "moncher": 0}
        self.real_num = {"total": 0, "chocopie": 0, "frenchpie": 0, "margaret": 0, "moncher": 0}
        self.frame_queue = []

        self.camera = cv2.VideoCapture(0)
        self.width = (self.camera.get(cv2.CAP_PROP_FRAME_WIDTH))
        self.height = (self.camera.get(cv2.CAP_PROP_FRAME_HEIGHT))
        self.fps = self.camera.get(cv2.CAP_PROP_FPS)
        self.fourcc = cv2.VideoWriter_fourcc(*'DIVX')
        self.writer = cv2.VideoWriter("video.avi", self.fourcc, self.fps, (int(self.width), int(self.height)))

        self.CNN_model=CNN_Model()
        self.LSTM_model=LSTM_MODEL()
        self.body_detector=Detector() # 불러오기


    #실질적인 메임 함수, 스트리밍하고 ai, 데이터 처리
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
                #스트리밍 처리 끝났고, ai 처리
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

    #스트리밍 처리 끝났고, ai 처리
    def work(self):
        det_ret=self.CNN_model.detect()
        #사람 검출 안 되면 그냥 넘어 감
        if det_ret.get("human") == 0:
            return
        # 사람이 있고 물건 개수 변함
        if self.update_num(dict=det_ret) == 1:
            #프레임 큐에 있는 프레임의 신체 좌표 추출
            data=[]
            for frame in self.frame_queue:
                #body detector의 리턴값 모양은 [사람][좌표데이터]
                data.append(self.body_detector.detectBody((frame)))
            #data는 [frame][person][좌표데이터]이다
            #추출한 좌표를 lstm에 입력하고 도난 판단
            if self.LSTM_model.predict(data)==1: # 도난임
                self.writeClip()
                #서버에 알리기
            #도난 아님
            else:
                return

        # 사람은 있는데 물건 개수는 안 바뀜
        else:
            #프레임 큐에 프레임만 저장하고 넘어 감
            self.frame_queue.append(self.frame)
            if len(self.frame_queue) > 30:
                self.frame_queue.pop()


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

    def writeClip(self):
        file_name = datetime.today().strftime("%Y%m%d%H%M%S") + ".avi"
        writer = cv2.VideoWriter(file_name, self.fourcc, self.fps, (int(self.width), int(self.height)))
        for frame in self.frame_queue:
            writer.write(frame)

if __name__=="__main__":
    cctv=CCTV()
    cctv.run()


















