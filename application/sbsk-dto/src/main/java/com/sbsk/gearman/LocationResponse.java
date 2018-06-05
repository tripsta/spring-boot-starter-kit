package com.sbsk.gearman;

import common.gearmanclient.api.GearmanEnvelope;

public class LocationResponse extends GearmanEnvelope {

  private static final long serialVersionUID = -7353410053169862016L;

  private ResourceMapSimpleCollectionType<String, LocalizedResource> resources;
  private ResourceMapSimpleCollectionType<String, String> mappings;
  private String locale;

  public LocationResponse() {
  }

  public LocationResponse(ResourceMapSimpleCollectionType<String, LocalizedResource> resources, ResourceMapSimpleCollectionType<String, String> mappings, String locale) {
    this.resources = resources;
    this.mappings = mappings;
    this.locale = locale;
  }

  public ResourceMapSimpleCollectionType<String, LocalizedResource> getResources() {
    return resources;
  }

  public void setResources(ResourceMapSimpleCollectionType<String, LocalizedResource> resources) {
    this.resources = resources;
  }

  public ResourceMapSimpleCollectionType<String, String> getMappings() {
    return mappings;
  }

  public void setMappings(ResourceMapSimpleCollectionType<String, String> mappings) {
    this.mappings = mappings;
  }

  public String getLocale() {
    return locale;
  }

  public void setLocale(String locale) {
    this.locale = locale;
  }
}
