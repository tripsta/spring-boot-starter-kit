package common.gearmanclient.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GearmanResponse<T> extends GearmanEnvelope implements Serializable {

	private static final long serialVersionUID = 966778562313034151L;
	protected Error error;
	private T result;

	public Error getError() {
		return error;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}
}
