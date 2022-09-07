/*
@Author Sankhajit Roy
*/
package com.menudrivenjdbc;

import java.sql.SQLException;
import java.util.Scanner;

public class CrudOperation {   // main class
	public static void main(String[] args) throws SQLException {   // main method 
		// created menudriven class object
		Menudriven e = new Menudriven();
		// created a scanner object
		Scanner s = new Scanner(System.in);
		int ch;
		// do-while loop to execute all method
		do {
			// take input from user & read the input
			System.out.println("1. INSERT \n2. DISPLAY \n3. UPDATE \n4. DELETE \n5. EXIT");
			System.out.print("Enter your choice: ");
			ch = Integer.parseInt(s.nextLine());
			
			System.out.println("----------------------------------");
			
			// using switch case to go through all method simultaneously
			switch (ch) {
			case 1:
				e.saveMenudriven();
				break;
			case 2:
				e.displayMenudriven();
				break;
			case 3:
				e.updateMenudriven();
				break;
			case 4:
				e.deleteMenudriven();
				break;
			case 5:
				System.exit(0);
				break;
			}	
		} while (ch!=5);
	}
}
