package com.indra.app.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 
 * @author arommartinez
 *
 */

public class PhoneValidator implements ConstraintValidator<Phone, String> {     
	   
	public void initialize(Phone paramA) {    	
	} 
	    
	public boolean isValid(String phoneNo, ConstraintValidatorContext ctx) {        
		if(phoneNo == null){            
			return false;        
			}
		//Validate phone numbers of format "123456789"
		if(phoneNo.matches("\\d{9}")) return true;
		//Validating phone number with -, . or spaces        
		else if(phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{3}")) return true;        
		//Validating phone number with extension length from 3 to 5        
		else if(phoneNo.matches("\\d{3}-\\d{3}-\\d{3}\\s(x|(ext))\\d{2,3}")) return true;        
		//Validating phone number where area code is in braces ()        
		else if(phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{3}")) return true;        
		//return false if nothing matches the input        
		else return false;
	}

}
