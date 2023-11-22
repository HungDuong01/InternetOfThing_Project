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
 * * Associated file: 
 * ------------------
 * 
 * Contributor Fortune:
 * --------------------

 * ------------------------------------------------------------------------------------
 */

import java.io.IOException;

import javafx.animation.FillTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GUIController {

    // one to one association
    private SmartHomeClient client;

    public void setClient(SmartHomeClient client) {
	// method used to set and connect client class with this GUI

	this.client = client;

    }
    /*
     * ----------------------------------- FIRST
     * PAGE--------------------------------------
     */

    /// -->>>>
    //
    @FXML
    private Pane Firstpagepane;

    @FXML
    void conitinueButtonPressed(ActionEvent event) {
	try {
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("welcomePage.fxml"));

	    loader.setController(this);

	    Parent root = loader.load();

	    // Perform any necessary operations or setup on the controller

	    Scene welcomePageScene = new Scene(root);
	    Stage stage = (Stage) Firstpagepane.getScene().getWindow();
	    stage.setScene(welcomePageScene);
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }
    // ----->>>>

    /*
     * ----------------------------------- WELCOME
     * PAGE--------------------------------------
     */

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

    /*
     * ------------------------------------ LOGIN PAGE
     * ---------------------------------------
     */

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
     * ------------------------------------ MAIN MENU
     * PAGE---------------------------------------
     */

    @FXML
    private GridPane mainMenupane;

    @FXML
    private Label userNameMainMenu;

    @FXML
    void logoutMainMenuPressed(ActionEvent event) {
	try {
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("Firstpage.fxml"));

	    loader.setController(this);

	    Parent root = loader.load();

	    // Perform any necessary operations or setup on the controller

	    Scene FirstpageScene = new Scene(root);
	    Stage stage = (Stage) mainMenupane.getScene().getWindow();
	    stage.setScene(FirstpageScene);
	} catch (IOException e) {
	    e.printStackTrace();
	}

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
	    client.sendToServer("lightData");
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
	    client.sendToServer("thermoData");
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

    /*
     * ----------------------------------- SMART THERMOSTAT PAGE
     * -----------------------
     */

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
	try {
	    client.sendToServer("thermoOFF");
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

    @FXML
    void onButtonPressed(ActionEvent event) {
	temperatureLabel.setVisible(true);
	try {
	    client.sendToServer("thermoON");
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

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
	try {
	    Platform.runLater(() -> temperatureLabel.setText(String.valueOf(msg)));
	} catch (Exception e) {
	    System.out.println("Server sent updated data to all client");
	}
    }

    /* ---------------------- SMART THERMOSTAT PAGE END ----------------------- */

    /* ---------------------- SMART LIGHT PAGE -------------------------------- */

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
    private Button increaseBrightnessButton;

    @FXML
    private Button decreaseBrightnessButton;

    // Shapes in the SDSmartLight Page
    @FXML
    private Polygon fortuneArt1, fortuneArt2, fortuneArt3, fortuneArt4, fortuneArt5, fortuneArt6, fortuneArt7,
	    fortuneArt8, fortuneArt9, fortuneArt10;

    @FXML
    void increaseBrightnessButton(ActionEvent event) {

	client.lightBrightnessIncreaseUpdate();

    }

    @FXML
    void decreaseBrightnessButton(ActionEvent event) {
	client.lightBrightnessDecreaseUpdate();

    }

    @FXML
    void colorPickerValueChanged(ActionEvent event) {
	String colorVal = String.valueOf(colorPicker.getValue());
	//
	// Create an array of the Polygon shapes
	Polygon[] polygons = { fortuneArt1, fortuneArt2, fortuneArt3, fortuneArt4, fortuneArt5, fortuneArt6,
		fortuneArt7, fortuneArt8, fortuneArt9, fortuneArt10 };

	// Duration for the transition
	Duration duration = Duration.seconds(1.0);

	// Calculate the duration for each polygon
	double durationPerPolygon = duration.toMillis() / polygons.length;
	double currentDuration = 0;

	// Apply FillTransition to each polygon with a delay
	for (Polygon polygon : polygons) {
	    FillTransition ft = new FillTransition(duration, polygon);
	    ft.setFromValue((Color) polygon.getFill());
	    ft.setToValue(colorPicker.getValue());
	    ft.setDelay(Duration.millis(currentDuration));
	    ft.play();
	    currentDuration += durationPerPolygon;
	}

	// end of code

	System.out.println(colorVal);
	client.lightColorUpdate(colorVal);

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

    // Display color on polygons
    public void displayColor(String color1) {
	try {
	    Color color = Color.web(color1);
	    Platform.runLater(() -> colorPicker.setValue(color));

	    fortuneArt1.setFill(color);
	    fortuneArt2.setFill(color);
	    fortuneArt3.setFill(color);
	    fortuneArt4.setFill(color);
	    fortuneArt5.setFill(color);
	    fortuneArt6.setFill(color);
	    fortuneArt7.setFill(color);
	    fortuneArt8.setFill(color);
	    fortuneArt9.setFill(color);
	    fortuneArt10.setFill(color);
	} catch (Exception e) {
	    System.out.println("Server sent updated data to all client");
	}
// NEW
    }

    @FXML
    void saveScheduleButton(ActionEvent event) {

    }

    @FXML
    void increaseButtonEntered(MouseEvent event) {
	increaseBrightnessButton
		.setStyle("-fx-text-fill: white; -fx-background-color: black; -fx-border-color: black;");

    }

    @FXML
    void increaseButtonExited(MouseEvent event) {
	increaseBrightnessButton
		.setStyle("-fx-text-fill: black; -fx-background-color: white; -fx-border-color: black;");
    }

    @FXML
    void decreaseButtonEntered(MouseEvent event) {
	decreaseBrightnessButton
		.setStyle("-fx-text-fill: white; -fx-background-color: black; -fx-border-color: black;");

    }

    @FXML
    void decreaseButtonExited(MouseEvent event) {
	decreaseBrightnessButton
		.setStyle("-fx-text-fill: black; -fx-background-color: white; -fx-border-color: black;");

    }

}

// comment
