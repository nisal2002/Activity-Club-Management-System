package com.example.activityclubmanagementsystem;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class warnings
{
    public static  void SqlWarning()
    {
        Alert alert = new Alert(Alert.AlertType.WARNING,"SQL Connection Error", ButtonType.OK);
        alert.showAndWait();
    }
    public static void register(String name)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,name, ButtonType.OK);
        alert.showAndWait();
    }
    public static void FileError()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR,"file cannot be created",ButtonType.OK);
        alert.showAndWait();
    }
    public static void attendanceWarning(event Event)
    {
        LocalDate nowDate = LocalDate.now();
        LocalTime nowTime = LocalTime.now();
        String warning=null;
        if (nowDate.isAfter(Event.getDate()))
        {
            warning="Event is over";
        }
        else if (nowDate.isBefore(Event.getDate()))
        {
            warning = "Event is yet to be started";
        }
        else if (nowDate.equals(Event.getDate()))
        {
            if (nowTime.isAfter(Event.getEndTime()))
            {
                warning="Event is over";
            }
            else
            {
                warning = "Event is yet to be started";
            }
        }
        Alert alert = new Alert(Alert.AlertType.WARNING,warning,ButtonType.OK);
        alert.showAndWait();
    }
    public static void attendanceActivityWarning(meeting Meeting)
    {
        LocalDate nowDate = LocalDate.now();
        LocalTime nowTime = LocalTime.now();
        String warning=null;
        if (nowDate.isAfter(Meeting.getDay()))
        {
            warning="Activity is over";
        }
        else if (nowDate.isBefore(Meeting.getDay()))
        {
            warning = "Activity is yet to be started";
        }
        else
        {
            if (nowTime.isBefore(Meeting.getStart()))
            {
                warning = "Activity is yet to be started";
            }
            else if (nowTime.isAfter(Meeting.getEnd()))
            {
                warning="Activity is over";
            }
        }
        Alert alert = new Alert(Alert.AlertType.WARNING,warning,ButtonType.OK);
        alert.showAndWait();


    }
    public static void attendanceMeetingWarning(meeting Meeting,int row)
    {
        ArrayList<LocalTime> timeList= new ArrayList<>();
        LocalTime time1_begin= LocalTime.parse("07:30:00");
        LocalTime time1_end= LocalTime.parse("08:05:00");
        LocalTime time2_begin= LocalTime.parse("08:05:00");
        LocalTime time2_end= LocalTime.parse("08:40:00");
        LocalTime time3_begin= LocalTime.parse("08:40:00");
        LocalTime time3_end= LocalTime.parse("09:15:00");
        LocalTime time4_begin= LocalTime.parse("09:15:00");
        LocalTime time4_end= LocalTime.parse("09:50:00");
        LocalTime time5_begin= LocalTime.parse("09:50:00");
        LocalTime time5_end= LocalTime.parse("10:25:00");
        LocalTime time6_begin= LocalTime.parse("10:25:00");
        LocalTime time6_end= LocalTime.parse("11:00:00");
        LocalTime time7_begin= LocalTime.parse("11:00:00");
        LocalTime time7_end= LocalTime.parse("11:45:00");
        LocalTime time8_begin= LocalTime.parse("11:45:00");
        LocalTime time8_end= LocalTime.parse("12:30:00");
        LocalTime time9_begin= LocalTime.parse("12:30:00");
        LocalTime time9_end= LocalTime.parse("13:15:00");
        timeList.add(time1_begin);
        timeList.add(time1_end);
        timeList.add(time2_begin);
        timeList.add(time2_end);
        timeList.add(time3_begin);
        timeList.add(time3_end);
        timeList.add(time4_begin);
        timeList.add(time4_end);
        timeList.add(time5_begin);
        timeList.add(time5_end);
        timeList.add(time6_begin);
        timeList.add(time6_end);
        timeList.add(time7_begin);
        timeList.add(time7_end);
        timeList.add(time8_begin);
        timeList.add(time8_end);
        timeList.add(time9_begin);
        timeList.add(time9_end);
        LocalDate nowDate = LocalDate.now();
        LocalTime nowTime = LocalTime.now();
        String warning=null;
        if (nowDate.isAfter(Meeting.getDay()))
        {
            warning="Meeting is over";
        }
        else if (nowDate.isBefore(Meeting.getDay()))
        {
            warning = "Meeting is yet to be started";
        } else if (nowDate.equals(Meeting.getDay()))
        {
            if (nowTime.isBefore(timeList.get(row*2)))
            {
                warning = "Meeting is yet to be started";
            }
            else if (nowTime.isAfter(timeList.get(row*2+1)))
            {
                warning="Meeting is over";
            }

        }
        Alert alert = new Alert(Alert.AlertType.WARNING,warning,ButtonType.OK);
        alert.show();
    }

}
