package com.example.activityclubmanagementsystem;

import java.io.Serial;
import java.io.Serializable;

public class person implements Serializable
{
    private String id;
    private String firstName;
    private String SurName;
    private String nic;
    private String dob;
    private String gender;
    private String email;

    private String password;
    @Serial
    private static final long serialVersionUID = -996908784191997247L;
// 996908784191997247
    public person(String id, String firstName, String surName, String nic, String dob, String gender, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.SurName = surName;
        this.nic = nic;
        this.dob = dob;
        this.gender = gender;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurName() {
        return SurName;
    }

    public String getNic() {
        return nic;
    }

    public String getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurName(String surName) {
        SurName = surName;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
