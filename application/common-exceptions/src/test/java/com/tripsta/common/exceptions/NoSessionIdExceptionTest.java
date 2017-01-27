package com.tripsta.common.exceptions;

import org.junit.Test;

import com.tripsta.common.exceptions.NoSessionIdException;

import static org.junit.Assert.assertEquals;

public class NoSessionIdExceptionTest {

  @Test
  public void testConstructors() throws Exception {

    NoSessionIdException ge = new NoSessionIdException();
    assertEquals(null, ge.getMessage());
    assertEquals(null, ge.getCause());
    assertEquals(0, ge.getSuppressed().length);
    ge.setStackTrace(new StackTraceElement[] {new StackTraceElement("Object", "toString", null, 0)});

    ge = new NoSessionIdException("message", new RuntimeException("inner"), true, true);
    assertEquals("message", ge.getMessage());
    assertEquals("inner", ge.getCause().getMessage());
    assertEquals(0, ge.getSuppressed().length);
    ge.setStackTrace(new StackTraceElement[] {new StackTraceElement("Object", "toString", null, 0)});

    ge = new NoSessionIdException("message", new RuntimeException("inner"));
    assertEquals("message", ge.getMessage());
    assertEquals("inner", ge.getCause().getMessage());
    assertEquals(0, ge.getSuppressed().length);
    ge.setStackTrace(new StackTraceElement[] {new StackTraceElement("Object", "toString", null, 0)});

    ge = new NoSessionIdException("message");
    assertEquals("message", ge.getMessage());
    assertEquals(null, ge.getCause());
    assertEquals(0, ge.getSuppressed().length);
    ge.setStackTrace(new StackTraceElement[] {new StackTraceElement("Object", "toString", null, 0)});

    ge = new NoSessionIdException(new RuntimeException("inner"));
    assertEquals("java.lang.RuntimeException: inner", ge.getMessage());
    assertEquals("inner", ge.getCause().getMessage());
    assertEquals(0, ge.getSuppressed().length);
    ge.setStackTrace(new StackTraceElement[] {new StackTraceElement("Object", "toString", null, 0)});
  }

}