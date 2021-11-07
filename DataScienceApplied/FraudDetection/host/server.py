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
    default = [
        2987016,86620,30,"H",1790,555,150,"visa",226,"debit",170,87,0,0,"aol.com","aol.com",1,1,0,0,0,1,0,1,0,1,1,0,1,1,0,0,0,0,0,0,0,26,0,0,0,0,0,0,0,"a","a","a","a","a","a","a","a","a",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6,18,140,0,0,0,0,1805,49,64,0,0,0,0,0,0,15622.99023,169755.7969,0,0,0,515,5155,2840,0,0,0,1,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7460,0,0,1,0,0,0,0,0,100,"NotFound","NotFound",-300,"Found","Found","Found",15,529,575,0,0,0,"na",0,0,0,"Found","Found","Mac OS X 10_11_6","chrome 62.0",24,"1280x800","match_status:2","T","F","T","T","desktop","MacOS","na"
    ]

    default = []
    for i in range(433):
        default.append(25)

    # Get the data from the POST request.
    data = request.get_json(force=True)
    data = data['data']
    print(data)

    device_info = {None: 0, 'mobile': 1, 'desktop': 2}
    prod_cd = {None: 0, 'C': 1, 'W': 2, 'H': 3, 'S': 4, 'R': 5}
    card_4 = {'discover': 0, 'visa': 1, 'mastercard': 2, 'american express': 3}
    card_6 = {'charge': 0, 'credit': 1, 'debit': 2}
    default[432] = device_info.get(data[0].lower() )
    default[3] = device_info.get(data[1].lower() )
    default[7] = device_info.get(data[2].lower() )
    default[9] = device_info.get(data[3].lower() )
    default[2] = device_info.get(data[4] )

    # Transform the data to match model's training data:
    df = format_data.get_df(default)

    # Make prediction using xgb model
    prediction = model.predict_proba(df)
    return jsonify(f"There is a {str(round(100*prediction[0][1], 2) )} percent chance of fraud:")

if __name__ == '__main__':
    app.run(port=5000, debug=True)