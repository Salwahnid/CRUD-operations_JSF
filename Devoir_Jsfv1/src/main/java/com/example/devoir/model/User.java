package com.example.devoir.model;

import java.util.Date;

public class User {
    public int id;
    private String firstName ;
    private String lastName ;
    private String City ;
    private String email ;
    private Date birthDate = new Date();


    public User(int id, String firstName, String lastName, String city, String email, Date birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        City = city;
        this.email = email;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User() {

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

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
