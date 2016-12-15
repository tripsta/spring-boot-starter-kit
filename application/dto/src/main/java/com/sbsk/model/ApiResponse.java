package com.sbsk.model;


import java.util.List;

/**
 * Generic Api Response Interface
 */
public interface ApiResponse extends ErrorContainer {

  String getVersion();

  String getSid();

  void setSid(String sid);

  boolean hasErrors();

  List<ApiError> getErrors();

  void addError(ApiError error);

  void addErrors(List<ApiError> errors);

  List<ApiWarning> getWarnings();

  void addWarning(ApiWarning warning);

  void addWarnings(List<ApiWarning> warnings);

  void insertData(String key, Object obj);

  Object retrieveData(String key);

  boolean isSuccess();

  void setSuccessful();

  Object retrieveMetadata(String key);

  void insertMetadata(String key, Object obj);
}
