package common.gearmanclient.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateSerializer extends JsonSerializer<LocalDateTime> {

	private final static String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	@Override
	public void serialize(LocalDateTime date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
			throws IOException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_FORMAT);
		String dateString = date.format(formatter);

		jsonGenerator.writeString(dateString);
	}
}
