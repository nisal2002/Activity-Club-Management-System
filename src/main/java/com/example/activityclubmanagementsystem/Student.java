package com.example.activityclubmanagementsystem;

import java.util.ArrayList;

public class Student extends person {
    private ArrayList<club> clubs;


    public Student(String studentID, String fname, String sname, String nic, String dob, String gender, String email, String pwd) {
        super(studentID, fname, sname, nic, dob, gender, email, pwd);
        clubs = new ArrayList<>();

    }

    public ArrayList<club> getClubs() {
        return clubs;
    }
    public void addClub(club Club)
    {
        clubs.add(Club);
    }

    public void setClubs(ArrayList<club> clubs) {
        this.clubs = clubs;
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