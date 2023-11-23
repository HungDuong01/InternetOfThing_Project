/*
 * Author: Fortune Ogulewe
 * Company: Venos Tech
 * 
 * Description:
 * This Java code is part of a software application developed by Fortune Ogulewe
 * for Venos Tech. It includes functionality for IOT device system 
 * 
 * Disclaimer:
 * This code is provided as-is, without any warranty or support. Use it at your
 * own risk. The author and Venos Tech shall not be liable for any damages or
 * issues arising from the use of this code.
 * 
 * Copyright (c) 2023 Fortune Ogulewe, Venos Tech. All rights reserved.
 */


public class Deviceinfo {
	private String Devicename;
	private String IDNumber;
	
	
	
	//defuakt contructor 
	public Deviceinfo() {
		
		
		
	}
	
	//define a constructor method for a Deviceinfo class that rakws four parameter 
	public  Deviceinfo(String Devicename, String IDNumber) {
		this.Devicename = Devicename;
		this.IDNumber = IDNumber;
	
		
	}
	
	// a method that returns the value of a private member variable called "Devicename" and for all of them 
	public String getDevicename() {
		return Devicename;
		
	}
	public void setDevicename(String Devicename) {
		this.Devicename = Devicename;
	}
	
	
	public String getIDNumber() {
		return IDNumber;
	}
	public void setIDNumber(String IDNumber) {
		this.IDNumber = IDNumber;
	}


	
	

}
