import unittest
import Fibbanaci

# Test Fibbanaci.py
# @author Tom Bohbot

class FibbanaciTest(unittest.TestCase):

    def testRecursiveFib(n):
        assert(Fibbanaci.recursiveFib(0) == 0)
        assert(Fibbanaci.recursiveFib(1) == 1)
        assert(Fibbanaci.recursiveFib(2) == 1)
        assert(Fibbanaci.recursiveFib(3) == 2)
        assert(Fibbanaci.recursiveFib(4) == 3)
        assert(Fibbanaci.recursiveFib(5) == 5)
        assert(Fibbanaci.recursiveFib(10) == 55)
        assert(Fibbanaci.recursiveFib(15) == 610)
        assert(Fibbanaci.recursiveFib(20) == 6765)

    def testImperativeFib(n):
        assert(Fibbanaci.iterativeFib(0) == 0)
        assert(Fibbanaci.iterativeFib(1) == 1)
        assert(Fibbanaci.iterativeFib(2) == 1)
        assert(Fibbanaci.iterativeFib(3) == 2)
        assert(Fibbanaci.iterativeFib(4) == 3)
        assert(Fibbanaci.iterativeFib(5) == 5)
        assert(Fibbanaci.iterativeFib(10) == 55)
        assert(Fibbanaci.iterativeFib(15) == 610)
        assert(Fibbanaci.iterativeFib(20) == 6765)

    def testMatricesFib(n):
        assert(Fibbanaci.matricesFib(0) == 0)
        assert(Fibbanaci.matricesFib(1) == 1)
        assert(Fibbanaci.matricesFib(2) == 1)
        assert(Fibbanaci.matricesFib(3) == 2)
        assert(Fibbanaci.matricesFib(4) == 3)
        assert(Fibbanaci.matricesFib(5) == 5)
        assert(Fibbanaci.matricesFib(10) == 55)
        assert(Fibbanaci.matricesFib(15) == 610)
        assert(Fibbanaci.matricesFib(20) == 6765)

if __name__ == '__main__':
    unittest.main()