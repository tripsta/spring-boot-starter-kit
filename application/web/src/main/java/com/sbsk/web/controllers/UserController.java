package com.sbsk.web.controllers;

import com.sbsk.dtos.user.UserRequestDto;
import com.sbsk.dtos.user.UserResponseDto;
import com.sbsk.service.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.basepath}/user")
public class UserController {

    @Autowired
    private UserService userService;

//    //	/api/v1/user/get?name=pooo
//    @RequestMapping(value = "/get", method = {RequestMethod.GET})
//    @ResponseBody
//    public String getUser(
//  //			@PathVariable(value = "firstname", required = false) String firstName,
//        @RequestParam(value = "name", required = false, defaultValue = "dummyUser") String name
//    ) {
//      return "hey " + name;
//    }
//
//    @RequestMapping(
//        value = "/get-dto",
//        method = {RequestMethod.GET},
//        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
//    )
//    @ResponseBody
//    public UserResponseDto getDtoUser() {
//      return new UserResponseDto("Nikos", "Koukos", 28, true);
//    }

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
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @ResponseBody
    public UserResponseDto createUser(
        @RequestBody UserRequestDto userRequestDto
    ) {
      return userService.createUser(userRequestDto);
    }

    @RequestMapping(value = "/throw-sample-exception", method = {RequestMethod.GET})
    public String throwSampleException() {
      if (true) { throw new RuntimeException("SampleException"); }
      return "Foo";
    }
}
