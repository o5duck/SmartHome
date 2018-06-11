#client receiving some image list from server
import socket
import subprocess
import image_receiver

from time import sleep

def main():
    HOST, PORT = "192.168.0.30", 7799
    TEXTFILE = 'imageNames.txt'

    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as sock:
        sock.connect((HOST, PORT))
        with open(TEXTFILE, 'w') as file:
            try:
                data = (sock.recv(1024)).decode('utf-8')
                while data:
                    print(data)
                    file.write(data)
                    data = (sock.recv(1024)).decode('utf-8')
                    
            except Exception as e:
                print(e)

        image_receiver.receive_image(TEXTFILE, HOST)
        image_receiver.send_list(TEXTFILE, HOST)

if __name__ == '__main__':
    while True:
        main()
        sleep(60)
