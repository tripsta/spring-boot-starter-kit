package common.gearmanclient.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DateDeserializerTest {
	private ObjectMapper objectMapper = new ObjectMapper();

	@Test
	public void deserialize() throws IOException {
		LocalDateTime date = deserialize("2018-03-23 15:00:23");
		assertEquals("2018-03-23T15:00:23", date.toString());

		date = deserialize("2018-03-23 15:00");
		assertEquals("2018-03-23T15:00", date.toString());

		date = deserialize("2018-03-23");
		assertNull(date);
	}

	private LocalDateTime deserialize(String datetime) throws IOException {
		String json = String.format("{\"value\":\"%s\"}", datetime);
		JsonParser jsonParser = objectMapper.getFactory().createParser(json);
		jsonParser.nextValue();
		jsonParser.nextValue();

		DeserializationContext ctxt = objectMapper.getDeserializationContext();
		return (new DateDeserializer()).deserialize(jsonParser, ctxt);
	}

}
