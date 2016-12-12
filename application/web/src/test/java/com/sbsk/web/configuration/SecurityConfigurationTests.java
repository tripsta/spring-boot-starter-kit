package com.sbsk.web.configuration;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityConfigurationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private Environment environment;

    private MockMvc mockMvc;
    private static String URI;

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
        mockMvc.perform(get("/admin/beans"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(
            username = "nikos",
            password = "12345",
            roles = "USER"
    )
    public void user_shouldBeAbleToAccessHealthPage() throws Exception {
        mockMvc.perform(get("/admin/health"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(
            username = "nikos",
            password = "12345",
            roles = "USER"
    )
    public void user_shouldBeAbleToAccessPublicPages() throws Exception {
        mockMvc.perform(get(URI + "/user/get")
                .param("name", "Nikos"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(
            username = "nikos",
            password = "12345",
            roles = "ADMIN"
    )
    public void admin_shouldBeAbleToAccessAdminPages() throws Exception {
        mockMvc.perform(get("/admin/beans"))
                .andExpect(status().isOk());
    }
}
