import pygame
import time
import numpy as np
import BoardSize

def init_gui_params():
    """
    This function initializes the necessary variables
    and state needed to visualize the board.

    Input: None
    Output: The screen object needed to display the board.
    """
    pygame.init()
    n_rows = BoardSize.size()+2
    n_cols = BoardSize.size()
    pixels = 200
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
    screen.fill((255, 255, 255))
    # draw board:
    for c in range (BoardSize.size()):
        for r in range(1,BoardSize.size()+2):
            if c % 2 == 0:
                if r%2 == 0:
                    pygame.draw.rect(screen, (0,163,108), (c*100, r*100+100, 100, 100))
                else:
                    pygame.draw.rect(screen, (55,100,55), (c*100, r*100+100, 100, 100))
            else:
                if r%2 == 0:
                    pygame.draw.rect(screen, (55,100,55), (c*100, r*100+100, 100, 100))
                else:
                    pygame.draw.rect(screen, (0,163,108), (c*100, r*100+100, 100, 100))
    # draw queens:
    for c in range (BoardSize.size()):
        for r in range(BoardSize.size()):
            if non_gui_board[r][c] != 0:
                player_color = (50,130,255) if non_gui_board[r][c] == 1 else (255,255,0)
                pygame.draw.circle(screen, player_color,(c*100+50, (BoardSize.size()-1)*100-(r-2)*100+50), 40)
    pygame.display.update()
    screen = init_gui_params()

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