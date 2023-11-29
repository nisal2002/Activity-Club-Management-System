package com.example.activityclubmanagementsystem;

import java.util.ArrayList;

public class getText // getting inputs
{
    public final ArrayList<String> inputs;
    public final Boolean complete;

    public getText(Boolean complete,ArrayList<String> inputs) {
        this.inputs = inputs;
        this.complete = complete;
    }
}
