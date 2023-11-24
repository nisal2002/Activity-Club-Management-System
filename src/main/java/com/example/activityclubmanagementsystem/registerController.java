package com.example.activityclubmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class registerController {
    @FXML
    private Pane teapane;

    @FXML
    private Button admin;

    @FXML
    private Pane adpane;

    @FXML
    private Button student;

    @FXML
    private Pane stupane;

    @FXML
    private Button teacher;
    @FXML
    private TextField teacherTxtID;
    @FXML
    private TextField teacherTxtFirstN;
    @FXML
    private TextField teacherTxtLastN;
    @FXML
    private TextField teacherTxtNic;
    @FXML
    private TextField teacherTxtEmail;
    @FXML
    private DatePicker teacherDateDob;
    @FXML
    private ChoiceBox<String> teacherChoiceGender;
    @FXML
    private PasswordField teacherTxtAuth;
    @FXML
    private PasswordField teacherTxtPwd;
    @FXML
    private PasswordField teacherTxtRePwd;
    @FXML
    public void onBackClick(ActionEvent event) throws IOException {
        Parent currentPage= FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene currentpageScene = new Scene(currentPage);
        Stage addStage =(Stage)((Node)event.getSource()).getScene().getWindow();
        addStage.setScene(currentpageScene);
        addStage.show();
    }
    @FXML
    public void selectClick(ActionEvent actionEvent) {
        if (actionEvent.getSource() == student) {
            stupane.toFront();
        }
        if (actionEvent.getSource() == teacher) {
            teapane.toFront();
        }
        if (actionEvent.getSource() == admin) {
            adpane.toFront();
        }
    }
    /////////////////////////Teacher Methods////////////////////////////

    public void onTeacherRegisterClick(ActionEvent event) throws SQLException {
        getText get = getTeacher();
        if (get.complete && get.inputs.get(8).equals("12345"))
        {
            ArrayList<String> inputs = get.inputs;
            Teacher newTeacher = new Teacher(inputs.get(0),inputs.get(1),inputs.get(2),inputs.get(3),inputs.get(4),inputs.get(5),inputs.get(6),inputs.get(7));
            Data.addTeacher(newTeacher);
            String first=null;
            if (inputs.get(5).equals("male"))
            {
                first = "Mr. ";
            }
            else
            {
                first ="Mrs. ";
            }
            String massage = first+inputs.get(1)+" "+inputs.get(2)+"has been added as a student";
            warnings.register(massage);
            clearTeacher();
            teacherTxtID.setText("T"+Data.getNextId("teachers"));

        }

    }

    public void onTeacherClearClick(ActionEvent event) {

        clearTeacher();
    }
    private void clearTeacher() {
        teacherTxtID.clear();
        teacherTxtFirstN.clear();
        teacherTxtLastN.clear();
        teacherTxtNic.clear();
        teacherDateDob.getEditor().clear();
        teacherDateDob.setValue(null);
        teacherChoiceGender.setValue(null);
        teacherTxtEmail.clear();
        teacherTxtAuth.clear();
        teacherTxtPwd.clear();
        teacherTxtRePwd.clear();
    }

}
