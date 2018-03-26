package com.sbsk.service.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("A user should")
public class UserUtilsTests {

  private UserUtils userUtils;

  @BeforeEach
  public void setUp() throws Exception {
      userUtils = new UserUtils();
  }

  @Test
  public void beAdultWhenAgeIsGreaterThanThreshold() {
    Integer age = 18;

    Boolean result = userUtils.isAdult(age);

    assertThat(result).isTrue();
  }

  @Test
  public void notBeAdultWhenAgeIsLessThanThreshold() {
    Integer age = 17;

    Boolean result = userUtils.isAdult(age);

    assertThat(result).isFalse();
  }
}
