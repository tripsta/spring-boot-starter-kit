package common.gearmanclient.util;

import java.util.regex.Pattern;

public class DateTimeMatcher implements Matcher {

	private static Pattern DATETIME_PATTERN = Pattern.compile(
			"^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$");

	@Override
	public boolean matches(String input) {
		return DATETIME_PATTERN.matcher(input).matches();
	}
}
