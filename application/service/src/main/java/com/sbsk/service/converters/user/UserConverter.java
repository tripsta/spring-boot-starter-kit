package com.sbsk.service.converters.user;

import com.sbsk.persistence.entity.user.UserEntity;
import org.springframework.stereotype.Component;

import com.sbsk.dto.UserRequestDto;
import com.sbsk.dto.UserResponseDto;
import com.sbsk.service.utils.UserUtils;

@Component
public class UserConverter {

	public static UserEntity userRequestDtoToDao(UserRequestDto userRequestDto) {
		return new UserEntity(
				userRequestDto.getFirstName(),
				userRequestDto.getLastName(),
				userRequestDto.getAge(),
				UserUtils.isAdult(userRequestDto.getAge()),
				new Integer(12345)
		);
	}

	public static UserResponseDto userDaoToResponseDto(UserEntity userEntity) {
		return new UserResponseDto(
				userEntity.getFirstName(),
				userEntity.getLastName(),
				userEntity.getAge(),
				userEntity.getIsAdult()
		);
	}

}
