package com.example.activityclubmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class eventsAttendanceController implements Initializable {

    private final event currentEvent;
    private final Student currentStudent;
    @FXML
    private Text attendTxtlSlot;
    @FXML
    private Text AttendTxtClub;
    @FXML
    private Text attendTxtDate;
    @FXML
    private Text attendTxtVenue;
    @FXML
    private Text attendTxtTimeSlot;
    @FXML
    private Text attendTxtTeacher;
    @FXML
    private Text attendTxtDescription;
    @FXML
    private Text attendTxtDesc;
    @FXML
    private Button attendBtnAttend;
    @FXML
    private Button attendBtnCancel;
    @FXML
    private Label attendLblStart;
    @FXML
    private Label attendLblEnd;
    @FXML
    private Label startTime;
    @FXML
    private Label endTime;
    @FXML
    private Label attendLblName;


    public eventsAttendanceController(event CurrentEvent, Student currentStudent)
    {
        this.currentEvent = CurrentEvent;
        this.currentStudent = currentStudent;
    }

    public void onAttendClick(ActionEvent event)  // attend for event
    {
        boolean hasAttended= false;
        ArrayList<String> students = currentEvent.getAttendance();
        for (String id:students)
        {
            if (id.equals(currentStudent.getId()))
            {
                hasAttended=true;
            }
        }
        if (!hasAttended)
        {
            currentEvent.getAttendance().add(currentStudent.getId());
            Data.updateEvent(currentEvent);
        }
        else
        {
            warnings.register("you have already attended the event");
        }

    }

    public void onCancelClick(ActionEvent event)
    {
        Stage st=(Stage) attendTxtDescription.getScene().getWindow();
        st.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        attendLblName.setText(currentEvent.getEventName());
        AttendTxtClub.setText(currentEvent.getHeldByClub().getClubName());
        attendTxtDate.setText(currentEvent.getDate().toString());
        attendTxtVenue.setText(currentEvent.getVenue());
        startTime.setText(currentEvent.getStartTime().toString());
        endTime.setText(currentEvent.getEndTime().toString());
        String first=null;
        if (currentEvent.getHeldByClub().getInCharge().getGender().equals("Male"))
        {
            first="Mr.";
        }
        else
        {
            first="Mrs.";
        }
        attendTxtTeacher.setText(first+currentEvent.getHeldByClub().getInCharge().getFirstName()+" "+currentEvent.getHeldByClub().getInCharge().getSurName());
        attendTxtDescription.setText(currentEvent.getDescription());



    }
}
