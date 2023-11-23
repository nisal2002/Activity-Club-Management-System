package com.example.activityclubmanagementsystem;

public class Teacher extends person
{
    private club inCharge;
    public Teacher(String id, String firstName, String surName, String nic, String dob, String gender, String email, String password)
    {
        super(id, firstName, surName, nic, dob, gender, email, password);
    }

    public club getInCharge() {
        return inCharge;
    }

    public void setInCharge(club inCharge) {
        this.inCharge = inCharge;
    }

    @Override
    public String toString() {
        if (getInCharge()!=null) {
            return "Teacher{" +
                    "ID='" + getId() + "FirstName+ " + getFirstName() + "LastName= " + getSurName() + '\'' +
                    "DOB=" + getDob() + " email= " + getEmail() + '\'' + "incharge=" + getInCharge().getClubName() +
                    '}';
        }
        else
        {
            return "Teacher{" +
                    "ID='" + getId() + "FirstName+ " + getFirstName() + "LastName= " + getSurName() + '\'' +
                    "DOB=" + getDob() + " email= " + getEmail() + '\'' + "incharge= _" +
                    '}';
        }
    }
}
