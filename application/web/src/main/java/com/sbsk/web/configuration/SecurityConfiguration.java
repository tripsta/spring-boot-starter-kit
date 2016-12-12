package com.sbsk.web.configuration;

import com.sbsk.web.com.sbsk.utils.SpringUtils;
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

    @Autowired
    Environment environment;

    @Autowired
    SpringUtils springUtils;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(environment.getProperty("security.role.user.username"))
                .password(environment.getProperty("security.role.user.password"))
                .roles("USER")
                .and()
                .withUser(environment.getProperty("security.role.admin.username"))
                .password(environment.getProperty("security.role.admin.password"))
                .roles("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // since we have a stateless API we dont need CSRF
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/public/**").permitAll()
                .antMatchers("/admin/ping").permitAll()
                .antMatchers("/admin/health").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN").anyRequest().authenticated()
                .and()
                .httpBasic();

        http.headers().frameOptions().disable();

        if (springUtils.isDevelopmentProfile()) {
            http.authorizeRequests().antMatchers("/admin/**").permitAll();
        }
    }
}
