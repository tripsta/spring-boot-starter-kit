package com.sbsk.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sbsk.dto.UserRequestDto;
import com.sbsk.dto.UserResponseDto;
import com.sbsk.service.services.user.UserService;

@RestController
@RequestMapping("${api.basepath}/user")
public class UserController {

	@Autowired
	private UserService userService;

	//	/api/v1/user/get?name=pooo
	@RequestMapping(value = "/get", method = {RequestMethod.GET})
	@ResponseBody
	public String getUser(
//			@PathVariable(value = "firstname", required = false) String firstName,
			@RequestParam(value = "name", required = false, defaultValue = "dummyUser") String name
	) {
		return "hey " + name;
	}

	@RequestMapping(
			value = "/get-dto",
			method = {RequestMethod.GET},
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	@ResponseBody
	public UserResponseDto getDtoUser() {
		return new UserResponseDto("Nikos", "Koukos", 28, true);
	}

	@RequestMapping(
			value = "/create",
			method = {RequestMethod.POST},
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	@ResponseBody
	public UserResponseDto createUser(
			@RequestBody UserRequestDto userRequestDto
	) {
		return userService.createUser(userRequestDto);
	}
}
