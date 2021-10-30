import numpy as np
from tensorflow.keras.layers import Dense, LSTM, TimeDistributed
from tensorflow.keras.models import Sequential,load_model
from tensorflow.keras.utils import to_categorical
from tensorflow.keras.optimizers import Adam

from math import hypot

class LSTM_MODEL:
    def __init__(self):
        self.model=self.build()
        self.threshold=0.13
    def build(self):
        try:
            return load_model("my_LSTM_model.h5")
        except:
            print("load failed")
            model=Sequential()
            model.add(TimeDistributed(Dense(units=22, activation='relu'),input_shape=(40,22)))
            model.add(LSTM(units=256, activation='tanh',return_sequences = False))
            model.add(Dense(units=64, activation='relu'))
            model.add(Dense(units=32, activation='relu'))
            # 나중에 softmax로 바꿀 수 있음, units 개수도 바꾸고
            # sigmoid? softmax??? 무조건 softmax가 아닌가? 일단 sigmoid로 해보자
            model.add(Dense(units=3,activation='softmax'))
            adam=Adam(learning_rate=0.00001)
            # 일단 binary_crossentropy로 하고 나중에 감지해야 될 종류가 늘어나면 categorical_crossentropy로 바꾸자
            model.compile(loss='categorical_crossentropy', optimizer=adam,metrics=['accuracy'])
            return model

    def save(self):
        self.model.save("my_LSTM_model.h5")
    def load(self):
        return load_model("my_LSTM_model.h5")
    def train(self, epoch):
        #csv 파일은 1*23이다. 0~21은 입력 좌표값, 22은 정답
        data=np.loadtxt("./training_data.csv",delimiter=',')
        #X는 [길이][18]
        #X, Y의 1차원 길이는 임의로 개수 맞춰서
        X=data[:5960,0:22]
        X=np.array(X)
        Y=[]
        for i in range(0,5960,40):
            temp_Y=data[i:i+40,22]
            cnt=[0,0,0]
            for y in temp_Y:
                if y==0:
                    cnt[0]+=1
                if y==1:
                    cnt[1]+=1
                if y==2:
                    cnt[2]+=1
            if cnt[1]>20:
                Y.append(1)
            elif cnt[2]>20:
                Y.append(2)
            else:
                Y.append(0)

        X = X.reshape((-1,40,22))
        Y = to_categorical(Y, num_classes=3)
        print(X)
        print(Y)
        self.model.fit(X,Y,epochs=epoch,batch_size=3)
        self.save()
    def predict(self,data,sorted_center_points):
        #data는 [사람 수 <= 3 ][프레임 40 ][데이터 22]
        #일단 최대 세사람
        ret=[0,0,0]
        n=len(sorted_center_points)
        for i in range (3):
            personal_data=np.reshape(data[i],(1,40,22))
            result=self.model.predict(personal_data)
            print(result)
            if result[0][1]>0.08 or result[0][2]>0.08:
                if result[0][1]>result[0][2]:
                    ret[i]=1
                else:
                    ret[i]=2
        print(ret)
        ret2={}
        for id in sorted_center_points:
            distant=99999
            j=100
            for i in range(3):
                dx=data[i][39][0]-sorted_center_points[id][0]*1920/640
                dy=data[i][39][1]-sorted_center_points[id][1]*1080/480
                temp=hypot(dx,dy)
                print(temp)
                if(temp<distant):
                    distant=temp
                    j=i
                    print(j)
            ret2[id]=ret[j]
        print(ret2)
        return ret2


    def pre(self,data):
        print("predict start")
        data=np.reshape(data,(1,40,22))
        return self.model.predict(data)
# m=LSTM_MODEL()
# m.train(200)
