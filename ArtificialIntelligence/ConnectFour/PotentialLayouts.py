def horizantal_layout(col):
    """
    This function returns the potential columns a horizantal
    connect four  can take place in depending on which column 
    you are currently at.

    Input: Current Column
    Output: Array of columns where a token could result in a
            win depending where your current column is.
    """
    if col == 0:
        return [1,2,3]
    if col == 1:
        return [0,2,3,4]
    if col == 2:
        return [0,1,3,4,5]
    if col == 3:
        return [0,1,2,4,5,6]
    if col == 4:
        return [1,2,3,5,6]
    if col == 5:
        return [2,3,4,6]
    if col == 6:
        return [3,4,5]

def vertical_layout(row):
    """
    This function returns the potential columns a vertical
    connect four  can take place in depending on which row 
    you are currently at.

    Input: Current Row
    Output: Array of rows where a token could result in a
            win depending where your current row is.
    """    
    if row == 0:
        return [1,2,3]
    if row == 1:
        return [0,2,3,4]
    if row == 2:
        return [0,1,3,4,5]
    if row == 3:
        return [0,1,2,4,5]
    if row == 4:
        return [1,2,3,5]
    if row == 5:
        return [2,3,4] 

def r_diag_layout(row, col):
    """
    This function returns the potential columns a right
    slanted connect four  can take place in depending 
    on which row you are currently at.

    Input: Current Row and Column
    Output: Sorted Array of Coordinates where a token could result 
            in a win depending where your current row is.
    """    
    coordinates = []
    for i in range(1,4):
        if row-i >= 0 and col-i >= 0:
            coordinates.append((row-i, col-i))
        if row+i <= 5 and col+i <= 6:
            coordinates.append((row+i, col+i))
    if len(coordinates) < 3:
        return None
    else:
        # sort the coordinates from least to greatest then return:
        return sorted(coordinates, key=lambda k: k)

def l_diag_layout(row, col):
    """
    This function returns the potential columns a left
    slanted connect four  can take place in depending 
    on which row you are currently at.

    Input: Current Row and Column
    Output: Sorted Array of Coordinates where a token could result 
            in a win depending where your current row is.
    """    
    coordinates = []
    for i in range(1, 4):
        if row+i <= 5 and col-i >= 0:
            coordinates.append((row+i, col-i))
        if row-i >= 0 and col+i <= 6:
            coordinates.append((row-i, col+i))
    if len(coordinates) < 3:
        return None
    else:
        # sort the coordinates from least to greatest then return:
        return sorted(coordinates, key=lambda k: [k[1], k[0] ])