package com.sbsk.service.converters.user;

import com.sbsk.dtos.user.UserRequestDto;
import com.sbsk.dtos.user.UserResponseDto;
import com.sbsk.persistence.entities.user.UserEntity;
import com.sbsk.service.utils.UserUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UserConverter.class)
public class UserConverterTests {

  @InjectMocks
  private UserConverter userConverter;

  @Mock
  private UserUtils userUtils;

  @Test
  public void convertUserRequestDtoToUserEntity_shouldMapProperly() {

    UserRequestDto userRequestDto = new UserRequestDto();
    userRequestDto.setFirstName("Foo");
    userRequestDto.setLastName("Bar");
    userRequestDto.setAge(69);

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

    UserEntity userEntity = new UserEntity();
    userEntity.setFirstName("Foo");
    userEntity.setLastName("Bar");
    userEntity.setAge(69);
    userEntity.setIsAdult(userUtils.isAdult(userEntity.getAge()));

    UserResponseDto expectedUserResponseDto = new UserResponseDto();
    expectedUserResponseDto.setFirstName(userEntity.getFirstName());
    expectedUserResponseDto.setLastName(userEntity.getLastName());
    expectedUserResponseDto.setAge(userEntity.getAge());
    expectedUserResponseDto.setIsAdult(userEntity.getIsAdult());

    UserResponseDto actualUserResponseDto = userConverter.convertUserEntityToUserResponseDto(userEntity);

    assertEquals(actualUserResponseDto.getFirstName(), expectedUserResponseDto.getFirstName());
    assertEquals(actualUserResponseDto.getLastName(), expectedUserResponseDto.getLastName());
    assertEquals(actualUserResponseDto.getAge(), expectedUserResponseDto.getAge());
    assertTrue(actualUserResponseDto.getIsAdult());

  }

}
