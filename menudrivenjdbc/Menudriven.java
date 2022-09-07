package com.menudrivenjdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Menudriven {
	
	Scanner s = new Scanner(System.in);
	Scanner s1 = new Scanner(System.in);
	Scanner s2 = new Scanner(System.in);
	Scanner s3 = new Scanner(System.in);
	Scanner s4 = new Scanner(System.in);
	Scanner s5 = new Scanner(System.in);
	
	
	int id, age, salary;
	String name,city;
	
	// saving details in database
	public void saveMenudriven() throws SQLException {
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
		
		Connection conn = ConnectionHelper.con();
		PreparedStatement s1 = conn.prepareStatement("insert into menudriven values(?,?,?,?,?)");
		s1.setInt(1, id);
		s1.setString(2, name);
		s1.setInt(3, age);
		s1.setString(4, city);
		s1.setInt(5, salary);
		s1.executeUpdate();
		
	}
	
	// display database
	public void displayMenudriven() throws SQLException {
		Connection conn = ConnectionHelper.con();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select * from menudriven");
		
		while (rs.next()) {
			System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4)+" "+rs.getInt(5));
			
		}
	}
	
	// updating database
	public void updateMenudriven() throws SQLException {
		Connection conn = ConnectionHelper.con();
		Statement st = conn.createStatement();
		System.out.print("Enter Employee City: ");
		city = s.nextLine();
		st.executeUpdate("update Menudriven set city='"+city+"' where Id="+id);
	}
	
	// deleting details from database
	public void deleteMenudriven() throws SQLException {
		Connection conn = ConnectionHelper.con();
		Statement st = conn.createStatement();
		System.out.print("Enter Employee ID: ");
		id = s.nextInt();
		st.executeUpdate("delete from menudriven where id = "+id);
	}
	
}





