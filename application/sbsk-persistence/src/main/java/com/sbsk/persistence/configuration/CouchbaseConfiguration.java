package com.sbsk.persistence.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableCouchbaseRepositories(basePackages = "com.sbsk.persistence.repositories")
public class CouchbaseConfiguration extends AbstractCouchbaseConfiguration {

  @Value("${spring.couchbase.bootstrap-hosts}")
  private String host;
  @Value("${spring.couchbase.bucket.name}")
  private String bucketName;
  @Value("${spring.couchbase.bucket.password}")
  private String bucketPassword;

  @Override
  protected List<String> getBootstrapHosts() {
    return Arrays.asList(this.host);
  }

  @Override
  protected String getBucketName() {
    return this.bucketName;
  }

  @Override
  protected String getBucketPassword() {
    return this.bucketPassword;
  }
}