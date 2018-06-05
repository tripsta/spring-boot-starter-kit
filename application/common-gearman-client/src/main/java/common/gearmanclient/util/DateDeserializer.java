package common.gearmanclient.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateDeserializer extends JsonDeserializer<LocalDateTime> {

	private final static String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private final static String DATETIME_WITHOUT_SECONDS_FORMAT = "yyyy-MM-dd HH:mm";

	@Override
	public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
		String datetime = jsonParser.getText();

		if ((new DateTimeMatcher()).matches(datetime)) {
			return buildDateWithFormat(DATETIME_FORMAT, datetime);
		} else if ((new DateTimeWithoutSecondsMatcher()).matches(datetime)) {
			return buildDateWithFormat(DATETIME_WITHOUT_SECONDS_FORMAT, datetime);
		}
		return null;
	}

	private LocalDateTime buildDateWithFormat(String format, String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return LocalDateTime.parse(date, formatter);
	}
}
