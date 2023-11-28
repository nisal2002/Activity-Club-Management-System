package com.example.activityclubmanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class scheduleMeetingController implements Initializable
{
    @FXML
    private TextArea txtDesc;
    @FXML
    private Text TxtClubName;
    @FXML
    private Text TxtTimeSlot;
    @FXML
    private Text TxtVenue;
    @FXML
    private Text TxtDate;
    private int id;
    private LocalDate date;
    private club club;
    private String venue;
    private int timeslot;

    ObservableList<day> dayList = FXCollections.observableArrayList(Data.getDayList());

    //Sevindu
    public scheduleMeetingController(int id, LocalDate date, com.example.activityclubmanagementsystem.club club, String venue, int timeslot) {
        this.id = id;
        this.date = date;
        this.club = club;
        this.venue = venue;
        this.timeslot = timeslot;
    }

    //Sevindu
    public void onScheduleClick(ActionEvent event) throws SQLException {


             Boolean found =false;
             meeting newmeeting= new meeting(id,date,club,venue,timeslot);
             if (!txtDesc.getText().isEmpty())
             {
                 newmeeting.setDescription(txtDesc.getText());
             }
            for (day day:dayList)
            {
                if (day.getDay().equals(date))
                {
                    found=true;
                    for (int i=0;i<9;i++)
                    {
                        if (i==timeslot)
                        {

                            if (venue.equals("1"))
                            {
                                day.getSlots().get(i).setVenue1(newmeeting);
                                Data.updateDay(day);
                            }
                            else
                            {
                                day.getSlots().get(i).setVenue2(newmeeting);
                                Data.updateDay(day);
                            }
                        }
                    }
                }

            }
            if(found.equals(false))
            {

                int id = Integer.parseInt(Data.getNextId("days"));
                com.example.activityclubmanagementsystem.day newDay = new day(date,id,9);
                if (venue.equals("1"))
                {
                    newDay.getSlots().get(timeslot).setVenue1(newmeeting);
                }
                else
                {
                    newDay.getSlots().get(timeslot).setVenue2(newmeeting);
                }
                Data.addDay(newDay);



            }
            Data.incrementMeetingID();
            ActionEvent ev = new ActionEvent();
            onCancelClick(ev);

    }



    //Sevindu
    public void onCancelClick(ActionEvent event)
    {
        Stage stage = (Stage) (TxtVenue.getScene().getWindow());
        stage.close();
    }

    //Sevindu
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TxtClubName.setText(club.getClubName());
        TxtVenue.setText(venue);
        TxtTimeSlot.setText(String.valueOf(timeslot+1));
        TxtDate.setText(date.toString());
    }
}
