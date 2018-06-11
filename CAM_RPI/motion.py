import sys
import numpy as np
import time
from time import sleep
sys.path.append("/home/pi/.virtualenvs/cv/lib/python3.4/site-packages")
import cv2

class MotionDetector:
    def __init__(self, thresh):
        self.thresh = thresh
        self.cam = cv2.VideoCapture(0)
        self.tmp = [None, None, None]
        for n in range(3):
            self.tmp[n] = self.getGrayCameraImage()

    def diffImage(self, i):
        diff0 = cv2.absdiff(i[0], i[1])
        diff1 = cv2.absdiff(i[1], i[2])
        return cv2.bitwise_and(diff0, diff1)

    def getGrayCameraImage(self):
        image = self.cam.read()[1]
        gimage = cv2.cvtColor(image, cv2.COLOR_RGB2GRAY)
        return gimage

    def updateCameraImage(self):
        self.tmp[0] = self.tmp[1]
        self.tmp[1] = self.tmp[2]
        self.tmp[2] = self.getGrayCameraImage()

    def runMotionDetector(self):
        diff = self.diffImage(self.tmp)
        ret, thrimg = cv2.threshold(diff, self.thresh, 1, cv2.THRESH_BINARY)
        count = cv2.countNonZero(thrimg)
        if(count > 1):
            nz = np.nonzero(thrimg)
            cv2.rectangle(diff, (min(nz[1]), min(nz[0])), (max(nz[1]), max(nz[0])), (255,0,0), 2)
            cv2.rectangle(self.tmp[0], (min(nz[1]), min(nz[0])), (max(nz[1]), max(nz[0])), (0,0,255), 2)
            now = time.localtime()
            name = '%04d%02d%02d-%02d%02d%02d' % (now.tm_year, now.tm_mon, now.tm_mday, now.tm_hour, now.tm_min, now.tm_sec)
            name = 'device1-'+name+".jpg"
            cv2.imwrite(name, self.tmp[0])
            with open("imageNames.txt", "a") as file:
                file.write(name+'\n')
        cv2.imshow('Detecting Motion', diff)
        self.updateCameraImage()
