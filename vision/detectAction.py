import numpy as np
from tensorflow.keras.layers import Dense, LSTM, Dropout
from tensorflow.keras.models import Sequential,load_model

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
            model.add(LSTM(units=256, activation='tanh',input_shape=(18,1),return_sequences = True))
            model.add(LSTM(units=128,activation='tanh'))
            model.add(Dropout(0.3))
            model.add(Dense(units=64, activation='relu'))
            model.add(Dense(units=32, activation='relu'))
            # 나중에 softmax로 바꿀 수 있음
            model.add(Dense(units=1,activation='sigmoid'))

            # 일단 binary_crossentropy로 하고 나중에 감지해야 될 종류가 늘어나면 categorical_crossentropy로 바꾸자
            model.compile(loss='binary_crossentropy', optimizer='adam',metrics=['accuracy'])
            return model

    def save(self):
        self.model.save("my_LSTM_model.h5")
    def load(self):
        return load_model("my_LSTM_model.h5")
    def train(self, epoch):
        #csv 파일은 1*19이다. 0~17은 입력 좌표값, 18은 정답
        data=np.loadtxt("./training_data.csv",delimiter=',')
        X=data[:,0:18]
        Y=data[:,18]
        X = X.reshape((X.shape[0], X.shape[1], 1))

        self.model.fit(X,Y,epochs=epoch,batch_size=10)
        self.save()
    def predict(self,pre_data):
        #여기 바꿔보자
        #data는 [프레임][사람][좌표 데이터]
        cnt=0
        #일단 세사람
        for person in (0,1,2):
            for frame in pre_data:
                #[사람][데이터], detectbody의 리턴형과 같다.
                if len(frame)>person:
                    data=frame[person]
                else:
                    continue
                #여기가 문제
                data=np.reshape(data,(1,18,1))
                result=self.model.predict(data)
                print(result)
                if result > self.threshold:
                    cnt+=1
                    print(cnt)
        #도난으로 판단
        if cnt > 10:
            return 1
        #도난 아님
        else:
            return 0
    def pre(self,data):

        data=np.reshape(data,(1,18,1))
        print(data.shape)
        return self.model.predict(data)
# m=LSTM_MODEL()
# m.train(300)