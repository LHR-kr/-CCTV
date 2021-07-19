from detectBody import Detector
import csv
import glob
from darkflow.net.build import TFNet
import cv2

class maker:
    def __init__(self):
        self.dt=Detector()
        self.options = {"pbLoad": "./darkflow/built_graph/my-tiny-yolo.pb", "metaLoad": "./darkflow/built_graph/my-tiny-yolo.meta", "threshold": 0.1,
                     "labels" : "./darkflow/labels.txt"}
        self.tfnet = TFNet(self.options)
        self.num = {"total": 0, "chocopie": 0, "frenchpie": 0, "margaret": 0, "moncher": 0}


    def make(self, image):

        img=cv2.imread(image)
        data=self.dt.detectBody(img)
        with open(image[:-4]+'.csv', 'w', newline='') as f:
            writer = csv.writer(f)
            writer.writerow(data)


img_list=glob.glob("C:\\ProgramData\\Anaconda3\\envs\\DarkflowTest\\rnn_data\\*.jpg")
m=maker()

print(len(img_list))
for img in img_list:
    print(img+"start")
    m.make(img)
    print(img+"end")