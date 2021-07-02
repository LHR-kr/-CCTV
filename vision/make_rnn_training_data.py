from detectBody import Detector
import csv
import glob
from darkflow.net.build import TFNet
import cv2
import numpy as np

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

        temp_num = {"total": 0, "chocopie": 0, "frenchpie": 0, "margaret": 0, "moncher": 0}
        result = self.tfnet.return_predict(img)
        for dic in result:
            name = dic.get("label")
            if name == 'Chocopie':
                temp_num.update(chocopie=(temp_num.get('chocopie') + 1))
            elif name == 'Frenchpie':
                temp_num.update(frenchpie=(temp_num.get('frenchpie') + 1))
            elif name == 'Margaret':
                temp_num.update(margaret=(temp_num.get('margaret') + 1))
            elif name == 'Moncher':
                temp_num.update(moncher=(temp_num.get('moncher') + 1))
        temp_num.update(total=sum(temp_num.values()))
        data=data.tolist()
        if (temp_num.get('total') != self.num.get('total')):
            self.num = temp_num.copy()
            data.append(1)
        else:
            data.append(0)


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