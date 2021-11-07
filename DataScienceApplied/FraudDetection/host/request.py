import requests
import numpy as np

url = 'http://localhost:5000/api'

input_arr = []
with open('input.txt') as f:
    input_arr = f.readlines()
word_one = input_arr[1][1]
content = input_arr[1].split(", ")
content[4] = float(content[4].rstrip() )

r = requests.post(url,json={'data':content})
print(r.json())