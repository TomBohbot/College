# Logistic Regression Model of Aug_Training Data
# @author Tom Bohbot

from sklearn.linear_model import LogisticRegression
from sklearn.ensemble import RandomForestClassifier
from sklearn.metrics import classification_report
import numpy as np
from pprint import pprint

def logistic_regression_model(train_x, test_x, train_y, test_y):
    """
    Logistic Regression Model.
    Returns the model itself and an array of all the predictions it made.
    """
    logistic_regression = LogisticRegression(max_iter=10000)
    logistic_regression.fit(train_x, train_y)
    predictions = logistic_regression.predict(test_x)
    return (logistic_regression, predictions)

def random_forest(train_x, test_x, train_y, test_y):
    """
    Random Forest Model.
    Returns the model itself and an array of all the predictions it made.
    """
    random_forest = RandomForestClassifier()
    random_forest.fit(train_x, train_y)
    probabilities = random_forest.predict_proba(test_x)
    return (random_forest, probabilities, classification_report(test_y, random_forest.predict(test_x)) )