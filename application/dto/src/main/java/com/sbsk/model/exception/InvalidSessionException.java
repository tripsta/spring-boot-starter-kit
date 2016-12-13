package com.sbsk.model.exception;
import static com.sbsk.model.exception.ExceptionMessages.INVALID_SESSION;

public class InvalidSessionException extends GenericException {

	private static final long serialVersionUID = 8794764361460080293L;

	public InvalidSessionException() {
		super(INVALID_SESSION);
	}

	public InvalidSessionException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidSessionException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidSessionException(String message) {
		super(message);
	}

	public InvalidSessionException(Throwable cause) {
		super(cause);
	}

}
