package com.sbsk.dtos.user;

public class UserResponseDto {

  private String id;
  private String firstName;
  private String lastName;
  private Integer age;
  private Boolean isAdult;

  public UserResponseDto() {
  }

  public UserResponseDto(String id, String firstName, String lastName, Integer age, Boolean isAdult) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
    this.isAdult = isAdult;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public Boolean getIsAdult() {
    return isAdult;
  }

  public void setIsAdult(Boolean isAdult) {
    this.isAdult = isAdult;
  }

  @Override
  public String toString() {
    return "UserResponseDto{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", age=" + age +
            ", isAdult=" + isAdult +
            '}';
  }
}
