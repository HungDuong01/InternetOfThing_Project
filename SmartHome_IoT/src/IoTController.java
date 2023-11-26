
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
 * Associated file: IoTServer.java (one to one) 
 *                  SmartDevice.java (many to one) 
 * ------------------------------------------------------------------------------------
 */
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class IoTController {
    // List to hold accounts
//    private List<Account> accounts;

    // List to hold smart devices
    private List<SmartDevice> devices;
    private SmartDevice thermo;
    private SmartDevice light;
    private SmartDevice lock;
    private SmartDevice water;
    private SmartDevice camera;

//    private Account admin;
//    private Account user;

    private LocalTime scheduleTime;

    public IoTController() {
	// --- INITIALIZE SMART DEVICES ---
	devices = new ArrayList<SmartDevice>();
	thermo = new SmartThermostat(0, true);
	light = new SmartLight(1, true);
	lock = new SmartLock(2, true);
	water = new SmartWaterSystem(3, true);
	camera = new SmartSecurityCamera(4, true);

	devices.add(thermo.deviceID(), thermo);
	devices.add(light.deviceID(), light);
	devices.add(lock.deviceID(), lock);
	devices.add(water.deviceID(), water);
	devices.add(camera.deviceID(), camera);
	// --- END ---

	// --- INITIALIZE ACCOUNTS ---
	// accounts = new ArrayList<Account>();
	// --- END ---

    }

// --- FUNCTIONS TO UPDATE AND RETURN THE STATUS OF DEVICES ---

    public void setDeviceStatus(String device, boolean status) {
	switch (device) {
	case "thermo":
	    devices.get(0).setDeviceStatus(status);
	    break;
	case "light":
	    devices.get(1).setDeviceStatus(status);
	    break;
	case "lock":
	    devices.get(2).setDeviceStatus(status);
	    break;
	case "water":
	    devices.get(3).setDeviceStatus(status);
	    break;
	case "camera":
	    devices.get(4).setDeviceStatus(status);
	    break;
	default:
	    break;
	}
    }

    public Boolean getDeviceStatus(int device) {
	Boolean tempStatus = null;
	for (int i = 0; i < devices.size(); i++) {
	    if (devices.get(i).deviceID() == device) {
		tempStatus = devices.get(i).getDeviceStatus();
	    }
	}
	return tempStatus;
    }

// --- END ---

// --- FUNCTION TO RETURN SMARTDEVICE ALERT MESSAGE ---

    public String getDeviceAlertMessage(int device) {
	String tempStr = null;
	for (int i = 0; i < devices.size(); i++) {
	    if (devices.get(i).deviceID() == device) {
		tempStr = devices.get(i).alertMessage();
	    }
	}
	return tempStr;
    }

// --- END ---

// --- CALL FUNCTIONS FROM THE SMART THERMOSTAT CLASS ---

    public void updateTemperature(String msg, Integer temperature) {
	if ("thermoIncrease".equals(msg)) {
	    ((SmartThermostat) devices.get(0)).increase(temperature);
	}
	if ("thermoDecrease".equals(msg)) {
	    ((SmartThermostat) devices.get(0)).decrease(temperature);
	}
    }

    public Integer getUpdateTemp() {
	return ((SmartThermostat) devices.get(0)).getTemperature();
    }

// --- END ---

// --- CALL FUNCTIONS FROM THE SMART LIGHT CLASS ---

    public void setLightColor(String color) {
	((SmartLight) devices.get(1)).setColor(color);
    }

    public String getLightColor() {
	return ((SmartLight) devices.get(1)).getColor();
    }

    public void updateBrightness(String msg, Integer brightness) {
	if ("lightBrightnessIncrease".equals(msg)) {
	    ((SmartLight) devices.get(1)).increaseBrightness(brightness);
	}
	if ("lightBrightnessDecrease".equals(msg)) {
	    ((SmartLight) devices.get(1)).decreaseBrightness(brightness);
	}
    }

    public Integer getUpdateBrightness() {
	return ((SmartLight) devices.get(1)).getBrightness();
    }

// --- END ---

// --- CALL FUNCTIONS FROM THE SMART LOCK CLASS

    public void setLockPassword(String newPassword) {
	((SmartLock) devices.get(2)).setLockPassword(newPassword);
    }

    public String getLockPassword() {
	return ((SmartLock) devices.get(2)).getLockPassword();
    }

// --- END ---

// --- CALL FUNCTIONS FROM THE SMART WATER SYSTEM CLASS --- 

    public Integer getWaterUsage() {
	return 0;
    }

    public void setWaterLimit(Integer waterLimit) {

    }

    public void decodeTimeValue(String timeAsStr) {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
	scheduleTime = LocalTime.parse(timeAsStr, formatter);
	// ((SmartWaterSystem) devices.get(3)).setScheduleTime(scheduleTime);
    }

// --- END ---

// --- CALL FUNCTION FROM THE ACCOUNT CLASS ---
    public void setUserInformation(String userName, String password) {

    }

// --- END ---

// --- CALL FUNCTION FROM THE ADMIN CLASS ---

// --- END ---

}

// Comment by said.
