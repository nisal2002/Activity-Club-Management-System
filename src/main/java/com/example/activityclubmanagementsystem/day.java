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
    private ArrayList<timeSlot> slots;

    public day(LocalDate day,int id) {
        this.day = day;
        this.id = id;
        slots = new ArrayList<>();
        for (int i=0;i<24;i++)
        {
            timeSlot slot = new timeSlot(day);
            slots.add(slot);
        }
//        timeSlot slot1 = new timeSlot(day);
//        timeSlot slot2 = new timeSlot(day);
//        timeSlot slot3 = new timeSlot(day);
//        timeSlot slot4 = new timeSlot(day);
//        timeSlot slot5 = new timeSlot(day);
//        timeSlot slot6 = new timeSlot(day);
//        timeSlot slot7 = new timeSlot(day);
//        timeSlot slot8 = new timeSlot(day);
//        timeSlot slot9 = new timeSlot(day);
//        slots.add(slot1);
//        slots.add(slot2);
//        slots.add(slot3);
//        slots.add(slot4);
//        slots.add(slot5);
//        slots.add(slot6);
//        slots.add(slot7);
//        slots.add(slot8);
//        slots.add(slot9);




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
}