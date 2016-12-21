package com.sbsk.service.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UserUtils.class)
public class UserUtilsTests {

  @InjectMocks
  private UserUtils userUtils;

  @Test
  public void isAdult_shouldReturnTrue_whenAgeIsGreaterThanThreshold() {

    Integer age = 18;

    Boolean result = userUtils.isAdult(age);

    assertTrue(result);
  }

  @Test
  public void isAdult_shouldReturnFalse_whenAgeIsLessThanThreshold() {

    Integer age = 17;

    Boolean result = userUtils.isAdult(age);

    assertFalse(result);
  }

}
