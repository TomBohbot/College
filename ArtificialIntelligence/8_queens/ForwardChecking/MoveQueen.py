import numpy as np
import BoardSize
import Gui

def drop_queen(x, y, board):
    board[y,x] = 1
    add_restricted_spots(x=x, y=y, board=board)

def spot_is_available(x, y, board):
    if board[y,x] == 0:
        return True
    else:
        return False

def add_restricted_spots(x, y, board, back_trace=False):
    val = 1
    if back_trace == True:
        val = 0
    # restrict options to the right of me:
    for i in range(x, BoardSize.size()):
        board[y,i] = val*(val+1) if board[y,i] != 1 else val
    # restrict options to the positive diagonal:
    pd_x = x
    pd_y = y
    while pd_x < BoardSize.size() and pd_y < BoardSize.size():
        board[pd_y,pd_x] = val*(val+1) if board[pd_y,pd_x] != 1 else val
        pd_x += 1
        pd_y += 1
    # restrict options to the negative diagonal to the right of my position:
    nd_x = x
    nd_y = y
    while nd_x < BoardSize.size() and nd_y >= 0:
        board[nd_y,nd_x] = val*(val+1) if board[nd_y,nd_x] != 1 else val
        nd_x += 1
        nd_y -= 1

def backtrace(x, y, board):
    # remove any restricted options touching queen to be removed:
    add_restricted_spots(x=x, y=y, board=board, back_trace=True)
    # get other queens and make sure all there restrictions are still correct:
    for i in range(x+1):
        for j in range(BoardSize.size() ):
            if board[j,i] == 1:
                add_restricted_spots(x=i, y=j, board=board, back_trace=False)
    board[y,x] = 2

def next_available_row(board, x):
    for y in range(BoardSize.size() ):
        if board[y,x] == 0:
            return y
    return -1

def find_y(board, x):
    for y in range(BoardSize.size() ):
        if board[y,x] == 1:
            return y
    return -1

