import numpy as np
import pandas as pd
import matplotlib.pylab as plt
from cycler import cycler
from sklearn.model_selection import train_test_split
from sklearn.metrics import roc_auc_score, accuracy_score
from fancyimpute import IterativeImputer
from xgboost import XGBClassifier
import pickle
from sklearn.metrics import classification_report
import os

def get_data():
    train_txn = pd.read_csv('data/train_transaction.csv')
    train_id = pd.read_csv('data/train_identity.csv')

    # the shape is still the same as using kaggle api:
    print(train_txn.shape)
    print(train_id.shape)
    return train_txn, train_id

def remove_heavy_null_columns(df, threshold, n_rows):
    cols_over_threshold = []
    for column in df:
        missing_values = df[column].isna().sum()
        if missing_values != 0 and missing_values/n_rows >= threshold:
            cols_over_threshold.append(column)
    df.drop(cols_over_threshold, axis=1, inplace=True)


# One hot encodings:
def one_hot_encoding(df, column_name):
    encoding = pd.get_dummies(df[column_name])
    df = df.drop(column_name, axis = 1)
    df = df.join(encoding)  
    return df

# Create maps for cols that should be one hot encoded, but I run out of RAM:
def get_mapped_vals(df, feature):
    unique_vals = df[feature].unique()
    map = {}
    for i in range(len(unique_vals) ):
        map[unique_vals[i] ] = i
    return map

def clean_data(train_txn, train_id):
    # # Remove null columns
    # threshold = 0.5
    # remove_heavy_null_columns(df=train_txn, threshold=threshold, n_rows=train_txn.shape[0])
    # remove_heavy_null_columns(df=train_id, threshold=threshold, n_rows=train_txn.shape[0])

    # Transormations to train_id:
    boolean_found = {'NotFound': 0, 'Found': 1, None: 0}
    boolean_new = {'New': 0, 'Found': 1, None: 0}
    true_false = {'T': 2, 'F': 1, None: 0}
    device_type = {None: 0, 'mobile': 1, 'desktop': 2}
    train_id['id_12'].replace(boolean_found, inplace=True)
    train_id['id_16'].replace(boolean_found, inplace=True)
    train_id['id_27'].replace(boolean_found, inplace=True)
    train_id['id_28'].replace(boolean_new, inplace=True)
    train_id['id_29'].replace(boolean_found, inplace=True)
    train_id['id_35'].replace(true_false, inplace=True)
    train_id['id_36'].replace(true_false, inplace=True)
    train_id['id_37'].replace(true_false, inplace=True)
    train_id['id_38'].replace(true_false, inplace=True)
    train_id['DeviceType'].replace(device_type, inplace=True)
    train_id['id_30'].replace(get_mapped_vals(df=train_id, feature='id_30'), inplace=True)
    train_id['id_31'].replace(get_mapped_vals(df=train_id, feature='id_31'), inplace=True)
    train_id['id_33'].replace(get_mapped_vals(df=train_id, feature='id_33'), inplace=True)
    train_id['DeviceInfo'].replace(get_mapped_vals(df=train_id, feature='DeviceInfo'), inplace=True)
    train_id['id_15'].replace(get_mapped_vals(df=train_id, feature='id_15'), inplace=True)
    train_id['id_23'].replace(get_mapped_vals(df=train_id, feature='id_23'), inplace=True)
    train_id['id_34'].replace(get_mapped_vals(df=train_id, feature='id_34'), inplace=True)

    # Transormations to train_txn:
    true_false = {'T': 2, 'F': 1, None: 0}
    train_txn['M1'].replace(true_false, inplace=True)
    train_txn['M2'].replace(true_false, inplace=True)
    train_txn['M3'].replace(true_false, inplace=True)
    train_txn['M6'].replace(true_false, inplace=True)
    train_txn['R_emaildomain'].replace(get_mapped_vals(df=train_txn, feature='R_emaildomain'), inplace=True)
    train_txn['M5'].replace(get_mapped_vals(df=train_txn, feature='M5'), inplace=True)
    train_txn['M7'].replace(get_mapped_vals(df=train_txn, feature='M7'), inplace=True)
    train_txn['M8'].replace(get_mapped_vals(df=train_txn, feature='M8'), inplace=True)
    train_txn['M9'].replace(get_mapped_vals(df=train_txn, feature='M9'), inplace=True)
    train_txn['P_emaildomain'].replace(get_mapped_vals(df=train_txn, feature='P_emaildomain'), inplace=True)
    train_txn['ProductCD'].replace(get_mapped_vals(df=train_txn, feature='ProductCD'), inplace=True)
    train_txn['card4'].replace(get_mapped_vals(df=train_txn, feature='card4'), inplace=True)
    train_txn['card6'].replace(get_mapped_vals(df=train_txn, feature='card6'), inplace=True)
    train_txn['M4'].replace(get_mapped_vals(df=train_txn, feature='M4'), inplace=True)
    return train_id.shape, train_txn.shape

def xgboost():
    train_txn, train_id = get_data()
    clean_data(train_txn=train_txn, train_id=train_id)

    # merge the dataframes:
    df = pd.merge(train_txn, train_id, on='TransactionID', how='outer')

    # Train test split:
    X = df.drop('isFraud', axis=1)
    y = df['isFraud']
    train_x, test_x, train_y, test_y = train_test_split(X, y, test_size=0.2, stratify=df['isFraud'])

    # Load previously trained model:
    try:
        path = str(os.path.join(os.path.dirname(os.path.abspath(__file__) ), 'xgb_model.txt'))
        xgb_clf = pickle.load(open(path, 'rb'))
    
    # If there is no previously trained model:
    except:
        xgb_clf = XGBClassifier(
            use_label_encoder=False
        )
        xgb_clf.fit(train_x, train_y, eval_metric='aucpr')

        # save the model to be reused in future:
        pickle.dump(xgb_clf, open('xgb_model.txt', 'wb') )

    # print metrics:
    predicted_proababilitis = xgb_clf.predict_proba(test_x)
    print("ACCURACY SCORE:", xgb_clf.score(test_x, test_y))
    print("ROC AUC SCORE:", roc_auc_score(test_y, predicted_proababilitis[:,1]) )

xgboost()



