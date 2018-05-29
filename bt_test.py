import serial
from time import sleep

bluetoothSerial = serial.Serial("/dev/rfcomm0", baudrate=9600)
count = None
while count == None:
    try:
        count = int(raw_input("Please enter the number of times to blink the Light"))
    except:
        pass

bluetoothSerial.write(str(count))
print (bluetoothSerial.readline())
