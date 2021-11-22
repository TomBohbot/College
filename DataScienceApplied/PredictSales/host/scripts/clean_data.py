import numpy as np
import pandas as pd

def get_merged_df():
    return pd.read_csv('scripts/data/merged_data.csv')

def transform_date(train_df):
    train_df['Date'] = pd.to_datetime(train_df['Date'])
    train_df['Year'] = train_df['Date'].dt.year
    train_df['Month'] = train_df['Date'].dt.month
    train_df['Day'] = train_df['Date'].dt.day
    train_df = train_df.drop(['Date'], axis=1)
    return train_df

def transformations(df):
    state_holiday_mapping = {'0': None, 0: None}
    df['StateHoliday'].replace(state_holiday_mapping, inplace=True)

def one_hot_encoding(df, feature, nan='dummy'):
  encoding = pd.get_dummies(df[feature])
  df = df.drop(feature, axis = 1)
  df = pd.merge(
    left=df,
    right=encoding,
    left_index=True,
    right_index=True,
  )
  return df

def one_hot_encoding_imputer(df, feature, nan='dummy'):
  df[feature] = df[feature].fillna(nan)
  encoding = pd.get_dummies(df[feature])
  df = df.drop(feature, axis = 1)
  df = pd.merge(
    left=df,
    right=encoding,
    left_index=True,
    right_index=True,
  )
  df = df.drop(nan, axis=1)
  return df


def get_clean_data():
    df = get_merged_df()
    df = transform_date(df)
    df = one_hot_encoding(df, 'StateHoliday')
    df = one_hot_encoding_imputer(df, 'CompetitionOpenSinceMonth')
    df = one_hot_encoding_imputer(df, 'CompetitionOpenSinceYear')
    df = one_hot_encoding_imputer(df, 'Promo2SinceWeek')
    df = one_hot_encoding_imputer(df, 'Promo2SinceYear')
    df = one_hot_encoding_imputer(df, 'PromoInterval')
    assortment = {'a': 'x', 'b':'y', 'c':'z'}
    df['Assortment'].replace(assortment, inplace=True)
    df = one_hot_encoding(df, 'StoreType')
    df = one_hot_encoding(df, 'Assortment')
    return df