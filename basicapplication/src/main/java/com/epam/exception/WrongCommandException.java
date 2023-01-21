package com.epam.exception;

public class WrongCommandException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3840652946209233741L;
	public WrongCommandException(String error)
	{
		super(error);
	}

}
