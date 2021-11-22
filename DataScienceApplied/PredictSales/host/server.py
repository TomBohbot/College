# Import libraries
import numpy as np
from flask import Flask, request, jsonify, render_template
import pickle
from scripts.clean_data import get_clean_data
from pandas.core import base
from scripts.model import xgb_model

app = Flask(__name__)

# Load the model
model = pickle.load(open('xgb_model.txt','rb'))

@app.route('/')
def index():
  return render_template('index.html')

@app.route('/my-link/')
def my_link():
  return 'Click.'

@app.route('/',methods=['POST'])
def predict():
    print("Began Process")
    df = get_clean_data()
    rmspe = xgb_model(df)
    if rmspe == -1:
        return jsonify("The new model did not meet the baseline score.")
    else:
        return jsonify(f"The new model has a rmspe score of {rmspe}.")

if __name__ == '__main__':
    app.run(port=5000, debug=True)