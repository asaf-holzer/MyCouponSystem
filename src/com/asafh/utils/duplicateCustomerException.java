package com.asafh.utils;

public class duplicateCustomerException extends Exception {

	public duplicateCustomerException() {
		super("sorry... The customer already exists ");
	}
}
