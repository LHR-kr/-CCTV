from darkflow.net.build import TFNet
import json

class CNN_Model:
    def __init__(self):
        self.options = options = {"pbLoad": "../../darkflow/built_graph/my-tiny-yolo.pb", "metaLoad": "../../darkflow/built_graph/my-tiny-yolo.meta", "threshold": 0.3,
                     "labels" : "./darkflow/labels.txt"}
        self.tfnet=TFNet(options)

    def detect(self, image):
        temp_num = {"total": 0, "Chocopie": 0, "Frenchpie": 0, "Margaret": 0, "Moncher": 0}
        result = self.tfnet.return_predict(image)

        for dic in result:
            name = dic.get("label")
            if name == 'Chocopie':
                temp_num.update(Chocopie=(temp_num.get('Chocopie') + 1))
            elif name == 'Frenchpie':
                temp_num.update(Frenchpie=(temp_num.get('Frenchpie') + 1))
            elif name == 'Margaret':
                temp_num.update(Margaret=(temp_num.get('Margaret') + 1))
            elif name == 'Moncher':
                temp_num.update(Moncher=(temp_num.get('Moncher') + 1))


        temp_num.update(total=sum(temp_num.values()))
        return temp_num


