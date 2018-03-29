package com.sbsk.web.configuration;

import com.sbsk.web.utils.ApplicationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@PropertySource(value = "classpath:security.properties")
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private static final String ROLE_ADMIN = "ADMIN";
  private static final String ROLE_USER = "USER";

  private static final String[] AUTH_WHITELIST = {
          "/public/**",
          "/admin/health",
          "/publicerror",
          "/swagger-resources/**",
          "/swagger-ui.html",
          "/v2/api-docs",
          "/webjdars/**"
  };

  private static final String[] AUTH_ADMIN = {
          "/admin/**"
  };

  @Value("${security.role.user.username}")
  private String userRoleUsername;

  @Value("${security.role.user.password}")
  private String userRolePassword;

  @Value("${security.role.admin.username}")
  private String adminRoleUsername;

  @Value("${security.role.admin.password}")
  private String adminRolePassword;

  @Autowired
  private ApplicationUtils applicationUtils;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    auth.inMemoryAuthentication()
        .withUser(userRoleUsername)
        .password(encoder.encode(userRolePassword))
        .roles(ROLE_USER)
        .and()
        .withUser(adminRoleUsername)
        .password(encoder.encode(adminRolePassword))
        .roles(ROLE_USER, ROLE_ADMIN);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // since we have a stateless API we don't need CSRF
    http.csrf().disable();

    if (applicationUtils.isDevelopmentProfile()) {
      http.authorizeRequests().antMatchers("**").permitAll();
    } else {
      http.authorizeRequests()
          .antMatchers(AUTH_WHITELIST).permitAll()
          .antMatchers(AUTH_ADMIN).hasRole(ROLE_ADMIN)
          .and()
          .httpBasic();
    }

    http.headers().frameOptions().disable();
  }
}
