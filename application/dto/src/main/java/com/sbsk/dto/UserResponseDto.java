package com.sbsk.dto;

public class UserResponseDto {

	private String firstName;
	private String lastName;
	private Integer age;
	private Boolean isAdult;

	public UserResponseDto() {
	}

	public UserResponseDto(String firstName, String lastName, Integer age, Boolean isAdult) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.isAdult = isAdult;
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
		return "UserResponseDto [firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", isAdult="
				+ isAdult + "]";
	}

}
