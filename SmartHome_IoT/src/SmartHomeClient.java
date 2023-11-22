
/* ------------------------------------------------------------------------------------
 * SmartLight.java
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
	this.temp = temp + 1;

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

    /* ---------------------- SMART THERMOSTAT PAGE --------------------------- */

    public void temperatureIncrement() {
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

    public void temperatureDecrement() throws IOException {
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

    /* --------------------------- SMART LIGHT PAGE -------------------------- */

    public void lightStatus() {

    }

    public void lightBrightnessIncreaseUpdate() {
	try {

	    // Send message to increase the brightness to the server
	    sendToServer("lightDecrease");
	    // Display message on the console after sending message
	    System.out.println("Sent brightness decrease of request to server");
	} catch (IOException e) {
	    // Error handle message
	    e.printStackTrace();
	    System.err.println("Error sending brightness decrease");

	}

    }

    public void lightBrightnessDecreaseUpdate() {

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

    /* ---------------------- HANDLE MESSAGES FROM SERVER ----------------------- */
    @Override
    protected void handleMessageFromServer(Object msg) {

	if (msg instanceof Integer) {
	    // handle temperature from server and set it.
	    setTempData((int) msg);
	    // Proper Asynchronous Execution. Set the label text on thermostat GUI
	    Platform.runLater(() -> controller.setTextTemperature(getTempData()));
	}
	// Display message on console
	System.out.println("Received message: " + getTempData());

	if (msg instanceof String) {
	    String receivedColor = (String) msg;
	    System.out.println("Received message: " + receivedColor);

	    Platform.runLater(() -> controller.displayColor(receivedColor));

	}

    }

}
