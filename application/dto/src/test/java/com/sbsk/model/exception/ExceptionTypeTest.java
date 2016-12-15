package com.sbsk.model.exception;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ExceptionTypeTest {

  @Test
  public void testMessages() throws Exception {

    assertEquals("11", ExceptionType.ADVISORY.getErrorCode());
    assertEquals("13", ExceptionType.APPLICATION_ERROR.getErrorCode());
    assertEquals("4", ExceptionType.AUTH_EXC.getErrorCode());
    assertEquals("5", ExceptionType.AUTH_TIMEOUT.getErrorCode());
    assertEquals("6", ExceptionType.AUTHORIZATION.getErrorCode());
    assertEquals("3", ExceptionType.BIZ_RULE.getErrorCode());
    assertEquals("7", ExceptionType.FIELD_VALIDATION.getErrorCode());
    assertEquals("2", ExceptionType.NO_IMPL.getErrorCode());
    assertEquals("12", ExceptionType.PROCESSING_EXCEPTION.getErrorCode());
    assertEquals("10", ExceptionType.RE_FIELD_MISSING.getErrorCode());
    assertEquals("1", ExceptionType.UNKNOWN.getErrorCode());
  }
}