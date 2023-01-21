package com.epam.exception;

public class EmployeeNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3840652946209233741L;
	public EmployeeNotFoundException(String error)
	{
		super(error);
	}

}
