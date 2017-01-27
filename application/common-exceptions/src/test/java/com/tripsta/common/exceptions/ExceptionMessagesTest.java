package com.tripsta.common.exceptions;

import org.junit.Test;

import com.tripsta.common.exceptions.ExceptionMessages;

import static org.junit.Assert.assertEquals;

public class ExceptionMessagesTest {

  @Test
  public void testMessages() throws Exception {

    assertEquals("Session is invalid", ExceptionMessages.INVALID_SESSION);
    assertEquals("Resource not found", ExceptionMessages.RESOURCE_NOT_FOUND);
    assertEquals("Session was not found", ExceptionMessages.SESSION_NOT_FOUND);
  }
}