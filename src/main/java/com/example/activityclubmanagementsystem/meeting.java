package com.example.activityclubmanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

//Sevindu
public class meeting  implements Serializable {
    private int meetingID;
    LocalDate day;
    private club club;
    private  String venue;
    private  int timeSlot;
    private String description;
    private int TotalStudents;
    ArrayList<String> attendence;

    public String code;
    private int totalSlots;
    private LocalTime start;
    private LocalTime end;

    //Sevindu
    public meeting(int Id, LocalDate  day, club club , String Venue, int timeSlot) {
        this.day=day;
        this.meetingID = Id;
        this.venue = Venue;
        this.timeSlot = timeSlot;
        this.club= club;
        attendence = new ArrayList<>();
        this.TotalStudents= club.getMembers().size();
    }

    public int getTotalStudents() {
        return TotalStudents;
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
    public void addAttendance(String  student)
    {

        attendence.add(student);
    }

    //Sevindu
    public ArrayList<String> getAttendence() {
        return attendence;
    }
    public ObservableList<Student> getStudentList()
    {
        ObservableList<Student> students = FXCollections.observableArrayList();
        for (String st:attendence)
        {
            for (Student student:Data.getStudentList())
            {
                if (student.getId().equals(st))
                {
                    students.add(student);
                    break;
                }
            }
        }
        return students;
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

    public int getTotalSlots()
    {
        return totalSlots;
    }

    public void setTotalSlots(int totalSlots)
    {
        this.totalSlots = totalSlots;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    //Sevindu
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

