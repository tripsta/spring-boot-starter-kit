package common.gearmanclient.exceptions;

public class AsyncFutureException extends Exception {

	private static final long serialVersionUID = 565607233136434805L;

	public AsyncFutureException() {
		super();
	}

	public AsyncFutureException(String message) {
		super(message);
	}

	public AsyncFutureException(String message, Throwable cause) {
		super(message, cause);
	}

	public AsyncFutureException(Throwable cause) {
		super(cause);
	}
}
