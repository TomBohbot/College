import Move_Piece as mp
import Rules as rules
import SimRules as sim_rules
import math

class Node:
    def __init__(self, parent=None, children=[], move=-1):
        self.parent = parent
        self.children = children
        self.move = move
        self.n_samples = 0
        self.n_games_won = 0 

total_samples = 0

def ucb(node):
    """
    Calculate a confidence interval for win ratio of each node in tree.
    """
    if node.n_samples == 0:
        return math.inf
    return (node.n_games_won) + (2)*math.sqrt( (math.log(total_samples) / node.n_samples) )

def simulation(board, player_one):
    """ 
    Iterate until you arrive at a terminal state.
    If you are not at a terminal state then you 
    take a random action and simulate until you 
    arrive at a terminal state.

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

def back_propagation(node, won_game):
    global total_samples
    # If the game was won then must uptick 
    val = 0
    if won_game == True:
        val = 1
    while True:
        if node.parent == None:
            node.n_samples += 1
            node.n_games_won += val
            total_samples += 1
            break
        node.n_samples += 1
        node.n_games_won += val
        node = node.parent 
    
def expansion(node, board, player):
    for i in range(7):
        if mp.valid_location(board=board, col=i):
            child_node = Node(parent=node, move=i)
            node.children.append(child_node)

def search(node, orig_board, player):
    while True:
        # Check if the root is a leaf node:
        if len(node.children) == 0:
            # If the node has been sampled zero times then jump to simulation:
            # Clone board:
            board = orig_board.copy()
            if node.n_samples == 0:
                # Make a random move on cloned board:
                col = node.move
                mp.next_move(board=board, player=player, col=col)
                # Simulate Game
                won_game = simulation(board=board, player_one=player)
                # Call back propagation to uptick number of samples and won games:
                back_propagation(node=node, won_game=won_game)
            # If it has been visited then do expansion:
            else:
                # Expansion:
                expansion(node=node, board=board, player=player)
                # Make a random move on cloned board:
                mp.next_move(board=board, player=player, col=node.children[0].move)
                # Simulate Game    
                won_game = simulation(board=board, player_one=player)
                # Call back propagation to uptick number of samples and won games:
                back_propagation(node=node, won_game=won_game)
            break
        else:
            # Find the largest ucb value between children nodes:
            max_ucb_score = -1
            node_to_explore = node.children[0]
            for child_node in node.children:
                curr_ucb_score = ucb(child_node)   
                if curr_ucb_score > max_ucb_score:
                    max_ucb_score = curr_ucb_score
                    node_to_explore = child_node
            # Set the current node to the node with highest ucb score:
            node = node_to_explore

def mcst(board, player):
    # Set a number of iterations to run the search:
    n_iterations = 8
    # Create a root node
    root_node = Node()
    # Give the root node children whose move are available:
    for i in range(7):
        if mp.valid_location(board=board, col=i):
            child_node = Node(parent=root_node, move=i, children=[])
            root_node.children.append(child_node)
    # Perform the search for n_iteration times
    for i in range(n_iterations):
        search(node=root_node, orig_board=board, player=player)
    # Find the largest amount of wins in the next move:
    max_score = -1
    best_col = None
    for child_node in root_node.children:
        curr_score = child_node.n_games_won
        if curr_score > max_score and mp.valid_location(board=board, col=child_node.move) == True:
            max_score = curr_score
            best_col = child_node.move
    return best_col

def next_move(board, player):
    best_move = mcst(board, player)
    return mp.next_move(board=board, player=player, col=best_move)