import pandas as pd

# Create maps for cols that should be one hot encoded, but I run out of RAM:
def get_mapped_vals(df, feature):
    unique_vals = df[feature].unique()
    map = {}
    for i in range(len(unique_vals) ):
        map[unique_vals[i] ] = i
    return map

def get_columns():
    # Get Column Names:
    column_names = ['TransactionID', 'TransactionDT', 'TransactionAmt', 'ProductCD']
    for i in range(6):
        name = 'card' + str(i+1)
        column_names.append(name)
    column_names.append('addr1')
    column_names.append('addr2')
    column_names.append('dist1')
    column_names.append('dist2')
    column_names.append('P_emaildomain')
    column_names.append('R_emaildomain')
    for i in range(14):
        name = 'C' + str(i+1)
        column_names.append(name)
    for i in range(15):
        name = 'D' + str(i+1)
        column_names.append(name)
    for i in range(9):
        name = 'M' + str(i+1)
        column_names.append(name)
    for i in range(339):
        name = 'V' + str(i+1)
        column_names.append(name)
    for i in range(38):
        if i < 10:
            name = 'id_0' + str(i+1)
        else:
            name = 'id_' + str(i+1)
        column_names.append(name)
    column_names.append('DeviceType')
    column_names.append('DeviceInfo')
    print(len(column_names) )
    return column_names

def transformations(df):
    device_type = {None: 0, 'mobile': 1, 'desktop': 2}
    prod_cd = {None: 0, 'C': 1, 'W': 2, 'H': 3, 'S': 4, 'R': 5}
    card_4 = {'discover': 0, 'visa': 1, 'mastercard': 2, 'american express': 3}
    card_6 = {'charge': 0, 'credit': 1, 'debit': 2}

    df['DeviceType'].replace(device_type, inplace=True) # 432
    df['ProductCD'].replace(card_4, inplace=True)       # 3
    df['card4'].replace(card_4, inplace=True)           # 7
    df['card6'].replace(card_6, inplace=True)           # 9

def get_df(data):
    column_names = get_columns()
    df = pd.DataFrame(columns=column_names)
    df.loc[0] = data
    return df