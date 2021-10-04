from sklearn.model_selection import train_test_split
from sklearn import metrics
from sklearn.metrics import classification_report
from imblearn.over_sampling import SMOTE
import numpy as np
import pandas as pd

def get_metrics(y_true, y_pred):
    """
    Returns the sklearn classification report of any machine learning model
    """
    return classification_report(y_true, y_pred)

def get_train_test_split(df, imbalanced=True):
    """
    Requirements: The target column must be called target.
    If imbalanced is set to True then it will balance the data set using SMOTE.
    Returns the given data set as a train/test split.
    """
    # Create data and target vars:
    x = df.drop(['target'], axis=1)
    y = df['target']

    if imbalanced:
        # Perform SMOTE to balance data:
        oversample = SMOTE()
        x, y = oversample.fit_resample(x, y)

    train_x, test_x, train_y, test_y = train_test_split(x, y, test_size=0.20)
    return (train_x, test_x, train_y, test_y)

def cross_validation (lr_model, rf_model, x, y):
    """
    Compare two machine learning models and creates a pandas dataframe of any instance in which the models differ in prediction.
    Returns a pandas datafrane of the features in the data set, the target, and which model was correct.
    """
    x = x.to_numpy()
    y = y.to_numpy()
    different_predictions = []
    for i in range(len(x)):
        lr_pred = lr_model.predict(np.array([x[i],] )) # add a 2nd dimension 
        rf_pred = rf_model.predict(np.array([x[i],] )) # add a 2nd dimension 
        if lr_pred != rf_pred:
            correct_model = 'random_forest'
            if lr_pred == y[i]:
                correct_model = 'logistic_regression'
            different_predictions.append((x[i][0], x[i][1], x[i][2], x[i][3], x[i][4], x[i][5], x[i][6], x[i][7], x[i][8], x[i][9], x[i][10], x[i][11], y[i], correct_model))
    df = pd.DataFrame(different_predictions, columns=['city', 'city_development_index', 'gender', 'relevent_experience', 'enrolled_university', 'education_level', 'major_discipline', 'experience', 'company_size', 'company_type', 'last_new_job', 'training_hours', 'target', 'correct_model'])
    return df