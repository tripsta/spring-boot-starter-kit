package com.tripsta.common.exceptions;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.tripsta.common.exceptions.GenericException;

public class GenericExceptionTest {

  @Test
  public void testConstructors() throws Exception {

    GenericException ge = new GenericException();
    assertEquals(null, ge.getMessage());
    assertEquals(null, ge.getCause());
    assertEquals(0, ge.getSuppressed().length);
    ge.setStackTrace(new StackTraceElement[] {new StackTraceElement("Object", "toString", null, 0)});

    ge = new GenericException("message", new RuntimeException("inner"), true, true);
    assertEquals("message", ge.getMessage());
    assertEquals("inner", ge.getCause().getMessage());
    assertEquals(0, ge.getSuppressed().length);
    ge.setStackTrace(new StackTraceElement[] {new StackTraceElement("Object", "toString", null, 0)});

    ge = new GenericException("message", new RuntimeException("inner"));
    assertEquals("message", ge.getMessage());
    assertEquals("inner", ge.getCause().getMessage());
    assertEquals(0, ge.getSuppressed().length);
    ge.setStackTrace(new StackTraceElement[] {new StackTraceElement("Object", "toString", null, 0)});

    ge = new GenericException("message");
    assertEquals("message", ge.getMessage());
    assertEquals(null, ge.getCause());
    assertEquals(0, ge.getSuppressed().length);
    ge.setStackTrace(new StackTraceElement[] {new StackTraceElement("Object", "toString", null, 0)});

    ge = new GenericException(new RuntimeException("inner"));
    assertEquals("java.lang.RuntimeException: inner", ge.getMessage());
    assertEquals("inner", ge.getCause().getMessage());
    assertEquals(0, ge.getSuppressed().length);
    ge.setStackTrace(new StackTraceElement[] {new StackTraceElement("Object", "toString", null, 0)});
  }

}