package com.sbsk.service.converters.user;

import com.sbsk.dtos.user.UserRequestDto;
import com.sbsk.dtos.user.UserResponseDto;
import com.sbsk.persistence.entities.couchbase.User;
import com.sbsk.service.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserConverter {

    @Autowired
    private UserUtils userUtils;

    public List<UserResponseDto> convertUserEntitiesToUserResponseDtos(List<User> users) {
        List<UserResponseDto> userResponseDtos = new ArrayList<UserResponseDto>();
        for (User user : users) {
            userResponseDtos.add(this.convertUserEntityToUserResponseDto(user));
        }
        return userResponseDtos;
    }

    public User convertUserRequestDtoToUserEntity(UserRequestDto userRequestDto) {
        User user = new User();
        user.setFirstName(userRequestDto.getFirstName());
        user.setLastName(userRequestDto.getLastName());
        user.setAge(userRequestDto.getAge());
        user.setIsAdult(userUtils.isAdult(userRequestDto.getAge()));
        return user;
    }

    public UserResponseDto convertUserEntityToUserResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setFirstName(user.getFirstName());
        userResponseDto.setLastName(user.getLastName());
        userResponseDto.setAge(user.getAge());
        userResponseDto.setIsAdult(user.getIsAdult());
        return userResponseDto;
    }

}
