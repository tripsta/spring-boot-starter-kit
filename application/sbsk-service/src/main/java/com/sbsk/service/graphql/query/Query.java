package com.sbsk.service.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.sbsk.persistence.entities.user.UserEntity;
import com.sbsk.persistence.repositories.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver {

  private UserRepository userRepository;

  public Query(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Iterable<UserEntity> users() {
    return userRepository.findAll();
  }
}
