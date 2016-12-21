package com.sbsk.service.utils;

import org.springframework.stereotype.Component;

@Component
public class UserUtils {

  private static final Integer ADULT_AGE_THRESHOLD = 18;

  public Boolean isAdult(Integer age) {
    return age >= ADULT_AGE_THRESHOLD;
  }

}
