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
    return new UserEntity(
        userRequestDto.getFirstName(),
        userRequestDto.getLastName(),
        userRequestDto.getAge(),
        UserUtils.isAdult(userRequestDto.getAge()),
        12345
    );
  }

  public UserResponseDto convertUserEntityToUserResponseDto(UserEntity userEntity) {
    return new UserResponseDto(
        userEntity.getFirstName(),
        userEntity.getLastName(),
        userEntity.getAge(),
        userEntity.getIsAdult()
    );
  }

}
