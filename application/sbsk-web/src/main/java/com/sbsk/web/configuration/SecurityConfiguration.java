package com.sbsk.web.configuration;

import com.sbsk.web.utils.ApplicationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@PropertySource(value = "classpath:security.properties")
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private static final String ROLE_ADMIN = "ADMIN";
  private static final String ROLE_USER = "USER";

  @Autowired
  Environment environment;

  @Autowired
  ApplicationUtils applicationUtils;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
        .withUser(environment.getProperty("security.role.user.username"))
        .password(environment.getProperty("security.role.user.password"))
        .roles(ROLE_USER)
        .and()
        .withUser(environment.getProperty("security.role.admin.username"))
        .password(environment.getProperty("security.role.admin.password"))
        .roles(ROLE_USER, ROLE_ADMIN);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        // since we have a stateless API we dont need CSRF
        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/public/**").permitAll()
        .antMatchers("/actuator/health").permitAll()
        .antMatchers("/actuator/**").hasRole(ROLE_ADMIN).anyRequest().authenticated()
        .antMatchers("/publicerror").permitAll()
        .and()
        .httpBasic();

    if (applicationUtils.isDevelopmentProfile()) {
      http.authorizeRequests().antMatchers("**").permitAll();
    }

    http.headers().frameOptions().disable();
  }
}
