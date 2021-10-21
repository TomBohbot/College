import random
import numpy as np

def place_n_queens(size):
    """
    Randomly place n queens on a nxn chess board.
    Returns a 2d array representing the chess board with n random queens placed. and an array representing queen positions
    """
    # positions = random.sample(range(0, size), size) # this was when I altered code to never be in same column and row. However, British Museum is not that smart..
    board = np.zeros((size, size))
    for i in range(size):
        # board[i,positions[i]] = 1
        column = random.randrange(0,size)
        board[i, column] = 1
    return board

def verify_n_queens(board, size):
    """
    Checks the board with the randomly placed n queens.
    Returns True if no queens are connected, False otherwise
    """
    # print(board)
    # Check if there are two queens on same row:
    for i in range(size):
        queen_count = 0
        for j in range(size):
            if board[i][j] == 1:
                queen_count += 1
        if queen_count > 1:
            # print("HORIZANTAL LOSS")
            return False

    # Check if two queens are on same column:
    for i in range(size):
        queen_count = 0
        for j in range(size):
            if board[j][i] == 1:
                queen_count += 1
        if queen_count > 1:
            # print("VERTICAL LOSS")
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
                        # print("R DIAG LOSS")
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
                        # print("L DIAG LOSS")
                        return False
    return True
        

def british_museum(size):
    """
    Preform british museum on a nxn board.
    Returns the board, number of iterations, and number of moves.
    """
    # Initialize necessary variables:
    num_iterations = 0
    num_moves = 0
    board = place_n_queens(size=size)
    # repeat placing n queens until you get a board in which no queens are connected:
    while verify_n_queens(board=board, size=size) == False:
        board = place_n_queens(size=size)
        num_iterations += 1
        num_moves += 1
    return (board, num_iterations, num_moves)


board, num_iterations, num_moves = british_museum(size=8)
print(board)
print(f"Number of iterations: {num_iterations}")
print(f"Number of moves {num_moves}")
