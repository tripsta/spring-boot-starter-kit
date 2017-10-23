package com.sbsk.service.services.user;

import com.sbsk.dtos.user.UserRequestDto;
import com.sbsk.dtos.user.UserResponseDto;
import com.sbsk.persistence.entities.couchbase.User;
import com.sbsk.persistence.repositories.UserRepository;
import com.sbsk.service.converters.user.UserConverter;
import com.sbsk.service.validators.UserValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UserServiceImpl.class)
public class UserServiceImplTests {

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    private UserValidator userValidator;

    @Mock
    private UserConverter userConverter;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserRequestDto userRequestDto;

    @Mock
    private User user;

    @Mock
    private UserResponseDto userResponseDto;

//    @Test
//    public void getAllUser_shouldFollowTheRightFlow() {
//
//        List<User> users = new ArrayList<User>();
//        List<UserResponseDto> userResponseDtos = new ArrayList<UserResponseDto>();
//        when(userRepository.findAll()).thenReturn(users);
//        when(userConverter.convertUserEntitiesToUserResponseDtos(users)).thenReturn(userResponseDtos);
//
//        userServiceImpl.getAllUsers();
//
//        verify(userRepository, times(1)).findAll();
//        verify(userConverter, times(1)).convertUserEntitiesToUserResponseDtos(users);
//    }

    @Test
    public void getUser_shouldFollowTheRightFlow_whenEverythingIsValidatedCorrectly() {

        String id = new String("0");
        when(userValidator.userExists(id)).thenReturn(true);
        when(userRepository.findOne(id)).thenReturn(user);
        when(userConverter.convertUserEntityToUserResponseDto(user)).thenReturn(userResponseDto);

        userServiceImpl.getUser(id);

        verify(userValidator, times(1)).userExists(id);
        verify(userRepository, times(1)).findOne(id);
        verify(userConverter, times(1)).convertUserEntityToUserResponseDto(user);
    }

    @Test
    public void getUser_shouldRaiseError_whenUserDoesNotExist() {

        String id = new String("0");
        when(userValidator.userExists(id)).thenReturn(false);
        when(userRepository.findOne(id)).thenReturn(user);
        when(userConverter.convertUserEntityToUserResponseDto(user)).thenReturn(userResponseDto);

        try {
            userServiceImpl.getUser(id);
            fail("Exception should have thrown");
        } catch (RuntimeException e) {
            assertEquals(e.getMessage(), "User does not exist");
        }

        verify(userValidator, times(1)).userExists(id);
        verify(userRepository, times(0)).findOne(id);
        verify(userConverter, times(0)).convertUserEntityToUserResponseDto(user);
    }

    @Test
    public void createUser_shouldFollowTheRightFlow_whenEverythingIsValidatedCorrectly() {

        when(userValidator.validateUser(userRequestDto)).thenReturn(true);
        when(userConverter.convertUserRequestDtoToUserEntity(userRequestDto)).thenReturn(user);
        when(userConverter.convertUserEntityToUserResponseDto(user)).thenReturn(userResponseDto);

        userServiceImpl.createUser(userRequestDto);

        verify(userValidator, times(1)).validateUser(userRequestDto);
        verify(userConverter, times(1)).convertUserRequestDtoToUserEntity(userRequestDto);
        verify(userRepository, times(1)).save(user);
        verify(userConverter, times(1)).convertUserEntityToUserResponseDto(user);
    }

    @Test
    public void createUser_shouldRaiseError_whenSomethingIsNotValidatedCorrectly() {

        when(userValidator.validateUser(userRequestDto)).thenReturn(false);
        when(userConverter.convertUserRequestDtoToUserEntity(userRequestDto)).thenReturn(user);
        when(userConverter.convertUserEntityToUserResponseDto(user)).thenReturn(userResponseDto);

        try {
            userServiceImpl.createUser(userRequestDto);
            fail("Exception should have thrown");
        } catch (RuntimeException e) {
            assertEquals(e.getMessage(), "Invalid input parameters");
        }

        verify(userValidator, times(1)).validateUser(userRequestDto);
        verify(userConverter, times(0)).convertUserRequestDtoToUserEntity(userRequestDto);
        verify(userRepository, times(0)).save(user);
        verify(userConverter, times(0)).convertUserEntityToUserResponseDto(user);
    }

    @Test
    public void updateUser_shouldFollowTheRightFlow_whenEverythingIsValidatedCorrectly() {

        when(user.getId()).thenReturn(new String("1"));
        when(userValidator.userExists(user.getId())).thenReturn(true);
        when(userValidator.validateUser(userRequestDto)).thenReturn(true);
        when(userConverter.convertUserRequestDtoToUserEntity(userRequestDto)).thenReturn(user);
        when(userConverter.convertUserEntityToUserResponseDto(user)).thenReturn(userResponseDto);

        userServiceImpl.updateUser(user.getId(), userRequestDto);

        verify(userValidator,times(1)).userExists(user.getId());
        verify(userValidator, times(1)).validateUser(userRequestDto);
        verify(userConverter, times(1)).convertUserRequestDtoToUserEntity(userRequestDto);
        verify(user, times(1)).setId(anyString());
        verify(userRepository, times(1)).save(user);
        verify(userConverter, times(1)).convertUserEntityToUserResponseDto(user);
    }

    @Test
    public void updateUser_shouldRaiseError_whenSomethingIsNotValidatedCorrectly() {

        when(user.getId()).thenReturn(new String("1"));
        when(userValidator.userExists(user.getId())).thenReturn(true);
        when(userValidator.validateUser(userRequestDto)).thenReturn(false);
        when(userConverter.convertUserRequestDtoToUserEntity(userRequestDto)).thenReturn(user);
        when(userConverter.convertUserEntityToUserResponseDto(user)).thenReturn(userResponseDto);

        try {
            userServiceImpl.updateUser(user.getId(), userRequestDto);
            fail("Exception should have thrown");
        } catch (RuntimeException e) {
            assertEquals(e.getMessage(), "Invalid input parameters");
        }

        verify(userValidator, times(1)).userExists(user.getId());
        verify(userValidator, times(1)).validateUser(userRequestDto);
        verify(userConverter, times(0)).convertUserRequestDtoToUserEntity(userRequestDto);
        verify(user, times(0)).setId(anyString());
        verify(userRepository, times(0)).save(user);
        verify(userConverter, times(0)).convertUserEntityToUserResponseDto(user);
    }

    @Test
    public void updateUser_shouldRaiseError_whenUserDoesNotExist() {

        when(user.getId()).thenReturn(new String("1"));
        when(userValidator.userExists(user.getId())).thenReturn(false);
        when(userValidator.validateUser(userRequestDto)).thenReturn(true);
        when(userConverter.convertUserRequestDtoToUserEntity(userRequestDto)).thenReturn(user);
        when(userConverter.convertUserEntityToUserResponseDto(user)).thenReturn(userResponseDto);

        try {
            userServiceImpl.updateUser(user.getId(), userRequestDto);
            fail("Exception should have thrown");
        } catch (RuntimeException e) {
            assertEquals(e.getMessage(), "User does not exist");
        }

        verify(userValidator, times(1)).userExists(user.getId());
        verify(userValidator, times(0)).validateUser(userRequestDto);
        verify(userConverter, times(0)).convertUserRequestDtoToUserEntity(userRequestDto);
        verify(user, times(0)).setId(anyString());
        verify(userRepository, times(0)).save(user);
        verify(userConverter, times(0)).convertUserEntityToUserResponseDto(user);
    }

    @Test
    public void deleteUser_shouldFollowTheRightFlow_whenEverythingIsValidatedCorrectly() {

        String id = new String("0");

        when(userValidator.userExists(id)).thenReturn(true);

        userServiceImpl.deleteUser(id);

        verify(userValidator, times(1)).userExists(id);
        verify(userRepository, times(1)).delete(id);
    }

    @Test
    public void deleteUser_shouldRaiseError_whenUserDoesNotExist() {

        String id = new String("0");

        when(userValidator.userExists(id)).thenReturn(false);

        try {
            userServiceImpl.deleteUser(id);
            fail("Exception should have thrown");
        } catch (RuntimeException e) {
            assertEquals(e.getMessage(), "User does not exist");
        }

        verify(userValidator, times(1)).userExists(id);
        verify(userRepository, times(0)).delete(id);
    }

}
