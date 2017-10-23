package com.sbsk.integration.integration.web.controllers;

import com.sbsk.persistence.entities.couchbase.User;
import com.sbsk.persistence.repositories.UserRepository;
import com.sbsk.service.utils.UserUtils;
import com.sbsk.web.WebApplication;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {WebApplication.class})
@ActiveProfiles("test")
public class UserControllerTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private UserUtils userUtils;

    @Autowired
    private UserRepository userRepository;

    private MockMvc mockMvc;

    private User user;

    private final String URI = "/api/v1/user";

    private static final String ID = "id";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String AGE = "age";
    private static final String IS_ADULT = "isAdult";

    @Before
    public void setupMockMvc() {

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        user = new User();
        user.setId("1");
        user.setFirstName("Foo");
        user.setLastName("Bar");
        user.setAge(70);
        user.setIsAdult(userUtils.isAdult(user.getAge()));

        userRepository.save(user);
    }

//    @Test
//    public void getAllUser_shouldReturnSuccessfully_whenHappyPath() throws Exception {
//
//        mockMvc.perform(get(URI))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data.user[0]." + ID).value(user.getId()))
//                .andExpect(jsonPath("$.data.user[0]." + FIRST_NAME).value(user.getFirstName()))
//                .andExpect(jsonPath("$.data.user[0]." + LAST_NAME).value(user.getLastName()))
//                .andExpect(jsonPath("$.data.user[0]." + AGE).value(user.getAge()))
//                .andExpect(jsonPath("$.data.user[0]." + IS_ADULT).value(user.getIsAdult()));
//    }

    @Test
    public void getUser_shouldReturnSuccessfully_whenHappyPath() throws Exception {

        mockMvc.perform(get(URI + "/" + user.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.user." + ID).value(user.getId()))
                .andExpect(jsonPath("$.data.user." + FIRST_NAME).value(user.getFirstName()))
                .andExpect(jsonPath("$.data.user." + LAST_NAME).value(user.getLastName()))
                .andExpect(jsonPath("$.data.user." + AGE).value(user.getAge()))
                .andExpect(jsonPath("$.data.user." + IS_ADULT).value(user.getIsAdult()));
    }

    @Test
    public void getUser_shouldReturnWithError_whenUserDoesNotExist() throws Exception {

        String errorString = "User does not exist";

        String nonExistingId = new String("-1");

        mockMvc.perform(get(URI + "/" + nonExistingId))
                .andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.errors[0].message").value(errorString));
    }

    @Test
    public void createUser_shouldReturnSuccessfully_whenHappyPath() throws Exception {

        JSONObject CREATE_REQUEST = new JSONObject()
                .put(FIRST_NAME, "Foo")
                .put(LAST_NAME, "Bar")
                .put(AGE, 69);

        mockMvc.perform(post(URI)
                  .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                  .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                  .content(CREATE_REQUEST.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.user." + FIRST_NAME).value(CREATE_REQUEST.get(FIRST_NAME)))
                .andExpect(jsonPath("$.data.user." + LAST_NAME).value(CREATE_REQUEST.get(LAST_NAME)))
                .andExpect(jsonPath("$.data.user." + AGE).value(CREATE_REQUEST.get(AGE)))
                .andExpect(jsonPath("$.data.user." + IS_ADULT).value(true));
    }

    @Test
    public void createUser_shouldReturnWithError_whenInvalidData() throws Exception {

        String errorString = "Invalid input parameters";

        JSONObject CREATE_REQUEST = new JSONObject()
                .put(FIRST_NAME, "")
                .put(LAST_NAME, "")
                .put(AGE, 0);

        mockMvc.perform(post(URI)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(CREATE_REQUEST.toString()))
                .andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.errors[0].message").value(errorString));
    }

    @Test
    public void updateUser_shouldReturnSuccessfully_whenHappyPath() throws Exception {

        JSONObject CREATE_REQUEST = new JSONObject()
                .put(FIRST_NAME, "FooUpdated")
                .put(LAST_NAME, "BarUpdated")
                .put(AGE, 70);

        mockMvc.perform(put(URI + "/" + user.getId())
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(CREATE_REQUEST.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.user." + ID).value(user.getId()))
                .andExpect(jsonPath("$.data.user." + FIRST_NAME).value(CREATE_REQUEST.get(FIRST_NAME)))
                .andExpect(jsonPath("$.data.user." + LAST_NAME).value(CREATE_REQUEST.get(LAST_NAME)))
                .andExpect(jsonPath("$.data.user." + AGE).value(CREATE_REQUEST.get(AGE)))
                .andExpect(jsonPath("$.data.user." + IS_ADULT).value(true));
    }

    @Test
    public void updateUser_shouldReturnWithError_whenInvalidData() throws Exception {

        String errorString = "Invalid input parameters";

        JSONObject CREATE_REQUEST = new JSONObject()
                .put(FIRST_NAME, "")
                .put(LAST_NAME, "")
                .put(AGE, 0);

        mockMvc.perform(put(URI + "/" + user.getId())
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(CREATE_REQUEST.toString()))
                .andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.errors[0].message").value(errorString));
    }

    @Test
    public void updateUser_shouldReturnWithError_whenUserDoesNotExist() throws Exception {

        String errorString = "User does not exist";

        Long nonExistingId = new Long(-1);

        JSONObject CREATE_REQUEST = new JSONObject()
                .put(FIRST_NAME, "FooUpdated")
                .put(LAST_NAME, "BarUpdated")
                .put(AGE, 70);

        mockMvc.perform(put(URI + "/" + nonExistingId)
                    .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .content(CREATE_REQUEST.toString()))
                .andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.errors[0].message").value(errorString));
    }

    @Test
    public void deleteUser_shouldReturnSuccessfully_whenHappyPath() throws Exception {

        mockMvc.perform(delete(URI + "/" + user.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteUser_shouldReturnWithError_whenUserDoesNotExist() throws Exception {

        String nonExistingId = new String("-1");

        mockMvc.perform(delete(URI + "/" + nonExistingId))
                .andExpect(status().is5xxServerError());
    }

}
