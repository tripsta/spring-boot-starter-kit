package com.sbsk.service.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class UserUtilsTests {

  private UserUtils userUtils;

  @Before
  public void setUp() throws Exception {
      userUtils = new UserUtils();
  }

  @Test
  public void isAdult_shouldReturnTrue_whenAgeIsGreaterThanThreshold() {
    Integer age = 18;

    Boolean result = userUtils.isAdult(age);

    Assert.assertTrue(result);
  }

  @Test
  public void isAdult_shouldReturnFalse_whenAgeIsLessThanThreshold() {
    Integer age = 17;

    Boolean result = userUtils.isAdult(age);

    assertFalse(result);
  }
}
