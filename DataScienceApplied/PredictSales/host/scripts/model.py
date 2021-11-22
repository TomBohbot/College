import numpy as np
import pandas as pd
from xgboost import XGBRegressor
import pickle
import math
from sktime.performance_metrics.forecasting import MeanSquaredPercentageError

def rmspe(trues, preds):
#   sum = 0
#   for i in range(len(trues)):
#     if trues[i] != 0:
#         diff = trues[i]-preds[i]
#         div = diff/trues[i]
#         squared = div*div
#         sum += squared
#         rmspe = math.sqrt((1/len(trues))*sum  )
#         return rmspe
    rmspe = MeanSquaredPercentageError(square_root=True)
    # Get rid of scores when they are zero:
    parsed_trues = []
    parsed_preds = []
    for i in range(len(trues)):
        if trues[i] != 0:
            parsed_trues.append(trues[i])
            parsed_preds.append(preds[i])
    return rmspe(parsed_trues, parsed_preds)

def train_test_split(df):
    # Get Size:
    total_size = df.shape[0]
    test_size = int(total_size*0.2)
    # Drop Sales:
    target_vales = df['Sales']
    df = df.drop(['Sales'], axis=1)
    # Get Training Data:
    x_train = df.iloc[test_size:]
    y_train = target_vales.iloc[test_size:]
    # Get Testing Data:
    x_test = df.iloc[:test_size]
    y_test = target_vales.iloc[:test_size].values
    return x_train, y_train, x_test, y_test

def xgb_model(df):
    baseline_score = 0.5
    x_train, y_train, x_test, y_test = train_test_split(df)
    model = XGBRegressor()
    model.fit(x_train, y_train)
    preds = model.predict(x_test)
    rmspe_score = rmspe(y_test, preds)
    print(rmspe_score)
    # save the model to be reused in future:
    if rmspe_score < baseline_score:
        pickle.dump(model, open('xgb_model.txt', 'wb') )
        return rmspe_score
    else:
        return -1