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

	System.out.println("Request received from : " + client + "\nMessage content: " + receivedStr);

// --- PERFORM THERMOSTAT USE CASES BASED ON THE RECEIVED MESSAGE ---	

	if (("thermoIncrease".equals(receivedStr) && serverController.getDeviceStatus(0) == true)
		|| ("thermoDecrease".equals(receivedStr) && serverController.getDeviceStatus(0) == true)) {
	    // ACTIONS TO INCREASE TEMPERATURE
	    serverController.updateTemperature(receivedStr, 1);
	    try {
		if (serverController.getUpdateTemp() < 15 || serverController.getUpdateTemp() > 26) {
		    // SEND BACK THE UPDATED TEMPERATURE
		    sendToAllClients(serverController.getUpdateTemp());
		    sendToAllClients(serverController.getDeviceAlertMessage(0));
		} else
		    sendToAllClients(serverController.getUpdateTemp());

		System.out.println("Thermo updated temperature: " + serverController.getUpdateTemp());
	    } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}

	if ("thermoON".equals(receivedStr)) {
	    // ACTIONS TO TURN ON THERMOSTAT
	    serverController.setDeviceStatus("thermo", true);
	    try {
		// SEND BACK THE LIGHT STATUS
		sendToAllClients(serverController.getDeviceStatus(0));
		System.out.println("Thermo status: " + serverController.getDeviceStatus(0));
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}

	if ("thermoOFF".equals(receivedStr)) {
	    // ACTIONS TO TURNING OFF THERMOSTAT
	    serverController.setDeviceStatus("thermo", false);
	    try {
		// SEND BACK THE LIGHT STATUS
		sendToAllClients(serverController.getDeviceStatus(0));
		System.out.println("Thermo status: " + serverController.getDeviceStatus(0));
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}

	if ("thermoData".equals(receivedStr)) {
	    try {
		// RETURN DATA FROM THE SERVER TO CLIENTS
		sendToAllClients(serverController.getUpdateTemp());
	    } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}

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

	if (receivedStr.contains("0x")) {
	    // ACTIONS TO CHANGE LIGHT COLOR
	    serverController.setLightColor(receivedStr);
	    try {
		sendToAllClients(serverController.getLightColor());
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}

	if ("lightON".equals(receivedStr)) {
	    // ACTIONS FOR TURNING ON LIGHT
	    serverController.setDeviceStatus("light", true);
	    try {
		// SEND BACK THE LIGHT STATUS
		sendToAllClients(serverController.getDeviceStatus(1));
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}

	if ("lightOFF".equals(receivedStr)) {
	    // ACTIONS FOR TURNING OFF LIGHT
	    serverController.setDeviceStatus("light", false);
	    try {
		// SEND BACK THE LIGHT STATUS
		sendToAllClients(serverController.getDeviceStatus(1));
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}

	if ("lightData".equals(receivedStr)) {
	    try {
		// RETURN DATA FROM THE SERVER TO CLIENTS
		sendToAllClients(serverController.getLightColor());
		sendToAllClients(serverController.getLightBrightness());
	    } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}

// --- END ---

// --- PERFORM LOCK USE CASES BASED ON THE RECEIVED MESSAGE ---

// --- END ---

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
