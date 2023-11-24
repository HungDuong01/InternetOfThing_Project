
/* ------------------------------------------------------------------------------------
 * SmartHomeClient.java
 * 
 * Copyright (c) 2023 Venos Tech. All rights reserved
 * 
 * Related Documents: 
 *    Specification Document 
 *    Design Document
 * 
 * File created by Said Hassan
 * 
 * Associated file: 
 * ------------------------------------------------------------------------------------
 */
import java.io.IOException;

import com.lloseng.ocsf.client.AbstractClient;

import javafx.application.Platform;

/*
LIGHT, LOCK, THERMOSTAT, CAMERA, WATER_SYSTEM, TEMPERATURE_INCREMENT, TEMPERATURE_DECREMENT,
LIGHT_STATUS, LIGHT_BRIGHTNESS_UPDATE, LIGHT_COLOR_UPDATE, WATER_USAGE, WATER_LIMIT, USER_INFO, LOCK_STATUS,
CAMERA_ANGLE

*/

public class SmartHomeClient extends AbstractClient {

    // create instance of GUIController class
    private GUIController controller;

    private int temp;

    // setter and getter method to handle the temperature from server
    public void setTempData(int temp) {
	this.temp = temp;
    }

    public int getTempData() {
	return this.temp;
    }

    // Constructor with the parameters including the controller class
    public SmartHomeClient(String host, int port, GUIController controller) {
	super(host, port);
	this.controller = controller;

    }

    public void connectToServer() {
	try {
	    // OCSF method inherited from AbstractClient Class to open connection to a
	    // server
	    openConnection();
	    System.out.println("Connected to server!");

	} catch (IOException e) {
	    e.printStackTrace();
	    System.err.println("Error - Cannot connect to server!");

	}

    }

    /* --------------------- SMART THERMOSTAT PAGE --------------------- */

    public void temperatureIncrementToServer() {
	// if client is connected to server do the following actions

	try {

	    // Send message to decrease to the server
	    sendToServer("thermoIncrease");
	    // Display message on the console after decrementing the temperature
	    System.out.println("Sent temperature increase of " + getTempData() + " request to server");
	} catch (IOException e) {
	    // Error handle message
	    e.printStackTrace();
	    System.err.println("Error sending temperature decrease");

	}
    }

    public void temperatureDecrementToServer() throws IOException {
	try {

	    // Send message to decrease to the server
	    sendToServer("thermoDecrease");
	    // Display message on the console after decrementing the temperature
	    System.out.println("Sent temperature decrease of " + getTempData() + " request to server");
	} catch (IOException e) {
	    // Error handle message
	    e.printStackTrace();
	    System.err.println("Error sending temperature decrease");

	}
    }

    public void thermostatOnToServer() {
	try {
	    sendToServer("thermoON");
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    public void thermostatOffToServer() {
	try {
	    sendToServer("thermoOFF");
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    /* --------------------------- SMART LIGHT PAGE -------------------------- */

    public void lightStatus() {

    }

    public void lightBrightnessIncreaseUpdate() {
	try {

	    // Send message to increase the brightness to the server
	    sendToServer("lightBrightnessIncrease");
	    // Display message on the console after sending message
	    System.out.println("Sent brightness increase of request to server");
	} catch (IOException e) {
	    // Error handle message
	    e.printStackTrace();
	    System.err.println("Error sending brightness increase");

	}

    }

    public void lightBrightnessDecreaseUpdate() {
	try {

	    // Send message to Decrease the brightness to the server
	    sendToServer("lightBrightnessDecrease");
	    // Display message on the console after sending message
	    System.out.println("Sent brightness decrease of request to server");
	} catch (IOException e) {
	    // Error handle message
	    e.printStackTrace();
	    System.err.println("Error sending brightness decrease");

	}

    }

    public void lightColorUpdate(String color) {

	try {

	    // Send message to increase the brightness to the server
	    sendToServer(color);
	    // Display message on the console after sending message
	    System.out.println("Sent color change request to server");
	} catch (IOException e) {
	    // Error handle message
	    e.printStackTrace();
	    System.err.println("Error sending color change");

	}

    }

    // Send message to Server to Turn OFF
    public void smartLightOnToServer() {
	try {
	    sendToServer("lightON");
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

    // Send message to Server to Turn ON
    public void smartLightOffToServer() {
	try {
	    sendToServer("lightOFF");
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

    /* ---------------------- HANDLE MESSAGES FROM SERVER ----------------------- */

    @Override
    protected void handleMessageFromServer(Object msg) {

	String message = (String) msg;

	String[] parts = message.split(":");

	if (parts.length >= 2) {
	    String device = (parts[0]);

	    String data = parts[1];

	    System.out.println("Received message from: " + device + ", with data: " + data);

	    // Receiving Data for Thermostat
	    if (device.equals("Thermostat")) {
		// Light is OFF
		if (data.equals("false")) {
		    Platform.runLater(() -> controller.setAlertMessagesetTextLightBrightness("Light: OFF"));
		    // Light is ON
		} else if (data.equals("true")) {
		    Platform.runLater(() -> controller.setAlertMessagesetTextLightBrightness("Light: ON"));
		    // Light is not in range of 15 to 26 degrees
		} else if (data.contains("Please")) {
		    Platform.runLater(() -> controller.setAlertMessageThermostat(data));
		    // Temperature data is sent from server. Example: 25
		} else {
		    Platform.runLater(() -> controller.setTextTemperature(data));
		    Platform.runLater(() -> controller.setAlertMessagesetTextLightBrightness(""));

		}

	    }

	    // Receiving Data for Smart Light
	    else if (device.equals("Light")) {
		if (data.contains("0x")) {
		    Platform.runLater(() -> controller.displayColor(data));

		} else if (data.contains("")) {

		} else {
		    Platform.runLater(() -> controller.setTextLightBrightness(data));
		}

	    }
	    // Receiving Data for Smart Water System
	    else if (device.equals("Lock")) {

	    }

	    // Receiving Data for Smart Lock
	    else if (device.equals("Water")) {

	    }

	    // Receiving Data for Smart Security Camera
	    else if (device.equals("Camera")) {

	    }

	}

//	if (msg instanceof Integer) {
//	    // handle temperature from server and set it.
//	    setTempData((int) msg);
//	    // Proper Asynchronous Execution. Set the label text on thermostat GUI
//	    Platform.runLater(() -> controller.setTextTemperature(getTempData()));
//	}
//	
//	// Display Thermostat Alert Message
//	if (msg instanceof String) {
//	    String receivedmsg = (String) msg;
//	    
//	    if(receivedmsg.contains("Please")) {
//	    	
//	    	Platform.runLater(() -> controller.setAlertMessageThermostat(receivedmsg));
//	    }
//	    
//	    // Display Color in Smart Light Page
//	    System.out.println("Received message: " + receivedmsg);
//
//	    Platform.runLater(() -> controller.displayColor(receivedmsg));
//	}

    }
}