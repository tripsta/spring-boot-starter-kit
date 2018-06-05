package com.sbsk.service.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.sbsk.gearman.*;
import com.sbsk.persistence.entities.user.UserEntity;
import com.sbsk.persistence.repositories.UserRepository;
import common.gearmanclient.exceptions.GearmanCommunicationException;
import common.gearmanclient.exceptions.GearmanResponseException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Query implements GraphQLQueryResolver {

  private UserRepository userRepository;
  private ResourceClient client;

  public Query(UserRepository userRepository, ResourceClient client) {
    this.userRepository = userRepository;
    this.client = client;
  }

  public Iterable<UserEntity> users() {
    return userRepository.findAll();
  }

  public Iterable<LocationResponse> locations() throws GearmanResponseException, GearmanCommunicationException {
    LocationRequest request = new LocationRequest();
    request.setLocale("en_US");
    request.setMappings(LocationRequest.EXTERNAL_RESOURCE_MAPPINGS_DEFAULT);
    request.setResources(defaultResources());

    List<LocationResponse> responses = client.getGearmanClientInstance().send(List.of(request), LocationResponse.class);

    return responses;
  }

  private ResourceMapSimpleCollectionType<String, String> defaultResources() {
    ResourceMapSimpleCollectionType<String, LocalizedResource> resources = new ResourceMapSimpleCollectionType<>();
    resources.add(defaultResource("OA"), LocalizedResourceType.airlines);
    resources.add(defaultResource("ATH"), LocalizedResourceType.airports);
    resources.add(defaultResource("Y"), LocalizedResourceType.cabinClasses);
    resources.add(defaultResource("A320"), LocalizedResourceType.equipment);
//    resources.add(defaultResource("ATH"), LocalizedResourceType.cities);
//    resources.add(defaultResource("ATH"), LocalizedResourceType.countries);

    return ResourceMapSimpleCollectionType.transform(resources);
  }

  private LocalizedResource defaultResource(String code) {
    LocalizedResource resource = new LocalizedResource();
    resource.setCode(code);

    return resource;
  }
}
