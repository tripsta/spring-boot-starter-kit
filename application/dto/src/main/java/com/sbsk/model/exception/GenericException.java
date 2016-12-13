package com.sbsk.model.exception;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.sbsk.model.ApiError;

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
	
	
	protected static String prepareErrorMessages(List<ApiError> errors) {
		StringBuffer buffer = new StringBuffer();
		
		if (!CollectionUtils.isEmpty(errors)) {
			for (Iterator<ApiError> it = errors.iterator(); it.hasNext();) {
				ApiError addOnsApiError = it.next();
				buffer.append(addOnsApiError.getMessage());
				if (it.hasNext())
					buffer.append("\n");	
			}
		}
		
		return buffer.toString();
	}

}
