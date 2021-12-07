import Move_Piece as mp
import Rules as rules
import Gui as gui
import time
import AI_Logic as ai

ai_player = 1
other_player = 2

def init_game():
    """
    This function initializes the game and displays the board.

    Input: None
    Output: The initialized board
    """
    board = mp.init_board()
    gui.display_board(board)
    player_one = rules.choose_turn()
    player_two = rules.get_opponent(player_one)
    return board, player_one, player_two

def connect_four():
    """ 
    This function plays the connect four game is the main function 
    of this project.

    Input: None
    Output: Winner of the Connect 4 Game
    """
    board, player_one, player_two = init_game()
    game_in_progress = True
    curr_player = player_one
    winner = None
    while game_in_progress and mp.full_board(board) == False:
        gui.display_board(board)
        if curr_player == ai_player:
            # position = mp.human_next_move(board, curr_player)
            position = ai.next_move(board, curr_player)
        if curr_player == other_player:
            # position = mp.human_next_move(board, curr_player)
            position = mp.random_next_move(board, curr_player)
            # position = ai.next_move(board, curr_player)
        if rules.won_game(board, position, curr_player):
            game_in_progress = False
            winner = curr_player
            if winner == ai_player:
              gui.create_message(f'AI Player has won Connect Four!')  
            if winner == other_player:
                gui.create_message(f'Other Player Two won Connect Four!') 
        gui.display_board(board)
        curr_player = rules.get_opponent(curr_player)
    # time.sleep(10)
    return winner

def evaluate_ai():
    """
    This function evaluates how many times the ai
    wins out of 1,000 games vs the random bot

    Input: None
    Output: Percentage of AI wins
    """
    ai_wins = 0
    score = 0
    for i in range(100):
        if connect_four() == ai_player:
            ai_wins += 1
    return ai_wins/100

print(evaluate_ai() )

# connect_four()
