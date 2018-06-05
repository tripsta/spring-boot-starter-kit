package common.gearmanclient.fixtures;

import common.gearmanclient.api.GearmanEnvelope;

public class GearmanRequestFixture extends GearmanEnvelope implements Fixture {

	private static final long serialVersionUID = -2565898276911298920L;

	private String request;

	public GearmanRequestFixture defaultRequest() {
		this.setRequest("what");
		return this;
	}

	public void setRequest(String request) {
		this.request = request;
	}
}
