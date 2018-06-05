package common.gearmanclient;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import common.gearmanclient.api.GearmanEnvelope;
import common.gearmanclient.api.GearmanResponse;
import common.gearmanclient.api.GearmanResponseHandler;
import common.gearmanclient.configuration.GearmanClientConfiguration;
import common.gearmanclient.exceptions.GearmanCommunicationException;
import common.gearmanclient.exceptions.GearmanResponseException;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang3.StringUtils;
import org.gearman.client.GearmanClientImpl;
import org.gearman.client.GearmanJob;
import org.gearman.client.GearmanJobImpl;
import org.gearman.client.GearmanJobResult;
import org.gearman.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class GearmanClient extends GearmanClientImpl {

  private static final Logger LOGGER = LoggerFactory.getLogger(GearmanClient.class);
  private static final ObjectMapper MAPPER = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

  protected GearmanClientConfiguration configuration;
  private GearmanResponseHandler gearmanResponseHandler = new GearmanResponseHandler();

  public GearmanClient() {
  }

  public GearmanClient(GearmanClientConfiguration configuration) {
    this.configuration = configuration;
    for (String server : configuration.getServers()) {
      String[] parts = server.split(":", 2);
      addJobServer(parts[0], Integer.parseInt(parts[1]));
    }
  }

  /**
   * Sends a list of JSON requests to an active Gearman worker by using the Gearman protocol (TPC)
   */
  public <R, T> List<T> send(List<? extends GearmanEnvelope> requests, Class<T> clazz)
      throws GearmanCommunicationException, GearmanResponseException {

    List<GearmanResponse<T>> responses;
    try {
      GearmanJob job = createJob(requests, configuration.getWorkerName());
      GearmanJobResult jobResult = submitJob(job, configuration.getTimeout());
      responses = processJobResult(jobResult, clazz);
      return processResponses(requests, responses, clazz);
    } catch (InterruptedException | ExecutionException | TimeoutException | IOException e) {
      throw new GearmanCommunicationException(e.getMessage(), e);
    } finally {
      shutdownNow();
    }
  }

  /**
   * Submits JSON requests to an active Gearman worker by using a properly constructed GearmanJob object.
   */
  protected GearmanJobResult submitJob(GearmanJob job, long timeout)
      throws InterruptedException, ExecutionException, TimeoutException {
    Future<GearmanJobResult> futureJobResult = submit(job);
    return (timeout > 0) ? futureJobResult.get(timeout, TimeUnit.MILLISECONDS) : futureJobResult.get();
  }

  /**
   * Creates a new GearmanJob by converting the provided list of java objects to a JSON requests converted into a byte
   * array.
   */
  protected <T, R> GearmanJob createJob(List<R> requests, String name) throws IOException {
    String jsonRequest = MAPPER.writeValueAsString(requests.toArray());
    LOGGER.debug(String.format("Json request: {%s}", jsonRequest));
    byte[] data = ByteUtils.toUTF8Bytes(jsonRequest);
    String jobId = UUID.randomUUID().toString();
    return GearmanJobImpl.createJob(name, data, jobId);
  }

  /**
   * Processes JSON response as received by a Gearman worker in the form of a GearmanJobResult object and then converts
   * this response to the corresponding object by using the provided java class (clazz).
   */
  protected <T> List<GearmanResponse<T>> processJobResult(GearmanJobResult result, Class<T> clazz)
      throws IOException, GearmanResponseException {
    String jsonResult = ByteUtils.fromUTF8Bytes(result.getResults());
    if (StringUtils.isEmpty(jsonResult)) {
      throw new GearmanResponseException(
          String.format("The request failed with an empty response for: %s", clazz.getName()));
    }
    LOGGER.debug(String.format("Json response: {%s}", jsonResult));
    TypeFactory typeFactory = MAPPER.getTypeFactory();
    JavaType parametricType = typeFactory.constructParametricType(GearmanResponse.class, clazz);
    return MAPPER.readValue(jsonResult, typeFactory.constructCollectionType(List.class, parametricType));
  }

  /**
   * Converts a list of GearmanResponse objects into a list of objects based on the provided target class
   * Supports interception through GearmanResponseHandler
   */
  protected <T> List<T> processResponses(List<? extends GearmanEnvelope> requests, List<GearmanResponse<T>> responses,
                                         Class<T> clazz)
      throws GearmanResponseException {
    List<T> results = new ArrayList<>();
    if (!CollectionUtils.isEmpty(responses)) {
      gearmanResponseHandler.handleResponse(requests, responses);
      for (GearmanResponse<T> response : responses) {
        if (response.getError() != null) {
          try {
            T instance = clazz.newInstance();
            BeanUtilsBean.getInstance().copyProperties(instance, response);
            results.add(instance);
          } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
            throw new GearmanResponseException(e.getMessage(), e);
          }
        } else if (response.getResult() != null) {
          results.add(response.getResult());
        }
      }
    }
    return results;
  }
}
