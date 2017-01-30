package com.sbsk.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ApiWarningTest {

  private static final String TEST_STR = "test";
  private ApiWarning apiWarning = null;

  @Before
  public void setup() {
    apiWarning = new ApiWarning(TEST_STR);
  }

  @Test
  public void testMwessage() throws Exception {
    assertEquals(TEST_STR, apiWarning.getMessage());

    apiWarning.setMessage("ouou");
    assertEquals("ouou", apiWarning.getMessage());
  }
}