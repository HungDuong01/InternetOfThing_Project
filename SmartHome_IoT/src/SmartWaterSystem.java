import java.time.LocalTime;

/* ------------------------------------------------------------------------------------
 * SmartWaterSystem.java
 * 
 * Copyright (c) 2023 Venos Tech. All rights reserved
 * 
 * Related Documents: 
 *    Specification Document 
 *    Design Document
 * 
 * File created by Duong Chan Hung on 11/08/2023
 * 
 * Associated file
 * ------------------------------------------------------------------------------------
 */
public class SmartWaterSystem extends SmartDevice {

    private LocalTime duration;

    public SmartWaterSystem(Integer deviceID, boolean status) {
	super(deviceID, status);
	// TODO Auto-generated constructor stub
    }

    public void setDuration(LocalTime duration) {
	this.duration = duration;
    }

    public void getDuration(LocalTime duration) {
	this.duration = duration;
    }

    @Override
    public String alertMessage() {
	// TODO Auto-generated method stub
	return "";
    }

}
