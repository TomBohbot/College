import random
import numpy as np
import time
import sys

def init_board():
    """
    This function initialized the matrix which 
    represents the connect 4 board.

    Input: None
    Output: Empty two dimensional array
    """
    board = np.zeros((6,7))
    return board

def valid_location(board, col):
    """
    This function ensures the user selected a column that 
    is neither full nor out of bounds.

    Input: 2d array board, current column
    Output: Boolean representing if the location is valid.
    """
    if col < 0 or col > 6:
        return False
    if board[5][col] != 0:
        return False
    return True

def open_spot_in_col(board, col):
    """
    This function ensures a row is available in the column and
    specifies the first row that is available.

    Input: 2d array board, current column
    Output: The available row or -1 if not available.
    """
    for r in range(6):
        if board[r][col] == 0:
            return r
    return -1

def full_board(board):
    for i in range(6):
        if valid_location(board, i) == True:
            return False
    return True

def first_valid_column(board):
    """
    This function returns the first available column in the board.

    Input: 2d array board
    Output: The first available column or an error message and exits
            game if there is no more room.
    """
    for i in range(7):
        if valid_location(board, i) == True:
            return  i            
    print('ERROR: NO EMPTY SLOTS LEFT!')
    return sys.exit()

def human_next_move(board, player):
    """
    This function allows a human player to place the next token in 
    the board.

    Input: 2d array board, current player
    Output: Coordinates of placed token
    """
    col = int(input(f"Which column would Player {player} like to place your token? ")) - 1 # zero index the input
    while valid_location(board, col) == False:
        col = int(input("invalid location, please try again.")) - 1
    row = open_spot_in_col(board, col)
    board[row,col] = player
    return (row, col)

def random_next_move(board, player):
    """
    This function randomly places the next token in 
    the board.

    Input: 2d array board, current player
    Output: Coordinates of placed token
    """
    col = random.randint(0,6)
    while valid_location(board, col) == False:
        col = random.randint(0,6)
    row = open_spot_in_col(board, col)
    board[row,col] = player
    # time.sleep(1)
    return (row, col)