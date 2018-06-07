#this program is for receiving some image files using iamge list in text file
import subprocess

def receive_image(fileName, address):
    with open(fileName, "r") as file:
        try:
            names = file.readlines()
            for name in names:
				dev_name, date, etc = name.split('-')
				path = "D:\rpi\img_server\" + dev_name + "\" + date
                subprocess.call(["pscp", "-i", "private-key.ppk", "pi@"+address+":/home/pi/"+fileName, path])
            return True
        except Exception as e:
            print(e)
            return False

def send_list(fileName, address):
    try:
        with open(fileName, "w") as file:
            subprocess.call(["pscp", "-i", "private-key.ppk", fileName, "pi@"+address+":/home/pi/smarthome/SmartHome/CAM_RPI"])
            return True
    except Exception as e:
        print(e)
        return False
