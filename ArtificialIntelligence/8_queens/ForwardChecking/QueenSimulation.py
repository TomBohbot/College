import numpy as np
import Gui
import MoveQueen
import BoardSize
import time

def class_slides():
    board = np.zeros((BoardSize.size(),BoardSize.size()))
    MoveQueen.drop_queen(x=0, y=0, board=board)
    MoveQueen.drop_queen(x=1, y=2, board=board)
    MoveQueen.backtrace(x=1, y=2, board=board)
    MoveQueen.drop_queen(x=1, y=3, board=board)
    MoveQueen.drop_queen(x=2, y=1, board=board)
    MoveQueen.backtrace(x=2, y=1, board=board)
    MoveQueen.backtrace(x=1, y=3, board=board)
    MoveQueen.backtrace(x=0, y=0, board=board)
    MoveQueen.drop_queen(x=0, y=1, board=board)
    MoveQueen.drop_queen(x=1, y=3, board=board)
    MoveQueen.drop_queen(x=2, y=0, board=board)
    MoveQueen.drop_queen(x=3, y=2, board=board)
    print(board)
    while True:
        Gui.display_board(board)

def main():
    num_moves = 0
    num_iterations = 0
    board = np.zeros((BoardSize.size(),BoardSize.size()))
    Gui.display_board(board)
    x = 0 # x is the column you are in. imagine x and y as a x-y plot.
    while x < BoardSize.size():
        # If the column is full then backtracing is necessary:
        if MoveQueen.next_available_row(board=board, x=x) == -1:   
            x -= 1         
            y = MoveQueen.find_y(board=board, x=x)
            MoveQueen.backtrace(x=x, y=y, board=board)
            Gui.display_board(board)
        else:
            y = MoveQueen.next_available_row(board=board, x=x)
            MoveQueen.drop_queen(x=x, y=y, board=board)
            Gui.display_board(board)
            x += 1
            num_moves += 1
            num_moves += 1
            num_iterations += 1
    Gui.create_message("SUCESS!")
    time.sleep(5)
    print(board)
    print(f"The average number of iterations in DFS are {num_iterations}.\nThe average number of moves in DFS are {num_moves}.")

main()