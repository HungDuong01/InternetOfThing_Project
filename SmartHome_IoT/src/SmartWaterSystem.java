import java.time.Duration;
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
    private Duration duration;

    public SmartWaterSystem(Integer deviceID, boolean status) {
	super(deviceID, status);
	// TODO Auto-generated constructor stub
    }

    public void setDuration(Duration duration) {
	this.duration = duration;
    }

    public Duration getDuration(LocalTime duration) {
	return this.duration;
    }

    @Override
    public String alertMessage() {
	// TODO Auto-generated method stub
	return "";
    }

}
