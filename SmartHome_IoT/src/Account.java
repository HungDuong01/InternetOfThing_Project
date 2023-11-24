/* ------------------------------------------------------------------------------------
 * SmartDevice.java
 * 
 * Copyright (c) 2023 Venos Tech. All rights reserved
 * 
 * Related Documents: 
 *    Specification Document 
 *    Design Document
 * 
 * File created by Duong Chan Hung on 11/08/2023
 * 
 * Associated Files: IoTController.java (one to many)
 *                   Admin.java (child class)
 *                   User.java (child class) 
 * ------------------------------------------------------------------------------------
 */
public abstract class Account {

    private String userName;
    private String password;

    public Account(String userName, String password) {
	this.userName = userName;
	this.password = password;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    public String getUserName() {
	return this.userName;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getPassword() {
	return this.password;
    }

}
