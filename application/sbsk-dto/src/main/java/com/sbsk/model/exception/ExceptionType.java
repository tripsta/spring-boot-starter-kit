package com.sbsk.model.exception;

public enum ExceptionType {

  /**
   * Custom Exception Types, used to group exceptions
   * <p>
   * UNKNOWN - Unhandled Exception
   * NO_IMPL - Not yet implemented
   * BIZ_RULE - Business Rule
   * AUTH_EXC - Authentication exception
   * AUTH_TIMEOUT - Authentication Timeout
   * AUTHORIZATION - Authorization exception
   * FIELD_VALIDATION - Field is not valid
   * RE_FIELD_MISSING - Required Field Missing from Request
   * ADVISORY - Informational Exception
   * PROCESSING_EXCEPTION -
   * APPLICATION_ERROR -
   */

  UNKNOWN("1"), NO_IMPL("2"), BIZ_RULE("3"), AUTH_EXC("4"), AUTH_TIMEOUT("5"), AUTHORIZATION("6"), FIELD_VALIDATION("7"),
  RE_FIELD_MISSING("10"), ADVISORY("11"), PROCESSING_EXCEPTION("12"), APPLICATION_ERROR("13");

  private String exceptionTypeCode;

  ExceptionType(String exceptionTypeCode) {
    this.exceptionTypeCode = exceptionTypeCode;
  }

  public String getErrorCode() {
    return exceptionTypeCode;
  }
}
