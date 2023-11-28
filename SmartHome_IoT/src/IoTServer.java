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

/* Strings used to compare with the message received from the clients:
 * 1. thermoData, thermoIncrease, thermoDecrease, thermoON, thermoOFF.
 * 2. lightData, lightBrightnessIncrease, lightBrightnessDecrease, hex - decimal value.
 * 3. lockData, lockON, lockOFF, lock
*/

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.lloseng.ocsf.server.AbstractServer;
import com.lloseng.ocsf.server.ConnectionToClient;

public class IoTServer extends AbstractServer {

    // A reference to the IoTController class
    private IoTController serverController; // Use the controller to access the function from

    private List<ConnectionToClient> client;

    public IoTServer(int port) {
	super(port);
	this.client = new ArrayList<ConnectionToClient>();
	this.serverController = new IoTController();

    }

    public long calculateDelay(LocalTime targetTime) {
	LocalDateTime now = LocalDateTime.now();
	LocalDateTime targetDateTime = now.with(targetTime);

	// If targetDateTime has already passed, add a day's duration
	if (targetDateTime.isBefore(now)) {
	    targetDateTime = targetDateTime.plusDays(1);
	}

	Duration duration = Duration.between(now, targetDateTime);
	return duration.toMillis();
    }

    public void startTimer(LocalTime targetTime) {
	long delay = calculateDelay(targetTime);

	Timer timer = new Timer();
	TimerTask task = new TimerTask() {
	    @Override
	    public void run() {
		System.out.println("Timer task executed at " + LocalTime.now());
		sendToAllClients("Water:" + serverController.getDeviceAlertMessage(3));
	    }
	};

	timer.schedule(task, delay);
    }

    @Override
    protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
	// TODO Auto-generated method stub
	String receivedMsg = (String) msg;
	String updateTempStr, updateThermoStatusStr, updateBrightnessStr, updateLightStatusStr, updateLightColorStr,
		updateLockStatusStr, updateLockPass, updateWaterLimit, updateWaterTimer, updateCameraAngle;

	System.out.println("\nRequest received from client: " + client + "\nMessage content: " + receivedMsg);

	// --- PERFORM THERMOSTAT USE CASES BASED ON THE RECEIVED MESSAGE ---

	// 1. ONLY ALLOW CLIENT TO INCREASE TEMPERATURE IF THE CURRENT BRIGHTNESS IS
	// LESS THAN 35
	// 2. ONLY ALLOW CLIENT TO DECREASE TEMPERATURE IF THE CURRENT BRIGHTNESS IS
	// GREATER THAN 15
	if (("thermoIncrease".equals(receivedMsg) && serverController.getDeviceStatus(0) == true
		&& serverController.getUpdateTemp() < 35)
		|| ("thermoDecrease".equals(receivedMsg) && serverController.getDeviceStatus(0) == true
			&& (serverController.getUpdateTemp() > 15))) {
	    // ACTIONS TO INCREASE TEMPERATURE

	    serverController.updateTemperature(receivedMsg, 1); // Call function to update the temperature
	    updateTempStr = serverController.getUpdateTemp().toString();

	    try {
		// If the temperature below 15 or above 26 => send back alert message
		if (serverController.getUpdateTemp() <= 17 || serverController.getUpdateTemp() >= 30) {
		    // SEND BACK THE UPDATED TEMPERATURE
		    sendToAllClients("Thermostat:" + updateTempStr);
		    sendToAllClients("Thermostat:" + serverController.getDeviceAlertMessage(0));
		} else
		    sendToAllClients("Thermostat:" + updateTempStr);
		System.out.println("Thermo updated temperature: " + updateTempStr);
	    } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }

	}

	if ("thermoON".equals(receivedMsg)) {
	    // ACTIONS TO TURN ON THERMOSTAT
	    serverController.setDeviceStatus("thermo", true); // Call function to change the thermostat status
	    try {
		// SEND BACK THE LIGHT STATUS
		updateThermoStatusStr = serverController.getDeviceStatus(0).toString();
		sendToAllClients("Thermostat:" + updateThermoStatusStr);
		System.out.println("Thermo status: " + serverController.getDeviceStatus(0));
	    } catch (Exception e) {

		e.printStackTrace();
	    }
	}

	if ("thermoOFF".equals(receivedMsg)) {
	    // ACTIONS TO TURNING OFF THERMOSTAT
	    serverController.setDeviceStatus("thermo", false); // Call function to change the thermostat status
	    try {
		// SEND BACK THE LIGHT STATUS
		updateThermoStatusStr = serverController.getDeviceStatus(0).toString();
		sendToAllClients("Thermostat:" + updateThermoStatusStr);
		System.out.println("Thermo status: " + serverController.getDeviceStatus(0));
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}

	if ("Thermo".equals(receivedMsg)) {
	    try {
		// RETURN DATA FROM THE SERVER TO CLIENTS
		updateThermoStatusStr = serverController.getDeviceStatus(0).toString();
		updateTempStr = serverController.getUpdateTemp().toString();
		sendToAllClients("Thermostat:" + updateTempStr);
		sendToAllClients("Thermostat:" + updateThermoStatusStr);
		System.out.println("Thermostat current temperature: " + updateTempStr);
		System.out.println("Thermostat current status: " + updateThermoStatusStr);
	    } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}

	// --- END ---

	// --- PERFORM LIGHT USE CASES BASED ON THE RECEIVED MESSAGE ---

	// 1. ONLY ALLOW CLIENT TO INCREASE BRIGHTNESS IF THE CURRENT BRIGHTNESS IS LESS
	// THAN 100
	// 2. ONLY ALLOW CLIENT TO DECREASE BRIGHTNESS IF THE CURRENT BRIGHTNESS IS
	// GREATER THAN 0
	if (("lightBrightnessIncrease".equals(receivedMsg) && serverController.getDeviceStatus(1) == true
		&& serverController.getUpdateBrightness() < 100)
		|| ("lightBrightnessDecrease".equals(receivedMsg) && serverController.getDeviceStatus(1) == true
			&& serverController.getUpdateBrightness() > 0)) {
	    // ACTIONS TO SET THE LIGHT BRIGHTNESS

	    serverController.updateBrightness(receivedMsg, 10);

	    try {
		// SEND BACK THE UPDATED BRIGHTNESS

		updateBrightnessStr = serverController.getUpdateBrightness().toString();
		sendToAllClients("Light:" + updateBrightnessStr);
		System.out.println("Light updated brightness: " + serverController.getUpdateBrightness());

	    } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }

	}

	// IF THE MESSAGE IS THE VALUE OF COLOR
	if (receivedMsg.contains("0x"))

	{
	    // ACTIONS TO CHANGE LIGHT COLOR
	    serverController.setLightColor(receivedMsg);
	    try {
		updateLightColorStr = serverController.getLightColor().toString();
		sendToAllClients("Light:" + updateLightColorStr);
		System.out.println("Light updated color: " + serverController.getLightColor());
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}

	if ("lightON".equals(receivedMsg)) {
	    // ACTIONS FOR TURNING ON LIGHT
	    serverController.setDeviceStatus("light", true);
	    try {
		// SEND BACK THE LIGHT STATUS
		updateLightStatusStr = serverController.getDeviceStatus(1).toString();
		sendToAllClients("Light:" + updateLightStatusStr);
		System.out.println("Light updated status: " + serverController.getDeviceStatus(1));
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}

	if ("lightOFF".equals(receivedMsg)) {
	    // ACTIONS FOR TURNING OFF LIGHT
	    serverController.setDeviceStatus("light", false);
	    try {
		// SEND BACK THE LIGHT STATUS
		updateLightStatusStr = serverController.getDeviceStatus(1).toString();
		sendToAllClients("Light:" + updateLightStatusStr);
		System.out.println("Light updated status: " + serverController.getDeviceStatus(1));
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}

	if ("Light".equals(receivedMsg)) {
	    try {
		// RETURN DATA FROM THE SERVER TO CLIENTS
		updateBrightnessStr = serverController.getUpdateBrightness().toString();
		updateLightStatusStr = serverController.getDeviceStatus(1).toString();
		updateLightColorStr = serverController.getLightColor().toString();
		sendToAllClients("Light:" + updateLightStatusStr);
		sendToAllClients("Light:" + updateLightColorStr);
		sendToAllClients("Light:" + updateBrightnessStr);
		System.out.println("Light current status: " + updateLightStatusStr);
		System.out.println("Light current color: " + updateLightColorStr);
		System.out.println("Light current brightness: " + updateBrightnessStr);

	    } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}

	// --- END ---

	// --- PERFORM LOCK USE CASES BASED ON THE RECEIVED MESSAGE ---

	if (receivedMsg.startsWith("Lock")) {
	    // RETURN DATA OF THE LOCK TO CLIENTS
	    updateLockStatusStr = serverController.getDeviceStatus(2).toString();
	    updateLockPass = serverController.getLockPassword();

	    try {
		sendToAllClients(updateLockStatusStr);
		sendToAllClients(updateLockPass);
		System.out.println("Lock current status: " + updateLockStatusStr);
		System.out.println("Lock current password: " + updateLockPass);
	    } catch (Exception e) {
		e.printStackTrace();
	    }

	}

	// IF THE MESSAGE CONTAINS A COMMAND WITH A DATA
	if (receivedMsg.startsWith("lockpass")) {
	    String[] part = receivedMsg.split(",");
	    String command = (part[0]);
	    String data = part[1];
	    try {
		// SEND BACK THE LOCK STATUS
		serverController.setLockPassword(data);
		System.out.println("Lock updated password: " + serverController.getLockPassword());
		sendToAllClients("Lock:New password");
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}

	if (receivedMsg.startsWith("lockON")) {
	    serverController.setDeviceStatus("lock", true);
	    try {
		// SEND BACK THE LOCK STATUS
		updateLockStatusStr = serverController.getDeviceStatus(2).toString();
		sendToAllClients("Lock:" + updateLockStatusStr);
		System.out.println("Lock updated status: Locked");
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
	// IF THE MESSAGE CONTAINS A COMMAND WITH A DATA
	if (receivedMsg.startsWith("lockOFF")) {
	    String[] part = receivedMsg.split(",");
	    String command = (part[0]);
	    String data = part[1];

	    if (data.equals(serverController.getLockPassword())) {
		serverController.setDeviceStatus("lock", false);
		try {
		    // SEND BACK THE LOCK STATUS
		    updateLockStatusStr = serverController.getDeviceStatus(2).toString();
		    sendToAllClients("Lock:" + updateLockStatusStr);
		    System.out.println("Lock updated status: Unlocked");
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    } else {
		sendToAllClients("Lock:" + serverController.getDeviceAlertMessage(2));
	    }
	}

	// --- END ---

	// --- PERFORM WATER SYSTEM USE CASES BASED ON THE RECEIVED MESSAGE ---

	if (receivedMsg.startsWith("Water")) {
	    String[] part = receivedMsg.split(",");
	    String data = part[1];
	    serverController.setWaterTimer(data);
	    startTimer(serverController.getWaterTimer());

	}

	if (receivedMsg.startsWith("waterLimit")) {
	    String[] part = receivedMsg.split(",");
	    String data = part[1];
	    Integer limit = Integer.parseInt(data);
	    serverController.setWaterLimit(limit);
	    updateWaterLimit = serverController.getWaterLimit().toString();

	    try {
		sendToAllClients("Water:New water limit has been set " + updateWaterLimit);
		System.out.println("Water updated limit: " + updateWaterLimit); // Testing
	    } catch (Exception e) {
		e.printStackTrace();
	    }

	}

	// --- END ---

	// --- PERFORM CAMERA USE CASES BASED ON THE RECEIVED MESSAGE ---
	/*
	 * 1. Change camera angle 2. Return camera video back to client, INPUT from user
	 * will be an integer
	 */

	if (receivedMsg.startsWith("Camera")) {
	    String[] part = receivedMsg.split(",");
	    String data = part[1];
	    Integer angle = Integer.parseInt(data);
	    serverController.setCameraAngle(angle);
	    updateCameraAngle = serverController.getCameraAngle().toString();
	    try {
		sendToAllClients("Camera:New camera angle has been set " + updateCameraAngle);
		System.out.println("Camera updated angle: " + updateCameraAngle); // Testing
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}

	// --- END ---

    }

    @Override
    protected void clientConnected(ConnectionToClient client) {
	// DISPLAY THE CONNECTED CLIENT
	System.out.println("Client connected: " + client);

    }

    public static void main(String[] args) {
	int port = 2090;
	IoTServer server = new IoTServer(port);
	try {
	    server.listen();
	    System.out.println("Server is listening");
	} catch (Exception e) {
	    System.out.println("ERROR - Could not listen for clients! ");
	}

    }

}
