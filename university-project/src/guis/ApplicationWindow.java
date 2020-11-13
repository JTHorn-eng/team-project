package guis;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import database.Database;

/**
 * @author James Horn
 * 
 * ApplicationWindow.java 03/11/2020
 * 
 * Handle all active frames
 * 
 * INFO
 * Don't forget to init frames
 * 
 * 
 * STARTUP
 * 1) Supply login credentials
 * 2) use permissions to generate proper frame options (displaying toolbar or not)
 * 3) Add / remove widgets depending on permissions
 * 4) 
 * 
 * 
 * 
 * 
 */

public class ApplicationWindow  {

	
	public ApplicationWindow() {
		
	}
	
	public static void main(String args[]) {
		//new DegreeFrame().display();
		
		//new DepartmentFrame().display();
		
		Database.testConnection();
		StudentFrame.initStudentFrame();
		StudentFrame.display();
		//Database.viewStudent("James Horn");
	}
	
	
	
	
	
	
	
}