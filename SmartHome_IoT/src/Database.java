
/* ------------------------------------------------------------------------------------
 * IoTController.java

 * 
 * Copyright (c) 2023 Venos Tech. All rights reserved
 * 
 * Related Documents: 
 *    Specification Document 
 *    Design Document
 * 
 * File created by Duong Chan Hung on 11/25/2023
 * 
 * Associated file: IoTServer.java (one to one) 
 *                  SmartDevice.java (many to one) 
 * ------------------------------------------------------------------------------------
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static final String URL = "jdbc:mysql://localhost:3306/userDB";
    private static final String USER = "yourUsername";
    private static final String PASSWORD = "yourPassword";

    public static Connection getConnection() {
	try {
	    return DriverManager.getConnection(URL, USER, PASSWORD);
	} catch (SQLException e) {
	    throw new RuntimeException("Error connecting to the database", e);
	}
    }

    public static void main(String[] args) {
	// TODO Auto-generated method stub

    }

}
