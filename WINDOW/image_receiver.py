#this program is for receiving some image files using iamge list in text file
import subprocess, json
import restful_service

def receive_image(fileName, address):
    with open(fileName, 'r') as file:
        try:
            names = file.readlines()
            for name in names:
                name = name.strip()
                subprocess.call(["pscp", "-i", "private-key.ppk", "pi@"+address+":/home/pi/smarthome/SmartHome/CAM_RPI/"+name, './device1'])
                insert("device1", name)
        except Exception as e:
            print(e)

def send_list(fileName, address):
    with open(fileName, "w") as file:
        print("init")
    subprocess.call(["pscp", "-i", "private-key.ppk", fileName, "pi@"+address+":/home/pi/smarthome/SmartHome/CAM_RPI"])

def insert(dev_name, photo_name):
    photo_path = dev_name + '/' + photo_name
    api_service = restful_service.ApiService("device1", "crimeMode/addImage")
    data = {"master_dev_id":dev_name, "photo_path":photo_path}
    data = json.dumps(data)
    print(data)
    response = api_service.sendRequest(data)
    print(response.text)
