package com.sbsk.model;

import java.util.List;

public interface ErrorContainer {

  void addError(ApiError error);

  void addErrors(List<ApiError> errors);

  List<ApiError> getErrors();

  boolean hasErrors();

  List<ApiWarning> getWarnings();

  void addWarning(ApiWarning warning);

  void addWarnings(List<ApiWarning> warnings);
}
