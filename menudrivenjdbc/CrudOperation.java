package com.menudrivenjdbc;

import java.sql.SQLException;
import java.util.Scanner;

public class CrudOperation {
	public static void main(String[] args) throws SQLException {
		
		Menudriven e = new Menudriven();
		Scanner s = new Scanner(System.in);
		int ch;
		do {
			System.out.println("1. INSERT \n2. DISPLAY \n3. UPDATE \n4. DELETE \n5. EXIT");
			System.out.print("Enter your choice: ");
			ch = Integer.parseInt(s.nextLine());
			
			System.out.println("----------------------------------");
			
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
