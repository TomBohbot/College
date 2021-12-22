import Move_Piece as mp
import Rules as rules
import SimRules as sim_rules

def next_move(board, player):
    """
    As we are using monte carlo we must simulate how many
    times we can win by making a move from a certain column:
    """
    best_col = mp.first_valid_column(board)
    max_wins = 0
    # Simulate 100 rounds from each position:
    for i in range(7):
        # First copy the board:
        copy_board = board.copy()
        # Check if curr column is ok:
        if mp.open_spot_in_col(board=copy_board, col=i) != -1:
            sum = 0
            # Simulate 100 times:
            for j in range(100):
                mp.next_move(board=copy_board, player=player, col=i)
                sum += simulate_connect_four(board=copy_board, player_one=player)
                copy_board = board.copy()  
            if sum > max_wins:
                best_col = i
                max_wins = sum
    return mp.next_move(board=board, player=player, col=best_col)


def simulate_connect_four(board, player_one):
    """ 
    Simulation of connect 4 for monte carlo

    Output: Winner of the Connect 4 Game
    """
    game_in_progress = True
    curr_player = player_one
    winner = None
    counter = 0
    while game_in_progress and mp.full_board(board) == False:
        counter += 1
        position = mp.random_next_move(board, curr_player)
        if sim_rules.won_game(board, position, curr_player):
            game_in_progress = False
            winner = curr_player
            return True if winner == player_one else False
        curr_player = sim_rules.get_opponent(curr_player)
    return False