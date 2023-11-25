package com.example.activityclubmanagementsystem;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class meeting  implements Serializable {
    private int meetingID;
    LocalDate day;
    private club club;
    private  String venue;
    private  int timeSlot;
    private String description;
    ArrayList<String> attendence;

    public boolean hasNext;
    public boolean isLast;
    public String code;


    public meeting(int Id, LocalDate  day, club club , String Venue, int timeSlot) {
        this.day=day;
        this.meetingID = Id;
        this.venue = Venue;
        this.timeSlot = timeSlot;
        this.club= club;
    }

    public com.example.activityclubmanagementsystem.club getClub() {
        return club;
    }
    public String getClubName()
    {
        return club.getClubName();
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public int getMeetingID() {
        return meetingID;
    }

    public LocalDate getDay() {
        return day;
    }

    public String getVenue() {
        return venue;
    }

    public int getTimeSlot() {
        return timeSlot;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (String st:attendence)
        {
            builder.append(st);
            builder.append("\n");
        }
        return "meeting{" +
                "meetingID=" + meetingID +
                ", day=" + day +
                ", club=" + club +
                ", venue='" + venue + '\'' +
                ", timeSlot=" + timeSlot +
                ", description='" + description + '\'' +
                ", attendence=" + builder.toString()+
                '}';
    }
}
