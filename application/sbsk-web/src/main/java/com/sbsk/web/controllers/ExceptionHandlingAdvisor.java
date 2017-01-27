package com.sbsk.web.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.RollbackException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerMapping;

import com.sbsk.model.ApiError;
import com.sbsk.model.ApiErrorResponse;
import com.tripsta.common.exceptions.ExceptionType;
import com.tripsta.common.exceptions.GenericException;
import com.tripsta.common.exceptions.InvalidSessionException;
import com.tripsta.common.exceptions.NoSessionIdException;
import com.tripsta.common.exceptions.ResourceNotFoundException;
import com.tripsta.common.exceptions.SessionNotFoundException;

@ControllerAdvice
public class ExceptionHandlingAdvisor {

  private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlingAdvisor.class);
  private static final String MALFORMED_DATA = "Could not read JSON. Request contains malformed data";

  public static ApiErrorResponse createErrorResponse(String message, ExceptionType type) {
    ApiErrorResponse errorResponse = new ApiErrorResponse();
    errorResponse.addError(new ApiError(message, type));
    return errorResponse;
  }

  /* Spring MVC Exceptions*/
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public HttpEntity<ApiErrorResponse> handleJsonParseExceptions(HttpMessageNotReadableException e) {
    logger.warn("Malformed json for request " + e.getMessage(), e);
    return new ResponseEntity<ApiErrorResponse>(createErrorResponse(MALFORMED_DATA, ExceptionType.BIZ_RULE), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public HttpEntity<ApiErrorResponse> handleJsonParseExceptions(HttpRequestMethodNotSupportedException e) {
    logger.error("Unknown handler request " + e.getMessage(), e);
    return new ResponseEntity<ApiErrorResponse>(createErrorResponse("no handler", ExceptionType.NO_IMPL), HttpStatus.BAD_REQUEST);
  }


	/* API specific Exceptions*/

  @ExceptionHandler(RollbackException.class)
  public HttpEntity<ApiErrorResponse> rollbackException(RollbackException e) {
    logger.error("Unknown handler request " + e.getMessage(), e);
    return new ResponseEntity<ApiErrorResponse>(createErrorResponse("Could not process your request ", ExceptionType.APPLICATION_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * NoSessionIdException,
   * Session Id is required
   *
   * @param e
   * @return
   */

  @ExceptionHandler(NoSessionIdException.class)
  public HttpEntity<ApiErrorResponse> handleNoSessionIdException(NoSessionIdException e) {
    logger.warn("Session Id is required " + e.getMessage(), e);
    ApiErrorResponse errorResponse = new ApiErrorResponse();
    errorResponse.addError(new ApiError("session id is required", ExceptionType.ADVISORY));

    return new ResponseEntity<ApiErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  /**
   * SessionNotFoundException,
   * Session was not found for the requested Session Id
   *
   * @param e
   * @return
   */

  @ExceptionHandler(SessionNotFoundException.class)
  public HttpEntity<ApiErrorResponse> handleSessionNotFoundException(SessionNotFoundException e) {
    logger.warn("Session not found for request " + e.getMessage(), e);

    return new ResponseEntity<ApiErrorResponse>(createErrorResponse(e.getMessage(), ExceptionType.ADVISORY), HttpStatus.BAD_REQUEST);
  }

  /**
   * InvalidSessionException,
   * Data were supposed to be found in Session but was not
   *
   * @param e
   * @return
   */

  @ExceptionHandler(InvalidSessionException.class)
  public HttpEntity<ApiErrorResponse> handleInvalidSessionException(InvalidSessionException e) {
    logger.error("Invalid session for request " + e.getMessage(), e);

    return new ResponseEntity<ApiErrorResponse>(createErrorResponse(e.getMessage(), ExceptionType.ADVISORY), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public HttpEntity<ApiErrorResponse> handleResourceNotFoundException(ResourceNotFoundException e) {
    logger.error("ResourceNotFoundException for request " + e.getMessage(), e);
    return new ResponseEntity<ApiErrorResponse>(createErrorResponse(e.getMessage(), ExceptionType.ADVISORY), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public HttpEntity<ApiErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    logger.warn("Invalid request " + e.getMessage(), e);

    List<ApiError> errorsList = new ArrayList<ApiError>();
    ApiErrorResponse errorResponse = new ApiErrorResponse();

    for (ObjectError error : e.getBindingResult().getAllErrors()) {
      String errorMsg = createErrorMessage(error);
      errorsList.add(new ApiError(errorMsg, ExceptionType.FIELD_VALIDATION));
    }
    errorResponse.addErrors(errorsList);
    return new ResponseEntity<ApiErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
  }

	/* Unhandled Exception handler */

  @ExceptionHandler(GenericException.class)
  public HttpEntity<ApiErrorResponse> handleGenericException(GenericException e, HttpServletRequest request) {
    logger.error("Generic exception for request " + e.getMessage(), e);

    request.removeAttribute(HandlerMapping.PRODUCIBLE_MEDIA_TYPES_ATTRIBUTE);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return new ResponseEntity<ApiErrorResponse>(createErrorResponse(e.getMessage(), ExceptionType.APPLICATION_ERROR), headers, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(Exception.class)
  public HttpEntity<ApiErrorResponse> handleAllExceptions(Exception e) {
    logger.error("Unknown exception for request " + e.getMessage(), e);
    return new ResponseEntity<ApiErrorResponse>(createErrorResponse(e.getMessage(), ExceptionType.UNKNOWN), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  private String createErrorMessage(ObjectError error) {
    StringBuilder errorMessage = new StringBuilder("");
    if (error instanceof FieldError) {
      FieldError fieldError = (FieldError) error;

      errorMessage.append("Validation failed: field [")
          .append(fieldError.getField()).append("] ")
          .append(error.getDefaultMessage());
    } else {
      errorMessage.append("[").append(error).append("] ");
    }
    return errorMessage.toString();
  }
}