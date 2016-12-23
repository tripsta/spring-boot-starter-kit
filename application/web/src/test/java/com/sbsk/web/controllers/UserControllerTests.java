package com.sbsk.web.controllers;

import com.sbsk.persistence.entities.user.UserEntity;
import com.sbsk.persistence.repositories.UserRepository;
import com.sbsk.service.utils.UserUtils;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
                .andExpect(jsonPath("$[0]." + ID).value(userEntity.getId()))
                .andExpect(jsonPath("$[0]." + FIRST_NAME).value(userEntity.getFirstName()))
                .andExpect(jsonPath("$[0]." + LAST_NAME).value(userEntity.getLastName()))
                .andExpect(jsonPath("$[0]." + AGE).value(userEntity.getAge()))
                .andExpect(jsonPath("$[0]." + IS_ADULT).value(userEntity.getIsAdult()));
    }

    @Test
    public void getUser_shouldReturnSuccessfully_whenHappyPath() throws Exception {

        mockMvc.perform(get(URI + "/" + userEntity.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$." + ID).value(userEntity.getId()))
                .andExpect(jsonPath("$." + FIRST_NAME).value(userEntity.getFirstName()))
                .andExpect(jsonPath("$." + LAST_NAME).value(userEntity.getLastName()))
                .andExpect(jsonPath("$." + AGE).value(userEntity.getAge()))
                .andExpect(jsonPath("$." + IS_ADULT).value(userEntity.getIsAdult()));
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
                .andExpect(jsonPath("$." + FIRST_NAME).value(CREATE_REQUEST.get(FIRST_NAME)))
                .andExpect(jsonPath("$." + LAST_NAME).value(CREATE_REQUEST.get(LAST_NAME)))
                .andExpect(jsonPath("$." + AGE).value(CREATE_REQUEST.get(AGE)))
                .andExpect(jsonPath("$." + IS_ADULT).value(true));
    }

}
