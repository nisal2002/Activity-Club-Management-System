package com.example.activityclubmanagementsystem;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class day implements Serializable
{
    @Serial
    private static final long serialVersionUID = -996908784191997212L;
    private int id;
    private final LocalDate day;
    private event Event;
    private final ArrayList<timeSlot> slots;
    private ArrayList<Integer> venue1;
    private ArrayList<Integer> venue2;
    private ArrayList<Integer> venue3;

    public day(LocalDate day,int id,int numberOfSlots) {
        this.day = day;
        this.id = id;
        slots = new ArrayList<>();
        for (int i=0;i<numberOfSlots;i++)
        {
            timeSlot slot = new timeSlot(day);
            slots.add(slot);
        }
        if (numberOfSlots==24)
        {
            venue1=new ArrayList<>();
            venue2=new ArrayList<>();
            venue3 = new ArrayList<>();
            for (int i=0;i<24;i++)
            {
                venue1.add(0);
                venue2.add(0);
                venue3.add(0);
            }
        }

    }

    public LocalDate getDay() {
        return day;
    }

    public ArrayList<timeSlot> getSlots() {
        return slots;
    }

    public int getId() {
        return id;
    }

    public event getEvent() {
        return Event;
    }

    public void setEvent(event event) {
        Event = event;
    }

    public ArrayList<Integer> getVenue1() {
        return venue1;
    }

    public ArrayList<Integer> getVenue2() {
        return venue2;
    }

    public ArrayList<Integer> getVenue3() {
        return venue3;
    }
}