import unittest
import KnnWithNoisyXor

# Knn Test Code
# @author Tom Bohbot

class TestKnn(unittest.TestCase):

    def testRecursiveFib(n):
        dataset = KnnWithNoisyXor.noisyXOR()
        assert(KnnWithNoisyXor.implementKnn(1,  1, 1, dataset ) == 1)
        assert(KnnWithNoisyXor.implementKnn(1,  100, 100, dataset) == 1)
        assert(KnnWithNoisyXor.implementKnn(1,  0.3, 0.81, dataset ) == 1)
        assert(KnnWithNoisyXor.implementKnn(3,  0.41, 0.3, dataset ) == 1)
        assert(KnnWithNoisyXor.implementKnn(5,  0.4, 0.2, dataset ) == 1)
        assert(KnnWithNoisyXor.implementKnn(10, 0.5, 0.5, dataset ) == 1)

    def testOneDoubleNegative(n):
        dataset = KnnWithNoisyXor.noisyXOR()
        assert(KnnWithNoisyXor.implementKnn(1,  -1, -1, dataset) == 1)
        assert(KnnWithNoisyXor.implementKnn(1,  -100, -100, dataset) == 1)
        assert(KnnWithNoisyXor.implementKnn(1,  -0.2, -0.4, dataset ) == 1)
        assert(KnnWithNoisyXor.implementKnn(3,  -0.31, -0.5, dataset ) == 1)
        assert(KnnWithNoisyXor.implementKnn(5,  -0.8, -0.512, dataset ) == 1)
        assert(KnnWithNoisyXor.implementKnn(10, -0.5, -0.5, dataset ) == 1)

    def testZeroNegX(n):
        dataset = KnnWithNoisyXor.noisyXOR()
        assert(KnnWithNoisyXor.implementKnn(1,  -1, 1, dataset) == 0)
        assert(KnnWithNoisyXor.implementKnn(1,  -100, 100, dataset) == 0)
        assert(KnnWithNoisyXor.implementKnn(1,  -0.613, 0.41, dataset ) == 0)
        assert(KnnWithNoisyXor.implementKnn(3,  -0.21, 0.51, dataset ) == 0)
        assert(KnnWithNoisyXor.implementKnn(5,  -0.31, 0.41, dataset ) == 0)
        assert(KnnWithNoisyXor.implementKnn(10, -0.5, 0.5, dataset ) == 0)

    def testZeroNegY(n):
        dataset = KnnWithNoisyXor.noisyXOR()
        assert(KnnWithNoisyXor.implementKnn(1,  1, -1, dataset) == 0)
        assert(KnnWithNoisyXor.implementKnn(1,  100, -100, dataset) == 0)
        assert(KnnWithNoisyXor.implementKnn(1,  0.31, -0.91, dataset ) == 0)
        assert(KnnWithNoisyXor.implementKnn(3,  0.41, -0.11, dataset ) == 0)
        assert(KnnWithNoisyXor.implementKnn(5,  0.51, -0.51, dataset ) == 0)
        assert(KnnWithNoisyXor.implementKnn(10, 0.5, -0.5, dataset ) == 0)

if __name__ == '__main__':
    unittest.main()