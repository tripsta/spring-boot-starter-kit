package com.sbsk.service.services.user;

import com.sbsk.dtos.user.UserRequestDto;
import com.sbsk.dtos.user.UserResponseDto;
import com.sbsk.persistence.entities.user.UserEntity;
import com.sbsk.persistence.repositories.UserRepository;
import com.sbsk.service.converters.user.UserConverter;
import com.sbsk.service.validators.UserValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@Transactional
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
    private UserEntity userEntity;

    @Mock
    private UserResponseDto userResponseDto;

    @Test
    public void getAllUser_shouldFollowTheRightFlow_whenEverythingIsValidatedCorrectly() {

        List<UserEntity> userEntities = new ArrayList<UserEntity>();
        List<UserResponseDto> userResponseDtos = new ArrayList<UserResponseDto>();
        when(userRepository.findAll()).thenReturn(userEntities);
        when(userConverter.convertUserEntitiesToUserResponseDtos(userEntities)).thenReturn(userResponseDtos);

        userServiceImpl.getAllUsers();

        verify(userRepository, times(1)).findAll();
        verify(userConverter, times(1)).convertUserEntitiesToUserResponseDtos(userEntities);
    }

    @Test
    public void getUser_shouldFollowTheRightFlow_whenEverythingIsValidatedCorrectly() {

        Long id = new Long(0);
        when(userRepository.findOne(id)).thenReturn(userEntity);
        when(userConverter.convertUserEntityToUserResponseDto(userEntity)).thenReturn(userResponseDto);

        userServiceImpl.getUser(id);

        verify(userRepository, times(1)).findOne(id);
        verify(userConverter, times(1)).convertUserEntityToUserResponseDto(userEntity);
    }

    @Test
    public void createUser_shouldFollowTheRightFlow_whenEverythingIsValidatedCorrectly() {

        when(userValidator.validateUser(userRequestDto)).thenReturn(true);
        when(userConverter.convertUserRequestDtoToUserEntity(userRequestDto)).thenReturn(userEntity);
        when(userConverter.convertUserEntityToUserResponseDto(userEntity)).thenReturn(userResponseDto);

        userServiceImpl.createUser(userRequestDto);

        verify(userValidator, times(1)).validateUser(userRequestDto);
        verify(userConverter, times(1)).convertUserRequestDtoToUserEntity(userRequestDto);
        verify(userRepository, times(1)).save(userEntity);
        verify(userConverter, times(1)).convertUserEntityToUserResponseDto(userEntity);
    }

    @Test
    public void createUser_shouldFollowTheRightFlow_whenSomethingIsNotValidatedCorrectly() {

        when(userValidator.validateUser(userRequestDto)).thenReturn(false);
        when(userConverter.convertUserRequestDtoToUserEntity(userRequestDto)).thenReturn(userEntity);
        when(userConverter.convertUserEntityToUserResponseDto(userEntity)).thenReturn(userResponseDto);

        try {
            userServiceImpl.createUser(userRequestDto);
            fail("Exception should have thrown");
        } catch (RuntimeException e) {
            assertEquals(e.getMessage(), "Invalid input parameters");
        }

        verify(userValidator, times(1)).validateUser(userRequestDto);
        verify(userConverter, times(0)).convertUserRequestDtoToUserEntity(userRequestDto);
        verify(userRepository, times(0)).save(userEntity);
        verify(userConverter, times(0)).convertUserEntityToUserResponseDto(userEntity);
    }

    @Test
    public void updateUser_shouldFollowTheRightFlow_whenEverythingIsValidatedCorrectly() {

        when(userEntity.getId()).thenReturn(new Long(1));
        when(userConverter.convertUserRequestDtoToUserEntity(userRequestDto)).thenReturn(userEntity);
        when(userConverter.convertUserEntityToUserResponseDto(userEntity)).thenReturn(userResponseDto);

        userServiceImpl.updateUser(new Long(1), userRequestDto);

        verify(userConverter, times(1)).convertUserRequestDtoToUserEntity(userRequestDto);
        verify(userEntity, times(1)).setId(anyLong());
        verify(userRepository, times(1)).save(userEntity);
        verify(userConverter, times(1)).convertUserEntityToUserResponseDto(userEntity);
    }

    @Test
    public void deleteUser_shouldFollowTheRightFlow_whenEverythingIsValidatedCorrectly() {

        Long id = new Long(0);

        userServiceImpl.deleteUser(id);

        verify(userRepository, times(1)).delete(id);
    }

}
