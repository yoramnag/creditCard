package com.example.creditCard.service;

public class Utils {
	
	public static boolean luhnValidetor(String creditCardNumber) {
		
		return org.apache.commons.validator.routines.checkdigit.LuhnCheckDigit.LUHN_CHECK_DIGIT.isValid(creditCardNumber);
		//return false;
		
	}

}
