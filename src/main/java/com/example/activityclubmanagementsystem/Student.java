package com.example.activityclubmanagementsystem;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Student extends person {
    private ArrayList<club> clubs;


    public Student(String studentID, String fname, String sname, String nic, String dob, String gender, String email, String pwd) {
        super(studentID, fname, sname, nic, dob, gender, email, pwd);
        clubs = new ArrayList<>();

    }
    public String getFullName()
    {
        String first=null;
        if (getGender().equals("Male"))
        {
            first="Master.";
        }
        else
        {
            first="Miss.";
        }
        return first+getFirstName()+" "+getSurName();
    }
    public int getGrade()
    {
        int birthYear= LocalDate.parse(getDob()).getYear();
        int nowYear = LocalDate.now().getYear();
        int grade = (birthYear+6)-nowYear;
        return grade;
    }

    public ArrayList<club> getClubs()
    {
        ArrayList<club> newClubs = new ArrayList<>();
        for (club Club:clubs)
        {
            for (club C:Data.getClubList())
            {
                if(C.getClubId().equals(Club.getClubId()))
                {
                    newClubs.add(C);
                    break;
                }
            }
        }
        return newClubs;
    }
    public void addClub(club Club)
    {
        clubs.add(Club);
    }

    public void setClubs(ArrayList<club> clubs) {
        this.clubs = clubs;
    }
    public String isMember(String clubId)
    {
        boolean member=false;
        String s=null;
        for (club Club:clubs)
        {
            if (Club.getClubId().equals(clubId))
            {
                member=true;
                break;
            }
        }
        if (member)
        {
            s="yes";
        }
        else
        {
            s="no";
        }
        return s;

    }

    @Override
    public String toString() {
        StringBuilder build = new StringBuilder();
        for (club c:clubs)
        {
            build.append(c.getClubName());
            build.append(" , ");
        }
        return "Student{" +
                "id=" + getId() +" name="+getFirstName()+" "+getSurName()+"\n"+
                build.toString()+
                '}';
    }
}