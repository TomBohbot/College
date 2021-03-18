
import numpy as np

def recursiveFib(n):
    if n == 0:
        return 0
    elif n == 1:
        return 1
    else:
        return recursiveFib(n-2) + recursiveFib(n-1)

def iterativeFib(n):
    if n == 0:
        return 0
    elif n == 1:
        return 1
    else:
        fibSeq = [0,1]
        for i in range(2,n+1):
            temp = fibSeq[1]
            fibSeq[1] += fibSeq[0]
            fibSeq[0] = temp
        return fibSeq[1]

def matricesFib(n):
    if n == 0:
        return 0
    A = np.array([ [1,1], [1,0] ])
    currFib = np.array([0,1])
    for i in range(n):
        currFib = np.dot(A,currFib)
    return currFib[0]

def main():
    # Driver:
    success = True
    for i in range(21):
        if recursiveFib(i) == iterativeFib(i) and recursiveFib(i) == matricesFib(i):
            continue
        else:
            print("ERROR!")
            success = False
            break
    if success == True:
    	print("Success")

main()