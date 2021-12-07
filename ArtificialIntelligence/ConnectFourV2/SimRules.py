import random
import PotentialLayouts as pot_layout

def choose_turn():
    """
    This function decides the original turn order 
    between player 1 and player 2.

    Input: None
    Output: Random number deciding if player one is first or second.
    """
    return random.randint(1,2)

def get_opponent(player):
    """
    This function returns the opponent of the current player.

    Input: Current Player
    Output: Opposing Player
    """
    if player == 1:
        return 2
    else:
        return 1

def vertical_win(board, position, player):
    """
    This function checks if the user's token
    has just made a connect 4 vertically.

    Input: 2d array board, 
           coordinaes of placed token, 
           players turn
    Output: Boolean deciding whether the player has won.
    """
    row, col = position
    pot_rows = pot_layout.vertical_layout(row)
    for i in range(len(pot_rows)-2):
        layout = [board[pot_rows[i], col], board[pot_rows[i+1], col], board[pot_rows[i+2], col]]
        if layout.count(player) >= 3:
            return True
    return False        

def horizantal_win(board, position, player):
    """
    This function checks if the user's token
    has just made a connect 4 horizantally.

    Input: 2d array board, 
           coordinaes of placed token, 
           players turn
    Output: Boolean deciding whether the player has won.
    """
    row, col = position
    pot_cols =  pot_layout.horizantal_layout(col)
    for i in range(len(pot_cols)-2 ):
        layout = [board[row, pot_cols[i]], board[row, pot_cols[i+1]], board[row, pot_cols[i+2]]]
        if layout.count(player) >= 3:
            return True
    return False

def right_diag_win(board, position, player):
    """
    This function checks if the user's token
    has just made a connect 4 in a right diagonal.

    Input: 2d array board, 
           coordinaes of placed token, 
           players turn
    Output: Boolean deciding whether the player has won.
    """   
    row, col = position
    pot_coords = pot_layout.r_diag_layout(row, col)
    if pot_coords == None:
        return 0
    for i in range(len(pot_coords)-2):
        layout = [board[pot_coords[i][0], pot_coords[i][1] ], board[pot_coords[i+1][0], pot_coords[i+1][1] ], board[pot_coords[i+2][0], pot_coords[i+2][1] ] ]
        if layout.count(player) >= 3:      
            return True
    return False

def left_diag_win(board, position, player):
    """
    This function checks if the user's token
    has just made a connect 4 in a left diagonal.

    Input: 2d array board, 
           coordinaes of placed token, 
           players turn
    Output: Boolean deciding whether the player has won.
    """
    row, col = position
    pot_coords = pot_layout.l_diag_layout(row, col)
    if pot_coords == None:
        return 0
    for i in range(len(pot_coords)-2):
        layout = [board[pot_coords[i][0], pot_coords[i][1] ], board[pot_coords[i+1][0], pot_coords[i+1][1] ], board[pot_coords[i+2][0], pot_coords[i+2][1] ] ]
        if layout.count(player) >= 3:
            return True
    return False

def won_game(board, position, player):
    """
    This function checks if the user's token
    has just made a connect 4.

    Input: 2d array board, 
           coordinaes of placed token, 
           players turn
    Output: Boolean deciding whether the player has won.
    """
    if vertical_win(board, position, player):
        return True
    if horizantal_win(board, position, player):
        return True
    if right_diag_win(board, position, player):
        return True
    if left_diag_win(board, position, player):
        return True
    else:
        return False