package com.sbsk.service.utils;

public class UserUtils {

  private static final Integer ADULT_AGE_THRESHOLD = 18;

  public static Boolean isAdult(Integer age) {
    return age >= ADULT_AGE_THRESHOLD;
  }

}
