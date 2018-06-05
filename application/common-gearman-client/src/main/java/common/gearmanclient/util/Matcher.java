package common.gearmanclient.util;

@FunctionalInterface
public interface Matcher {
	boolean matches(String input);
}
