package common.gearmanclient.exceptions;

public class GearmanCommunicationException extends Exception {

	private static final long serialVersionUID = -2057350476960448415L;

	public GearmanCommunicationException() {
		super();
	}

	public GearmanCommunicationException(String message) {
		super(message);
	}

	public GearmanCommunicationException(String message, Throwable cause) {
		super(message, cause);
	}

	public GearmanCommunicationException(Throwable cause) {
		super(cause);
	}
}
