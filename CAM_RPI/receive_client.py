#client receiving a data about variable mode(crime, outing, sleep) or status(on, off)
import socket
import threading
from motion import MotionDetector
from time import sleep

MODE = 'off'

def rcvMsg(sock):
    global MODE
    while True:
        try:
            data = sock.recv(1024)
            if not data:
                break;
            MODE = data.decode('utf-8')
            print(MODE)
        except:
            pass

def main():
    HOST, PORT = 'host ip,,', 7799
    motionDetector=MotionDetector(32)
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as sock:
        sock.connect((HOST, PORT))
        sock.send("receiver".encode('utf-8'))
        thread = threading.Thread(target=rcvMsg, args=(sock,))
        thread.daemon = True
        thread.start()

        while True:
            if MODE == 'on':
                motionDetector.runMotionDetector()
            else:
                continue


if __name__ == '__main__':
    main()
