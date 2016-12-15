package com.sbsk.model.exception;

public class NoSessionIdException extends GenericException {

  private static final long serialVersionUID = 819227927953131325L;

  public NoSessionIdException() {
    super();
  }

  public NoSessionIdException(String message, Throwable cause,
                              boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public NoSessionIdException(String message, Throwable cause) {
    super(message, cause);
  }

  public NoSessionIdException(String message) {
    super(message);
  }

  public NoSessionIdException(Throwable cause) {
    super(cause);
  }
}
