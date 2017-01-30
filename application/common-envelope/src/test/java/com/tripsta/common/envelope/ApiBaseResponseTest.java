package com.tripsta.common.envelope;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ApiBaseResponseTest {

  private static final String TEST_STR = "test";
  private ApiBaseResponse apiBaseResponse = null;

  @Before
  public void setup() {
    apiBaseResponse = new ApiBaseResponse();
  }

  @Test
  public void testGetVersion() throws Exception {
    String result = apiBaseResponse.getVersion();

    assertEquals("1.0.0", result);
  }

  @Test
  public void testSid() throws Exception {
    apiBaseResponse.setSid(TEST_STR);

    assertEquals(TEST_STR, apiBaseResponse.getSid());
  }

  @SuppressWarnings("serial")
  @Test
  public void testData() throws Exception {
    Map<String, Object> data = new HashMap<String, Object>() {{put("testkey", "testval");}};
    apiBaseResponse.setData(data);

    assertEquals(data, apiBaseResponse.getData());

    apiBaseResponse.insertData("testkey1", TEST_STR);

    assertEquals(TEST_STR, apiBaseResponse.retrieveData("testkey1"));
    assertEquals("testval", apiBaseResponse.retrieveData("testkey"));
  }

  @SuppressWarnings("serial")
  @Test
  public void testMetadata() throws Exception {
    Map<String, Object> metadata = new HashMap<String, Object>() {{put("testkey", "testval");}};
    apiBaseResponse.setMetadata(metadata);

    assertEquals(metadata, apiBaseResponse.getMetadata());

    apiBaseResponse.insertMetadata("testkey1", TEST_STR);

    assertEquals(TEST_STR, apiBaseResponse.retrieveMetadata("testkey1"));
    assertEquals("testval", apiBaseResponse.retrieveMetadata("testkey"));
  }

  @Test
  public void testIsSuccess() throws Exception {
    assertEquals(false, apiBaseResponse.isSuccess());

    apiBaseResponse.setSuccess(true);
    assertEquals(true, apiBaseResponse.isSuccess());
  }
}