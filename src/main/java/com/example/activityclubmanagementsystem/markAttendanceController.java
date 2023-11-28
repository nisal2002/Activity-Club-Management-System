package com.example.activityclubmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class markAttendanceController implements Initializable {
    @FXML
    private Text attendTxtDate;
    @FXML
    private Text attendTxtVenue;
    @FXML
    private Text attendTxtTimeSlot;
    @FXML
    private Text attendTxtDesc;
    @FXML
    private Text attendTxtTeacher;
    @FXML
    private Text AttendTxtClub;
    meeting CurrentMeeing;
    Student student;

    public markAttendanceController(meeting currentMeeting,Student student)
    {
        this.CurrentMeeing = currentMeeting;
        this.student = student;


    }


    @FXML
    private Button attendBtnAttend;
    @FXML
    private Button attendBtnCancel;
    public void onAttendClick(ActionEvent event) throws SQLException {
        ArrayList<String> attendence = CurrentMeeing.getAttendence();
        Boolean hasAttended=false;
        for (String id:attendence)
        {
            if (id.equals(this.student.getId()))
            {
                hasAttended=true;
            }
        }
        if (!hasAttended)
        {
            CurrentMeeing.addAttendance(this.student.getId());
            Data.updateMeeting(CurrentMeeing);
            warnings.register("you attended");

        }
        else
        {
            warnings.register("you have already attended this meeting");
        }
        ActionEvent ev = new ActionEvent();
        onCancelClick(ev);
    }
    public void onCancelClick(ActionEvent event)
    {

        Stage stage = (Stage) (attendTxtDesc.getScene().getWindow());
        stage.close();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        AttendTxtClub.setText(CurrentMeeing.getClubName());
        attendTxtDate.setText(CurrentMeeing.getDay().toString());
        attendTxtTimeSlot.setText(String.valueOf(CurrentMeeing.getTimeSlot()+1));
        attendTxtVenue.setText(CurrentMeeing.getVenue());
        attendTxtDesc.setText(CurrentMeeing.getDescription());
        attendTxtTeacher.setText(CurrentMeeing.getClub().getInCharge().getFirstName()+" "+CurrentMeeing.getClub().getInCharge().getSurName());

    }
}
