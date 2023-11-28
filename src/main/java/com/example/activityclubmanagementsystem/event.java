package com.example.activityclubmanagementsystem;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class event implements Serializable
{
    @Serial
    private static final long serialVersionUID = -996908784481997212L;
    private club heldByClub;
    private String  eventName;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;
    private String description;
    private String venue;
    private ArrayList<String> attendance = new ArrayList<>();

    //Sevindu
    public event(club heldByClub, String eventName, LocalTime startTime, LocalTime endTime, LocalDate date, String description,String venue) {
        this.heldByClub = heldByClub;
        this.eventName = eventName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.description = description;
        this.venue=venue;
    }

    public club getHeldByClub() {
        return heldByClub;
    }

    public void setHeldByClub(club heldByClub) {
        this.heldByClub = heldByClub;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getAttendance() {
        return attendance;
    }

    public String getVenue() {
        return venue;
    }

    //Sevindu
    private ArrayList<Student> getStudents()
    {
        ArrayList<Student> studentList = new ArrayList<>();
        for (String studentId:attendance)
        {
            for (Student student:Data.getStudentList())
            {
                if (student.getId().equals(studentId))
                {
                    studentList.add(student);
                    break;
                }
            }
        }
        return studentList;
    }

    public void setAttendance(String id) {
        this.attendance.add(id);
        this.attendance.add("|");
    }
}