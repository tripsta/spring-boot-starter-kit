package com.sbsk.service.services.user;

import com.sbsk.dto.UserRequestDto;
import com.sbsk.dto.UserResponseDto;

public interface UserService {

	public UserResponseDto createUser(UserRequestDto userRequestDto);

}