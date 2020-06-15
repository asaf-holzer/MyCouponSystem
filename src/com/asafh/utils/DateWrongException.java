package com.asafh.utils;

public class DateWrongException extends Exception{

	
	public DateWrongException() {
		super("Sorry... The start date can't be after end date");
	}

}
