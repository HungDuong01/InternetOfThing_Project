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

    private String email;
    private String password;

    public Account(String email, String password) {
	this.email = email;
	this.password = password;
    }

    public void setUserName(String email) {
	this.email = email;
    }

    public String getUserName() {
	return this.email;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getPassword() {
	return this.password;
    }

}
