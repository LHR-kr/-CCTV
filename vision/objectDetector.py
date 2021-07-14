from darkflow.net.build import TFNet
import json

class CNN_Model:
    def __init__(self):
        self.options = options = {"pbLoad": "../../darkflow/built_graph/my-tiny-yolo.pb", "metaLoad": "../../darkflow/built_graph/my-tiny-yolo.meta", "threshold": 0.1,
                     "labels" : "./darkflow/labels.txt"}
        self.tfnet=TFNet(options)

    def detect(self, image):
        temp_num = {"total": 0, "chocopie": 0, "frenchpie": 0, "margaret": 0, "moncher": 0, "human": 0}
        result = self.tfnet.return_predict(image)

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
            elif name == 'Human':
                temp_num.update(human=(temp_num.get('human') + 1))

        temp_num.update(total=sum(temp_num.values()))
        return temp_num



        if (temp_num.get('total') != self.num.get('total')):
            self.num = temp_num.copy()
            self.json_num = json.dumps(self.num)
            # json num 서버로 보내기???



