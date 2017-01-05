package com.sbsk.web.controllers;

import com.sbsk.dtos.user.UserRequestDto;
import com.sbsk.dtos.user.UserResponseDto;
import com.sbsk.model.ApiBaseResponse;
import com.sbsk.service.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.basepath}/user")
public class UserController {

    private static final String USER = "user";

    @Autowired
    private UserService userService;

    @RequestMapping(
        method = {RequestMethod.GET},
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @ResponseBody
    public ApiBaseResponse getAllUsers() {
        ApiBaseResponse apiBaseResponse = new ApiBaseResponse();
        apiBaseResponse.insertData(USER, userService.getAllUsers());
        apiBaseResponse.setSuccess(true);
        return apiBaseResponse;
    }

    @RequestMapping(
        value = "/{id}",
        method = {RequestMethod.GET},
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @ResponseBody
    public ApiBaseResponse getUser(
            @PathVariable Long id
    ) {
        ApiBaseResponse apiBaseResponse = new ApiBaseResponse();
        apiBaseResponse.insertData(USER, userService.getUser(id));
        apiBaseResponse.setSuccess(true);
        return apiBaseResponse;
    }

    @RequestMapping(
        method = {RequestMethod.POST},
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE

    )
    @ResponseBody
    public ApiBaseResponse createUser(
        @RequestBody UserRequestDto userRequestDto
    ) {
        ApiBaseResponse apiBaseResponse = new ApiBaseResponse();
        apiBaseResponse.insertData(USER, userService.createUser(userRequestDto));
        apiBaseResponse.setSuccess(true);
        return apiBaseResponse;
    }

    @RequestMapping(
        value = "/{id}",
        method = {RequestMethod.PUT},
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @ResponseBody
    public ApiBaseResponse updateUser(
            @PathVariable Long id,
            @RequestBody UserRequestDto userRequestDto
    ) {
        ApiBaseResponse apiBaseResponse = new ApiBaseResponse();
        apiBaseResponse.insertData(USER, userService.updateUser(id, userRequestDto));
        apiBaseResponse.setSuccess(true);
        return apiBaseResponse;
    }

    @RequestMapping(
        value = "/{id}",
        method = {RequestMethod.DELETE}
    )
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

}
