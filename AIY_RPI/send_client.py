#client sending a data about variable mode(crime, outing, sleep) or status(on, off)
import socket

HOST, PORT = '192.168.0.78', 7799

class SenderClient:
    def __init__(self):
        self.socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

    def startClient(self):
        self.socket.connect((HOST, PORT))
        self.sendMsg("sender")
        
    def stopClient(self):
        self.sendMsg("exit")
        self.socket.close()

    #send only one message to other client(ex: ON, OFF)
    def sendMsg(self, msg):
        try:
            self.socket.send(msg.encode('utf-8'))
            return 0
        except Exception as e:
            print(e)
            return -1
