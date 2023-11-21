/* ------------------------------------------------------------------------------------
 * IoTController.java


 * 
 * Copyright (c) 2023 Venos Tech. All rights reserved
 * 
 * Related Documents: 
 *    Specification Document 
 *    Design Document
 * 
 * File created by Duong Chan Hung on 11/08/2023
 * 
 * Associated file: 
 * ------------------------------------------------------------------------------------
 */
public class SmartLock extends SmartDevice {

    public SmartLock(Integer deviceID, boolean status) {
	super(deviceID, status);
	// TODO Auto-generated constructor stub
    }

    @Override
    public String alertMessage(String action) {
	// TODO Auto-generated method stub
	return "Intruder alert: There's someone breaking into the house right now!";
    }

}
