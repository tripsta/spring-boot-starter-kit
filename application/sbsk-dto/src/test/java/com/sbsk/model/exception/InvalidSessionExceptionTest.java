package com.sbsk.model.exception;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InvalidSessionExceptionTest {

  @Test
  public void testConstructors() throws Exception {

    InvalidSessionException ge = new InvalidSessionException();
    assertEquals(ExceptionMessages.INVALID_SESSION, ge.getMessage());
    assertEquals(null, ge.getCause());
    assertEquals(0, ge.getSuppressed().length);
    ge.setStackTrace(new StackTraceElement[] {new StackTraceElement("Object", "toString", null, 0)});

    ge = new InvalidSessionException("message", new RuntimeException("inner"), true, true);
    assertEquals("message", ge.getMessage());
    assertEquals("inner", ge.getCause().getMessage());
    assertEquals(0, ge.getSuppressed().length);
    ge.setStackTrace(new StackTraceElement[] {new StackTraceElement("Object", "toString", null, 0)});

    ge = new InvalidSessionException("message", new RuntimeException("inner"));
    assertEquals("message", ge.getMessage());
    assertEquals("inner", ge.getCause().getMessage());
    assertEquals(0, ge.getSuppressed().length);
    ge.setStackTrace(new StackTraceElement[] {new StackTraceElement("Object", "toString", null, 0)});

    ge = new InvalidSessionException("message");
    assertEquals("message", ge.getMessage());
    assertEquals(null, ge.getCause());
    assertEquals(0, ge.getSuppressed().length);
    ge.setStackTrace(new StackTraceElement[] {new StackTraceElement("Object", "toString", null, 0)});

    ge = new InvalidSessionException(new RuntimeException("inner"));
    assertEquals("java.lang.RuntimeException: inner", ge.getMessage());
    assertEquals("inner", ge.getCause().getMessage());
    assertEquals(0, ge.getSuppressed().length);
    ge.setStackTrace(new StackTraceElement[] {new StackTraceElement("Object", "toString", null, 0)});
  }

}