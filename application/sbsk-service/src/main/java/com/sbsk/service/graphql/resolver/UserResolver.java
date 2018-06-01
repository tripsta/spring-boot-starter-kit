package com.sbsk.service.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.sbsk.persistence.entities.user.UserEntity;
import com.sbsk.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserResolver implements GraphQLResolver<UserEntity> {

  private UserRepository userRepository;

  @Autowired
  public UserResolver(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
}
