package com.sbsk.service.validators;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UserValidator.class)
public class UserValidatorTests {

  @InjectMocks
  private UserValidator userValidator;

  @Test
  public void isNameValid_shouldReturnTrue_whenNameHasValidLength() {

    String name = "Foo";

    Boolean result = userValidator.isNameValid(name);

    assertTrue(result);
  }

  @Test
  public void isNameValid_shouldReturnFalse_whenNameHasLengthLessThanThreshold() {

    String name1 = "";
    String name2 = "Fo";

    Boolean result1 = userValidator.isNameValid(name1);
    Boolean result2 = userValidator.isNameValid(name2);

    assertFalse(result1);
    assertFalse(result2);
  }

  @Test
  public void isAgeValid_shouldReturnTrue_whenAgeIsWithinBounds() {
    Integer age1 = 1;
    Integer age2 = 25;
    Integer age3 = 119;

    Boolean result1 = userValidator.isAgeValid(age1);
    Boolean result2 = userValidator.isAgeValid(age2);
    Boolean result3 = userValidator.isAgeValid(age3);

    assertTrue(result1);
    assertTrue(result2);
    assertTrue(result3);
  }

  @Test
  public void isAgeValid_shouldReturnFalse_whenAgeIsOutOfBounds() {
    Integer age1 = 0;
    Integer age2 = 120;

    Boolean result1 = userValidator.isAgeValid(age1);
    Boolean result2 = userValidator.isAgeValid(age2);

    assertFalse(result1);
    assertFalse(result2);
  }

}
