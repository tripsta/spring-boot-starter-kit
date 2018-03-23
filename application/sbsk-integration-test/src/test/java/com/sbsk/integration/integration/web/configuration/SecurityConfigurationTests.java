package com.sbsk.integration.integration.web.configuration;

import com.sbsk.web.WebApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {WebApplication.class})
@ActiveProfiles("test")
public class SecurityConfigurationTests {

  private static String URI;
  @Autowired
  private WebApplicationContext webApplicationContext;
  @Autowired
  private Environment environment;
  private MockMvc mockMvc;

  @Before
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
    URI = environment.getProperty("api.basepath");
  }

  @Test
  @WithMockUser(
      username = "nikos",
      password = "12345",
      roles = "USER"
  )
  public void user_shouldNotBeAbleToAccessAdminPages() throws Exception {
    mockMvc.perform(get("/actuator/beans"))
        .andExpect(status().isForbidden());
  }

  @Test
  @WithMockUser(
      username = "nikos",
      password = "12345",
      roles = "USER"
  )
  public void user_shouldBeAbleToAccessHealthPage() throws Exception {
    mockMvc.perform(get("/actuator/health"))
        .andExpect(status().isOk());
  }

  @Test
  @WithMockUser(
      username = "nikos",
      password = "12345",
      roles = "ADMIN"
  )
  public void admin_shouldBeAbleToAccessAdminPages() throws Exception {
    mockMvc.perform(get("/actuator/beans"))
        .andExpect(status().isOk());
  }
}
