#if you want to use "pydispatch", you have to execute this command
#sudo pip3 install python-dispatch (demanded: pip3 10-x-x)

import aiy.audio
import aiy.cloudspeech
import aiy.voicehat
from time import sleep
import subprocess
from send_client import SenderClient

def runSmartHome(recognizer, led):
	subprocess.call(["python3", "main_server.py"])
    while True:
        print("Waiting for your choice...[smart_home]")
        led.set_state(aiy.voicehat.LED.ON)
        text = recognizer.recognize()
        if not text:
            print('Sorry, I did not hear you.[smart_home]')
            led.set_state(aiy.voicehat.LED.OFF)
            sleep(1)
        else:
            print('You said "', text, '"[smart_home]')
            if 'outside mode' in text:
                led.set_state(aiy.voicehat.LED.BLINK)
                sleep(1.5)
            elif 'crime mode' in text:
                led.set_state(aiy.voicehat.LED.BLINK)
                sleep(1.5)
                client = SenderClient()
                client.startClient()
                sleep(1)
                client.sendMsg('on')
                sleep(1)
                client.stopClient()
            elif 'sleeping mode' in text:
                led.set_state(aiy.voicehat.LED.BLINK)
                sleep(1.5)
            elif 'goodbye' in text:
                led.set_state(aiy.voicehat.LED.OFF)
                sleep(1)
                return
            else:
                print('Sorry, please tell me one more time.[smart_home]')
                led.set_state(aiy.voicehat.LED.OFF)
                sleep(1)

def main():
    recognizer = aiy.cloudspeech.get_recognizer()
    #recognizer에 인식할 수 있는 구문 추가
    recognizer.expect_phrase('Smart Home')
    recognizer.expect_phrase('OK, Yonam')

    recognizer.expect_phrase('outside mode')
    recognizer.expect_phrase('crime mode')
    recognizer.expect_phrase('sleeping mode')
    recognizer.expect_phrase('goodbye')
    #직접 rpi의 gpio control원할 시, voicehat수정
    #voicehat에 등록되어있는 gpio control불러오기
    button = aiy.voicehat.get_button()
    led = aiy.voicehat.get_led()
    #recorder시작(녹음 준비)
    aiy.audio.get_recorder().start()

    while True:
        print("Waiting for your order...")
        led.set_state(aiy.voicehat.LED.ON)
        text = recognizer.recognize()
        if not text:
            print('Sorry, I did not hear you.')
            led.set_state(aiy.voicehat.LED.OFF)
            sleep(1)
        else:
            print('You said "', text, '"')
            if 'Smart Home' in text:
                led.set_state(aiy.voicehat.LED.BLINK)
                sleep(1.5)
                runSmartHome(recognizer, led)
            elif 'OK, Yonam' in text:
                led.set_state(aiy.voicehat.LED.BLINK)
                sleep(1.5)
            elif 'goodbye' in text:
                led.set_state(aiy.voicehat.LED.OFF)
                sleep(1)
                break
            else:
                print('Sorry, please tell me one more time.')
                led.set_state(aiy.voicehat.LED.OFF)
                sleep(1)

if __name__ == '__main__':
    main()
