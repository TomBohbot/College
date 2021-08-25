import pygame

def init_gui_params():
    """
    This function initializes the necessary variables
    and state needed to visualize the board.

    Input: None
    Output: The screen object needed to display the board.
    """
    pygame.init()
    n_rows = 8
    n_cols = 7
    pixels = 100
    width = n_cols * pixels
    height = n_rows * pixels
    size = (width, height)
    screen = pygame.display.set_mode(size)
    return screen
    
def display_board(non_gui_board):
    """
    This function displays the connect 4 board as a gui
    so that the user can better enjoy the game as either
    a player or a spectator.

    Input: The matrix representing the connect 4 board.
    Output: None
    """
    screen = init_gui_params()
    # draw board:
    for c in range (7):
        for r in range(1,8):
            pygame.draw.rect(screen, (0,0,255), (c*100, r*100+100, 100, 100))
            pygame.draw.circle(screen, (0,0,0), (c*100+50, r*100+50), 40)
    # draw tokens:
    for c in range (7):
        for r in range(6):
            if non_gui_board[r][c] != 0:
                player_color = (255,0,0) if non_gui_board[r][c] == 1 else (255,255,0)
                player_color = (0,255,0) if non_gui_board[r][c] == 3 else player_color
                pygame.draw.circle(screen, player_color,(c*100+50, 7*100-r*100+50), 40)
    pygame.display.update()

def create_message(text):
    """
    This function is to easily display messages on pygame
    without having to worry about the intricate details.

    Input: Text wanted to be displayed
    Output: None
    """
    screen = init_gui_params()
    font = pygame.font.SysFont('Calibri', 45)
    message = font.render(text, False, (255,0,0) )
    screen.blit(message, (0,0) )
    pygame.display.update()

def visualize_winning_tokens(board, coordinates):
    """
    This function changes the winning 4 tokens
    to turn into green so that the user can 
    better visualize the winning tokens.

    Input: 2d board array, 
           the four winning coordinates
    Output: None
    """
    for coord in coordinates:
        board[int(coord[0]), int(coord[1])] = 3
