package com.sbsk.model.exception;

import static com.sbsk.model.exception.ExceptionMessages.SESSION_NOT_FOUND;

public class SessionNotFoundException extends GenericException {

	private static final long serialVersionUID = 6627126171351043691L;

	public SessionNotFoundException() {
		super(SESSION_NOT_FOUND);
	}

	public SessionNotFoundException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SessionNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public SessionNotFoundException(String message) {
		super(message);
	}

	public SessionNotFoundException(Throwable cause) {
		super(cause);
	}

	
}
