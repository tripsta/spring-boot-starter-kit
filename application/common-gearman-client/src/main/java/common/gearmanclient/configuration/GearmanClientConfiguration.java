package common.gearmanclient.configuration;

import common.gearmanclient.GearmanClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class GearmanClientConfiguration {

  @Value("${gearman.server}")
  private List<String> servers;

  @Value("${gearman.worker}")
  private String workerName;

  @Value("${gearman.timeout}")
  private long timeout;

  @Bean
  public GearmanClient gearmanClient() {
    return new GearmanClient(this);
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
