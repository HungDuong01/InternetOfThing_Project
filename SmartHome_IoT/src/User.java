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
 *                   Account (parent class)
 * ------------------------------------------------------------------------------------
 */
public class User extends Account {

    public User(String email, String password) {
	super(email, password);
    }

}
