package com.sbsk.service.converters.user;

import com.sbsk.dtos.user.UserRequestDto;
import com.sbsk.dtos.user.UserResponseDto;
import com.sbsk.persistence.entities.user.UserEntity;
import com.sbsk.service.utils.UserUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class UserConverterTests {

    @Mock UserUtils userUtils;
    @Mock UserRequestDto userRequestDto;
    @Mock UserEntity userEntity;
    @Mock UserResponseDto userResponseDto;
    @InjectMocks UserConverter userConverter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void convertUserRequestDtoToUserEntity_shouldMapProperly() {
        when(userUtils.isAdult(69)).thenReturn(true);

        when(userRequestDto.getFirstName()).thenReturn("Foo");
        when(userRequestDto.getLastName()).thenReturn("Bar");
        when(userRequestDto.getAge()).thenReturn(69);

        UserEntity expectedUserEntity = new UserEntity();
        expectedUserEntity.setFirstName(userRequestDto.getFirstName());
        expectedUserEntity.setLastName(userRequestDto.getLastName());
        expectedUserEntity.setAge(userRequestDto.getAge());
        expectedUserEntity.setIsAdult(userUtils.isAdult(userRequestDto.getAge()));

        UserEntity actualUserEntity = userConverter.convertUserRequestDtoToUserEntity(userRequestDto);

        assertEquals(actualUserEntity.getFirstName(), expectedUserEntity.getFirstName());
        assertEquals(actualUserEntity.getLastName(), expectedUserEntity.getLastName());
        assertEquals(actualUserEntity.getAge(), expectedUserEntity.getAge());
        assertTrue(actualUserEntity.getIsAdult());
    }

    @Test
    public void convertUserEntityUserResponseDtoTo_shouldMapProperly() {
        when(userUtils.isAdult(69)).thenReturn(true);

        when(userEntity.getFirstName()).thenReturn("Foo");
        when(userEntity.getLastName()).thenReturn("Bar");
        when(userEntity.getAge()).thenReturn(69);
        when(userEntity.getIsAdult()).thenReturn(true);

        UserResponseDto expectedUserResponseDto = new UserResponseDto();
        expectedUserResponseDto.setId(userEntity.getId());
        expectedUserResponseDto.setFirstName(userEntity.getFirstName());
        expectedUserResponseDto.setLastName(userEntity.getLastName());
        expectedUserResponseDto.setAge(userEntity.getAge());
        expectedUserResponseDto.setIsAdult(userEntity.getIsAdult());

        UserResponseDto actualUserResponseDto = userConverter.convertUserEntityToUserResponseDto(userEntity);

        assertEquals(actualUserResponseDto.getId(), expectedUserResponseDto.getId());
        assertEquals(actualUserResponseDto.getFirstName(), expectedUserResponseDto.getFirstName());
        assertEquals(actualUserResponseDto.getLastName(), expectedUserResponseDto.getLastName());
        assertEquals(actualUserResponseDto.getAge(), expectedUserResponseDto.getAge());
        assertTrue(actualUserResponseDto.getIsAdult());
    }
}
