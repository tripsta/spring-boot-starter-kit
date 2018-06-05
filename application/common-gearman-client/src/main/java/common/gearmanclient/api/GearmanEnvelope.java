package common.gearmanclient.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class GearmanEnvelope implements Serializable {

	private static final long serialVersionUID = -3929747791803492357L;
	private String transactionId;
}
