package com.sbsk.service.graphql.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.sbsk.dtos.user.UserRequestDto;
import com.sbsk.persistence.entities.user.UserEntity;
import com.sbsk.persistence.repositories.UserRepository;
import com.sbsk.service.converters.user.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mutation implements GraphQLMutationResolver {

  private UserRepository userRepository;
  private UserConverter userConverter;

  @Autowired
  public Mutation(UserRepository userRepository, UserConverter userConverter) {
    this.userRepository = userRepository;
    this.userConverter = userConverter;
  }

  public UserEntity createUser(String firstName, String lastName, Integer age) {
    UserRequestDto userRequestDto = new UserRequestDto(firstName, lastName, age);
    UserEntity userEntity = userConverter.convertUserRequestDtoToUserEntity(userRequestDto);
    return userRepository.save(userEntity);
  }
}
