import pandas as pd

# Create maps for cols that should be one hot encoded, but I run out of RAM:
def get_mapped_vals(df, feature):
    unique_vals = df[feature].unique()
    map = {}
    for i in range(len(unique_vals) ):
        map[unique_vals[i] ] = i
    print(map)
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
     # Transormations to train_id:
    boolean_found = {'NotFound': 0, 'Found': 1, None: 0}
    boolean_new = {'New': 0, 'Found': 1, None: 0}
    true_false = {'T': 2, 'F': 1, None: 0}
    device_type = {None: 0, 'mobile': 1, 'desktop': 2}
    df['id_12'].replace(get_mapped_vals(df=df, feature='id_12'), inplace=True)
    df['id_16'].replace(boolean_found, inplace=True)
    df['id_27'].replace(boolean_found, inplace=True)
    df['id_28'].replace(boolean_new, inplace=True)
    df['id_29'].replace(boolean_found, inplace=True)
    df['id_35'].replace(true_false, inplace=True)
    df['id_36'].replace(true_false, inplace=True)
    df['id_37'].replace(true_false, inplace=True)
    df['id_38'].replace(true_false, inplace=True)
    df['DeviceType'].replace(device_type, inplace=True)
    df['id_30'].replace(get_mapped_vals(df=df, feature='id_30'), inplace=True)
    df['id_31'].replace(get_mapped_vals(df=df, feature='id_31'), inplace=True)
    df['id_33'].replace(get_mapped_vals(df=df, feature='id_33'), inplace=True)
    df['DeviceInfo'].replace(get_mapped_vals(df=df, feature='DeviceInfo'), inplace=True)
    df['id_15'].replace(get_mapped_vals(df=df, feature='id_15'), inplace=True)
    df['id_23'].replace(get_mapped_vals(df=df, feature='id_23'), inplace=True)
    df['id_34'].replace(get_mapped_vals(df=df, feature='id_34'), inplace=True)
    # Transormations to train_txn:
    true_false = {'T': 2, 'F': 1, None: 0}
    df['M1'].replace(true_false, inplace=True)
    df['M2'].replace(true_false, inplace=True)
    df['M3'].replace(true_false, inplace=True)
    df['M6'].replace(true_false, inplace=True)
    df['R_emaildomain'].replace(get_mapped_vals(df=df, feature='R_emaildomain'), inplace=True)
    df['M5'].replace(get_mapped_vals(df=df, feature='M5'), inplace=True)
    df['M7'].replace(get_mapped_vals(df=df, feature='M7'), inplace=True)
    df['M8'].replace(get_mapped_vals(df=df, feature='M8'), inplace=True)
    df['M9'].replace(get_mapped_vals(df=df, feature='M9'), inplace=True)
    df['P_emaildomain'].replace(get_mapped_vals(df=df, feature='P_emaildomain'), inplace=True)
    df['ProductCD'].replace(get_mapped_vals(df=df, feature='ProductCD'), inplace=True)
    df['card4'].replace(get_mapped_vals(df=df, feature='card4'), inplace=True)
    df['card6'].replace(get_mapped_vals(df=df, feature='card6'), inplace=True)
    df['M4'].replace(get_mapped_vals(df=df, feature='M4'), inplace=True)

def get_df(data):
    column_names = get_columns()
    df = pd.DataFrame(columns=column_names)
    df.loc[0] = data
    return df