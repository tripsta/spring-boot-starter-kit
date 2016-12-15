package com.sbsk.web.controllers;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTests {

  private static final String GET_RESPONSE = "hey Nikos";
  private static final String CREATE_REQUEST = new JSONObject()
      .put("firstName", "Nikos")
      .put("lastName", "Koukos")
      .put("age", 28)
      .toString();

  private static final String RESPONSE = new JSONObject()
      .put("firstName", "Nikos")
      .put("lastName", "Koukos")
      .put("age", 28)
      .put("isAdult", true)
      .toString();
  private static String URI;
  @Autowired
  private WebApplicationContext webApplicationContext;
  @Autowired
  private Environment environment;
  private MockMvc mockMvc;

  @Before
  public void setup() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    URI = environment.getProperty("api.basepath") + "/user";
  }

  @Test
  public void getUser_shouldReturnCorrectUser() throws Exception {
    mockMvc.perform(get(URI + "/get")
        .param("name", "Nikos"))
        .andExpect(status().isOk())
        .andExpect(content().string(GET_RESPONSE));
  }

  @Test
  public void getDtoUser_shouldReturnCorrectUserResponseDto() throws Exception {
    mockMvc.perform(get(URI + "/get-dto"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(content().json(RESPONSE));
  }

  @Test
  public void createUser_shouldReturnUserResponseDto_whenGivenUserRequestDto() throws Exception {
    mockMvc.perform(post(URI + "/create")
        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
        .content(CREATE_REQUEST))
        .andExpect(status().isOk())
        .andExpect(content().json(RESPONSE));
  }
}
