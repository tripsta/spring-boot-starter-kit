package com.sbsk.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sbsk.model.exception.ExceptionType;

public class ApiError {

	private String message;
	@JsonIgnore
	private int code;
	private ExceptionType type;

	public static final String GENERAL_ERROR_MSG = "Generic Error";

	/**
	 * Session Has Expired
	 */
	public static final ApiError SESSION_HAS_EXPIRED = new ApiError("Session Has Expired", ExceptionType.BIZ_RULE);

	/**
	 * Generic Unknown error
	 * */
	public static final ApiError GENERAL_ERROR = new ApiError(GENERAL_ERROR_MSG, ExceptionType.PROCESSING_EXCEPTION);

	/**
	 * Request was not authenticated
	 */
	public static final ApiError AUTHENTICATION_ERROR = new ApiError("Authentication Error", ExceptionType.AUTH_EXC);

	/**
	 * Request was not authorized for the required operation
	 */
	public static final ApiError AUTHORIZATION_ERROR = new ApiError("Authorization Error", ExceptionType.AUTH_EXC);

	/**
	 * Request was authenticated but the timestamp on it was expired
	 */
	public static final ApiError AUTH_TIMEOUT_ERROR = new ApiError("Authorization Timeout", ExceptionType.AUTH_TIMEOUT);

	/**
	 * Request is invalid
	 */
	public static final ApiError INVALID_REQUEST_ERROR = new ApiError("Generic Invalid Request Error", ExceptionType.BIZ_RULE);

	/**
	 * Unsupported Media Type on Request Media
	 */
	public static final ApiError INVALID_MEDIA_ERROR = new ApiError("Unsupported Media Type Error", ExceptionType.NO_IMPL);

	/**
	 * Provider error
	 */
	public static final ApiError PROVIDER_ERROR = new ApiError("Unsupported Media Type Error", ExceptionType.PROCESSING_EXCEPTION);
	
	/**
	 * Resource Not Found
	 */
	public static final ApiError RESOURCE_NOT_FOUND = new ApiError("Resource Not Found", ExceptionType.PROCESSING_EXCEPTION);
	

	public ApiError() {}
	
	public ApiError(String message) {
		this.message = message;
		this.type = ExceptionType.UNKNOWN;
	}

	public ApiError(String message, ExceptionType type) {
		this.message = message;
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public ExceptionType getType() {
		return type;
	}

	public void setType(ExceptionType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "AddOnsApiError [message=" + message + ", code=" + code + ", type=" + type + "]";
	}
	
	
	
}
