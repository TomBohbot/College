import unittest
import pandas as pd
import Parse_Csv as parser
import numpy as np

class TestParser(unittest.TestCase):

    def test_parser(n):
        content = np.array([
            ['Id','Sepal Length (CM)','Sepal Width (CM)','Petal Length (CM)','Petal Width (CM)','Species'], 
            [1,5.1,3.5,1.4,0.2,'setosa']
        ])
        df = parser.parse_data('data/Test_Iris.csv')
        assert np.array_equal(df.columns.to_numpy(), content[0])
        assert np.array_equal(df.to_numpy()[0], content[1])

    def test_pd_dataset(n):
        df_one = pd.read_csv('data/Iris.csv')
        df_two = parser.parse_data('data/Iris.csv')
        assert np.array_equal(df_one.to_numpy().astype(np.str), df_two.to_numpy())
        assert np.array_equal(df_one.columns.to_numpy(), df_two.columns.to_numpy())

if __name__ == '__main__':
    unittest.main()