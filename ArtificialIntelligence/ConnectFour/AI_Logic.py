import Move_Piece as mp
import time
import PotentialLayouts as pot_layouts
import Reward_Logic as reward

def horizantal_score(board, player, col, row):
    """
    This function quantifies the likelyhood of a horizantal 
    victory using this column.

    Input: board, player id, column
    Output: numeric benefit of choosing the column in regards to a horizantal connect four
    """
    row = mp.open_spot_in_col(board, col)
    # Edge case if the column is full:
    if row == -1:
        return 0
    max_score = 0
    pot_cols = pot_layouts.horizantal_layout(col)
    for i in range(len(pot_cols)-2 ):
        layout = [board[row, pot_cols[i]], board[row, pot_cols[i+1]], board[row, pot_cols[i+2]]]
        curr_score = reward.layouts_score(layout, player)
        max_score = max(max_score, curr_score)
    return max_score

def vertical_score(board, player, col, row):
    """
    This function quantifies the likelyhood of a 
    vertically oriented victory using this column.

    Input: board, player id, column
    Output: numeric benefit of choosing the column
    """
    row = mp.open_spot_in_col(board, col)
    # Edge case if the column is full:
    if row == -1:
        return 0
    max_score = 0
    pot_rows = pot_layouts.vertical_layout(row)
    for i in range(len(pot_rows)-2):
        layout = [board[pot_rows[i], col], board[pot_rows[i+1], col], board[pot_rows[i+2], col]]
        curr_score = reward.layouts_score(layout, player)
        max_score = max(max_score, curr_score)
    return max_score


def right_diagonal_score(board, player, col, row):
    """
    This function quantifies the likelyhood of a right
    slanted diagonal oriented victory using this column.

    Input: board, player id, column
    Output: numeric benefit of choosing the column
    """
    row = mp.open_spot_in_col(board, col)
    if row == -1:
        return 0
    max_score = 0
    pot_coords = pot_layouts.r_diag_layout(row, col)
    if pot_coords == None:
        return 0
    for i in range(len(pot_coords)-2):
        layout = [board[pot_coords[i][0], pot_coords[i][1] ], board[pot_coords[i+1][0], pot_coords[i+1][1] ], board[pot_coords[i+2][0], pot_coords[i+2][1] ] ]
        curr_score = reward.layouts_score(layout, player)
        max_score = max(max_score, curr_score)
    return max_score

def left_diagonal_score(board, player, col):
    """
    This function quantifies the likelyhood of a left
    slanted diagonal oriented victory using this column.

    Input: board, player id, column
    Output: numeric benefit of choosing the column
    """
    row = mp.open_spot_in_col(board, col)
    if row == -1:
        return 0
    max_score = 0
    pot_coords = pot_layouts.l_diag_layout(row, col)
    if pot_coords == None:
        return 0
    for i in range(len(pot_coords)-2):
        layout = [board[pot_coords[i][0], pot_coords[i][1] ], board[pot_coords[i+1][0], pot_coords[i+1][1] ], board[pot_coords[i+2][0], pot_coords[i+2][1] ] ]
        curr_score = reward.layouts_score(layout, player)
        max_score = max(max_score, curr_score)
    return max_score

def best_move(board, player):
    """
    This function invokes the score of every direction
    a connect 4 can occur in as quantifies the likelyhood
    using a self-implemented reward logic.

    Input: The current Connect 4 board
           The player who is set to play
    Output: The best column that the player should drop
            their token    
    """
    score = 0
    # find the first col that can be returned
    col = mp.first_valid_column(board)
    for curr_col in range(7):
        curr_score = 0
        curr_row = mp.open_spot_in_col(board, curr_col)
        if curr_row == -1:
            score = -1
        else:
            curr_multipliter = reward.col_multiplier(curr_col+1)
            curr_score += horizantal_score(board, player, curr_col, curr_row) + curr_multipliter
            curr_score += vertical_score(board, player, curr_col, curr_row) + curr_multipliter
            curr_score += right_diagonal_score(board, player, curr_col, curr_row) + curr_multipliter
            curr_score += left_diagonal_score(board, player, curr_col) + curr_multipliter
            if curr_score > score:
                score = curr_score
                col = curr_col
    return col

# def mini_max(board):
#     max_score = 0
#     for c in range(7):
#         curr_score = pot_score(c)
#         max_score = curr_score if curr_score > max_score else max_score
#     return max_score
    
def next_move(board, player):
    """
    Main function which invokes helper function to
    evaluate which column is the best next move.

    Input: Current board
           Current player
    Output: The best column which should be played
    """
    col = best_move(board, player)
    row = mp.open_spot_in_col(board, col)
    board[row, col] = player
    # time.sleep(1)
    return (row, col)