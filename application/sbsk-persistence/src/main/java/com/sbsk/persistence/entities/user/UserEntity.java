package com.sbsk.persistence.entities.user;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;

@Document(expiry = 10)
public class UserEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Field
    private String firstName;
    @Field
    private String lastName;
    @Field
    private Integer age;
    @Field
    private Boolean isAdult;
    @Field
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Integer getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Integer dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", isAdult=" + isAdult +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
