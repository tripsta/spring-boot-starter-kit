package com.sbsk.integration;

import com.sbsk.web.WebApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {WebApplication.class})
public class IntegrationApplicationTests {

	@Test
	public void contextLoads() {
	}

}
