import xlwings as xw
import pandas as pd

import scripts.CleanData as clean_data
import scripts.GetData as get_training_data
import scripts.Models as models
import pickle
import os

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
    work_sheet = xw.Book('PredictJobCandidates.xlsm').sheets('Sheet1')

    # Try to load already trained model, if it doesnt exist then create it (the creation can be done without the excel button since step 5 must only use an existing model): 
    try:
        # Load previously trained model:
        rf_model = pickle.load(open('/Users/tombohbot/TomsGit/TomsPublicCode/DataScienceApplied/week_02/part_02/PredictJobCandidates/random_forest_model.txt', 'rb'))

        # output to excel if previous model was used:
        work_sheet.range('Q2').options(transpose=True).value = "Reused previous model"
    except:
        # Train Random Forest Model:
        df = clean_data.get_clean_dataframe()
        train_x, test_x, train_y, test_y = get_training_data.get_train_test_split(df, imbalanced=True)
        rf_model, random_forest_probs, metrics_report = models.random_forest(train_x, test_x, train_y, test_y)

        # save the metrics report as a file:
        metrics_file_name = 'metrics_report.txt'
        f = open("metrics_file_name.txt", "w")
        f.write(metrics_report)
        f.close()

        # save the model to be reused in future:
        pickled_model_file_name = 'random_forest_model.txt'
        pickle.dump(rf_model, open(pickled_model_file_name, 'wb') )

        # output to excel if new model was used:
        work_sheet.range('Q2').options(transpose=True).value = "Had to retrain model"

    # Predict Values From Excel:
    probabilities = rf_model.predict_proba(data)

    # Transform Predictions Array To Be Understandable By Recruiters. Be more selective and increase precision by raising threshold:
    transformed_preds = ['Should Approach' if elem[1] >= 0.6 else 'Should Not Approach' for elem in probabilities]

    # Return Predictions To Excel:
    work_sheet.range('O2').options(transpose=True).value = transformed_preds


if __name__ == "__main__":
    xw.Book("PredictJobCandidates.xlsm").set_mock_caller()
    main()
