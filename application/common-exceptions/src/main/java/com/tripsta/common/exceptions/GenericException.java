package com.tripsta.common.exceptions;

public class GenericException extends Exception {

  private static final long serialVersionUID = 5820181797566873346L;

  public GenericException() {
    super();
  }

  public GenericException(String message, Throwable cause,
                          boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public GenericException(String message, Throwable cause) {
    super(message, cause);
  }

  public GenericException(String message) {
    super(message);
  }

  public GenericException(Throwable cause) {
    super(cause);
  }

}
