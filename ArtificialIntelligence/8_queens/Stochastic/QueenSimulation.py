import numpy as np
import time
import MoveQueen
import Constants

def evaluate_stochastic_n_queens():
    num_moves = 0
    num_iterations = 0    
    current_attempts = 0
    board, queen_positions = MoveQueen.place_n_queens()
    while MoveQueen.verify_n_queens(board) == False:
        # move the most constricted piece:
        queen = MoveQueen.most_constricts(board=board, queens=queen_positions)
        # The board was guessed correctly
        if queen == None:
            break
        board = MoveQueen.least_constricts(board=board, queen=queen)
        num_moves += 1
        current_attempts += 1
        # make a new random board:
        if current_attempts == Constants.max_attempts():
            board, queen_positions = MoveQueen.place_n_queens()
            num_iterations += 1
            num_moves += 1
            current_attempts = 0
    return board, num_moves, num_iterations

def main():
    sample_size = 100
    total_moves = 0
    total_iters = 0
    for i in range(sample_size):
        board, num_moves, num_iterations = evaluate_stochastic_n_queens()
        total_moves += num_moves
        total_iters += num_iterations
    print(f"The average number of iterations in DFS are {total_iters/sample_size}.\nThe average number of moves in DFS are {total_moves/sample_size}.")
    # print(board) # if you want to see the last board generated. Intentionally commented out

main()