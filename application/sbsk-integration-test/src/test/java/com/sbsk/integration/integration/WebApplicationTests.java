package com.sbsk.integration.integration;

import com.sbsk.web.WebApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {WebApplication.class})
@ActiveProfiles("test")
public class WebApplicationTests {

  @Test
  public void contextLoads() {
  }

}
