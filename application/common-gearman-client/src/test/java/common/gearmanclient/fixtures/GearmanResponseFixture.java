package common.gearmanclient.fixtures;

import common.gearmanclient.api.GearmanEnvelope;

public class GearmanResponseFixture extends GearmanEnvelope implements Fixture {

	private static final long serialVersionUID = -233679760887849638L;

	private String response;

	private String error;

	public String defaultResponse() {
		return "[{\"result\": {\"response\": \"that\"}}]";
	}

	public String errorResponse() {

		return "[{\"error\": \"failed\"}]";


	}
}
