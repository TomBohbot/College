import unittest
import state

class TestHeuristics(unittest.TestCase):

    def test_complete_board(n):
        board = [0,1,2,3,4,5,6,7,8]
        four_board = [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15]
        assert state.hdistance1(board) == 0
        assert state.hdistance1(four_board) == 0
        assert state.hdistance2(board) == 0

    def test_out_of_order(n):
        board = [1,2,3,4,5,6,7,8,0]
        assert state.hdistance1(board) == 9
        assert state.hdistance2(board) == 16

    def test_random_out_of_order(n):
        board = [1,5,0,3,8,6,4,7,2]
        assert state.hdistance1(board) == 7
        assert state.hdistance2(board) == 14

    def test_class_example(n):
        board = [7,2,4,5,0,6,8,3,1]
        assert state.hdistance1(board) == 8+1
        assert state.hdistance2(board) == 18+2

if __name__ == '__main__':
    unittest.main()