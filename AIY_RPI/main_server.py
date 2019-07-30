#server informing that crime mode is turned on or turned off
import socketserver
import threading

class ClientManager:
    lock = threading.Lock()

    def __init__(self):
        self.clients = {}

    def addClient(self, clientName, conn, addr):
        self.lock.acquire()
        self.clients[clientName] = (conn, addr)
        self.lock.release()
        print("%s is added to client list.." %clientName)
        return clientName

    def removeClient(self, clientName):
        if clientName not in self.clients:
            return

        self.lock.acquire()
        del self.clients[clientName]
        self.lock.release()
        print("%s is removed from client list..." %clientName)

    def sendMessage(self, msg):
        for conn, addr in self.clients.values():
            conn.send(msg.encode('utf-8'))

    def messageHandler(self, clientName, msg):
        if msg.strip() == 'exit':
            self.removeClient(clientName)
            return -1
        else:
            self.sendMessage(msg)
            return 0

class TCPSocketHandler(socketserver.BaseRequestHandler):
    clientManager = ClientManager()

    def handle(self):
        sock = self.request
        print("%s is connected" %self.client_address[0])
        try:
            clientName = self.registerClientName()
            msg = sock.recv(1024)
            while msg:
                msg = msg.decode('utf-8')
                print(msg)
                if self.clientManager.messageHandler(clientName, msg) == -1:
                    sock.close()
                    break
                msg = sock.recv(1024)
        except Exception as e:
            print(e)


    def registerClientName(self):
        sock = self.request
        while True:
            clientName = sock.recv(1024)
            clientName = clientName.decode('utf-8').strip()
            if self.clientManager.addClient(clientName, sock, self.client_address):
                return clientName

class MainServer(socketserver.ThreadingMixIn, socketserver.TCPServer):
    pass

def main():
    HOST, PORT = "host ip", 7799
    try:
        server = MainServer((HOST, PORT), TCPSocketHandler)
        server.serve_forever()
    except Exception as e:
        print(e)

if __name__ == '__main__':
    main()
