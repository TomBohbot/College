import requests
import numpy as np

url = 'http://localhost:5000/api'

# fill default values per class discussion:

# data = [
#     2987016,86620,30,"H",1790,555,150,"visa",226,"debit",170,87,0,0,"aol.com","aol.com",1,1,0,0,0,1,0,1,0,1,1,0,1,1,0,0,0,0,0,0,0,26,0,0,0,0,0,0,0,"a","a","a","a","a","a","a","a","a",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6,18,140,0,0,0,0,1805,49,64,0,0,0,0,0,0,15622.99023,169755.7969,0,0,0,515,5155,2840,0,0,0,1,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7460,0,0,1,0,0,0,0,0,100,"NotFound","NotFound",-300,"Found","Found","Found",15,529,575,0,0,0,"na",0,0,0,"Found","Found","Mac OS X 10_11_6","chrome 62.0",24,"1280x800","match_status:2","T","F","T","T","desktop","MacOS","na"
# ]

# data = []
# for i in range(433):
#     data.append(i)

# take in a few custom specs from user:
input_arr = []
with open('input.txt') as f:
    input_arr = f.readlines()
word_one = input_arr[1][1]
content = input_arr[1].split(", ")
content[4] = float(content[4].rstrip() )

# # add to data:
# data[432] = device_info.get(content[0].lower() )
# data[3] = device_info.get(content[1].lower() )
# data[7] = device_info.get(content[2].lower() )
# data[9] = device_info.get(content[3].lower() )
# data[2] = device_info.get(content[4].lower() )


r = requests.post(url,json={'data':content})
print(r.json())