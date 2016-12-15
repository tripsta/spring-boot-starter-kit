package com.sbsk.web.controllers;

import com.sbsk.model.ApiErrorResponse;
import com.sbsk.model.exception.ExceptionType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/publicerror")
public class ErrorPublicController {


  @RequestMapping(method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseBody
  public HttpEntity<ApiErrorResponse> getError() {
    return
        new ResponseEntity<ApiErrorResponse>(
            ExceptionHandlingAdvisor.createErrorResponse("Not authorized", ExceptionType.AUTH_EXC),
            HttpStatus.UNAUTHORIZED
        );
  }

}
