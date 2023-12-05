# InternetOfThing_Project

## Introduction
Welcome to the Smart Home IoT Project! Our goal is to create an intuitive, user-friendly smart home system leveraging IoT technology. 
This project integrates various smart devices and sensors to automate home functions and provide a centralized control system accessible via a mobile or web application.

## Features
1. Automated Lighting: Control lights based on occupancy or time of day.
2. Temperature Control: Smart thermostat integration for efficient heating and cooling.
3. Security Monitoring: Integration with security cameras, and smart lock alongside alert notifications.
4. Water usage: Integration with irrigation systems.

## Usage

To run the application please follow these steps:

1. Configure the project build path and add these libraries:
  <br> +) javafx.base.jar <br>
  <br> +) javafx.controls.jar <br>
  <br> +) javafx.fxml.jar <br>
  <br> +) javafx.graphics.jar <br>
  <br> +) javafx.media.jar <br>
  <br> +) javafx.swing.jar <br>
  <br> +) javax.web.jar <br>
  <br> +) javafx-swt.jar <br>
  <br> +) mysql-connector-j-8.2.0.jar ("library to connect MySql to java program") <br>
   
2. Check your network IP address and port number.

3. Open the IoTServer.java file on the computer terminal. Run the IoTServer.java file by using these commands: "javac IoTServer.java", followed by "java IoTServer.java".

4. On the client side, access the GUI.java file to change the Host's IP address and port number.

5. After updating the IP and port number of the host. Please go to the run configuration of the GUI.java file. Make sure to have the VM arguments as follow:
   "--module-path /"your javafx libraries path" --add-modules javafx.controls,javafx.fxml,javafx.media"

6. To use the "Log in" and "Sign Up" functions, please create a small MySQL database on your computer. Here is a link about how to create MySql database from terminal:
   "https://blog.devart.com/mysql-command-line-client.html"

7. In the MySql part, please create 2 tables with attributes as follow:
 <br> 7.1 CREATE TABLE User(
   FirstName VARCHAR(100),
   LastName VARCHAR(100),
   Email VARCHAR(50),
   Password VARCHAR(50) ); <br>
<br>  7.2 CREATE TABLE Admin(
   FirstName VARCHAR(100),
   LastName VARCHAR(100),
   Email VARCHAR(50),
   Password VARCHAR(50) ); <br>

       




