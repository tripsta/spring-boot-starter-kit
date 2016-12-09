package com.sbsk.service.converters.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.sbsk.persistence.entity.user.UserEntity;
import org.junit.Test;

import com.sbsk.dto.UserRequestDto;
import com.sbsk.dto.UserResponseDto;

public class UserConverterTests {

	@Test
	public void userRequestDtoToDao_should_when() {
		UserRequestDto userRequestDto = new UserRequestDto("Nikos", "Koukos", 17);
		UserEntity expectedDao = new UserEntity("Nikos", "Koukos", 17, false, 0);

		UserEntity actualDao = UserConverter.userRequestDtoToDao(userRequestDto);

		assertThat(actualDao).isEqualToIgnoringGivenFields(expectedDao, "dateCreated");
	}

	@Test
	public void userDaoToResponseDto_should_when() {
		UserEntity userEntity = new UserEntity("Nikos", "Koukos", 17, false, 0);
		UserResponseDto expectedUserResponseDto = new UserResponseDto("Nikos", "Koukos", 17, false);

		UserResponseDto actualUserResponseDto = UserConverter.userDaoToResponseDto(userEntity);

		assertThat(actualUserResponseDto).isEqualToComparingFieldByField(expectedUserResponseDto);
	}

}
