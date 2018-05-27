#client receiving some image list from server
import socket
import subprocess
import image_receiver

from time import sleep

def main():
    HOST, PORT = "192.168.0.78", 7799
    TEXTFILE = "imageNames2.txt"
    PIADDRESS = "220.66.115.152"

    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as sock:
        sock.connect((HOST, PORT))
        with open(TEXTFILE, 'w') as file:
            try:
                data = (sock.recv(1024)).decode('utf-8')
                while data:
                    print(data)
                    file.write(data)
                    data = (sock.recv(1024)).decode('utf-8')

                if image_receiver.receive_image(TEXTFILE, PIADDRESS):
                    print("image received successfully...")
                    image_receiver.send_list(TEXTFILE, PIADDRESS)
                    
            except Exception as e:
                print(e)

if __name__ == '__main__':
    while True:
        main()
        sleep(60)
