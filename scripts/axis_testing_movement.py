import time, os
from adafruit_servokit import ServoKit
kit = ServoKit(channels=16)
# Orientating servo testing

for i in range(2):
	kit.servo[1].angle = 40
	kit.servo[0].angle = 20
	os.system("clear")
	print("\n" + " [i] Servo Axis Testing...  [X:40-120] [Y:20-50] [0/4]" + "\n" + " -----------------------------------------------------")
	print("\n" + " [!] Eje X: 40" + "\n" + " [!] Eje Y: 20" + "\n")
	time.sleep(1)
	kit.servo[0].angle = 50
	os.system("clear")
	print("\n" + " [i] Servo Axis Testing...  [X:40-120] [Y:20-50] [1/4]" + "\n" + " -----------------------------------------------------")
	print("\n" + " [!] Eje X: 40" + "\n" + " [!] Eje Y: 50" + "\n")
	time.sleep(1)
	kit.servo[1].angle = 120
	kit.servo[0].angle = 50
	os.system("clear")
	print("\n" + " [i] Servo Axis Testing...  [X:120-40] [Y:50-20] [2/4]" + "\n" + " -----------------------------------------------------")
	print("\n" + " [!] Eje X: 120" + "\n" + " [!] Eje Y: 50" + "\n")
	time.sleep(1)
	kit.servo[0].angle = 20
	os.system("clear")
	print("\n" + " [i] Servo Axis Testing...  [X:120-40] [Y:50-20] [3/4]" + "\n" + " -----------------------------------------------------")
	print("\n" + " [!] Eje X: 120" + "\n" + " [!] Eje Y: 20" + "\n")
	time.sleep(1)
	kit.servo[1].angle = 40
	os.system("clear")
	print("\n" + " [i] Servo Axis Testing...     Finished !!            " + "\n" + " -----------------------------------------------------")
	print("\n" + " [!] Eje X: 40" + "\n" + " [!] Eje Y: 20" + "\n")
	time.sleep(1)

