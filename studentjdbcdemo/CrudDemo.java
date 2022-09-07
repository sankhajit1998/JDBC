/*
@Author Sankhajit Roy
*/
package com.studentjdbcdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

//student management using CRUD operation

public class CrudDemo {     // main method
	
	public static void main(String[] args) {    // main method
		
		// create an object of CrudDemo class
		CrudDemo c = new CrudDemo();
		// take user input 
		Scanner scan = new Scanner(System.in);
		
		int choice;
		// to select what operation to be done from user
		do {
			System.out.println("--- Menu ---");
			System.out.println(" 1.Insert");
			System.out.println(" 2.Display");
			System.out.println(" 3.Delete");
			System.out.println(" 4.Update");
			System.out.println(" 5.Exit");
			System.out.print("Enter your choice: ");
			// read the user input
			choice = scan.nextInt();
			
			
			// switch case to choose the the operation
			switch(choice) {
			// 1. Insert
			case 1: 
				
				c.insertData();
				
				break;
			// 2. Display
			case 2:
				
				c.displayData();
				
				break;
			// 3. Delete	
			case 3:
				
				c.deleteData();
				
				break;
			// 4. Update
			case 4:
				
				c.updateData();
				
				break;
			// 5. Exit	
			case 5:
				System.out.println("Thank You.");
				
			default:
				break;
			}
		// condition	
		} while (choice != 5);
	}
	// create a method to access connection
	public Connection getConnect() {
		// try block
		try {
			// register the driver with jdbc
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			// get connection from database
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","anit@1998*07");
			
			return conn;
		// catch block	
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	// method to insert the data
	public void insertData() {
		// created scanner objects
		Scanner scan1 = new Scanner(System.in);
		Scanner scan2 = new Scanner(System.in);
		Scanner scan3 = new Scanner(System.in);
		Scanner scan4 = new Scanner(System.in);
		// create a student class object
		Student s = new Student();
		
		// to read input of student ID
		System.out.print("Enter Student ID: ");
		s.setStdId(scan1.nextInt());
		// to read input of student Name
		System.out.print("Enter Student Name: ");
		s.setStdName(scan2.nextLine());
		// to read input of student Address
		System.out.print("Enter Student Address: ");
		s.setStdAddress(scan3.nextLine());
		// to read input of student Phone Number
		System.out.print("Enter Contact Number: ");
		s.setStdNumber(scan4.nextLong());
		// try block
		try {
			// call getConnection() method
			Connection conn = getConnect();
			// declare the sql statement
			PreparedStatement pstm = conn.prepareStatement("insert into student values(?,?,?,?)");
			// get all data insert into table
			pstm.setInt(1, s.getStdId());
			pstm.setString(2, s.getStdName());
			pstm.setString(3, s.getStdAddress());
			pstm.setLong(4, s.getStdNumber());
			// execute the statement
			pstm.execute();
			System.out.println("Inserted succesfully.");
			// close the objects
			pstm.close();
			conn.close();
		// catch block	
		} catch (Exception e) {
			System.out.println("Duplicate entry!"+e);
		}
		
	}

	// method to display the data
	public void displayData() {
		// try block
		try {
			// called getConnection() method
			Connection conn = getConnect();
			// declare the sql statement
			PreparedStatement pstm = conn.prepareStatement("select * from student");
			// show the result of statement
			ResultSet rs = pstm.executeQuery();
			
			System.out.println("--------------------------");
			// traversing through table
			while (rs.next()) {
				
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getLong(4));
			}
			System.out.println("--------------------------");
			// close the objects
			rs.close();
			pstm.close();
			conn.close();
		// catch block	
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	// method to delete the data
	public void deleteData() {
		// try block
		try {
			
			// created a scanner object
			Scanner s = new Scanner(System.in);
			int id;
			System.out.print("Enter ID to delete: ");
			// read user input
			id = s.nextInt();
			// called the connection method
			Connection conn = getConnect();
			// declare the sql statement
			PreparedStatement pstm = conn.prepareStatement("delete from student where stdId = ?");
			pstm.setInt(1, id);
			
			// execute the statement
			int x = pstm.executeUpdate();
			// condition to check operation done or not
			if(x==1) { 
				System.out.println("Selected data deleted!");
			}
			else {
				System.out.println("Data not found!");
			}
			// close objects
			pstm.close();
			conn.close();
		// catch block	
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	// method to update the data
	public void updateData() {
		// created scanner object
		Scanner s1 = new Scanner(System.in);
		Scanner s2 = new Scanner(System.in);
		Scanner s3 = new Scanner(System.in);
		Scanner s4 = new Scanner(System.in);
		// created a student class object
		Student std = new Student();
		// to read input of student ID
		System.out.print("Enter ID to update: ");
		int id = s1.nextInt();
		std.setStdId(id);
		// to read input of student Name
		System.out.print("Enter Name to update: ");
		String name = s2.nextLine();
		std.setStdName(name);
		// to read input of student Address
		System.out.print("Enter Address to update: ");
		String address = s3.nextLine();
		std.setStdAddress(address);
		// to read input of student Phone Number
		System.out.print("Enter Number to update: ");
		long num = s4.nextLong();
		std.setStdNumber(num);
		// try block
		try {
			// called getConnection() method
			Connection conn = getConnect();
			// declare the sql statement
			String str = "update student set stdName = ?, stdAddress = ?, stdNumber = ? where stdId = ?";
			PreparedStatement pstm = conn.prepareStatement(str);
			
			// get all data under statement
			pstm.setString(1, std.getStdName());
			pstm.setNString(2, std.getStdAddress());
			pstm.setLong(3, std.getStdNumber());
			pstm.setInt(4, std.getStdId());
			// execute the statement
			int x = pstm.executeUpdate();
			// close objects
			pstm.close();
			conn.close();
			// condition to check the operation done or not
			if(x==1) {
				System.out.println("Update done.");
			}
			else {
				System.out.println("Data not found!");
			}

		// catch block	
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
}
