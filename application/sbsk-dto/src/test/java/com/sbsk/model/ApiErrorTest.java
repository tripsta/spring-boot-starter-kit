package com.sbsk.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tripsta.common.exceptions.ExceptionType;

public class ApiErrorTest {

  private static final String TEST_STR = "test";
  private ApiError apiError = null;

  @Before
  public void setup() {
    apiError = new ApiError();
  }

  @Test
  public void testMessage() throws Exception {
    apiError.setMessage(TEST_STR);

    assertEquals(TEST_STR, apiError.getMessage());
  }

  @Test
  public void testCode() throws Exception {
    apiError.setCode(5);

    assertEquals(5, apiError.getCode());

    Field field = ApiError.class.getDeclaredField("code");
    assertNotNull(field.getAnnotation(JsonIgnore.class));
  }

  @Test
  public void testExceptionType() throws Exception {
    assertEquals(null, apiError.getType());

    apiError.setType(com.tripsta.common.exceptions.ExceptionType.ADVISORY);

    assertEquals(ExceptionType.ADVISORY, apiError.getType());
  }

  @Test
  public void testFinals() throws Exception {
    assertEquals("Generic Error", ApiError.GENERAL_ERROR_MSG);

    assertEquals("Authorization Timeout", ApiError.AUTH_TIMEOUT_ERROR.getMessage());
    assertEquals(ExceptionType.AUTH_TIMEOUT, ApiError.AUTH_TIMEOUT_ERROR.getType());
    assertEquals(0, ApiError.AUTH_TIMEOUT_ERROR.getCode());

    assertEquals("Authentication Error", ApiError.AUTHENTICATION_ERROR.getMessage());
    assertEquals(ExceptionType.AUTH_EXC, ApiError.AUTHENTICATION_ERROR.getType());
    assertEquals(0, ApiError.AUTHENTICATION_ERROR.getCode());

    assertEquals("Authorization Error", ApiError.AUTHORIZATION_ERROR.getMessage());
    assertEquals(ExceptionType.AUTH_EXC, ApiError.AUTHORIZATION_ERROR.getType());
    assertEquals(0, ApiError.AUTHORIZATION_ERROR.getCode());

    assertEquals(ApiError.GENERAL_ERROR_MSG, ApiError.GENERAL_ERROR.getMessage());
    assertEquals(ExceptionType.PROCESSING_EXCEPTION, ApiError.GENERAL_ERROR.getType());
    assertEquals(0, ApiError.GENERAL_ERROR.getCode());

    assertEquals("Unsupported Media Type Error", ApiError.INVALID_MEDIA_ERROR.getMessage());
    assertEquals(ExceptionType.NO_IMPL, ApiError.INVALID_MEDIA_ERROR.getType());
    assertEquals(0, ApiError.INVALID_MEDIA_ERROR.getCode());

    assertEquals("Generic Invalid Request Error", ApiError.INVALID_REQUEST_ERROR.getMessage());
    assertEquals(ExceptionType.BIZ_RULE, ApiError.INVALID_REQUEST_ERROR.getType());
    assertEquals(0, ApiError.INVALID_REQUEST_ERROR.getCode());

    assertEquals("Unsupported Media Type Error", ApiError.PROVIDER_ERROR.getMessage());
    assertEquals(ExceptionType.PROCESSING_EXCEPTION, ApiError.PROVIDER_ERROR.getType());
    assertEquals(0, ApiError.PROVIDER_ERROR.getCode());


    assertEquals("Resource Not Found", ApiError.RESOURCE_NOT_FOUND.getMessage());
    assertEquals(ExceptionType.PROCESSING_EXCEPTION, ApiError.RESOURCE_NOT_FOUND.getType());
    assertEquals(0, ApiError.RESOURCE_NOT_FOUND.getCode());

    assertEquals("Session Has Expired", ApiError.SESSION_HAS_EXPIRED.getMessage());
    assertEquals(ExceptionType.BIZ_RULE, ApiError.SESSION_HAS_EXPIRED.getType());
    assertEquals(0, ApiError.SESSION_HAS_EXPIRED.getCode());
  }

  @Test
  public void testConstructors() throws Exception {
    apiError = new ApiError(TEST_STR);
    assertEquals(TEST_STR, apiError.getMessage());

    apiError = new ApiError(TEST_STR, ExceptionType.ADVISORY);
    assertEquals(TEST_STR, apiError.getMessage());
    assertEquals(ExceptionType.ADVISORY, apiError.getType());
  }
}