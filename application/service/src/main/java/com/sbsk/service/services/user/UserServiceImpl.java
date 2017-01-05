package com.sbsk.service.services.user;

import com.sbsk.dtos.user.UserRequestDto;
import com.sbsk.dtos.user.UserResponseDto;
import com.sbsk.persistence.entities.user.UserEntity;
import com.sbsk.persistence.repositories.UserRepository;
import com.sbsk.service.converters.user.UserConverter;
import com.sbsk.service.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private UserRepository userRepository;


    @Override
    public List<UserResponseDto> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userConverter.convertUserEntitiesToUserResponseDtos(userEntities);
    }

    @Override
    public UserResponseDto getUser(Long id) {
        if (!userValidator.userExists(id)) {
            throw new RuntimeException("User does not exist");
        }
        UserEntity userEntity = userRepository.findOne(id);
        return userConverter.convertUserEntityToUserResponseDto(userEntity);
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        if (!userValidator.validateUser(userRequestDto)) {
            throw new RuntimeException("Invalid input parameters");
        }
        UserEntity userEntity = userConverter.convertUserRequestDtoToUserEntity(userRequestDto);
        userRepository.save(userEntity);
        return userConverter.convertUserEntityToUserResponseDto(userEntity);
    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) {
        if (!userValidator.userExists(id)) {
            throw new RuntimeException("User does not exist");
        }
        if (!userValidator.validateUser(userRequestDto)) {
            throw new RuntimeException("Invalid input parameters");
        }
        UserEntity userEntity = userConverter.convertUserRequestDtoToUserEntity(userRequestDto);
        userEntity.setId(id);
        userRepository.save(userEntity);
        return userConverter.convertUserEntityToUserResponseDto(userEntity);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userValidator.userExists(id)) {
            throw new RuntimeException("User does not exist");
        }
        userRepository.delete(id);
    }

}
