import numpy as np
from sklearn.datasets import load_iris
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import OneHotEncoder
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense
from tensorflow.keras.optimizers import Adam
import pandas as pd
import matplotlib.pyplot as plt
import numpy as np
import Parse_Csv as parser

def exploraty_data_analysis(df):
    print(df.head(10))
    df.plot(x ='Sepal Length (CM)', y='Sepal Width (CM)', c='Species', kind = 'scatter', colormap='jet', title='Comparing Sepal Length to Sepal Width \n Setosa(Blue) Vesicolor(Green) Virginica(Red) \n We can see that comparing these two features will yield better than average results in classifying setosas vs. others as they are nearly linearly seperable.')
    df.plot(x ='Petal Length (CM)', y='Petal Width (CM)', c='Species', kind = 'scatter', colormap='jet', title='Comparing Petal Length to Petal Width \n Setosa(Blue) Vesicolor(Green) Virginica(Red) \n We can see that comparing these two features will yield strong results in classifying setosas vs. others as they are linearly seperable.')
    plt.show()
    return 1

def covert_type_to_int(iris_type):
    if iris_type == 'setosa':
        return 0
    if iris_type == 'versicolor':
        return 1
    if iris_type == 'virginica':
        return 2


# Read data set and set into a pandas: dataframe:
df = parser.parse_data('data/Iris.csv')
df['Species'] = df['Species'].apply(covert_type_to_int)

# Perform EDA:
exploraty_data_analysis(df)

# take the two attributes show setosa are linearly seperable:
x = df[['Petal Length (CM)', 'Petal Width (CM)']].to_numpy().astype(np.float)

# One Hot encode the class labels
encoder = OneHotEncoder(sparse=False)
y_ = df['Species'].to_numpy().reshape(-1, 1) == 0
y = encoder.fit_transform(y_)

# Split the data for training and testing
train_x, test_x, train_y, test_y = train_test_split(x, y, test_size=0.20)

# Build the model
model = Sequential()
model.add(Dense(10, input_shape=(5,2), activation='relu', name='fc1'))
model.add(Dense(10, activation='relu', name='fc2'))
model.add(Dense(2, activation='softmax', name='output'))

# Adam optimizer with learning rate of 0.001
optimizer = Adam(lr=0.001)
model.compile(optimizer, loss='categorical_crossentropy', metrics=['accuracy'])
print('Neural Network Model Summary: ')
print(model.summary())

# Train the model
model.fit(train_x, train_y, verbose=2, batch_size=5, epochs=25)

# Test on unseen data
results = model.evaluate(test_x, test_y)
print('Final test set loss: {:4f}'.format(results[0]))
print('Final test set accuracy: {:4f}'.format(results[1]))