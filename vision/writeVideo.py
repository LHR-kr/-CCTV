import cv2
def run():
    cap = cv2.VideoCapture(0)

    width = (cap.get(cv2.CAP_PROP_FRAME_WIDTH))
    height = (cap.get(cv2.CAP_PROP_FRAME_HEIGHT))
    fps = cap.get(cv2.CAP_PROP_FPS)
    fourcc = cv2.VideoWriter_fourcc(*'DIVX')

    writer = cv2.VideoWriter("video.avi", fourcc, fps, (int(width), int(height)))

    if(cap.isOpened()==False or writer.isOpened()==False):
        print("cannot open camera")
        return
    while(cap.isOpened() and writer.isOpened()):
        ret, frame=cap.read()
        if not ret:
            break
        writer.write(frame)
    cap.release()
    writer.release()


run()




