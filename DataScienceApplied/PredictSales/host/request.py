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
one_week = 60*60*24*7 # calculate one week in seconds.. 60 = 60 seconds in 1 min , 60 = 60 min in 1 hour, 24 = 24 hours in 1 day, 7 = 7 days in 1 week
url = 'http://localhost:5000/'
while True:
    r = requests.post(url,json={'data':None})
    print(r.json())
    # time.sleep(one_week)
    time.sleep(5)
    print("NEW REQUEST")