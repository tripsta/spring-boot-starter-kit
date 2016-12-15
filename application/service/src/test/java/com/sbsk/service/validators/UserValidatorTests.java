package com.sbsk.service.validators;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserValidatorTests {

  @Test
  public void isNameValid_shouldReturnTrue_whenNameHasValidLength() {
    // Arrange
    String name = "Nikos";

    // Act
    Boolean result = UserValidator.isNameValid(name);

    // Assert
    assertTrue(result);

    // Annihilate (not applicable in this case)
  }

  @Test
  public void isNameValid_shouldReturnFalse_whenNameHasLengthLessThanThreshold() {
    String name1 = "";
    String name2 = "Ni";

    Boolean result1 = UserValidator.isNameValid(name1);
    Boolean result2 = UserValidator.isNameValid(name2);

    assertFalse(result1);
    assertFalse(result2);
  }

  @Test
  public void isAgeValid_shouldReturnTrue_whenAgeIsWithinBounds() {
    Integer age1 = 1;
    Integer age2 = 25;
    Integer age3 = 119;

    Boolean result1 = UserValidator.isAgeValid(age1);
    Boolean result2 = UserValidator.isAgeValid(age2);
    Boolean result3 = UserValidator.isAgeValid(age3);

    assertTrue(result1);
    assertTrue(result2);
    assertTrue(result3);
  }

  @Test
  public void isAgeValid_shouldReturnFalse_whenAgeIsOutOfBounds() {
    Integer age1 = 0;
    Integer age2 = 120;

    Boolean result1 = UserValidator.isAgeValid(age1);
    Boolean result2 = UserValidator.isAgeValid(age2);

    assertFalse(result1);
    assertFalse(result2);
  }

}
