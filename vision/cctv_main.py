import makeWeb
import cv2
from multiprocessing import process, queues
from queue import Queue
from objectDetector import CNN_Model
from detectBody import Detector
import detectAction

class cctv:
    def __init__(self):

        self.frame

        #시작 전에 바꿔주기
        self.idar_num = {"total": 0, "chocopie": 0, "frenchpie": 0, "margaret": 0, "moncher": 0}
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

        self.update_num_queue=queues()
        self.detect_action_queue=queues()


    def run(self):
        while True:
            ret, self.frame=cv2.read()
            if not ret:
                break
            write=process(target=self.write_video, args=(self.frame,))
            update=process(target=self.update_num, args=(self.frame,self.update_num_queue,))

            write.start()
            update.start()

            write.join()
            update.join()

            self.real_num=self.update_num_queue.get()


    def write_video(self,frame):
        self.writer.write(frame)

    def update_num(self,frame,q):
        num=self.CNN_model.detect(frame)
        q.put(num)
    def detect_action(self,frame,q):
        result=self.body_detector.detectBody(frame)
        result.append(result,)












































if __name__=='__main__':
    frameQueue=queues()
    writeVideoProcess=process(target=writeVideo.run,args=(frameQueue))
    makeWebProcess=process(target=makeWeb.run,args=(frameQueue))
    objectDetectionProcess=process(target=cliHandler,args=)

    writeVideoProcess.start()
    makeWebProcess.start()
    objectDetectionProcess.start()

    writeVideoProcess.join()
    makeWebProcess.join()
    objectDetectionProcess.join()
