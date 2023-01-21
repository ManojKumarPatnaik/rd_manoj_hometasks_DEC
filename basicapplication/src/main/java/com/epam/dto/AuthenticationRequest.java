package com.epam.dto;

import java.io.Serializable;
import java.sql.Date;
import java.text.ParseException;

public class AuthenticationRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String username;
	
	private Date password;
	
	public AuthenticationRequest() {
		// TODO Auto-generated constructor stub
	}
	

	public AuthenticationRequest(String username, Date password) {
		super();
		this.username = username;
		this.password = password;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getPassword() {
		return password;
	}

	public void setPassword(String password) throws ParseException {
		this.password = Date.valueOf(password);
	}
	
	
}
