package common.gearmanclient.junit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import common.gearmanclient.GearmanClient;
import common.gearmanclient.api.GearmanResponse;
import common.gearmanclient.configuration.GearmanClientConfiguration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseTest {

	protected static final ObjectMapper MAPPER =
			new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	protected class GearmanClientTester extends GearmanClient {

		private Map<String, Integer> servers;

		public GearmanClientTester(GearmanClientConfiguration configuration) {
			super(configuration);
		}

		@Override
		public boolean addJobServer(String host, int port) {
			if (servers == null) {
				servers = new HashMap<>();
			}
			servers.put(host, port);
			return true;
		}
	}

	private static String getFileContents(String path) throws IOException {
		return Resources.toString(Resources.getResource(path), Charsets.UTF_8);
	}

	protected static <T> T loadRequest(String path, Class<T> clazz) throws Exception {
		return MAPPER.readValue(getFileContents(path), clazz);
	}

	protected static <T> List<T> loadRequests(String path, Class<T> clazz) throws Exception {
		TypeFactory typeFactory = MAPPER.getTypeFactory();
		return MAPPER.readValue(getFileContents(path), typeFactory.constructCollectionType(List.class, clazz));
	}

	protected static <T> List<GearmanResponse<T>> loadResponses(String path, Class<T> clazz) throws Exception {
		TypeFactory typeFactory = MAPPER.getTypeFactory();
		JavaType parametricType = typeFactory.constructParametricType(GearmanResponse.class, clazz);
		return MAPPER.readValue(getFileContents(path), typeFactory.constructCollectionType(List.class, parametricType));
	}

	protected static <T> String toJson(T object) throws JsonProcessingException  {
		return MAPPER.writeValueAsString(object);
	}

	protected static <T> List<GearmanResponse<T>> loadGearmanResponses(List<T> responses) throws Exception {
		List<GearmanResponse<T>> gearmanResponses = new ArrayList<>();
		responses.stream().forEach(
				response -> {
					GearmanResponse<T> gearmanReponse = new GearmanResponse<>();
					gearmanReponse.setResult(response);
					gearmanResponses.add(gearmanReponse);
				});

		return gearmanResponses;
	}

}
