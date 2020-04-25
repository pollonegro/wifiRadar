# wifiRadar      BETA
"Directional wifi antenna servo controlled on a raspberry pi3 B+"

The device scans all the wifi targets within reach, saves the best orientation for each one of them and launch airgeddon, wifite2 (auto scripts) or perform a manual wifi audit.

The control of the device is done through ssh connected by ethernet, although it would be possible to have the ssh service through wifi, using the raspberry´s integrated one.



Beta concept:

![](https://github.com/pollonegro/wifiRadar/blob/master/img/wifiRadarv2.gif)

Manual Control.py:

![](https://github.com/pollonegro/wifiRadar/blob/master/img/manual_control.png)

Android App - wifiRadar.apk:

![](https://github.com/pollonegro/wifiRadar/blob/master/img/wifiRadarAPK_1.png)

wifiRadar.apk SSH Connection:

![](https://github.com/pollonegro/wifiRadar/blob/master/img/wifiRadarSSH_Logcat_1.png)

wifiRadar.apk DASHBOARD and control pad:

![](https://github.com/pollonegro/wifiRadar/blob/master/img/wifiRadarSCAN_Wifis_1.png)

Construction plain:

![](https://github.com/pollonegro/wifiRadar/blob/master/img/plain.png)


Scripts videos:
---------------
axis_testing_movement.py - Just check servo mobility.


![](https://github.com/pollonegro/wifiRadar/blob/master/img/axis-testing-movement.gif)


10x_servo_testing.py - Check fast movement grades by 10x home made scale...


![](https://github.com/pollonegro/wifiRadar/blob/master/img/10x-servo-testing.gif)


TODO:
-----
 - 4G GSM implementation module.
 - Batteries and solar charging...


Material needed:
----------------------------------

1 - Raspberry Pi 3B+ (Kali Linux 128GB SD card) - 35,30 € + 22,70 € = 58 €

  https://www.amazon.es/gp/product/B07BFH96M3/ref=ppx_yo_dt_b_asin_title_o02_s00?ie=UTF8&psc=1
  
  https://www.amazon.es/Samsung-EVO-Plus-Tarjeta-Adaptador/dp/B06XFHQGB9/ref=sr_1_5?__mk_es_ES=%C3%85M%C3%85%C5%BD%C3%95%C3%91&crid=6TQCMBWYB3BZ&keywords=128gb+micro+sd&qid=1577723191&s=computers&sprefix=128gb%2Ccomputers%2C162&sr=1-5
  
  
2 - Directional antenna 
  Approx APPUSB26DB - Adaptador Wireless USB (26 dBi, 2000 MW, 150 Mbps) - 37,91 €
  
  https://www.amazon.es/gp/product/B00VGJ4PUU/ref=ppx_od_dt_b_asin_title_s00?ie=UTF8&psc=1
  
  
3 - Adafruit 16-Channel PWM/Servo HAT for Raspberry Pi - 21,85 €

  https://www.adafruit.com/product/2327
  
  https://www.amazon.es/gp/product/B00SI1SPHS/ref=ppx_yo_dt_b_search_asin_title?ie=UTF8&psc=1
  
  
4 - Servo engines MG995 x 2 - 33,90 €

  https://www.amazon.es/gp/product/B01LXWRWWV/ref=ppx_yo_dt_b_search_asin_title?ie=UTF8&psc=1
  
  
5 - Power Supply Adapter (for Pi Servo HAT) - 8,50 €

  https://www.amazon.es/gp/product/B07DX11WJJ/ref=ppx_yo_dt_b_asin_title_o05_s00?ie=UTF8&psc=1



-------------------------------------------------------------------------------------------------
Total: 160,16 € 
-------------------------------------------------------------------------------------------------
7 - Eth cable and SSH conection

6 - TIME !!!

  
  
  
