/* ------------------------------------------------------------------------------------
 * SmartLight.java
 * 
 * Copyright (c) 2023 Venos Tech. All rights reserved
 * 
 * Related Documents: 
 *    Specification Document 
 *    Design Document
 * 
 * File created by Said Hassan on 11/08/2023
 * 
 * Associated file: 
 * ------------------------------------------------------------------------------------
 */

//hassan

import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class GUIController {

    // one to one association
    private SmartHomeClient client;

    public void setClient(SmartHomeClient client) {
	// method used to set and connect client class with this GUI
    	
	this.client = client;
    }

    
    
    
    /*
     * ----------------------------------- WELCOME PAGE-------------------------------------- */

    @FXML
    private GridPane welcomePane;

    @FXML
    void adminButtonPressed(ActionEvent event) {

    }

    @FXML
    void userButtonPressed(ActionEvent event) {
	try {
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("userLoginPage.fxml"));

	    loader.setController(this);

	    Parent root = loader.load();

	    // Perform any necessary operations or setup on the controller

	    Scene mainMenuScene = new Scene(root);
	    Stage stage = (Stage) welcomePane.getScene().getWindow();
	    stage.setScene(mainMenuScene);
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

    /* ------------------------------------ LOGIN PAGE --------------------------------------- */

    @FXML
    private TextField emailTextField;

    @FXML
    private GridPane loginPane;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    void loginButtonPressed(ActionEvent event) {
	try {
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("mainMenuPage.fxml"));

	    loader.setController(this);

	    Parent root = loader.load();

	    // Perform any necessary operations or setup on the controller

	    Scene mainMenuScene = new Scene(root);
	    Stage stage = (Stage) loginPane.getScene().getWindow();
	    stage.setScene(mainMenuScene);
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

    @FXML
    void signupButtonPressed(ActionEvent event) {

    }
    
    @FXML
    void backButtonPressed(ActionEvent event) {

    	try {
    	    FXMLLoader loader = new FXMLLoader(getClass().getResource("welcomePage.fxml"));

    	    loader.setController(this);

    	    Parent root = loader.load();

    	    // Perform any necessary operations or setup on the controller

    	    Scene mainMenuScene = new Scene(root);
    	    Stage stage = (Stage) loginPane.getScene().getWindow();
    	    stage.setScene(mainMenuScene);
    	} catch (IOException e) {
    	    e.printStackTrace();
    	}
    }

    /*
     * ------------------------------------ MAIN MENU PAGE--------------------------------------- */

    @FXML
    private GridPane mainMenupane;

    @FXML
    private Label userNameMainMenu;

    @FXML
    void logoutMainMenuPressed(ActionEvent event) {

    }

    @FXML
    void mainMenuDropDown(ActionEvent event) {

    }

    @FXML
    void smartCameraButtonpressed(ActionEvent event) {
    	try {
    	    FXMLLoader loader = new FXMLLoader(getClass().getResource("SDThermostat.fxml"));

    	    loader.setController(this);

    	    Parent root = loader.load();

    	    // Perform any necessary operations or setup on the controller

    	    Scene cameraScene = new Scene(root);
    	    Stage stage = (Stage) mainMenupane.getScene().getWindow();
    	    stage.setScene(cameraScene);
    	    client.sendToServer("Camera");
    	    // SEND MSG TO SERVER TO RETRIEVE LIGHT DATA
    	} catch (IOException e) {
    	    e.printStackTrace();
    	

    	}
    }

    @FXML
    void smartLightButtonpressed(ActionEvent event) {
	try {
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("SDSmartLight.fxml"));

	    loader.setController(this);

	    Parent root = loader.load();

	    // Perform any necessary operations or setup on the controller

	    Scene lightScene = new Scene(root);
	    Stage stage = (Stage) mainMenupane.getScene().getWindow();
	    stage.setScene(lightScene);
	    client.sendToServer("Light");
	    // SEND MSG TO SERVER TO RETRIEVE LIGHT DATA
	} catch (IOException e) {
	    e.printStackTrace();
		}

    }

    @FXML
    void smartLockButtonpressed(ActionEvent event) {
    	try {
    	    FXMLLoader loader = new FXMLLoader(getClass().getResource("SmartLock.fxml"));

    	    loader.setController(this);

    	    Parent root = loader.load();

    	    // Perform any necessary operations or setup on the controller

    	    Scene lockScene = new Scene(root);
    	    Stage stage = (Stage) mainMenupane.getScene().getWindow();
    	    stage.setScene(lockScene);
    	    client.sendToServer("Lock");
    	    // SEND MSG TO SERVER TO RETRIEVE LIGHT DATA
    	} catch (IOException e) {
    	    e.printStackTrace();
    	

    	}
    }

    @FXML
    void smartThermostatButtonPressed(ActionEvent event) {
	try {
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("SDThermostat.fxml"));

	    loader.setController(this);

	    Parent root = loader.load();

	    // Perform any necessary operations or setup on the controller

	    Scene thermostatScene = new Scene(root);
	    Stage stage = (Stage) userNameMainMenu.getScene().getWindow();
	    stage.setScene(thermostatScene);
	    client.sendToServer("Thermo");
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

    @FXML
    void waterSystemButtonPressed(ActionEvent event) {
    	try {
    	    FXMLLoader loader = new FXMLLoader(getClass().getResource("IrrigationSystem.fxml"));

    	    loader.setController(this);

    	    Parent root = loader.load();

    	    // Perform any necessary operations or setup on the controller

    	    Scene waterScene = new Scene(root);
    	    Stage stage = (Stage) mainMenupane.getScene().getWindow();
    	    stage.setScene(waterScene);
    	    client.sendToServer("Water");
    	    // SEND MSG TO SERVER TO RETRIEVE LIGHT DATA
    	} catch (IOException e) {
    	    e.printStackTrace();
    	

    	}
    }
    /*------------------------------- MAIN MENU PAGE END --------------------------------- */

    /* ----------------------------------- SMART THERMOSTAT PAGE ----------------------- */

    @FXML
    private TextArea historyMessageBox;

    @FXML
    private Label temperatureLabel;

    @FXML
    void decreaseTemperatureButton(ActionEvent event) throws IOException {
	if (client != null) {
	    // Use method in client class to send the data
	    client.temperatureDecrement();
	    // Decrement the value inside the GUI after sending the data

	    // Show the data on the GUI
	    // setTextTemperature(temp);

	} else {
	    System.err.println("Error - SmartHomeClient not set in the controller");
		}

    }

    @FXML
    void increaseTemperatureButton(ActionEvent event) {
	if (client != null) {
	    // Use method in client class to send the data
	    client.temperatureIncrement();
	    // Decrement the value inside the GUI after sending the data

	    // Show the data on the GUI

	    temperatureLabel.setText(String.valueOf(client.getTempData()));

	} else {
	    System.err.println("Error - SmartHomeClient not set in the controller");
	}

    }

    @FXML
    void offButtonPressed(ActionEvent event) {
	temperatureLabel.setVisible(false);
	
	

    }

    @FXML
    void onButtonPressed(ActionEvent event) {
	temperatureLabel.setVisible(true);
	
	
    }

    @FXML
    void returnButtonPressed(ActionEvent event) {
	try {
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("mainMenuPage.fxml"));

	    loader.setController(this);

	    Parent root = loader.load();

	    // Perform any necessary operations or setup on the controller

	    Scene mainMenuScene = new Scene(root);
	    Stage stage = (Stage) historyMessageBox.getScene().getWindow();
	    stage.setScene(mainMenuScene);
	} catch (IOException e) {
	    e.printStackTrace();
		}

    }

    public void setTextTemperature(int msg) {
	Platform.runLater(() -> temperatureLabel.setText(String.valueOf(msg)));
    }

    /* ---------------------- SMART THERMOSTAT PAGE END ----------------------- */

    /* ---------------------- SMART LIGHT PAGE -------------------------------- */

    @FXML
    private Slider brightnessSlider;

    @FXML
    private Label brightnessLabel;

    @FXML
    private TextArea colorNotification;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private MenuButton fromScheduleTime;

    @FXML
    private MenuButton toScheduleTime;

    @FXML
    void brightnessSliderChanged(MouseEvent event) {

    	brightnessSlider.setValue(50);
    	
    	brightnessSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
    		
        brightnessLabel.setText(String.format("%.0f", newValue.doubleValue()));
             
    	 });

    }

    @FXML
    void colorPickerValueChanged(ActionEvent event) {

    }

    @FXML
    void offSmartLightButton(ActionEvent event) {

    }

    @FXML
    void onSmartLightButton(ActionEvent event) {

    }

    @FXML
    void returnSmartLightButton(ActionEvent event) {
	try {
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("mainMenuPage.fxml"));

	    loader.setController(this);

	    Parent root = loader.load();

	    // Perform any necessary operations or setup on the controller

	    Scene mainMenuScene = new Scene(root);
	    Stage stage = (Stage) colorPicker.getScene().getWindow();
	    stage.setScene(mainMenuScene);
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

    @FXML
    void saveScheduleButton(ActionEvent event) {

    }

}
