package com.sbsk.service.services.user;

import com.sbsk.dtos.user.UserRequestDto;
import com.sbsk.dtos.user.UserResponseDto;

import java.util.List;

public interface UserService {

  public List<UserResponseDto> getAllUsers();

  public UserResponseDto getUser(Long id);

  public UserResponseDto createUser(UserRequestDto userRequestDto);

  public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto);

}