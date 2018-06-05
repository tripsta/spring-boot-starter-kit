package common.gearmanclient.fixtures;

import common.gearmanclient.configuration.GearmanClientConfiguration;

import java.util.Arrays;

public class GearmanClientConfigurationFixture implements Fixture {

	GearmanClientConfiguration configuration = new GearmanClientConfiguration();

	public GearmanClientConfiguration defaultConfiguration() {
		configuration.setServers(Arrays.asList("127.0.0.1:4730", "remote:4893"));
		configuration.setTimeout(60000L);
		configuration.setWorkerName("my Worker");

		return configuration;
	}

}
