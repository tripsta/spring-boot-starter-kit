package com.sbsk.service.services.user;

import com.sbsk.dtos.user.UserRequestDto;
import com.sbsk.dtos.user.UserResponseDto;
import com.sbsk.persistence.entities.user.UserEntity;
import com.sbsk.persistence.repositories.UserRepository;
import com.sbsk.service.converters.user.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserConverter userConverter;

  @Override
  public UserResponseDto createUser(UserRequestDto userRequestDto) {
    UserEntity userEntity = userConverter.convertUserRequestDtoToUserEntity(userRequestDto);
    userRepository.save(userEntity);
    return userConverter.convertUserEntityToUserResponseDto(userEntity);
  }

}
