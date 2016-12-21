package com.sbsk.service.converters.user;

import com.sbsk.dtos.user.UserRequestDto;
import com.sbsk.dtos.user.UserResponseDto;
import com.sbsk.persistence.entities.user.UserEntity;
import com.sbsk.service.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

  @Autowired
  private UserUtils userUtils;

  public UserEntity convertUserRequestDtoToUserEntity(UserRequestDto userRequestDto) {

    UserEntity userEntity = new UserEntity();
    userEntity.setFirstName(userRequestDto.getFirstName());
    userEntity.setLastName(userRequestDto.getLastName());
    userEntity.setAge(userRequestDto.getAge());
    userEntity.setIsAdult(userUtils.isAdult(userRequestDto.getAge()));
    return userEntity;
  }

  public UserResponseDto convertUserEntityToUserResponseDto(UserEntity userEntity) {
      UserResponseDto userResponseDto = new UserResponseDto();
      userResponseDto.setId(userEntity.getId());
      userResponseDto.setFirstName(userEntity.getFirstName());
      userResponseDto.setLastName(userEntity.getLastName());
      userResponseDto.setAge(userEntity.getAge());
      userResponseDto.setIsAdult(userEntity.getIsAdult());
    return userResponseDto;
  }

}
