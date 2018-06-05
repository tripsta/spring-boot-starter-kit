package common.gearmanclient.exceptions;

public class GearmanResponseException extends Exception {

	private static final long serialVersionUID = -5859341832495859960L;

	public GearmanResponseException() {
		super();
	}

	public GearmanResponseException(String message) {
		super(message);
	}

	public GearmanResponseException(String message, Throwable cause) {
		super(message, cause);
	}

	public GearmanResponseException(Throwable cause) {
		super(cause);
	}
}
