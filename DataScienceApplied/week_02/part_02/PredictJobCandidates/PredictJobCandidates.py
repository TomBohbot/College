import xlwings as xw
import pandas as pd

import scripts.CleanData as clean_data
import scripts.GetData as get_training_data
import scripts.Models as models

def get_users_data():
    """
    Returns a data frame of pandas data
    """
    df = xw.Range('A1').expand().options(pd.DataFrame).value
    df.reset_index(inplace=True)
    return df

def clean_users_data(df):
    """
    Return a clean and imputed dataframe using 
    same functions as the training data.
    """
    clean_data.remove_redundant_columns(df)
    clean_data.map_strings_to_ints(df)
    imputed_df = clean_data.mice_imputer_wo_target(df)
    clean_data.convert_to_proper_type(imputed_df)
    return imputed_df


def main():
    # Clean and get users data:
    df = get_users_data()
    data = clean_users_data(df).to_numpy()

    # Get Random Forest Model:
    df = clean_data.get_clean_dataframe()
    train_x, test_x, train_y, test_y = get_training_data.get_train_test_split(df, imbalanced=True)
    rf_model, random_forest_probs = models.random_forest(train_x, test_x, train_y, test_y)

    # Predict Values From Excel:
    probabilities = rf_model.predict_proba(data)

    # Transform Predictions Array To Be Understandable By Recruiters:
    transformed_preds = ['Should Approach' if elem[1] >= 0.6 else 'Should Not Approach' for elem in probabilities]

    # Return Predictions To Excel:
    work_sheet = xw.Book('PredictJobCandidates.xlsm').sheets('Sheet1')
    work_sheet.range('O2').options(transpose=True).value = transformed_preds

if __name__ == "__main__":
    xw.Book("PredictJobCandidates.xlsm").set_mock_caller()
    main()
