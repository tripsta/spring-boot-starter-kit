package com.sbsk.web.controllers;

import com.sbsk.persistence.entities.user.UserEntity;
import com.sbsk.persistence.repositories.UserRepository;
import com.sbsk.service.utils.UserUtils;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserControllerTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private UserUtils userUtils;

    @Autowired
    private UserRepository userRepository;

    private MockMvc mockMvc;

    private UserEntity userEntity;

    private final String URI = "/api/v1/user";

    private static final String ID = "id";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String AGE = "age";
    private static final String IS_ADULT = "isAdult";

    @Before
    public void setupMockMvc() {

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        userEntity = new UserEntity();
        userEntity.setFirstName("Foo");
        userEntity.setLastName("Bar");
        userEntity.setAge(69);
        userEntity.setIsAdult(userUtils.isAdult(userEntity.getAge()));

        userRepository.save(userEntity);
    }

    @Test
    public void getAllUser_shouldReturnSuccessfully_whenHappyPath() throws Exception {

        mockMvc.perform(get(URI))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.user[0]." + ID).value(userEntity.getId()))
                .andExpect(jsonPath("$.data.user[0]." + FIRST_NAME).value(userEntity.getFirstName()))
                .andExpect(jsonPath("$.data.user[0]." + LAST_NAME).value(userEntity.getLastName()))
                .andExpect(jsonPath("$.data.user[0]." + AGE).value(userEntity.getAge()))
                .andExpect(jsonPath("$.data.user[0]." + IS_ADULT).value(userEntity.getIsAdult()));
    }

    @Test
    public void getUser_shouldReturnSuccessfully_whenHappyPath() throws Exception {

        mockMvc.perform(get(URI + "/" + userEntity.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.user." + ID).value(userEntity.getId()))
                .andExpect(jsonPath("$.data.user." + FIRST_NAME).value(userEntity.getFirstName()))
                .andExpect(jsonPath("$.data.user." + LAST_NAME).value(userEntity.getLastName()))
                .andExpect(jsonPath("$.data.user." + AGE).value(userEntity.getAge()))
                .andExpect(jsonPath("$.data.user." + IS_ADULT).value(userEntity.getIsAdult()));
    }

    @Test
    public void getUser_shouldReturnWithError_whenUserDoesNotExist() throws Exception {

        String errorString = "User does not exist";

        Long nonExistingId = new Long(-1);

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

        mockMvc.perform(put(URI + "/" + userEntity.getId())
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(CREATE_REQUEST.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.user." + ID).value(userEntity.getId()))
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

        mockMvc.perform(put(URI + "/" + userEntity.getId())
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

        mockMvc.perform(delete(URI + "/" + userEntity.getId()))
                .andExpect(status().isOk());
    }

}
