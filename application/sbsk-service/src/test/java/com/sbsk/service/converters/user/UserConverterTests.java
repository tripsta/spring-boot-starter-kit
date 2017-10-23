package com.sbsk.service.converters.user;

import com.sbsk.dtos.user.UserRequestDto;
import com.sbsk.dtos.user.UserResponseDto;
import com.sbsk.persistence.entities.couchbase.User;
import com.sbsk.service.utils.UserUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UserConverter.class)
public class UserConverterTests {

    @InjectMocks
    private UserConverter userConverter;

    @Mock
    private UserUtils userUtils;

    @Mock
    private UserRequestDto userRequestDto;

    @Mock
    private User user;

    @Mock
    private UserResponseDto userResponseDto;

    @Test
    public void convertUserRequestDtoToUserEntity_shouldMapProperly() {

        when(userUtils.isAdult(69)).thenReturn(true);

        when(userRequestDto.getFirstName()).thenReturn("Foo");
        when(userRequestDto.getLastName()).thenReturn("Bar");
        when(userRequestDto.getAge()).thenReturn(69);

        User expectedUser = new User();
        expectedUser.setFirstName(userRequestDto.getFirstName());
        expectedUser.setLastName(userRequestDto.getLastName());
        expectedUser.setAge(userRequestDto.getAge());
        expectedUser.setIsAdult(userUtils.isAdult(userRequestDto.getAge()));

        User actualUser = userConverter.convertUserRequestDtoToUserEntity(userRequestDto);

        assertEquals(actualUser.getFirstName(), expectedUser.getFirstName());
        assertEquals(actualUser.getLastName(), expectedUser.getLastName());
        assertEquals(actualUser.getAge(), expectedUser.getAge());
        assertTrue(actualUser.getIsAdult());
    }

    @Test
    public void convertUserEntityUserResponseDtoTo_shouldMapProperly() {

        when(userUtils.isAdult(69)).thenReturn(true);

        when(user.getFirstName()).thenReturn("Foo");
        when(user.getLastName()).thenReturn("Bar");
        when(user.getAge()).thenReturn(69);
        when(user.getIsAdult()).thenReturn(true);

        UserResponseDto expectedUserResponseDto = new UserResponseDto();
        expectedUserResponseDto.setId(user.getId());
        expectedUserResponseDto.setFirstName(user.getFirstName());
        expectedUserResponseDto.setLastName(user.getLastName());
        expectedUserResponseDto.setAge(user.getAge());
        expectedUserResponseDto.setIsAdult(user.getIsAdult());

        UserResponseDto actualUserResponseDto = userConverter.convertUserEntityToUserResponseDto(user);

        assertEquals(actualUserResponseDto.getId(), expectedUserResponseDto.getId());
        assertEquals(actualUserResponseDto.getFirstName(), expectedUserResponseDto.getFirstName());
        assertEquals(actualUserResponseDto.getLastName(), expectedUserResponseDto.getLastName());
        assertEquals(actualUserResponseDto.getAge(), expectedUserResponseDto.getAge());
        assertTrue(actualUserResponseDto.getIsAdult());

    }

}
