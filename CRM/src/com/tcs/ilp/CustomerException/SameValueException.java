package com.tcs.ilp.CustomerException;

public class SameValueException extends Exception 
{
	public SameValueException(String message)
	{
		super(message);
	}
	
	@Override
	public String getMessage()
	{
		return super.getMessage();
	}
}
