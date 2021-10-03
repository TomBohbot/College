# Fill in missing values of the aug_train csv file
# @author Tom Bohbot

import numpy as np
import pandas as pd
from fancyimpute import IterativeImputer

def get_csv():
    return pd.read_csv('data/aug_train.csv')

def remove_redundant_columns(df):
    df.drop(['enrollee_id'], axis=1, inplace=True)

def map_strings_to_ints(df):
    # Necessary mappings:
    gender = {'Male': 0, 'Female': 1, 'Other': 2}
    experience = {'No relevent experience': 0, 'Has relevent experience': 1}
    enrollment_status = {'no_enrollment': 0, 'Part time course': 1, 'Full time course': 2}
    educaton_degree = {'Primary School': 0, 'High School': 1, 'Graduate': 2, 'Masters': 3, 'Phd': 4}
    major = {'STEM': 0, 'Business Degree': 1, 'Arts': 2, 'Humanities': 3, 'No Major': 4, 'Other': 5}
    prior_jobs = {'<1' : 0, '>20': 21}
    for i in range(21):
        prior_jobs[str(i)] = i
    company_size = {'50-99': 50, '<10': 1, '10000+': 10000, '5000-9999': 5000, '1000-4999': 1000, '10/49': 10, '100-500': 100, '500-999': 500}
    company_type = {'Other': 0, 'Funded Startup': 1, 'Early Stage Startup': 2, 'Pvt Ltd': 3, 'Public Sector': 4, 'NGO': 5}
    last_new_job = {'never': 0, '1': 1, '2': 2, '3': 3, '4': 4, '>4': 5}
    # Alterations to dataframe:
    df['city'] = df['city'].apply(lambda x: ' '.join(x.split('city_')[1:]))
    df['city_development_index'] = df['city_development_index']
    df['gender'].replace(gender, inplace=True)
    df['relevent_experience'].replace(experience, inplace=True)
    df['enrolled_university'].replace(enrollment_status, inplace=True)
    df['education_level'].replace(educaton_degree, inplace=True)
    df['major_discipline'].replace(major, inplace=True)
    df['experience'].replace(prior_jobs, inplace=True)
    df['company_size'].replace(company_size, inplace=True)
    df['company_type'].replace(company_type, inplace=True)
    df['last_new_job'].replace(last_new_job, inplace=True)
    df['training_hours'] = df['training_hours']

def fill_nan_with_mean(df):
    mice = IterativeImputer()
    return pd.DataFrame(mice.fit_transform(df), columns= ['city', 'city_development_index', 'gender', 'relevent_experience', 'enrolled_university', 'education_level', 'major_discipline', 'experience', 'company_size', 'company_type', 'last_new_job', 'training_hours', 'target'])

def convert_to_proper_type(df):
    df['city'] = df['city'].astype(int)
    df['city_development_index'] = df['city_development_index'].astype(float)
    df['gender'] = df['gender'].round(decimals = 0).astype(int)
    df['relevent_experience'] = df['relevent_experience'].astype(int)
    df['enrolled_university'] = df['enrolled_university'].astype(int)
    df['education_level'] = df['education_level'].astype(int)
    df['major_discipline'] = df['major_discipline'].astype(int)
    df['experience'] = df['experience'].astype(int)
    df['company_size'] = df['company_size'].astype(int)
    df['company_type'] = df['company_type'].astype(int)
    df['last_new_job'] = df['last_new_job'].astype(int)
    df['training_hours'] = df['training_hours'].astype(int)

def get_clean_dataframe():
    df = get_csv()
    remove_redundant_columns(df)
    map_strings_to_ints(df)
    imputed_df = fill_nan_with_mean(df)
    convert_to_proper_type(imputed_df)
    # missing_values = imputed_df.isnull().sum()
    return imputed_df