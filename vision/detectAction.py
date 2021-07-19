import tensorflow.keras
import numpy as np
import tensorflow as tf
from tensorflow.keras.layers import Dense, LSTM, Dropout
from tensorflow.keras.models import Sequential,load_model

class LSTM_MODEL:
    def __init__(self):
        self.model=self.build()
    def build(self):
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
        self.model=load_model("my_LSTM_model.h5")
    def train(self, epoch):

        #csv 파일은 1*19이다. 0~17은 입력 좌표값, 18은 정답
        data=np.loadtxt("./training_data.csv",delimiter=',')
        X=data[:,0:18]
        Y=data[:,18]
        X = X.reshape((X.shape[0], X.shape[1], 1))

        self.model.fit(X,Y,epochs=epoch,batch_size=6)
        self.save()
    def predict(self,data):
        data=np.reshape(data,(1,18,1))
        return self.model.predict(data)

