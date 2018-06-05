package com.sbsk.gearman;

import java.util.*;
import java.util.stream.Collectors;

public class ResourceMapSimpleCollectionType<K, V> extends HashMap<String, Set<V>> {

	private static final long serialVersionUID = 6710217741919586895L;

	public ResourceMapSimpleCollectionType() {
		super();
	}

	public static <V extends LocalizedResource> Map<String, V> transform(Set<V> collection) {
		return collection.stream()
		    .collect(Collectors.toMap(
		        k -> k.getCode(),
		        v -> v
		    ));
	}

	public static ResourceMapSimpleCollectionType<String, String> transform(ResourceMapSimpleCollectionType<String, LocalizedResource> values) {
		 return values.entrySet().stream()
		    .collect(Collectors.toMap(
		        k -> k.getKey(),
		        v -> v.getValue().stream().map(r -> r.getCode()).collect(Collectors.toSet()),
		        (k,v) -> k, ResourceMapSimpleCollectionType::new
		    ));
	}

	public void add(List<V> collection, LocalizedResourceType type) {
		Set<V> values = this.getOrDefault(type.name(), new HashSet<>());
		values.addAll(collection);
		this.put(type.name(), values);
	}

	public void add(V value, LocalizedResourceType type) {
		Set<V> values = this.getOrDefault(type.name(), new HashSet<>());
		values.add(value);
		this.put(type.name(), values);
	}

	public void add(Set<V> collection, LocalizedResourceType type) {
		Set<V> values = this.getOrDefault(type.name(), new HashSet<>());
		values.addAll(collection);
		this.put(type.name(), values);
	}

	@SuppressWarnings("unchecked")
	public void addAsLocalizedResource(Set<K> values, LocalizedResourceType type) {
		Set<V> resources = this.getOrDefault(type.name(), new HashSet<>());
		values.stream().forEach(
				value -> {
					LocalizedResource resource = new LocalizedResource();
					resource.setCode(String.valueOf(value));
					resources.add((V) resource);
				});
		this.put(type.name(), resources);
	}
}
