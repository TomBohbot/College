# KNN Model
# @author Tom Bohbot
import matplotlib.pyplot as plt
import numpy as np

def noisyXOR():
    dataset = []
    for i in range(200):
        x = np.random.uniform(-1, 1)
        y = np.random.uniform(-1, 1)
        if (x > 0 and y > 0) or x < 0 and y < 0:
            output = 1
        else:
            output = 0
        dataset.append([x,y, output ] )
    graphData(dataset)
    return dataset     

def implementKnn(k, x, y, dataset):
    closestPoints = []
    for i in range(len(dataset)):
        closestPoints.append([distance(x,y,dataset[i][0],dataset[i][1]), dataset[i][2]])
    sortedDistances = sorted(closestPoints, key=lambda x: x[0])
    zeros = 0
    ones = 0
    for i in range(k):
        if sortedDistances[i][1] == 1:
            ones += 1
        else:
            zeros += 1
    if (ones > zeros):
        return 1
    else:
        return 0

def distance(x1, y1, x2, y2):
    return np.sqrt( (x1-x2)**2 + (y1-y2)**2)

def graphData(dataset):
    fig = plt.figure(figsize=(6,6) )
    for i in range(len(dataset)):
      if dataset[i][2] == 0:
        plt.plot(dataset[i][0],
                dataset[i][1],
                'o',
                markersize = 10,
                alpha = 0.75,
                color = 'blue'
                )
      elif dataset [i][2] == 1:
        plt.plot(dataset[i][0],
        dataset[i][1],
        '<',
        markersize = 10,
        alpha = 0.75,
        color = 'red'
        )
    plt.xlabel(r'x_0')
    plt.ylabel(r'x_1')
    plt.show()
            
def main():
    # Driver:
    result = implementKnn(5, -0.4, 0.5, noisyXOR() )
    print("RESULT", result)

main()