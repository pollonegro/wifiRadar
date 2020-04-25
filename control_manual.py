import threading
from tkinter import * 
from pynput import keyboard
import time, os
from adafruit_servokit import ServoKit
kit = ServoKit(channels=16)

global latitude, longitude, listvar, grade

latitude = 90
longitude = 90
listvar = [longitude, latitude]

kit.servo[0].angle = longitude
kit.servo[1].angle = latitude
grade = 3


def on_press(key):
	#print(' [-] Tecla liberada: ' + str(key))
	if str(key) == "Key.left":
		print(" [<] Moviendo izquierda...")
		print(listvar[1])
		if listvar[1]<170:
			kit.servo[1].angle = listvar[1]
			time.sleep(0.45)
			listvar[1] = listvar[1]+grade
		else:
			print("Max range raised")


	elif str(key) == "Key.right":
		print(" [>] Moviendo derecha...")
		print(listvar[1])
		if listvar[1]>10:
			kit.servo[1].angle = listvar[1]
			time.sleep(0.45)
			listvar[1] = listvar[1]-grade
		else:
			print("Max range raised")


	elif str(key) == "Key.down":
		print(" [v] Moviendo abajo...")
		if listvar[0]>10:
			kit.servo[0].angle = listvar[0]
			time.sleep(0.45)
			listvar[0] = listvar[0]-grade
		else:
			print("Max range raised")


	elif str(key) == "Key.up":
		print(" [^] Moviendo arriba...")
		if listvar[0]<170:
			kit.servo[0].angle = listvar[0]
			time.sleep(0.45)
			listvar[0] = listvar[0]+grade
		else:
			print("Max range raised")


	elif str(key) == "Key.esc": # keyboard.Key.esc:
		print(" Saliendo...")
		#time.sleep(2)
		sys.exit()
		hilo1.stop()
		hilo2.stop()
		return False

	else:
		pass

        
def on_release(key):
	try:
		os.system("clear")
		print(' [+] Tecla presionada: ' + str(key))

	except AttributeError:
		print(' [!] Error al presionar: ' + str(key))


def escuchar():
    with keyboard.Listener(on_press=on_press, on_release=on_release) as listener:
        listener.join()
 
def ventana():
    master = Tk()
    w = Label(master, text="\n" + "\n" + "\n" + "\n" + "Control manual de la torreta, debes pulsar las teclas de direccion con esta ventana activa." + "\n" + "\n" + "\n" + "\n")
    w.pack()
    mainloop()
 
hilo1 = threading.Thread(target = ventana)
hilo2 = threading.Thread(target = escuchar)
 
hilo1.start()
hilo2.start()