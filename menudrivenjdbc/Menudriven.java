/*
@Author Sankhajit
*/
package com.menudrivenjdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Menudriven {   // class
	// created a scanner objects
	Scanner s = new Scanner(System.in);
	Scanner s1 = new Scanner(System.in);
	Scanner s2 = new Scanner(System.in);
	Scanner s3 = new Scanner(System.in);
	Scanner s4 = new Scanner(System.in);
	Scanner s5 = new Scanner(System.in);
	
	
	int id, age, salary;
	String name,city;
	
	// method for saving details in database
	public void saveMenudriven() throws SQLException {
		// take user input
		System.out.println("Enter Employee ID: ");
		id = s1.nextInt();
		System.out.println("Enter Employee Name: ");
		name = s2.next();
		System.out.println("Enter Employee Age: ");
		age = s3.nextInt();
		System.out.println("Enter Employee City: ");
		city = s4.next();
		System.out.println("Enter Employee Salary: ");
		salary = s5.nextInt();
		// create a ConnectionHelper class object
		Connection conn = ConnectionHelper.con();
		// declare sql statement to insert value into table
		PreparedStatement s1 = conn.prepareStatement("insert into menudriven values(?,?,?,?,?)");
		// set values into table
		s1.setInt(1, id);
		s1.setString(2, name);
		s1.setInt(3, age);
		s1.setString(4, city);
		s1.setInt(5, salary);
		// execute the statement
		s1.executeUpdate();
		
	}
	
	// method for display database
	public void displayMenudriven() throws SQLException {
		// create a ConnectionHelper class object
		Connection conn = ConnectionHelper.con();
		// crated a statement object
		Statement st = conn.createStatement();
		// declare sql statement
		ResultSet rs = st.executeQuery("select * from menudriven");
		// traversing the table
		while (rs.next()) {
			System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4)+" "+rs.getInt(5));
			
		}
	}

	// method for updating database
	public void updateMenudriven() throws SQLException{
		// create a ConnectionHelper class object
		Connection conn = ConnectionHelper.con();
		// crated a statement object
		Statement st = conn.createStatement();
		// take input from user & read the input
		System.out.println("Enter Employe Id you want to update: ");
		id = s.nextInt();
		// take input from user & read the input
		Scanner s1 = new Scanner(System.in);
		System.out.println("Enter Employe City: ");
		city = s1.nextLine();
		// execute the statement 
		st.executeUpdate("update Menudriven set city='"+city+"' where Id="+id);
	}
	
	// method for deleting details from database
	public void deleteMenudriven() throws SQLException {
		// create a ConnectionHelper class object
		Connection conn = ConnectionHelper.con();
		// crated a statement object
		Statement st = conn.createStatement();
		// take input from user & read the input
		System.out.print("Enter Employee ID: ");
		id = s.nextInt();
		// execute the statement
		st.executeUpdate("delete from menudriven where id = "+id);
	}
	
}





