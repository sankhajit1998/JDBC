/*
@Author Sankhajit Roy
*/
package com.studentjdbcdemo;

// student management using CRUD operation

public class Student {     // parent class
	// taking variables
	private int stdId;
	private String stdName;
	private String stdAddress;
	private long stdNumber;
	
	// getter & setter method
	public int getStdId() {
		return stdId;
	}

	public void setStdId(int stdId) {
		this.stdId = stdId;
	}

	public String getStdName() {
		return stdName;
	}

	public void setStdName(String stdName) {
		this.stdName = stdName;
	}
	
	public String getStdAddress() {
		return stdAddress;
	}

	public void setStdAddress(String stdAddress) {
		this.stdAddress = stdAddress;
	}

	public long getStdNumber() {
		return stdNumber;
	}

	public void setStdNumber(long stdNumber) {
		this.stdNumber = stdNumber;
	}

}






