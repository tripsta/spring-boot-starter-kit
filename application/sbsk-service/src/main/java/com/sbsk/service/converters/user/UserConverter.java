package com.sbsk.service.converters.user;

import com.sbsk.dtos.user.UserRequestDto;
import com.sbsk.dtos.user.UserResponseDto;
import com.sbsk.persistence.entities.user.UserEntity;
import com.sbsk.service.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserConverter {

    @Autowired
    private UserUtils userUtils;

    public List<UserResponseDto> convertUserEntitiesToUserResponseDtos(List<UserEntity> userEntities) {
        List<UserResponseDto> userResponseDtos = new ArrayList<UserResponseDto>();
        for (UserEntity userEntity : userEntities) {
            userResponseDtos.add(this.convertUserEntityToUserResponseDto(userEntity));
        }
        return userResponseDtos;
    }

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
