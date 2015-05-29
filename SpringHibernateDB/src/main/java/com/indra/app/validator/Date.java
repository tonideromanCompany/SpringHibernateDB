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
@Constraint(validatedBy = DateValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Date {
		
	String message() default "Incorrect format, please re-introduce in a correct format dd/MM/yyyy";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
