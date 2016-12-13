package com.sbsk.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;



public abstract class GenericErrorContainer implements ErrorContainer {

	@JsonInclude(Include.ALWAYS)
	List<ApiError> errors = new ArrayList<>();
	List<ApiWarning> warnings = new ArrayList<>();
	
	@Override
	public void addError(ApiError error) {
		errors.add(error);
	}

	@Override
	public void addErrors(List<ApiError> errors) {
		this.errors = CollectionUtils.isEmpty(this.errors) ? new ArrayList<>() : this.errors;
		this.errors.addAll(errors);
	}


	@Override
	public List<ApiWarning> getWarnings() {
		return warnings;
	}

	@Override
	public void addWarning(ApiWarning warning) {
		this.warnings = CollectionUtils.isEmpty(this.warnings) ? new ArrayList<>() : this.warnings;
		this.warnings.add(warning);
	}

	@Override
	public void addWarnings(List<ApiWarning> warnings) {
		if(!CollectionUtils.isEmpty(warnings)){
			this.warnings = CollectionUtils.isEmpty(this.warnings) ? new ArrayList<>() : this.warnings;
			this.warnings.addAll(warnings);
		}
	}

	@Override
	public boolean hasErrors() {
		return !CollectionUtils.isEmpty(errors);
	}

	@Override
	public List<ApiError> getErrors() {
		if (errors == null)
			errors = new ArrayList<>();
		return errors;
	}

	public void setErrors(List<ApiError> errors) {
		this.errors = errors;
	}
}
