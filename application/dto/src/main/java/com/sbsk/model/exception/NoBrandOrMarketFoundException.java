package com.sbsk.model.exception;

public class NoBrandOrMarketFoundException extends GenericException {

	public static final String INVALID_BRAND_MARKET = "Invalid brand/market";
	private static final long serialVersionUID = -5147768341747514255L;

	public NoBrandOrMarketFoundException() {
		super(INVALID_BRAND_MARKET);
	}

	public NoBrandOrMarketFoundException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NoBrandOrMarketFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoBrandOrMarketFoundException(String message) {
		super(message);
	}

	public NoBrandOrMarketFoundException(Throwable cause) {
		super(cause);
	}

}
