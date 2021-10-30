from darkflow.net.build import TFNet

class CNN_Model:
    def __init__(self):
        #여기 나중에 수정
        self.options = {"pbLoad": "./my-tiny-yolo.pb", "metaLoad": "./my-tiny-yolo.meta", "threshold": 0.3,
                     "labels" : "./darkflow/labels.txt"}
        self.tfnet=TFNet(self.options)

    def detect(self, image):
        stock = {"total": 0, "chocopie": 0, "frenchpie": 0, "margaret": 0, "moncher": 0}
        result = self.tfnet.return_predict(image)

        person_num=0
        person_bounding_boxes=[]

        for dic in result:
            name = dic.get("label")
            if name == 'Chocopie':
                stock['chocopie']=stock['chocopie']+1

            elif name == 'Frenchpie':
                stock['frenchpie']=stock['frenchpie']+1

            elif name == 'Margaret':
                stock['margaret']=stock['margaret']+1

            elif name == 'Moncher':
                stock['moncher']=stock['moncher']+1

            if name == "person":
                person_num+=1
                x=dic.get("topleft").get("x")
                y=dic.get("topleft").get("y")
                w=dic.get("bottomright").get("x")-x
                h=dic.get("bottomright").get("y")-y
                person_bounding_boxes.append([x,y,w,h])

        stock.update(total=sum(stock.values()))
        return stock, person_num, person_bounding_boxes #만약 demo 실험하면 result 적기 


