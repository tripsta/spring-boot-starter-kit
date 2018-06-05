package com.sbsk.gearman;

import common.gearmanclient.api.GearmanEnvelope;

import java.util.Arrays;
import java.util.HashSet;

public class LocationRequest extends GearmanEnvelope {

  private static final long serialVersionUID = 2653066881558614249L;

  public static final ResourceMapSimpleCollectionType<String, String> EXTERNAL_RESOURCE_MAPPINGS_DEFAULT;

  static {
    EXTERNAL_RESOURCE_MAPPINGS_DEFAULT = new ResourceMapSimpleCollectionType<>();
    EXTERNAL_RESOURCE_MAPPINGS_DEFAULT.put(
        LocalizedResourceType.airports.name(),
        new HashSet<>(Arrays.asList(LocalizedResourceType.cities.name(), LocalizedResourceType.countries.name())));
  }

  private ResourceMapSimpleCollectionType<String, String> resources;
  private ResourceMapSimpleCollectionType<String, String> mappings;
  private String locale;

  public LocationRequest() {
  }

  public LocationRequest(ResourceMapSimpleCollectionType<String, String> resources, ResourceMapSimpleCollectionType<String, String> mappings, String locale) {
    this.resources = resources;
    this.mappings = mappings;
    this.locale = locale;
  }

  public ResourceMapSimpleCollectionType<String, String> getResources() {
    return resources;
  }

  public void setResources(ResourceMapSimpleCollectionType<String, String> resources) {
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
