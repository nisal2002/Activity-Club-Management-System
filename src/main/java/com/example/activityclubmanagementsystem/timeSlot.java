package com.example.activityclubmanagementsystem;

import java.io.Serializable;
import java.time.LocalDate;

public class timeSlot implements Serializable
{
    private meeting venue1;
    private meeting venue2;
    private meeting venue3;
    LocalDate date;


    public timeSlot(LocalDate  day) {
        this.date=day;
    }

    public meeting getVenue1() {
        return venue1;
    }

    public meeting getVenue2() {
        return venue2;
    }

    public void setVenue1(meeting venue1) {
        this.venue1 = venue1;
    }

    public void setVenue2(meeting venue2) {
        this.venue2 = venue2;
    }

    public meeting getVenue3() {
        return venue3;
    }

    public void setVenue3(meeting venue3) {
        this.venue3 = venue3;
    }
}
