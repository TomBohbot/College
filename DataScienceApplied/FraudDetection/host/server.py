# Import libraries
import numpy as np
from flask import Flask, request, jsonify
import pickle
import format_data

app = Flask(__name__)

# Load the model
model = pickle.load(open('xgb_model.txt','rb'))

@app.route('/api',methods=['POST'])
def predict():
    # Get the data from the POST request.
    data = request.get_json(force=True)

    # Transform the data to match model's training data:
    df = format_data.get_df(data['data'])

    # Make prediction using xgb model
    prediction = model.predict_proba(df)
    return jsonify(f"There is a {str(round(100*prediction[0][1], 2) )} percent chance of fraud:")

if __name__ == '__main__':
    app.run(port=5000, debug=True)