# Logistic Regression Model of Aug_Training Data
# @author Tom Bohbot

from sklearn.linear_model import LogisticRegression
from sklearn.ensemble import RandomForestClassifier
import numpy as np
import CleanData as Data
import GetData
from pprint import pprint

def logistic_regression_model(train_x, test_x, train_y, test_y):
    logistic_regression = LogisticRegression(max_iter=10000)
    logistic_regression.fit(train_x, train_y)
    predictions = logistic_regression.predict(test_x)
    return (logistic_regression, predictions)

def random_forest(train_x, test_x, train_y, test_y):
    random_forest = RandomForestClassifier()
    random_forest.fit(train_x, train_y)
    predictions = random_forest.predict(test_x)
    return (random_forest, predictions)

def models_differ_in_predictions (lr_model, rf_model, x, y):
    x = x.to_numpy()
    y = y.to_numpy()
    different_predictions = []
    for i in range(len(x)):
        lr_pred = lr_model.predict(np.array([x[i],] )) # add a 2nd dimension 
        rf_pred = rf_model.predict(np.array([x[i],] )) # add a 2nd dimension 
        if lr_pred != rf_pred:
            different_predictions.append((x[i], y[i]))
    return different_predictions

def main():
    # Retrieve the Cleaned Data:
    df = Data.get_clean_dataframe()

    # train_test_split and balance data if desired by setting imbalanced to true:
    train_x, test_x, train_y, test_y = GetData.get_train_test_split(df, imbalanced=True)

    # Call both Models:
    lr_model, lr_preds = logistic_regression_model(train_x, test_x, train_y, test_y)
    rf_model, random_forest_preds = random_forest(train_x, test_x, train_y, test_y)

    # Find out where models differ in predictions:
    different_predictions = models_differ_in_predictions(lr_model=lr_model, rf_model=rf_model, x=test_x, y=test_y)
    pprint(different_predictions[:5])

    # Print Classification Reports:
    print("Logistic Regression Metrics:")
    print(GetData.get_metrics(test_y, lr_preds) )
    print("Random Foreset Metrics:")
    print(GetData.get_metrics(test_y, random_forest_preds) )

main()
