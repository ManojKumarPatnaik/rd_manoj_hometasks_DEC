package com.epam.dto;

import java.text.ParseException;
import java.sql.Date;

public class EmployeeDto {

	@Override
	public String toString() {
		return "EmployeeDto [empId=" + empId + ", empName=" + empName + ", address=" + address + ", dob=" + dob
				+ ", password=" + password + "]";
	}

	private int empId;

	private String empName;

	private String address;

	private Date dob;

	private String password;

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public Date getDob() {
		return dob;
	}
	
	public void setDob(String dob) throws ParseException {
		this.dob = Date.valueOf(dob);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
