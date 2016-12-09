package com.sbsk.service.validators;

public class UserValidator {

	private static final Integer VALID_NAME_THRESHOLD = 2;
	private static final Integer AGE_LOWER_BOUND = 0;
	private static final Integer AGE_UPPER_BOUND = 120;

	public static Boolean isNameValid(String name) {
		return name.length() > VALID_NAME_THRESHOLD;
	}

	public static Boolean isAgeValid(Integer age) {
		return age > AGE_LOWER_BOUND && age < AGE_UPPER_BOUND;
	}

}
