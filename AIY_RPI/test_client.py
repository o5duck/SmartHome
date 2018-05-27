from send_client import SenderClient
from time import sleep

def main():
    mode = str(input("select mode: on, off"))
    client = SenderClient()
    client.startClient()
    sleep(1)
    client.sendMsg(mode)
    sleep(1)
    client.stopClient()


if __name__ == '__main__':
    main()
