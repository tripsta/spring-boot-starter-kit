package common.gearmanclient.configuration;

import java.util.List;

public class GearmanClientConfiguration {

  private List<String> servers;
  private String workerName;
  private long timeout;

  public GearmanClientConfiguration(List<String> servers, String workerName, long timeout) {
    this.servers = servers;
    this.workerName = workerName;
    this.timeout = timeout;
  }

  public List<String> getServers() {
    return servers;
  }

  public void setServers(List<String> servers) {
    this.servers = servers;
  }

  public String getWorkerName() {
    return workerName;
  }

  public void setWorkerName(String workerName) {
    this.workerName = workerName;
  }

  public long getTimeout() {
    return timeout;
  }

  public void setTimeout(long timeout) {
    this.timeout = timeout;
  }
}
