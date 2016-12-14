package com.sbsk.model.exception;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.sbsk.model.ApiError;

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
	
	@Test
	public void testPrepareErrorMessages() throws Exception {
		@SuppressWarnings("serial")
		List<ApiError> errors = new ArrayList<ApiError>() {{add(new ApiError("test1")); add(new ApiError("test2"));}};
		
		assertEquals("test1\ntest2", GenericException.prepareErrorMessages(errors));
	}
	
	
}