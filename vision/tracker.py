import math

class tracker:
    def __init__(self):
        # 아이디를 키로, 중심좌표를 value로 가지는 딕셔너리
        #이전 프레임의 물체 위치
        self.center_points = {}

        self.id_count = 0
    #입력은 [사람 수][4]인데 리턴은 [사람수][5]
    #인자는 물체가 있는  사각형 영역이 담긴 이차원 배열, 박스가 저장된 배열만 집어 넣으면, 아이디랑 박스좌표 담긴 배열 반환
    def update(self, objects_rect):
        # 아이디와 중심좌표 임시로 저장하는 이차원 배열
        objects_bbs_ids = []

        # 새로 입력된 각 사각형마다
        for rect in objects_rect:
            x, y, w, h = rect
            cx = (x + x + w) // 2
            cy = (y + y + h) // 2

            # Find out if that object was detected already
            same_object_detected = False
            #이전 프레임에서 찾은 박스들의 좌표와 아이디와 비교
            #새 프레임에 있지만 이전 프레임에 없는 경우 : 새로운 아이디로 등록
            #새 프레임에 있고 이전 프레임에도 있는 경우 : 좌표 최신화, 이전 프레임 정보에서 pop
            #새 프레임에서 없고 이전 프레임에 있는 경우 : 이전 프레임 정보에 남김
            items=self.center_points.items()
            for id, pt in items:
                #두 좌표 사이의 거리 계산
                #만약 업데이트 하면 이걸 변경
                dist = math.hypot(cx - pt[0], cy - pt[1])
                #거리가 일정 값 미만이면 같은 물체로 취급하여 아이디, 중심좌표 저장하고 이 물체에 대한 트랙킹 종료
                if dist < 25:
                    self.center_points.pop(id)
                    #배열에 임시 저장
                    objects_bbs_ids.append([x, y, w, h, id])
                    same_object_detected = True
                    break

            # 만약 이전 프레임에서 찾지 못했다면 새롭게 아이디 등록
            if same_object_detected is False:
                #배열에 임시 저장
                objects_bbs_ids.append([x, y, w, h, self.id_count])
                self.id_count += 1

        # Clean the dictionary by center points to remove IDS not used anymore
        #새 아이디와 그 물체의 중심좌표 저장하는 딕셔너리
        new_center_points = {}
        #업데이트
        for obj_bb_id in objects_bbs_ids:
            x, y, w, h, object_id = obj_bb_id
            center = ((x + x + w) // 2,(y + y + h) // 2)
            new_center_points[object_id] = center
            
        #여기만 변경
        # Update dictionary with IDs not used removed
        cant_detected=self.center_points.copy()
        self.center_points = new_center_points.copy()
        #이차원 배열, 딕셔너리 리턴
        return objects_bbs_ids, cant_detected
