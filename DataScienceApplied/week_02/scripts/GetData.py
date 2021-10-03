from sklearn.model_selection import train_test_split
from sklearn import metrics
from sklearn.metrics import classification_report
from imblearn.over_sampling import SMOTE

def get_metrics(y_true, y_pred):
    return classification_report(y_true, y_pred)

def get_train_test_split(df, imbalanced=False):
    # Create data and target vars:
    x = df.drop(['target'], axis=1)
    y = df['target']

    if imbalanced:
        # Perform SMOTE to balance data:
        oversample = SMOTE()
        x, y = oversample.fit_resample(x, y)

    train_x, test_x, train_y, test_y = train_test_split(x, y, test_size=0.20)
    return (train_x, test_x, train_y, test_y)