package com.tripsta.common.envelope;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GenericErrorContainerTest {

  private GenericErrorContainer genericErrorContainer = null;

  @Before
  public void setup() {
    genericErrorContainer = Mockito.mock(GenericErrorContainer.class, Mockito.CALLS_REAL_METHODS);
  }

  @SuppressWarnings("serial")
  @Test
  public void testErrors() throws Exception {
    assertTrue(genericErrorContainer.getErrors().isEmpty());
    assertTrue(!genericErrorContainer.hasErrors());

    genericErrorContainer.addError(new ApiError("test1"));
    assertEquals(1, genericErrorContainer.getErrors().size());
    assertEquals("test1", genericErrorContainer.getErrors().get(0).getMessage());

    List<ApiError> errors = new ArrayList<ApiError>() {{add(new ApiError("test2"));}};
    genericErrorContainer.addErrors(errors);
    assertEquals(2, genericErrorContainer.getErrors().size());
    assertEquals("test1", genericErrorContainer.getErrors().get(0).getMessage());
    assertEquals("test2", genericErrorContainer.getErrors().get(1).getMessage());

    genericErrorContainer.setErrors(errors);
    assertEquals(1, genericErrorContainer.getErrors().size());
    assertEquals("test2", genericErrorContainer.getErrors().get(0).getMessage());

    assertTrue(genericErrorContainer.hasErrors());

    Field field = GenericErrorContainer.class.getDeclaredField("errors");
    assertNotNull(field.getAnnotation(JsonInclude.class));
    assertEquals(Include.ALWAYS, field.getAnnotation(JsonInclude.class).value());
  }

  @SuppressWarnings("serial")
  @Test
  public void testWarnings() throws Exception {
    genericErrorContainer.addWarning(new ApiWarning("test1"));
    assertEquals(1, genericErrorContainer.getWarnings().size());
    assertEquals("test1", genericErrorContainer.getWarnings().get(0).getMessage());

    List<ApiWarning> warnings = new ArrayList<ApiWarning>() {{add(new ApiWarning("test2"));}};
    genericErrorContainer.addWarnings(warnings);
    assertEquals(2, genericErrorContainer.getWarnings().size());
    assertEquals("test1", genericErrorContainer.getWarnings().get(0).getMessage());
    assertEquals("test2", genericErrorContainer.getWarnings().get(1).getMessage());
  }
}