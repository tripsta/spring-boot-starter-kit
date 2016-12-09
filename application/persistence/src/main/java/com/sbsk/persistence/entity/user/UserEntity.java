package com.sbsk.persistence.entity.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
//import javax.validation.constraints.Size;

@Entity(name = "user")
@Table(name = "user", indexes = {@Index(columnList = "lastName", unique = false)})
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

//    @Column(unique = true, nullable = false)
	private String firstName;

//    @Size(max = 20)
	private String lastName;

	private Integer age;
	private Boolean isAdult;
	private Integer dateCreated;

	public UserEntity() {
	}

	public UserEntity(String firstName, String lastName, Integer age, Boolean isAdult, Integer dateCreated) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.isAdult = isAdult;
		this.dateCreated = dateCreated;
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

	public Integer getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Integer dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age
				+ ", isAdult=" + isAdult + ", dateCreated=" + dateCreated + "]";
	}

}
