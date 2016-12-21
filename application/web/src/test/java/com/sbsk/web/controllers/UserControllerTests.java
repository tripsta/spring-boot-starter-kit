package com.sbsk.web.controllers;

import com.sbsk.persistence.entities.user.UserEntity;
import com.sbsk.persistence.repositories.UserRepository;
import com.sbsk.service.utils.UserUtils;
import org.json.JSONObject;
import org.junit.Before;
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

    private final String URI = "/api/v1/user";

    private static final String ID = "id";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String AGE = "age";
    private static final String IS_ADULT = "isAdult";

    @Before
    public void setupMockMvc() {
      mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getUser_shouldReturnSuccessfully_whenHappyPath() throws Exception {

        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName("Foo");
        userEntity.setLastName("Bar");
        userEntity.setAge(69);
        userEntity.setIsAdult(userUtils.isAdult(userEntity.getAge()));

        userRepository.save(userEntity);

        mockMvc.perform(get(URI + "/1"))
                .andExpect(status().isOk()).andExpect(jsonPath("$." + ID).value(userEntity.getId()))
                .andExpect(status().isOk()).andExpect(jsonPath("$." + FIRST_NAME).value(userEntity.getFirstName()))
                .andExpect(status().isOk()).andExpect(jsonPath("$." + LAST_NAME).value(userEntity.getLastName()))
                .andExpect(status().isOk()).andExpect(jsonPath("$." + AGE).value(userEntity.getAge()))
                .andExpect(status().isOk()).andExpect(jsonPath("$." + IS_ADULT).value(userEntity.getIsAdult()));
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
                .andExpect(status().isOk()).andExpect(jsonPath("$." + FIRST_NAME).value(CREATE_REQUEST.get(FIRST_NAME)))
                .andExpect(status().isOk()).andExpect(jsonPath("$." + LAST_NAME).value(CREATE_REQUEST.get(LAST_NAME)))
                .andExpect(status().isOk()).andExpect(jsonPath("$." + AGE).value(CREATE_REQUEST.get(AGE)))
                .andExpect(status().isOk()).andExpect(jsonPath("$." + IS_ADULT).value(true));
    }


//  private static final String GET_RESPONSE = "hey Nikos";
//  private static final String CREATE_REQUEST = new JSONObject()
//      .put("firstName", "Nikos")
//      .put("lastName", "Koukos")
//      .put("age", 28)
//      .toString();
//
//  private static final String RESPONSE = new JSONObject()
//      .put("firstName", "Nikos")
//      .put("lastName", "Koukos")
//      .put("age", 28)
//      .put("isAdult", true)
//      .toString();
//  private static String URI;
//  @Autowired
//  private WebApplicationContext webApplicationContext;
//  @Autowired
//  private Environment environment;
//  private MockMvc mockMvc;
//
//  @Before
//  public void setup() {
//    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//    URI = environment.getProperty("api.basepath") + "/user";
//  }
//
//  @Test
//  public void getUser_shouldReturnCorrectUser() throws Exception {
//    mockMvc.perform(get(URI + "/get")
//        .param("name", "Nikos"))
//        .andExpect(status().isOk())
//        .andExpect(content().string(GET_RESPONSE));
//  }
//
//  @Test
//  public void getDtoUser_shouldReturnCorrectUserResponseDto() throws Exception {
//    mockMvc.perform(get(URI + "/get-dto"))
//        .andExpect(status().isOk())
//        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//        .andExpect(content().json(RESPONSE));
//  }
//
//  @Test
//  public void createUser_shouldReturnUserResponseDto_whenGivenUserRequestDto() throws Exception {
//    mockMvc.perform(post(URI + "/create")
//        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
//        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
//        .content(CREATE_REQUEST))
//        .andExpect(status().isOk())
//        .andExpect(content().json(RESPONSE));
//  }
}
