package com.sbsk.web.com.sbsk.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class SpringUtils {

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
