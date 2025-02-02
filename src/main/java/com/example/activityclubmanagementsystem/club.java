package com.example.activityclubmanagementsystem;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class club implements Serializable {
    @Serial
    private static final long serialVersionUID = -996908784191997275L;

    private String clubName;
    private String clubId;
    private Teacher inCharge;
    private String description;
    private ArrayList<Student> boardMembers = new ArrayList<>();
    private ArrayList<Student> members=new ArrayList<>();
    private ArrayList<meeting> attendence;

    public club(String Id,String clubName,Teacher inCharge,String desc) {
        this.clubId = Id;
        this.clubName = clubName;
        this.inCharge = inCharge;
        this.description = desc;
        attendence = new ArrayList<>();
        for (int i=0;i<6;i++)
        {
            boardMembers.add(null);
        }
    }

    public String getClubId() {
        return clubId;
    }
    public String getClubName() {
        return clubName;
    }

    public Teacher getInCharge() {
        return inCharge;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Student> getBoardMembers() {
        return boardMembers;
    }

    public ArrayList<Student> getMembers() {
        return members;
    }

    public void setInCharge(Teacher inCharge) {
        this.inCharge = inCharge;
    }


    public ArrayList<meeting> getAttendence() {
        return attendence;
    }

    public void setBoardMembers(ArrayList<Student> boardMembers) {
        this.boardMembers = boardMembers;
    }

    public void setMembers(ArrayList<Student> members) {
        this.members = members;
    }

//    public void setAttendence(ArrayList<ArrayList<String>> attendence) {
//        this.attendence = attendence;
//    }

    @Override
    public String toString() {  //
        return "club{" +
                "clubName='" + clubName + '\'' +
                ", clubId=" + clubId +
                ", ticId='" + inCharge.getId() + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
