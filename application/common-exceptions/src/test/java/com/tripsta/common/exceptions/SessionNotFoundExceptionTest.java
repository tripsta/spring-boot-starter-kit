package com.tripsta.common.exceptions;

import org.junit.Test;

import com.tripsta.common.exceptions.ExceptionMessages;
import com.tripsta.common.exceptions.SessionNotFoundException;

import static org.junit.Assert.assertEquals;

public class SessionNotFoundExceptionTest {

  @Test
  public void testConstructors() throws Exception {

    SessionNotFoundException ge = new SessionNotFoundException();
    assertEquals(ExceptionMessages.SESSION_NOT_FOUND, ge.getMessage());
    assertEquals(null, ge.getCause());
    assertEquals(0, ge.getSuppressed().length);
    ge.setStackTrace(new StackTraceElement[] {new StackTraceElement("Object", "toString", null, 0)});

    ge = new SessionNotFoundException("message", new RuntimeException("inner"), true, true);
    assertEquals("message", ge.getMessage());
    assertEquals("inner", ge.getCause().getMessage());
    assertEquals(0, ge.getSuppressed().length);
    ge.setStackTrace(new StackTraceElement[] {new StackTraceElement("Object", "toString", null, 0)});

    ge = new SessionNotFoundException("message", new RuntimeException("inner"));
    assertEquals("message", ge.getMessage());
    assertEquals("inner", ge.getCause().getMessage());
    assertEquals(0, ge.getSuppressed().length);
    ge.setStackTrace(new StackTraceElement[] {new StackTraceElement("Object", "toString", null, 0)});

    ge = new SessionNotFoundException("message");
    assertEquals("message", ge.getMessage());
    assertEquals(null, ge.getCause());
    assertEquals(0, ge.getSuppressed().length);
    ge.setStackTrace(new StackTraceElement[] {new StackTraceElement("Object", "toString", null, 0)});

    ge = new SessionNotFoundException(new RuntimeException("inner"));
    assertEquals("java.lang.RuntimeException: inner", ge.getMessage());
    assertEquals("inner", ge.getCause().getMessage());
    assertEquals(0, ge.getSuppressed().length);
    ge.setStackTrace(new StackTraceElement[] {new StackTraceElement("Object", "toString", null, 0)});
  }

}