package common.gearmanclient.fixtures;

public class Fixtures {

	public static <T extends Fixture> T getInstance(Class<T> clazz)
			throws InstantiationException, IllegalAccessException {
		return clazz.newInstance();

	}
}