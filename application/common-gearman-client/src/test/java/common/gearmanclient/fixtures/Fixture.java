package common.gearmanclient.fixtures;


import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public interface Fixture {

	default Date toDate(String date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date, new ParsePosition(0));
	}
}
