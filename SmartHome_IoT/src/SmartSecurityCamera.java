import java.util.List;

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
    private List<String> videoResource;

    public SmartSecurityCamera(Integer deviceID, boolean status) {
	super(deviceID, status);
	// TODO Auto-generated constructor stub
	// THE VIDEO LIST SHOULD BE INITIALIZED HERE
    }

    // --- SETTER AND GETTER FUNCTIONS TO CHANGE THE CAMERA'S ANGLE ---

    public Integer getAngle() {
	return angle;
    }

    public void setAngle(Integer angle) {
	this.angle = angle;
    }

    // --- END ---

    // --- GETTER FUNCTIONS TO GET VIDEO FOOTAGE ---

    public String getVideoResource(Integer index) {
	return videoResource.get(index);
    }

    // 2 TIME VALUES PASSED FROM THE CLIENT:
    // ASSUME THAT EACH VIDEO IS 1 HOUR LONG
    // START - END = timeDiff
    //

    // --- END ---

    @Override
    public String alertMessage() {
	// TODO Auto-generated method stub
	return "";
    }

}
