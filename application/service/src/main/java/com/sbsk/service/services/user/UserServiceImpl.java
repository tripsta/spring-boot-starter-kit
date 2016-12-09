package com.sbsk.service.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbsk.dto.UserRequestDto;
import com.sbsk.dto.UserResponseDto;
import com.sbsk.persistence.entity.user.UserEntity;
import com.sbsk.persistence.repository.repositories.UserRepository;
import com.sbsk.service.converters.user.UserConverter;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserResponseDto createUser(UserRequestDto userRequestDto) {
		UserEntity userEntity = UserConverter.userRequestDtoToDao(userRequestDto);
		userRepository.save(userEntity);
		return UserConverter.userDaoToResponseDto(userEntity);
	}

}
