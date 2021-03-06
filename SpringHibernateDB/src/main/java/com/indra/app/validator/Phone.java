package com.indra.app.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 
 * @author arommartinez
 *
 */

@Documented
@Constraint(validatedBy = PhoneValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Phone {
		
	String message() default "Invalid format, valid formats are 1234567890, 123-456-789, 123 456 789 ";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
