package com.example.activityclubmanagementsystem;

import javafx.beans.value.ObservableValue;

import java.util.ArrayList;

public class scheduleRow
{

    public int timeslot;

    ObservableValue<meeting> sat1=null;
    ObservableValue<meeting> sat2=null;
    ObservableValue<meeting> sat3=null;
    ObservableValue<meeting> sun1=null;
    ObservableValue<meeting> sun2=null;
    ObservableValue<meeting> sun3=null;


    public ArrayList<meeting> list = new ArrayList<>();



    public scheduleRow(int timeslot)
    {

        this.timeslot = timeslot;
        for (int i=0;i<10;i++)
        {
            list.add(null);
        }
    }

    public ArrayList<meeting> getList() {
        return list;
    }
}
