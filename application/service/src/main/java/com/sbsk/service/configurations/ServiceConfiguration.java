package com.sbsk.service.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sbsk.persistence.repository.repositories.UserRepository;
import com.sbsk.service.services.user.UserService;
import com.sbsk.service.services.user.UserServiceImpl;

@Configuration
public class ServiceConfiguration {

//	@Bean(name = "userService")
//	public UserService userService(UserRepository userRepository) {
//		return new UserServiceImpl(userRepository);
//	}

}
