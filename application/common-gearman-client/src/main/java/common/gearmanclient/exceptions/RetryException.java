package common.gearmanclient.exceptions;

public class RetryException extends Exception {

	private static final long serialVersionUID = 1755651515917537921L;

	public RetryException() {
		super();
	}

	public RetryException(String message) {
		super(message);
	}

	public RetryException(String message, Throwable cause) {
		super(message, cause);
	}

	public RetryException(Throwable cause) {
		super(cause);
	}
}
