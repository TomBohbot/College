columns = [] #columns is the locations for each of the queens
size = 8
import random #hint -- you will need this for the following code: column=random.randrange(0,size)

"""
Void function that prints the board.
"""
def display():
    for row in range(len(columns)):
        for column in range(size):
            if column == columns[row]:
                print('♛', end=' ')
            else:
                print(' .', end=' ')
        print()

"""This of course is not necessary legal, so we'll write a simple DFS search with backtracking:"""
def solve_queen(size):
    columns.clear()
    number_of_moves = 0 #where do I change this so it counts the number of Queen moves?
    number_of_iterations = 0  
    row = 0
    column = 0
    # iterate over rows of board
    while True:
        # place queen in next row
        while column < size:
            number_of_iterations+=1
            number_of_moves += 1
            if next_row_is_safe(column):
                place_in_next_row(column)
                row += 1
                column = 0
                break
            else:
                column += 1
        # if I could not find an open column or if board is full
        if (column == size or row == size):
            number_of_iterations+=1
            number_of_moves += 1
            # if board is full, we have a solution
            if row == size:
                return number_of_iterations, number_of_moves
            # I couldn't find a solution so I now backtrack
            prev_column = remove_in_current_row()
            if (prev_column == -1): #I backtracked past column 1
                return number_of_iterations, number_of_moves
            # try previous row again
            row -= 1
            # start checking at column = (1 + value of column in previous row)
            column = 1 + prev_column

def place_in_next_row(column):
    columns.append(column)
 
def remove_in_current_row():
    if len(columns) > 0:
        return columns.pop()
    return -1
 
def next_row_is_safe(column):
    row = len(columns) 
    # check column
    for queen_column in columns:
        if column == queen_column:
            return False
    # check diagonal
    for queen_row, queen_column in enumerate(columns):
        if queen_column - queen_row == column - row:
            return False
    # check other diagonal
    for queen_row, queen_column in enumerate(columns):
        if ((size - queen_column) - queen_row
            == (size - column) - row):
            return False
    return True

def evaluate_dfs():
    n_iterations = 0
    n_moves = 0
    for i in range(100):
        curr_iterations, curr_moves=solve_queen(size)
        n_iterations += curr_iterations
        n_moves += curr_moves
    average_n_iterations = n_iterations/100
    average_n_moves = n_moves/100
    return (average_n_iterations, average_n_moves)

evaluated_dfs = evaluate_dfs()
print(f"The average number of iterations in DFS are {evaluated_dfs[0]}.\nThe average number of moves in DFS are {evaluated_dfs[1]}.")