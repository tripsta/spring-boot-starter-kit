package com.sbsk.service.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UserUtilsTests {

	@Test
	public void isAdult_shouldReturnTrue_whenAgeIsGreaterThanThreshold() {
		Integer age = 18;

		Boolean result = UserUtils.isAdult(age);

		assertTrue(result);
	}

	@Test
	public void isAdult_shouldReturnFalse_whenAgeIsLessThanThreshold() {
		Integer age = 17;

		Boolean result = UserUtils.isAdult(age);

		assertFalse(result);
	}

}
