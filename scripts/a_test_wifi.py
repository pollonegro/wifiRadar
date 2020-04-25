#!/usr/bin/env python3
# -*- coding: utf-8 -*-
import os, re, sys
import time
import nmcli
import subprocess
from termcolor import colored
from adafruit_servokit import ServoKit # Disabled when Adafruit hat is not present
kit = ServoKit(channels=16) # Disabled when Adafruit hat is not present

# Listing detected wireless interfaces 
os.system("clear")
print(colored('\n' + '[+] Listing Wireless Interfaces... ', 'yellow') + '\n')
os.system("ifconfig | grep 'wlan'")

print(colored('\n' + '**********************************', 'yellow'))
option = ""

# Scan wifis and store in a file
print(colored('[+] Scanning Networks... ', 'yellow'))
time.sleep(0.5)
os.system("nmcli -f SSID,SIGNAL dev wifi > redes.txt")
time.sleep(0.5)

# Read file, feed "lines" and close file
text_file = open("redes.txt", "r")
lines = text_file.readlines()
text_file.close()

print(colored('[+] Networks detected:', 'green'))
os.system("cat redes.txt")