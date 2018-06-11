import requests, json

METHOD_GET = 'get'
METHOD_POST = 'post'
METHOD_PUT = 'put'
METHOD_DELETE = 'delete'

class ApiService:
	def __init__(self, device_name, service_url):
		self.API_SERVER_URL = "http://220.66.115.153:8080/smarthome"
		self.SERVICE_URL = self.API_SERVER_URL + service_url
		self.device_name = device_name

	def sendRequest(self, body="", request_method=METHOD_POST):
		response = None
		headers = {"Content-Type":"application/json"}

		if request_method == METHOD_GET:
			response = requests.get(self.SERVICE_URL, headers=headers)
		elif request_method == METHOD_POST:
			response = requests.post(self.SERVICE_URL, headers=headers, data=body)
		elif request_method == METHOD_PUT:
			response = requests.put(self.SERVICE_URL, headers=headers, data=body)
		elif request_method == METHOD_DELETE:
			response = requests.delete(self.SERVICE_URL, headers=headers, data=body)
		return response
