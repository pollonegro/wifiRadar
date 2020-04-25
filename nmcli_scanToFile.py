#!/usr/bin/env python3
# -*- coding: utf-8 -*-
import os, re, sys
import time
import nmcli

os.system("nmcli -f SSID,SIGNAL,BSSID dev wifi > redes.txt")
