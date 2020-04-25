import time, os
from adafruit_servokit import ServoKit
kit = ServoKit(channels=16)
# file for test servo control over android ssh

for i in range(2):
	kit.servo[0].angle = 90
	time.sleep(0.5)
	kit.servo[0].angle = 120
	time.sleep(0.5)
	kit.servo[0].angle = 90


