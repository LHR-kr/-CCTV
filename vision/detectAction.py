import tensorflow.keras
from numpy import numarray
import tensorflow as tf
from tensorflow.keras.layers import Dense, LSTM, Input,Dropout
from tensorflow.keras.models import Sequential,load_model

def build():
    model=Sequential()
    model.add(Dropout(0.3))
    model.add(LSTM(units=256, activation='tanh'))
    model.add(LSTM(units=128,activation='tanh'))
    model.add(Dropout(0.3))
    model.add(Dense(units=64, activation='relu'))
    model.add(Dense(units=1,activation='sigmoid'))

    # 일단 binary_crossentropy로 하고 나중에 감지해야 될 종류가 늘어나면 categorical_crossentropy로 바꾸자
    model.compile(loss='binary_crossentropy', optimizer='adam',metrics=['accuracy'])
    return model

def save(model):
    model.save("my_model")
def load():
    return load_model("my_model")

