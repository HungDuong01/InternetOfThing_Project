/* ------------------------------------------------------------------------------------
 * IoTServer.java

 * 
 * Copyright (c) 2023 Venos Tech. All rights reserved
 * 
 * Related Documents: 
 *    Specification Document 
 *    Design Document
 * 
 * File created by Duong Chan Hung on 11/08/2023
 * 
 * Associated Files: IoTController.java (one to one)
 * ------------------------------------------------------------------------------------
 */

/* Strings used:
	LIGHT, LOCK, THERMOSTAT, CAMERA, WATER_SYSTEM, TEMPERATURE_INCREMENT, TEMPERATURE_DECREMENT,
	LIGHT_STATUS, LIGHT_BRIGHTNESS_UPDATE, LIGHT_COLOR_UPDATE, WATER_USAGE, WATER_LIMIT, USER_INFO, LOCK_STATUS,
	CAMERA_ANGLE
	
*/

import java.util.ArrayList;
import java.util.List;

import com.lloseng.ocsf.server.AbstractServer;
import com.lloseng.ocsf.server.ConnectionToClient;

public class IoTServer extends AbstractServer {

    // A reference to the IoTController class
    private IoTController serverController; // Use the controller to access the function from
    // smart devices

    private enum DeviceType {

	THERMOSTAT, LIGHT, LOCK, WATER, CAMERA
    }

    private List<ConnectionToClient> client;

    public IoTServer(int port) {
	super(port);
	this.client = new ArrayList<ConnectionToClient>();
	this.serverController = new IoTController();

    }

    @Override
    protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
	// TODO Auto-generated method stub
	String receivedStr = (String) msg;

	System.out.println("Message received: " + receivedStr + " from " + client);

// --- PERFORM THERMOSTAT USE CASES BASED ON THE RECEIVED MESSAGE ---	

	if ("thermoIncrease".equals(receivedStr)) {
	    // ACTIONS TO INCREASE TEMPERATURE
	    serverController.increaseTemperature(1);
	    try {
		// SEND BACK THE UPDATED TEMPERATURE
		sendToAllClients(serverController.getUpdateTemp());

	    } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}

	if ("thermoDecrease".equals(receivedStr)) {
	    // ACTIONS TO DECREASE TEMPERATURE
	    serverController.decreaseTemperature(1);
	    try {
		// SEND BACK THE UPDATED TEMPERATURE
		sendToAllClients(serverController.getUpdateTemp());

	    } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}

	if ("thermostatON".equals(receivedStr)) {
	    // ACTIONS TO TURN ON THERMOSTAT
	    try {
		// SEND BACK THE LIGHT STATUS
		sendToAllClients(serverController.setDeviceStatus("thermo", true));
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}

	if ("thermostatOFF".equals(receivedStr)) {
	    // ACTIONS TO TURNING OFF THERMOSTAT
	    try {
		// SEND BACK THE LIGHT STATUS
		sendToAllClients(serverController.setDeviceStatus("thermo", false));
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
	// Chan Hung//

// --- END ---	

// --- PERFORM LIGHT USE CASES BASED ON THE RECEIVED MESSAGE ---

	if ("lightBrightnessIncrease".equals(receivedStr)) {
	    // ACTIONS TO SET THE LIGHT BRIGHTNESS
	    serverController.increaseLightBrightness(10);
	    try {
		serverController.getLightBrightness();
	    } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}

	if ("lightBrightnessDecrease".equals(receivedStr)) {
	    // Actions for decrease the temperature
	    serverController.decreaseLightBrightness(10);
	    try {
		serverController.getLightBrightness();
	    } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}

	if ("lightON".equals(receivedStr)) {
	    // ACTIONS FOR TURNING ON LIGHT
	    try {
		// SEND BACK THE LIGHT STATUS
		sendToAllClients(serverController.setDeviceStatus("light", true));
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}

	if ("lightOFF".equals(receivedStr)) {
	    // ACTIONS FOR TURNING OFF LIGHT
	    try {
		// SEND BACK THE LIGHT STATUS
		sendToAllClients(serverController.setDeviceStatus("light", false));
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}

// --- END ---

// --- PERFORM LOCK USE CASES BASED ON THE RECEIVED MESSAGE ---

// --- END ---

	if ("thermoData".equals(receivedStr))

	{
	    try {
		sendToAllClients(serverController.getUpdateTemp());
	    } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}

    }

    // If the message indicates to increase the temperature of the thermostat

    public static void main(String[] args) {
	int port = 2010;
	IoTServer server = new IoTServer(port);
	try {
	    server.listen();
	    System.out.println("Server is listening");
	} catch (Exception e) {
	    System.out.println("ERROR - Could not listen for clients! ");
	}

    }

}
