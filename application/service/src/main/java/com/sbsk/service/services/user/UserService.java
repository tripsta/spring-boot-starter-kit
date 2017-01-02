package com.sbsk.service.services.user;

import com.sbsk.dtos.user.UserRequestDto;
import com.sbsk.dtos.user.UserResponseDto;

import java.util.List;

public interface UserService {

  List<UserResponseDto> getAllUsers();

  UserResponseDto getUser(Long id);

  UserResponseDto createUser(UserRequestDto userRequestDto);

  UserResponseDto updateUser(Long id, UserRequestDto userRequestDto);

  void deleteUser(Long id);

}