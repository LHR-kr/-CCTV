import cv2
from datetime import datetime
from queue import Queue
def run():
    cap = cv2.VideoCapture(0)
    q=Queue()

    width = cap.get(cv2.CAP_PROP_FRAME_WIDTH)
    height = cap.get(cv2.CAP_PROP_FRAME_HEIGHT)
    fps = cap.get(cv2.CAP_PROP_FPS)
    fourcc = cv2.VideoWriter_fourcc(*'DIVX')
    file_name = datetime.today().strftime("%Y%m%d%H%M%S")+".avi"

    print(file_name)
    writer = cv2.VideoWriter(file_name, fourcc, fps, (int(width), int(height)))

    cnt=0
    if (cap.isOpened() == False ):
        print("cannot open camera")
        return
    while (cnt<60):
        cnt+=1
        ret, frame = cap.read()
        if not ret:
            break
        q.put(frame)

    for i in range(60):
        frame=q.get()
        if frame is not None:
            writer.write(frame)
    cap.release()
    writer.release()


run()
