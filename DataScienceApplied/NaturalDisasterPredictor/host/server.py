# Import libraries
import pickle
import pandas as pd
from model import predict_tweet
from flask import Flask, request, jsonify, render_template

app = Flask(__name__)

@app.route('/')
def index():
  return render_template('index.html')

@app.route('/',methods=['POST'])
def predict():
    print("Began Protocol:")
    # Get the data from the POST request.
    text = request.form["tweet"]
    # Create DataFrame:
    data = {'text': [text]}
    df = pd.DataFrame(data)
    prediction = str(round(predict_tweet(text=text)*100, 2))
    return jsonify(f"There is a {prediction} percent chance that your tweet is about a natural disaster:")


if __name__ == '__main__':
    app.run(port=5000, debug=True)