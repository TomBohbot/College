import requests
import numpy as np
import time
# from crontab import CronTab

# days = 7
# cron = CronTab()
# url = 'http://localhost:5000/'
# task = requests.post(url, json={'data':None})
# task.hour.every(24*days)

min_to_sleep = 10
url = 'http://localhost:5000/'
while True:
    r = requests.post(url,json={'data':None})
    print(r.json())
    time.sleep(5)
    print("NEW REQUEST")