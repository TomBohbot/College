import csv
import pandas as pd
import numpy as np

def parse_data(data):
    parsed_data = []
    column_name = False
    with open(data, 'r') as file:
        parser = csv.reader(file, delimiter=',')
        for row in parser:
            if row[0] == 'Id':
                column_names = row
                continue
            try: 
                index = int(row[0])
                first_elem = float(row[1])
                second_elem = float(row[2])
                third_elem = float(row[3])
                fourth_elem = float(row[4])
                fifth_elem = row[5] in ['setosa', 'versicolor', 'virginica']
                if index >= 0 and first_elem >= 0 and second_elem >= 0 and third_elem >= 0 and fourth_elem >= 0 and fifth_elem == True:
                    parsed_data.append(row)
            except:
                # print(f"ERROR: Row {row[0]}")
                continue
    np_parsed_data = np.array(parsed_data)
    df = pd.DataFrame(np_parsed_data)
    if column_names != False:
        df.columns = column_names
    return df