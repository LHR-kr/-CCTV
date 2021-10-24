import cv2
from ai import ai_model
from flask import Response
from flask import Flask
from flask import render_template
from flask import request

import requests


#cctv 화면 절반 라인 조금 넘게 매대가 오도록 화면 앵글 설정




app = Flask(__name__)



@app.route('/')
def index():
    #rendering webpage
    url = requests.get("https://api.ipify.org").text+":8080"
    data = {"cctvID": "aaaa", "cctvUrl": url}
    # res = requests.post('http://3.233.241.48:8080/cctv/save', data=json.dumps(data), headers=self.headers)
    # print(res)
    #
    # res = requests.post('http://3.233.241.48:8080/stock/aaaa', data=json.dumps(self.stock), headers=self.headers)
    # print(res)

    return render_template('streamingWeb.html')

def gen(model):
    while True:
        frame = model.work()  # ai처리하고  웹캠이 읽은 프레임 리턴한다.

        if frame is None:
            break

        yield (b'--frame\r\n'
                   b'Content-Type: image/jpeg\r\n\r\n' + frame + b'\r\n\r\n')
        #스트리밍 처리 끝났고, ai 처리

    model.release()
    print("end")



@app.route('/video_feed')
def video_feed():
    return Response(gen(ai_model()),
                    mimetype='multipart/x-mixed-replace; boundary=frame')

    # defining server ip address and port
    app.run(host='0.0.0.0', port='8080', debug=False)




if __name__=="__main__":
    app.run(host='0.0.0.0', port='8080', debug=False)

















