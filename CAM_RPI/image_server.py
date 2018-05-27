#server sending image file list to Windows
import socketserver
from time import sleep

class TCPSocketHandler(socketserver.BaseRequestHandler):
    def handle(self):
        sock = self.request
        with open('imageNames.txt', 'r') as file:
            try:
                names=file.readlines()
                for name in names:
                    print(name)
                    sock.send((name.encode('utf-8')))
            except Exception as exc:
                print(exc)

def main():
    HOST, PORT = "192.168.0.78", 7799
    while True:
        server = socketserver.TCPServer((HOST, PORT), TCPSocketHandler)
        server.serve_forever()
        sleep(60)

if __name__ == '__main__':
    main()
