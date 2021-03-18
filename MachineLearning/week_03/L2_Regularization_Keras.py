# -*- coding: utf-8 -*-
"""L2_Regularization_Keras.ipynb

Automatically generated by Colaboratory.

Original file is located at
    https://colab.research.google.com/drive/1TZwVsVqfNLlF9J94R7BE6264rC159Uzy
"""

import numpy as np
from sklearn.datasets import load_iris
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import OneHotEncoder
from keras.models import Sequential
from keras.layers import Dense
from keras.optimizers import Adam
from keras.regularizers import l2

# load the iris dataset
iris_data = load_iris()

# Extract two characteristics of data set and target values:
x = iris_data.data[:, :2]
y_= iris_data.target.reshape(-1, 1) == 0 # Convert target data to a single column #== 0 makes it a setosa as 1 and other flower as 0.

# One Hot encode the class labels
encoder = OneHotEncoder(sparse=False)
y = encoder.fit_transform(y_)

# Split the data for training and testing
train_x, test_x, train_y, test_y = train_test_split(x, y, test_size=0.20)

# Build the model. L2 Regularization rakes place in 3 lines adding data to mode1:
model = Sequential()
model.add(Dense(10, input_shape=(5,2), activation='relu', name='fc1', bias_regularizer=l2(0.01)))
model.add(Dense(10, activation='relu', name='fc2', bias_regularizer=l2(0.01)))
model.add(Dense(2, activation='softmax', name='output', bias_regularizer=l2(0.01)))

# Adam optimizer with learning rate of 0.001
optimizer = Adam(lr=0.001)
model.compile(optimizer, loss='categorical_crossentropy', metrics=['accuracy'])

# Train the model
model.fit(train_x, train_y, verbose=2, batch_size=5, epochs=200)

# Test on unseen data
results = model.evaluate(test_x, test_y)
print('Final test set loss: {:4f}'.format(results[0]))
print('Final test set accuracy: {:4f}'.format(results[1]))