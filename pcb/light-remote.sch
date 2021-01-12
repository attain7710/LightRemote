EESchema Schematic File Version 4
EELAYER 30 0
EELAYER END
$Descr A4 11693 8268
encoding utf-8
Sheet 1 1
Title ""
Date ""
Rev ""
Comp ""
Comment1 ""
Comment2 ""
Comment3 ""
Comment4 ""
$EndDescr
$Comp
L Light_Remote_Library:ESP8266 U2
U 1 1 5FFC7CFF
P 5350 3450
F 0 "U2" H 5375 4315 50  0000 C CNN
F 1 "ESP8266" H 5375 4224 50  0000 C CNN
F 2 "Light_Remote:ESP8266_Node" H 5350 3450 50  0001 C CNN
F 3 "" H 5350 3450 50  0001 C CNN
	1    5350 3450
	1    0    0    -1  
$EndComp
$Comp
L Light_Remote_Library:Battery_Pins BT1
U 1 1 5FFC878E
P 3950 5100
F 0 "BT1" H 3983 5515 50  0000 C CNN
F 1 "Battery_Pins" H 3983 5424 50  0000 C CNN
F 2 "Light_Remote:Domino_2_5.05mm" V 3850 5400 50  0001 C CNN
F 3 "" V 3850 5400 50  0001 C CNN
	1    3950 5100
	1    0    0    -1  
$EndComp
$Comp
L Light_Remote_Library:LED D1
U 1 1 5FFC9544
P 7500 3850
F 0 "D1" H 7500 4065 50  0000 C CNN
F 1 "LED" H 7500 3974 50  0000 C CNN
F 2 "Light_Remote:Led" H 7500 4000 50  0001 C CNN
F 3 "" H 7500 4000 50  0001 C CNN
	1    7500 3850
	1    0    0    -1  
$EndComp
$Comp
L Light_Remote_Library:Resistor R1
U 1 1 5FFC9C81
P 6900 3850
F 0 "R1" H 6925 3635 50  0000 C CNN
F 1 "Resistor" H 6925 3726 50  0000 C CNN
F 2 "Light_Remote:Resistor" H 7450 3650 50  0001 C CNN
F 3 "" H 7450 3650 50  0001 C CNN
	1    6900 3850
	-1   0    0    1   
$EndComp
Wire Wire Line
	3500 3350 3600 3350
Wire Wire Line
	3600 3350 3600 5700
Wire Wire Line
	3600 5700 4350 5700
Wire Wire Line
	4350 5700 4350 5200
Wire Wire Line
	4350 5200 4150 5200
Wire Wire Line
	4150 5000 4350 5000
Wire Wire Line
	4350 5000 4350 4150
Wire Wire Line
	4350 3050 3500 3050
Wire Wire Line
	4800 4250 4600 4250
Wire Wire Line
	4600 4250 4600 5200
Wire Wire Line
	4600 5200 4350 5200
Connection ~ 4350 5200
Wire Wire Line
	4800 4150 4350 4150
Connection ~ 4350 4150
Wire Wire Line
	4350 4150 4350 3050
Wire Wire Line
	4000 2450 6200 2450
Wire Wire Line
	6200 2450 6200 3650
Wire Wire Line
	6200 3650 5950 3650
Wire Wire Line
	3850 2300 6400 2300
Wire Wire Line
	6400 2300 6400 3750
Wire Wire Line
	6400 3750 5950 3750
Wire Wire Line
	5950 3850 6600 3850
Wire Wire Line
	7150 3850 7350 3850
Wire Wire Line
	7650 3850 7650 4150
Wire Wire Line
	7650 4150 5950 4150
$Comp
L Light_Remote_Library:Module_Relay U1
U 1 1 5FFDD21E
P 3250 3200
F 0 "U1" H 3283 3615 50  0000 C CNN
F 1 "Module_Relay" H 3283 3524 50  0000 C CNN
F 2 "Light_Remote:Module_Relay" H 4500 2450 50  0001 C CNN
F 3 "" H 4500 2450 50  0001 C CNN
	1    3250 3200
	1    0    0    -1  
$EndComp
Wire Wire Line
	4000 3250 3500 3250
Wire Wire Line
	4000 2450 4000 3250
Wire Wire Line
	3500 3150 3850 3150
Wire Wire Line
	3850 3150 3850 2300
$EndSCHEMATC
