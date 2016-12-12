package com.sbsk.web.configuration;

import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActuatorConfiguration {
    @Bean
    public static Endpoint<String> pingEndpoint() {
        return new Endpoint<String>() {
            @Override
            public String getId() {
                return "ping";
            }

            @Override
            public boolean isEnabled() {
                return true;
            }

            @Override
            public boolean isSensitive() {
                return false;
            }

            @Override
            public String invoke() {
                return "OK";
            }
        };
    }
}