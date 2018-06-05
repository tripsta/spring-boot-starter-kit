package com.sbsk.gearman;

import common.gearmanclient.GearmanClient;
import common.gearmanclient.configuration.GearmanClientConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResourceClient {

  @Value("${gearman.server}")
  private List<String> servers;

  @Value("${gearman.worker}")
  private String workerName;

  @Value("${gearman.timeout}")
  private long timeout;

  public GearmanClient getGearmanClientInstance() {
    return new GearmanClient(new GearmanClientConfiguration(servers, workerName, timeout));
  }
}
