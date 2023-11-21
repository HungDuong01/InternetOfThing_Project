
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
import java.util.ArrayList;
import java.util.List;

public class IoTController {
    // List to hold accounts
    // private List<Account> accounts;

    // List to hold smart devices
    private List<SmartDevice> devices;
    private SmartDevice thermo;
    private SmartDevice light;
    private SmartDevice lock;
    private SmartDevice water;
    private SmartDevice camera;
//    thermo = new SmartThermostat(0, false);
//    light = new SmartLight(1, false);
//    lock = new SmartLock(2, false);
//	water = new SmartWaterSystem(3, false);
//	camera = new SmartSecurityCamera(4, false);

    public IoTController() {
	devices = new ArrayList<SmartDevice>();
	thermo = new SmartThermostat(0, false);
	light = new SmartLight(1, false);
	lock = new SmartLock(2, false);
	water = new SmartWaterSystem(3, false);
	camera = new SmartSecurityCamera(4, false);

	devices.add(thermo.deviceID(), thermo);
	devices.add(light.deviceID(), light);
	devices.add(lock.deviceID(), lock);
	devices.add(water.deviceID(), water);
	devices.add(camera.deviceID(), camera);

    }

// --- UPDATE THE STATUS OF DEVICES ---

    public Boolean setDeviceStatus(String device, boolean status) {
	boolean returnStatus = false;
	switch (device) {
	case "thermo":
	    devices.get(0).setDeviceStatus(status);
	    returnStatus = devices.get(0).getDeviceStatus();
	    break;
	case "light":
	    devices.get(1).setDeviceStatus(status);
	    returnStatus = devices.get(1).getDeviceStatus();
	    break;
	case "lock":
	    devices.get(2).setDeviceStatus(status);
	    returnStatus = devices.get(2).getDeviceStatus();
	    break;
	case "water":
	    devices.get(3).setDeviceStatus(status);
	    returnStatus = devices.get(3).getDeviceStatus();
	    break;
	case "camera":
	    devices.get(4).setDeviceStatus(status);
	    returnStatus = devices.get(4).getDeviceStatus();
	    break;
	default:
	    break;
	}
	return returnStatus;
    }

// --- END ---

// --- CALL FUNCTIONS FROM THE SMART THERMOSTAT CLASS ---

    public void increaseTemperature(Integer temperature) {
	((SmartThermostat) devices.get(0)).increase(temperature);
    }

    public void decreaseTemperature(Integer temperature) {
	((SmartThermostat) devices.get(0)).decrease(temperature);
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

    public void increaseLightBrightness(Integer brightness) {
	((SmartLight) devices.get(1)).increaseBrightness(brightness);
    }

    public void decreaseLightBrightness(Integer brightness) {
	((SmartLight) devices.get(1)).decreaseBrightness(brightness);
    }

    public Integer getLightBrightness() {
	return ((SmartLight) devices.get(1)).getBrightness();
    }

// --- END ---

// --- CALL FUNCTIONS FROM THE SMART LOCK CLASS

// --- CALL FUNCTIONS FROM THE SMART WATER SYSTEM CLASS --- 

    public Integer getWaterUsage() {
	return 0;
    }

    public void setWaterLimit(Integer waterLimit) {

    }

// --- END ---

// --- CALL FUNCTION FROM THE USER CLASS ---

    public void setUserInformation(String accountName, String userName, String password, String email) {

    }

// --- END ---

// --- CALL FUNCTION FROM THE ADMIN CLASS ---

// --- END ---

}
