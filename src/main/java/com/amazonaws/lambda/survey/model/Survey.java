package com.amazonaws.lambda.survey.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tm_survey")
public class Survey implements Serializable {

	private static final long serialVersionUID = -1725563866279171711L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name="userid")
    private String userId;
    @Column(name="firstname")
    private String firstName;
    @Column(name="lastname")
    private String lastName;
    @Column(name="age")
    private int age;
    @Column(name="preference")
    private String preference;
    
    public Survey() {}

    public Survey(Long id, String userId, String firstName, String lastName, int age, String preference) {
        this.id = id;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.preference = preference;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPreference() {
        return this.preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    public Survey id(Long id) {
        this.id = id;
        return this;
    }

    public Survey userId(String userId) {
        this.userId = userId;
        return this;
    }

    public Survey firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Survey lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Survey age(int age) {
        this.age = age;
        return this;
    }

    public Survey preference(String preference) {
        this.preference = preference;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Survey)) {
            return false;
        }
        Survey survey = (Survey) o;
        return Objects.equals(id, survey.id) && Objects.equals(userId, survey.userId) && Objects.equals(firstName, survey.firstName) && Objects.equals(lastName, survey.lastName) && age == survey.age && Objects.equals(preference, survey.preference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, firstName, lastName, age, preference);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", userId='" + getUserId() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", age='" + getAge() + "'" +
            ", preference='" + getPreference() + "'" +
            "}";
    }
}
