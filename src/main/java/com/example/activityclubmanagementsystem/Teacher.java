package com.example.activityclubmanagementsystem;

import java.time.LocalDate;

public class Teacher extends person
{
    private club inCharge;
    public Teacher(String id, String firstName, String surName, String nic, String dob, String gender, String email, String password)
    {
        super(id, firstName, surName, nic, dob, gender, email, password);
    }

    public club getInCharge() {
        club returnClub = null;

        if (inCharge!=null)
        {
            for (club Club:Data.getClubList())
            {
                if (Club.getClubId().equals(inCharge.getClubId()))
                {
                    returnClub=Club;
                }
            }
        }
        return returnClub;
    }

    public void setInCharge(club inCharge) {
        this.inCharge = inCharge;
    }

    public String getFullName()
    {
        String first=null;
        if (getGender().equals("Male"))
        {
            first="Mr.";
        }
        else
        {
            first="Mrs.";
        }
        return first+getFirstName()+" "+getSurName();
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
