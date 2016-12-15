package com.sbsk.model.exception;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ExceptionMessagesTest {

  @Test
  public void testMessages() throws Exception {

    assertEquals("Session is invalid", ExceptionMessages.INVALID_SESSION);
    assertEquals("Resource not found", ExceptionMessages.RESOURCE_NOT_FOUND);
    assertEquals("Session was not found", ExceptionMessages.SESSION_NOT_FOUND);
  }
}