import requests
import json

# GET
res = requests.get('http://127.0.0.1:5000')
print(str(res.status_code) + " | " + res.text)

# POST
headers = {'Content-Type': 'application/json; chearset=utf-8'}
data = {"cctvID":"aaaa","cctvUrl":"http://www.naver.com"}
res = requests.post('http://3.238.53.109:8080/cctv/save', data=json.dumps(data), headers=headers)
print(str(res.status_code) + " | " + res.text)