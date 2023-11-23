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

    private LocalTime startTime;
    private LocalTime endTime;

    public SmartWaterSystem(Integer deviceID, boolean status) {
	super(deviceID, status);
	// TODO Auto-generated constructor stub
    }

    public void setStartTime(LocalTime startTime) {
	this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
	this.endTime = endTime;
    }

    @Override
    public String alertMessage() {
	// TODO Auto-generated method stub
	return "";
    }

}
