package com.sbsk.service.converters.user;

import com.sbsk.dtos.user.UserRequestDto;
import com.sbsk.dtos.user.UserResponseDto;
import com.sbsk.persistence.entities.user.UserEntity;
import com.sbsk.service.utils.UserUtils;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

  public static UserEntity userRequestDtoToDao(UserRequestDto userRequestDto) {
    return new UserEntity(
        userRequestDto.getFirstName(),
        userRequestDto.getLastName(),
        userRequestDto.getAge(),
        UserUtils.isAdult(userRequestDto.getAge()),
        new Integer(12345)
    );
  }

  public static UserResponseDto userDaoToResponseDto(UserEntity userEntity) {
    return new UserResponseDto(
        userEntity.getFirstName(),
        userEntity.getLastName(),
        userEntity.getAge(),
        userEntity.getIsAdult()
    );
  }

}
