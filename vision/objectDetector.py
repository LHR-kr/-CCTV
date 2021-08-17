from darkflow.net.build import TFNet
import json

class CNN_Model:
    def __init__(self):
        #여기 나중에 수정
        self.options = options = {"pbLoad": "../../darkflow/built_graph/my-tiny-yolo.pb", "metaLoad": "../../darkflow/built_graph/my-tiny-yolo.meta", "threshold": 0.3,
                     "labels" : "./darkflow/labels.txt"}
        self.tfnet=TFNet(options)

    def detect(self, image):
        stock = {"total": 0, "Chocopie": 0, "Frenchpie": 0, "Margaret": 0, "Moncher": 0}
        result = self.tfnet.return_predict(image)

        person_num=0
        person_bounding_boxes=[]

        for dic in result:
            name = dic.get("label")
            if name == 'Chocopie':
                stock.update(Chocopie=(stock.get('Chocopie') + 1))
            elif name == 'Frenchpie':
                stock.update(Frenchpie=(stock.get('Frenchpie') + 1))
            elif name == 'Margaret':
                stock.update(Margaret=(stock.get('Margaret') + 1))
            elif name == 'Moncher':
                stock.update(Moncher=(stock.get('Moncher') + 1))
            if name == "person":
                person_num+=1
                x=dic.get("topleft").get("x")
                y=dic.get("topleft").get("y")
                w=dic.get("bottomright").get("x")-x
                h=dic.get("bottomright").get("y")-y
                person_bounding_boxes.append([x,y,w,h])

        stock.update(total=sum(stock.values()))
        return stock, person_num, person_bounding_boxes


