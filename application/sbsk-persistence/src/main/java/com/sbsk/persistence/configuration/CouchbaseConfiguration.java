package com.sbsk.persistence.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableCouchbaseRepositories
public class CouchbaseConfiguration extends AbstractCouchbaseConfiguration {

    @Autowired
    Environment environment;

    @Value("${spring.couchbase.bootstrap-hosts}")
    private String host;
    @Value("${spring.couchbase.bucket.name}")
    private String bucketName;
    @Value("${spring.couchbase.bucket.password}")
    private String bucketPassword;

    @Override
    protected List<String> getBootstrapHosts() {
        return  Arrays.asList(host);
    }

    @Override
    protected String getBucketName() {
        return bucketName;
    }

    @Override
    protected String getBucketPassword() {
        return bucketPassword;
    }
}