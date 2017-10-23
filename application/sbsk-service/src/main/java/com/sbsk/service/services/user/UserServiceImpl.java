package com.sbsk.service.services.user;

import com.sbsk.dtos.user.UserRequestDto;
import com.sbsk.dtos.user.UserResponseDto;
import com.sbsk.persistence.entities.couchbase.User;
import com.sbsk.persistence.repositories.UserRepository;
import com.sbsk.service.converters.user.UserConverter;
import com.sbsk.service.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
//        List<User> users = userRepository.findAll();
        return null;
    }

    @Override
    public UserResponseDto getUser(String id) {
        if (!userValidator.userExists(id)) {
            throw new RuntimeException("User does not exist");
        }
        User user = userRepository.findOne(id);
        return userConverter.convertUserEntityToUserResponseDto(user);
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        if (!userValidator.validateUser(userRequestDto)) {
            throw new RuntimeException("Invalid input parameters");
        }
        User user = userConverter.convertUserRequestDtoToUserEntity(userRequestDto);
        userRepository.save(user);
        return userConverter.convertUserEntityToUserResponseDto(user);
    }

    @Override
    public UserResponseDto updateUser(String id, UserRequestDto userRequestDto) {
        if (!userValidator.userExists(id)) {
            throw new RuntimeException("User does not exist");
        }
        if (!userValidator.validateUser(userRequestDto)) {
            throw new RuntimeException("Invalid input parameters");
        }
        User user = userConverter.convertUserRequestDtoToUserEntity(userRequestDto);
        user.setId(id);
        userRepository.save(user);
        return userConverter.convertUserEntityToUserResponseDto(user);
    }

    @Override
    public void deleteUser(String id) {
        if (!userValidator.userExists(id)) {
            throw new RuntimeException("User does not exist");
        }
        userRepository.delete(id);
    }

}
