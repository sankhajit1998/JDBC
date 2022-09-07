/*
@Author Sankhajit Roy
*/

package com.menudrivenjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

//menu driven operation using jdbc

public class Banking {    // main class
	
public static void main(String[] args) {    // main method
		
		// create an object of Banking class
		Banking b = new Banking();
		// take user input 
		Scanner scan = new Scanner(System.in);
		
		int choice;
		// to select what operation to be done from user
		do {
			System.out.println("  --- Bank ---");
			System.out.println(" 1.Create Account");
			System.out.println(" 2.Account Details");
			System.out.println(" 3.Close Account");
			System.out.println(" 4.Update Account");
			System.out.println(" 5.Deposit Money");
			System.out.println(" 6.Withdraw Money");
			System.out.println(" 7.Exit");
			System.out.print("Enter your choice: ");
			// read the user input
			choice = scan.nextInt();
			
			// switch case to choose the the operation
			switch(choice) {
			// 1. Insert
			case 1: 
				
				b.createAccount();
				
				break;
			// 2. Display
			case 2:
				
				b.accountDetails();
				
				break;
			// 3. Delete	
			case 3:
				
				b.closeAccount();
				
				break;
			// 4. Update
			case 4:
				
				b.updateAccount();
				
				break;
			// deposit	
			case 5:
				
				b.depositMoney();
				
				break;
			// withdraw	
			case 6:
				
				b.withdrawMoney();
				
				break;
			// 5. Exit	
			case 7:
				System.out.println("Thank You.");
				
			default:
				break;
			}
		// condition	
		} while (choice != 7);
	}
	// create a method to access connection
	public Connection getConnect() {
		// try block
		try {
			// register the driver with jdbc
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			// get connection from database
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","anit@1998*07");
			
			return conn;
		// catch block	
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	// method to insert the data
	public void createAccount() {
		// created scanner objects
		Scanner scan1 = new Scanner(System.in);
		Scanner scan2 = new Scanner(System.in);
		Scanner scan3 = new Scanner(System.in);
		Scanner scan4 = new Scanner(System.in);
		Scanner scan5 = new Scanner(System.in);
		// create a class object
		Bank bank = new Bank();
		
		// to read input of ID
		System.out.print("Enter ID: ");
		bank.setCustId(scan1.nextInt());
		// to read input of Name
		System.out.print("Enter Name: ");
		bank.setCustName(scan2.nextLine());
		// to read input of Address
		System.out.print("Enter Address: ");
		bank.setCustAddress(scan3.nextLine());
		// to read input of Account Number
		System.out.print("Enter Account Number: ");
		bank.setAccountNo(scan4.nextLong());
		System.out.print("Enter Amount: ");
		bank.setBalance(scan5.nextDouble());
		// try block
		try {
			// call getConnection() method
			Connection conn = getConnect();
			// declare the sql statement
			PreparedStatement pstm = conn.prepareStatement("insert into bank values(?,?,?,?,?)");
			// get all data insert into table
			pstm.setInt(1, bank.getCustId());
			pstm.setString(2, bank.getCustName());
			pstm.setString(3, bank.getCustAddress());
			pstm.setLong(4, bank.getAccountNo());
			pstm.setDouble(5, bank.getBalance());
			// execute the statement
			pstm.execute();
			System.out.println("Account created succesfully.");
			// close the objects
			pstm.close();
			conn.close();
		// catch block	
		} catch (Exception e) {
			System.out.println("Duplicate entry!"+e);
		}
		
	}

	// method to display the data
	public void accountDetails() {
		// try block
		try {
			// called getConnection() method
			Connection conn = getConnect();
			// declare the sql statement
			PreparedStatement pstm = conn.prepareStatement("select * from bank");
			// show the result of statement
			ResultSet rs = pstm.executeQuery();
			
			System.out.println("-----------------------------------");
			// traversing through table
			while (rs.next()) {
				
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getLong(4)+" "+rs.getDouble(5));
			}
			System.out.println("-----------------------------------");
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
	public void closeAccount() {
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
			PreparedStatement pstm = conn.prepareStatement("delete from bank where custId = ?");
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
	public void updateAccount() {

		// created scanner object
		Scanner s1 = new Scanner(System.in);
		Scanner s2 = new Scanner(System.in);
		Scanner s3 = new Scanner(System.in);
		Scanner s4 = new Scanner(System.in);
		// created a class object
		Bank bank = new Bank();
		// to read input of ID
		System.out.print("Enter ID to update: ");
		int id = s1.nextInt();
		bank.setCustId(id);
		// to read input of Name
		System.out.print("Enter Name to update: ");
		String name = s2.nextLine();
		bank.setCustName(name);
		// to read input of Address
		System.out.print("Enter Address to update: ");
		String address = s3.nextLine();
		bank.setCustAddress(address);
		// to read input of Account Number
		System.out.print("Enter Account Number to update: ");
		long num = s4.nextLong();
		bank.setAccountNo(num);
		// try block
		try {
			// called getConnection() method
			Connection conn = getConnect();
			// declare the sql statement
			String str = "update bank set custName = ?, custAddress = ?, accountNo = ? where custId = ?";
			PreparedStatement pstm = conn.prepareStatement(str);
			
			// get all data under statement
			pstm.setString(1, bank.getCustName());
			pstm.setNString(2, bank.getCustAddress());
			pstm.setLong(3, bank.getAccountNo());
			pstm.setInt(4, bank.getCustId());
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
	
	//Deposit Money to an Account
	public void depositMoney() {
		// created scanner object
		Scanner br = new Scanner(System.in);
		// try block
		try {
			// called getConnection() method
			Connection conn = getConnect();
			// create statement
			Statement stm = conn.createStatement();
			System.out.print("Enter ID to update: ");
			int id = br.nextInt();
			System.out.print("Enter Deposit Ammount: ");
			double amount = br.nextDouble();
			
			// execute the sql query to select column
			ResultSet rs = stm.executeQuery("select balance from bank where custId="+id);
			while(rs.next()) {
				// logic for deposit the money
				double newAmount = (rs.getDouble(1)+amount);
				System.out.println("Previous Balance: "+rs.getDouble(1));
				System.out.println("Current Balance: "+newAmount);
				// execute the sql query to update balance
				stm.executeUpdate("update bank set balance="+newAmount+" where custId="+id);
				System.out.println("Money deposited succesfully.");
			}
			
			// close the objects
			rs.close();
			conn.close();
		// catch block	
		} catch (Exception e) {
			System.out.println();
		}
		
	}
	
	// Withdraw Money to an Account
	public void withdrawMoney() {
		// created scanner object
		Scanner br = new Scanner(System.in);
		// try block
		try {
			// called getConnection() method
			Connection conn = getConnect();
			// create statement
			Statement stm = conn.createStatement();
			System.out.print("Enter ID to update: ");
			int id = br.nextInt();
			System.out.print("Enter Withdraw Ammount: ");
			double amount = br.nextDouble();
			
			// execute the sql query to select column
			ResultSet rs = stm.executeQuery("select balance from bank where custId="+id);
			while(rs.next()) {
				// logic for withdraw the money
				double newAmount = (rs.getDouble(1)-amount);
				System.out.println("Previous Balance: "+rs.getDouble(1));
				System.out.println("Current Balance: "+newAmount);
				// execute the sql query to update balance
				stm.executeUpdate("update bank set balance="+newAmount+" where custId="+id);
				System.out.println("Money withdraw succesfully.");
			}
			
			// close the objects
			rs.close();
			conn.close();
		// catch block	
		} catch (Exception e) {
			System.out.println();
		}
	}
}
