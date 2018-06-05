package com.sbsk.gearman;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LocalizedResource  implements Serializable {

	private static final long serialVersionUID = 3436130089746654518L;

	private String code;
	private String name;
	private String uri;

	public LocalizedResource() {
	}

	public LocalizedResource(String code, String name, String uri) {
		this.code = code;
		this.name = name;
		this.uri = uri;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	@Override
	public String toString() {
		return "LocalizedResource{" +
				"code='" + code + '\'' +
				", name='" + name + '\'' +
				", uri='" + uri + '\'' +
				'}';
	}
}
