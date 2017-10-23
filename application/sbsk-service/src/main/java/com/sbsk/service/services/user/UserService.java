package com.sbsk.service.services.user;

import com.sbsk.dtos.user.UserRequestDto;
import com.sbsk.dtos.user.UserResponseDto;

import java.util.List;

public interface UserService {

  List<UserResponseDto> getAllUsers();

  UserResponseDto getUser(String id);

  UserResponseDto createUser(UserRequestDto userRequestDto);

  UserResponseDto updateUser(String id, UserRequestDto userRequestDto);

  void deleteUser(String id);

}