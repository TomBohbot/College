import Rules as rules

def col_multiplier(col):
    """
    This function determines the benefit of choosing a specific 
    column as a more central column should be better rewarded.

    Input: Column
    Output: Multiplier value of column
    """
    if col == 4:
        return 10
    if col in [3, 5]:
        return 6
    if col in [2, 6]:
        return 3
    if col in [1, 7]:
        return 1

def max_players_layout_score(layout, player):
    """
    This function determines the max score a player
    can achieve in the current turn if the goal
    is to play offensively.

    Input: The potential connect fours that can occur 
           represented as an array of anything within 
           four token width of the direction the function 
           was caled in. 
           The current player
    Output: The arbitrary score quantifying the likelyhood
            of scoring a connect 4.
    """
    opponent = rules.get_opponent(player)
    if layout.count(player) == 3:
        return 100000
    if layout.count(player) == 2 and layout.count(0) == 1:
        return 70
    if layout.count(player) == 1 and layout.count(0) == 2:
        return 30
    if layout.count(opponent) > 0:
        return 0
    else:
        return 1

def max_opponents_layout_score(layout, player):
    """
    This function determines the max score a player
    can achieve in the current turn if the goal
    is to play defensively.

    Input: The potential connect fours that can occur 
           represented as an array of anything within 
           four token width of the direction the function 
           was caled in. 
           The current player
    Output: The arbitrary score quantifying the likelyhood
            of the opponent scoring a connect 4.
    """
    opponent = rules.get_opponent(player)
    if layout.count(opponent) == 3:
        return 99999
    if layout.count(opponent) == 2 and layout.count(0) == 1:
        return 69
    if layout.count(opponent) == 1 and layout.count(0) == 2:
        return 29
    if layout.count(player) > 0:
        return 0
    else:
        return 1

def layouts_score(layout, player):
    """
    This function determines if it is more beneficial
    for the current player to play offensively or 
    defensively 

    Input: The potential connect fours that can occur 
           represented as an array of anything within 
           four token width of the direction the function 
           was caled in. 
           The current player
    Output: The arbitrary score quantifying the likelyhood
            of either the current player or opponent scoring
            a connect 4.
    """
    return max(max_players_layout_score(layout, player), max_opponents_layout_score(layout, player))