import cv2
import tensorflow as tf
from objectDetector import CNN_Model
from detectBody import Detector
from detectAction import LSTM_MODEL

from datetime import datetime

from flask import Response
from flask import Flask
from flask import render_template

from tensorflow.python.keras.backend import set_session


#cctv 화면 절반 라인 조금 넘게 매대가 오도록 화면 앵글 설정
class CCTV:
    def __init__(self):

        self.frame=None
        #시작 전에 개수 초기화 해야 함
        self.ideal_num = {"total": 0, "Chocopie": 0, "Frenchpie": 0, "Margaret": 0, "Moncher": 0}
        self.real_num = {"total": 5, "Chocopie": 1, "Frenchpie": 2, "Margaret": 0, "Moncher": 2}
        self.frame_queue = []

        #임의로 테스트용으로 함, 나중에 변경
        self.camera = cv2.VideoCapture("./video/kakaoTalk_20210709_155435322.mp4")
        # 이미지 해상도 변경
        self.camera.set(3, int(1920))
        self.camera.set(4, int(1080))

        self.width = (self.camera.get(cv2.CAP_PROP_FRAME_WIDTH))
        self.height = (self.camera.get(cv2.CAP_PROP_FRAME_HEIGHT))
        self.fps = self.camera.get(cv2.CAP_PROP_FPS)
        self.fourcc = cv2.VideoWriter_fourcc(*'DIVX')
        self.writer = cv2.VideoWriter("video.avi", self.fourcc, self.fps, (int(self.width), int(self.height)))

        self.CNN_model=CNN_Model()
        self.body_detector=Detector() # 불러오기

        global graph
        global sess
        sess = tf.Session()
        graph = tf.get_default_graph()
        set_session(sess)
        self.LSTM_model=LSTM_MODEL()
        
        #이거는 실험용, 나중에 삭제
        self.cnt=0



    #실질적인 메임 함수, 스트리밍하고 ai, 데이터 처리
    def run(self):
        app = Flask(__name__)
        graph = tf.get_default_graph()


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
                with graph.as_default():
                    set_session(sess)
                    self.work()
            print("end")

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
        self.cnt+=1
        obj_det_ret=self.CNN_model.detect(self.frame)
        self.frame_queue.append(self.frame)
        if len(self.frame_queue) > 60:
            self.frame_queue.pop(0)

        # 사람이 있고 물건 개수 변함
        # 개수 달라졌는지 체크하고 달라졌으면 업데이트 후 1반환, 아니면 0 반환
        if self.update_num(dict=obj_det_ret) == 1: #self.cnt==190:
            #임시로 만든 거
            #프레임 큐에 있는 프레임의 신체 좌표 추출
            joint_data=[]
            #끝에 20프레임만 입력
            for frame in self.frame_queue[-20:]:
                #body detector의 리턴값 모양은 [사람][좌표데이터]
                    joint_data.append(self.body_detector.detectBody(frame))
            #data는 [frame][person][좌표데이터]이다
            #추출한 좌표를 lstm에 입력하고 도난 판단
            if self.LSTM_model.predict(joint_data)==1: # 도난임
                print("*************clip****************")
                self.writeClip()
                #서버에 알리기
            #도난 아님
            else:
                return



    #개수 달라졌느지 체크하고 달라졌으면 업데이트 후 1반환, 아니면 0 반환
    #인식률 낮은게 문제
    def update_num(self,dict):
        #개수 변환
        num=list(zip(dict.values(),self.real_num.values()))
        for index in (1,2,3,4):
            if num[index][0] < num[index][1]:
                #업데이트가 안 됨
                print(num)
                print(num[index][0])
                print(num[index][1])
                self.real_num=dict.copy()
                return 1
        #임의로 1 반환하게 만들고, 원래는 0 반환해야 한다.
        return 0

    #클립 작성 이상 없음
    def writeClip(self):
        file_name = datetime.today().strftime("%Y%m%d%H%M%S") + ".avi"
        writer = cv2.VideoWriter(file_name, self.fourcc, self.fps, (int(self.width), int(self.height)))
        for frame in self.frame_queue:
            writer.write(frame)
        writer.release()

if __name__=="__main__":
    cctv=CCTV()
    cctv.run()

















