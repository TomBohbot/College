import numpy as np
import random
import Constants

def place_n_queens():
    """
    Randomly place n queens on a nxn chess board.
    Returns a 2d array representing the chess board with n random queens placed. and an array representing queen positions
    """
    size = Constants.size()
    board = np.zeros((size, size))
    positions = random.sample(range(0, size), size)
    queen_positions = []
    for i in range(len(positions)):
        board[i,positions[i]] = 1
        queen_positions.append([i, positions[i]])
    return board, queen_positions

def verify_n_queens(board):
    """
    Checks the board with the randomly placed n queens.
    Returns True if no queens are connected, False otherwise
    """
    size = Constants.size()
    # Check if there are two queens on same row:
    for i in range(size):
        queen_count = 0
        for j in range(size):
            if board[i][j] == 1:
                queen_count += 1
        if queen_count > 1:
            return False

    # Check if two queens are on same column:
    for i in range(size):
        queen_count = 0
        for j in range(size):
            if board[j][i] == 1:
                queen_count += 1
        if queen_count > 1:
            return False

    # R DIAG:
    for i in range(size*2):
        queen_count = 0
        for j in range(i+1):
            elem = i-j
            if elem < size and j < size:
                if board[elem][j] == 1:
                    queen_count += 1
                    if queen_count > 1:
                        return False

    # L DIAGS
    # transpose the array and then check r diags since its rotated now:
    rotated_board = np.rot90(board)
    for i in range(size*2):
        queen_count = 0
        for j in range(i+1):
            elem = i-j
            if elem < size and j < size:
                if rotated_board[elem][j] == 1:
                    queen_count += 1
                    if queen_count > 1:
                        return False
    return True      

def n_constricts(board, queen):
    n_constricts = 0
    i,j = queen
    i += 1
    j += 1
    # check r diags:
    while i < Constants.size() and j < Constants.size():
        if board[i][j] == 1:
            n_constricts += 1
        i += 1
        j += 1
    i, j = queen
    i -= 1
    j -= 1
    while i >= 0 and j >= 0:
        if board[i][j] == 1:
            n_constricts += 1
        i -= 1
        j -= 1
    # check left diags:
    i, j = queen
    i -= 1
    j += 1
    while i >= 0 and j < Constants.size():
        if board[i][j] == 1:
            n_constricts += 1
        i -= 1
        j += 1
    i, j = queen
    i += 1
    j -= 1
    while i < Constants.size() and j >= 0:
        if board[i][j] == 1:
            n_constricts += 1
        i += 1
        j -= 1
    return n_constricts

def most_constricts(board, queens):
    max_constricts = 0
    max_constricted_queen = None
    for curr_queen in queens:
        curr_constricts = n_constricts(board=board, queen=curr_queen)
        if curr_constricts > max_constricts:
            max_constricts = curr_constricts
            max_constricted_queen = curr_queen
    return max_constricted_queen

def least_constricts(board, queen):
    i,j = queen
    least_constraints = Constants.size() # impossible to have more conflicts than there are queens.
    least_constrained_queen = None
    for y in range(Constants.size() ):
        curr_constraints = n_constricts(board=board, queen=(y,j))
        if curr_constraints < least_constraints:
            least_constraints = curr_constraints
            least_constrained_queen = (y,j)
    # move queen to new position:
    new_i, new_j = least_constrained_queen
    board[i,j] = 0
    board[new_i, new_j] = 1
    # find queen that is now conflicting horizantally and switch it to row that has only zeros:
    conflicting_queen = None
    for z in range(Constants.size() ):
        if z != new_j and board[new_i, z] == 1:
            # swap placement of this queen:
            board[new_i, z] = 0
            board[i, z] = 1
            break
    return board