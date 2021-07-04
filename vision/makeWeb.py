from flask import Response
from flask import Flask
from flask import render_template
import cv2

def run():
    camera = cv2.VideoCapture(0)

    app=Flask(__name__)


    def gen():
        while True:
            success, frame = camera.read()  # read the camera frame
            if not success:
                break
            else:
                ret, buffer = cv2.imencode('.jpg', frame)
                frame = buffer.tobytes()
                yield (b'--frame\r\n'
                        b'Content-Type: image/jpeg\r\n\r\n' + frame + b'\r\n\r\n')

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

if __name__=="__main__":
    run()

