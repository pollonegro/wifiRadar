import time, os
from adafruit_servokit import ServoKit
kit = ServoKit(channels=16)
# Orientating servo testing

for i in range(40, 120, 5):			# eje horizontal - empieza en 40º hasta º120
	kit.servo[1].angle = i
	time.sleep(0.2)
	for j in range(20, 50, 5):		# eje vertical - empieza en 20º hasta º50
		kit.servo[0].angle = j
		os.system("clear")
		print("\n" + " [i] Servo Movement Testing...  [X:40-120] [Y:20-50] [5x] [1/2]" + "\n" + " --------------------------------------------------------------")		# Cabecera
		print("\n" + " [!] Eje X: " + str(i) + "\n" + " [!] Eje Y: " + str(j) + "\n")				# Info sobre ejes	
		time.sleep(0.2)

for i in range(120, 40, -5):			
	kit.servo[1].angle = i
	time.sleep(0.2)
	for j in range(50, 20, -5):		
		kit.servo[0].angle = j
		os.system("clear")
		print("\n" + " [i] Servo Movement Testing...  [X:120-40] [Y:50-20] [5x] [2/2]" + "\n" + " --------------------------------------------------------------")		# Cabecera
		print("\n" + " [!] Eje X: " + str(i) + "\n" + " [!] Eje Y: " + str(j) + "\n")				# Info sobre ejes
		time.sleep(0.2)