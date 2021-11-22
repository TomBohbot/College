import pandas as pd

df_store = pd.read_csv('store.csv')
df_train = pd.read_csv('train.csv')

df = df_train.merge(df_store, left_on='Store', right_on='Store')

print(len(df.columns ))
df = df.drop(['Customers'], axis=1)

print(len(df.columns ))

df.to_csv("merged_data.csv", index=False)