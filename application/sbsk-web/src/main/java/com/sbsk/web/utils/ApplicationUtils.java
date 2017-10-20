package com.sbsk.web.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ApplicationUtils {

  public final static String PROFILE_DEVELOPMENT = "development";
  public final static String PROFILE_PRODUCTION = "production";
  public final static String PROFILE_STAGING = "staging";
  public final static String PROFILE_TEST = "test";

  @Autowired
  Environment environment;

  public boolean isDevelopmentProfile() {
    for (String profile : environment.getActiveProfiles()) {
      if (profile.equals("development")) {
        return true;
      }
    }
    return false;
  }
}
