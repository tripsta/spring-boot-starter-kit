package common.gearmanclient.util;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

public class IsoDateSerializerTest {

	@Test
	public void serialize() throws IOException {
		Writer jsonWriter = new StringWriter();
		JsonGenerator jsonGenerator = new JsonFactory().createGenerator(jsonWriter);
		SerializerProvider serializerProvider = new ObjectMapper().getSerializerProvider();
		String expectedDate = "2018-03-23T21:45:59";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime date = LocalDateTime.parse("2018-03-23 21:45:59", formatter);
		new IsoDateSerializer().serialize(date, jsonGenerator, serializerProvider);
		jsonGenerator.flush();
		assertEquals(String.format("\"%s\"", expectedDate), jsonWriter.toString());
	}
}