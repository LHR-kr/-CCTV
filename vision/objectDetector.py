
import cv2
from darkflow.net.build import TFNet
import json

cap=cv2.VideoCapture(0)

width=cap.get(cv2.CAP_PROP_FRAME_WIDTH)
height=cap.get(cv2.CAP_PROP_FRAME_HEIGHT)
print(width, height)
fourcc = cv2.VideoWriter_fourcc(*'DIVX')
out=cv2.VideoWriter("output.avi",fourcc,30.0,(int(width),int(height)))


options = options = {"pbLoad": "./darkflow/built_graph/my-tiny-yolo.pb", "metaLoad": "./darkflow/built_graph/my-tiny-yolo.meta", "threshold": 0.1,
                     "labels" : "./darkflow/labels.txt"}

num={"total":0, "chocopie" : 0, "frenchpie":0, "margaret" : 0, "moncher" : 0}

tfnet = TFNet(options)


while cap.isOpened():
    temp_num={"total":0, "chocopie" : 0, "frenchpie":0, "margaret" : 0, "moncher" : 0}
    ret, image=cap.read()
    if not ret:
        break
    #딕셔너리가 담긴 리스트
    result = tfnet.return_predict(image)
    for dic in result:
        name = dic.get("label")
        if name == 'Chocopie':
            temp_num.update(chocopie=(temp_num.get('chocopie')+1))
        elif name == 'Frenchpie':
            temp_num.update(frenchpie=(temp_num.get('frenchpie')+1))
        elif name== 'Margaret':
            temp_num.update(margaret=(temp_num.get('margaret')+1))
        elif name=='Moncher':
            temp_num.update(moncher=(temp_num.get('moncher')+1))
    temp_num.update(total=sum(temp_num.values()))
    if(temp_num.get('total') != num.get('total')):
        num=temp_num.copy()
        json_num=json.dumps(num)
        print(json_num)
        # json num 서버로 보내기


cap.release()
out.release()