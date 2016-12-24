package com.sbsk.web.controllers;

import com.sbsk.dtos.user.UserRequestDto;
import com.sbsk.dtos.user.UserResponseDto;
import com.sbsk.service.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.basepath}/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(
        method = {RequestMethod.GET},
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @ResponseBody
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(
        value = "/{id}",
        method = {RequestMethod.GET},
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @ResponseBody
    public UserResponseDto getUser(
            @PathVariable Long id
    ) {
        return userService.getUser(id);
    }

    @RequestMapping(
        method = {RequestMethod.POST},
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE

    )
    @ResponseBody
    public UserResponseDto createUser(
        @RequestBody UserRequestDto userRequestDto
    ) {
      return userService.createUser(userRequestDto);
    }

    @RequestMapping(
        value = "/{id}",
        method = {RequestMethod.PUT},
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @ResponseBody
    public UserResponseDto updateUser(
            @PathVariable Long id,
            @RequestBody UserRequestDto userRequestDto
    ) {
        return userService.updateUser(id, userRequestDto);
    }

    @RequestMapping(value = "/throw-sample-exception", method = {RequestMethod.GET})
    public String throwSampleException() {
      if (true) { throw new RuntimeException("SampleException"); }
      return "Foo";
    }
}
