/* ------------------------------------------------------------------------------------
 * SmartSecurityCamera.java
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
public class SmartSecurityCamera extends SmartDevice {
    private Integer angle;

    public SmartSecurityCamera(Integer deviceID, boolean status) {
	super(deviceID, status);
	// TODO Auto-generated constructor stub
    }

// --- SETTER AND GETTER FUNCTIONS FOR CHANGING THE CAMERA'S ANGLE --- 
    public Integer getAngle() {
	return angle;
    }

    public void setAngle(Integer angle) {
	this.angle = angle;
    }
// --- END ---

    @Override
    public String alertMessage() {
	// TODO Auto-generated method stub
	return "Intruder alert: There's someone breaking into the house right now!";
    }

}
