package com.example.activityclubmanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class registerController implements Initializable {
    @FXML
    private TextField adminTxtShowPwd;
    public Button teacherPwdShow;
    @FXML
    private TextField adminTxtShowRePwd;
    @FXML
    private TextField teacherTxtShowPwd;
    @FXML
    private TextField teacherTxtShowRePwd;
    @FXML
    private TextField StudenttxtPwdShow;
    @FXML
    private TextField StudenttxtRePwdShow;
    @FXML
    private TextField teacherTxtAuthShow;

    //TODO: write code for show buttons buttons add show buttons for password fields

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
    private Button teacherBtnRegister;
    @FXML
    private Button teacherBtnClear;
    @FXML
    private Button teacherBtnShow;
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
    ////////student FXML//////////
    @FXML
    private TextField studentTxtId;
    @FXML
    private TextField studentTxtFirstN;
    @FXML
    private TextField studentTxtLastN;
    @FXML
    private TextField studentTxtNic;
    @FXML
    private TextField studentTxtEmail;
    @FXML
    private DatePicker studentDateDob;
    @FXML
    private Button studentBtnRegister;
    @FXML
    private Button studentBtnClear;
    @FXML
    private PasswordField studentTxtPwd;
    @FXML
    private PasswordField studentTxtRePwd;
    @FXML
    private ChoiceBox<String> studentChoiceGender;
    ///////////////////Admin FXML///////////////
    @FXML
    private TextField adminTxtId;
    @FXML
    private TextField adminTxtFirstN;
    @FXML
    private TextField adminTxtLastN;
    @FXML
    private TextField adminTxtNic;
    @FXML
    private TextField adminTxtEmail;
    @FXML
    private DatePicker adminDateDob;
    @FXML
    private Button adminBtnRegister;
    @FXML
    private Button adminBtnClear;
    @FXML
    private PasswordField adminTxtAuth;
    @FXML
    private Button adminBtnShow;
    @FXML
    private PasswordField adminTxtPwd;
    @FXML
    private PasswordField adminTxtRePwd;
    @FXML
    private ChoiceBox<String> adminChoiceGender;
    @FXML
    private StackPane stack;
    @FXML
    private TextField authCodeShow;
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
            String massage = first+inputs.get(1)+" "+inputs.get(2)+"has been added as an Teacher";
            warnings.register(massage);
            clearTeacher();
            teacherTxtID.setText("T"+Data.getNextId("teachers"));

        }

    }

    public void onTeacherClearClick(ActionEvent event)
    {

        clearTeacher();
    }
    private void clearTeacher()
    {

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


    public void onShowClick(ActionEvent event)
    {

    }
    private getText getTeacher() //student details filling
    {
        String firstName = null;
        String surName= null;
        String nic= null;
        String email= null;
        String Auth= null;
        String dob= null;
        String gender= null;
        String pwd = null;

        Boolean completed = true;
        if(teacherTxtFirstN.getText().isEmpty())
        {
            completed=false;
            teacherTxtFirstN.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else
        {
            teacherTxtFirstN.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
            firstName = teacherTxtFirstN.getText();
        }
        if(teacherTxtLastN.getText().isEmpty())
        {
            completed=false;
            teacherTxtLastN.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else
        {
            teacherTxtLastN.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
            surName = teacherTxtLastN.getText();
        }
        if(teacherTxtNic.getText().isEmpty())
        {
            completed=false;
            teacherTxtNic.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");

        }
        else
        {
            teacherTxtNic.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
            nic = teacherTxtNic.getText();
        }
        if(teacherDateDob.getValue()==null)
        {
            completed=false;
            teacherDateDob.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else
        {
            teacherDateDob.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
            dob = teacherDateDob.getValue().toString();
        }
        String emailRegx="^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        if(teacherTxtEmail.getText().isEmpty() ||!teacherTxtEmail.getText().matches(emailRegx))
        {
            completed=false;
            teacherTxtEmail.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else
        {
            teacherTxtEmail.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
            email = teacherTxtEmail.getText();
        }
        if(teacherChoiceGender.getValue()==null)
        {
            completed=false;
            teacherChoiceGender.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else
        {
            teacherChoiceGender.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
            gender = teacherChoiceGender.getValue().toString();
        }
        if(teacherTxtAuth.getText().isEmpty())
        {
            completed=false;
            teacherTxtAuth.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else
        {
            teacherTxtAuth.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
            Auth = teacherTxtAuth.getText();
        }
        if(teacherTxtPwd.getText().isEmpty())
        {
            completed=false;
            teacherTxtPwd.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else
        {
            teacherTxtPwd.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");

        }
        if (teacherTxtPwd.getLength() < 6 || teacherTxtPwd.getLength() > 10)
        {
            completed=false;
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText(null);
            alert.setContentText("Password must be between 6-10 Characters");

            alert.showAndWait(); // Display the alert and wait for user interaction
        }
        else
        {
            completed=true;
        }
        if(teacherTxtRePwd.getText().isEmpty())
        {
            completed=false;
            teacherTxtRePwd.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else
        {
            teacherTxtRePwd.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");

        }
        if(teacherTxtPwd.getText().equals(teacherTxtRePwd.getText()))
        {
            pwd = teacherTxtPwd.getText();
            teacherTxtRePwd.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
            teacherTxtPwd.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");

        }
        else
        {
            completed =false;
            teacherTxtPwd.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            teacherTxtRePwd.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");

        }

        ArrayList<String> inputString = new ArrayList<>();
        inputString.add(teacherTxtID.getText());
        inputString.add(firstName);
        inputString.add(surName);
        inputString.add(nic);
        inputString.add(dob);
        inputString.add(gender);
        inputString.add(email);
        inputString.add(pwd);
        inputString.add(Auth);
        getText inputs = new getText(completed,inputString);
        return inputs;


    }
    ////////////////////student Methods/////////////////////////////
    public void onStudentRegisterClick(ActionEvent event) throws SQLException {
        getText input = getStudent();
        ArrayList<String> inputs = input.inputs;
        if (input.complete.equals(true))
        {
            Student addStudent = new Student(inputs.get(0),inputs.get(1),inputs.get(2),inputs.get(3),inputs.get(4),inputs.get(5),inputs.get(6),inputs.get(7));
            Data.addStudent(addStudent);
            String first=null;
            if (inputs.get(5).equals("Male"))
            {
                first = "Master ";
            }
            else
            {
                first ="Miss ";
            }
            String message = first+inputs.get(1)+" "+inputs.get(2)+" has been registered as a Student";
            warnings.register(message);
            clearStudent();

            studentTxtId.setText("S"+Data.getNextId("students"));

        }
    }
    public void onStudentClearClick(ActionEvent event) // clear student record
    {
        clearStudent();
    }
    private void clearStudent()
    {

        studentTxtFirstN.clear();
        studentTxtLastN.clear();
        studentTxtNic.clear();
        studentDateDob.getEditor().clear();
        studentDateDob.setValue(null);
        studentChoiceGender.setValue(null);
        studentTxtEmail.clear();
        studentTxtPwd.clear();
        studentTxtRePwd.clear();
    }
    private getText getStudent()
    {
        String firstName = null;
        String surName= null;
        String nic= null;
        String email= null;
        String Auth= null;
        String dob= null;
        String gender= null;
        String pwd = null;

        Boolean completed = true;
        if(studentTxtFirstN.getText().isEmpty())
        {
            completed=false;
            studentTxtFirstN.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else
        {
            studentTxtFirstN.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
            firstName = studentTxtFirstN.getText();
        }
        if(studentTxtLastN.getText().isEmpty())
        {
            completed=false;
            studentTxtLastN.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else
        {
            studentTxtLastN.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
            surName = studentTxtLastN.getText();
        }
        if(studentTxtNic.getText().isEmpty())
        {
            completed=false;
            studentTxtNic.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");

        }
        else
        {
            studentTxtNic.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
            nic = studentTxtNic.getText();
        }
        if(studentDateDob.getValue()==null)
        {
            completed=false;
            studentDateDob.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else
        {
            studentDateDob.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
            dob = studentDateDob.getValue().toString();
        }
        String emailRegx="^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        if(studentTxtEmail.getText().isEmpty()||!studentTxtEmail.getText().matches(emailRegx))
        {
            completed=false;
            studentTxtEmail.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else
        {
            studentTxtEmail.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
            email = studentTxtEmail.getText();
        }
        if(studentChoiceGender.getValue()==null)
        {
            completed=false;
            studentChoiceGender.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else
        {
            studentChoiceGender.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
            gender = studentChoiceGender.getValue().toString();
        }

        if(studentTxtPwd.getText().isEmpty())
        {
            completed=false;
            studentTxtPwd.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else
        {
            studentTxtPwd.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");

        }
        if (studentTxtPwd.getLength() < 6 || studentTxtPwd.getLength() > 10)
        {
            completed=false;
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText(null);
            alert.setContentText("Password must be between 6-10 Characters");

            alert.showAndWait(); // Display the alert and wait for user interaction
        }
        else
        {
            completed=true;
        }
        if(studentTxtRePwd.getText().isEmpty())
        {
            completed=false;
            studentTxtRePwd.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else
        {
            studentTxtRePwd.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");

        }
        if(studentTxtPwd.getText().equals(studentTxtRePwd.getText()))
        {
            pwd = studentTxtPwd.getText();
            studentTxtRePwd.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
            studentTxtPwd.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");

        }
        else
        {
            completed =false;
            studentTxtPwd.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            studentTxtRePwd.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");

        }

        ArrayList<String> inputString = new ArrayList<>();
        inputString.add(studentTxtId.getText());
        inputString.add(firstName);
        inputString.add(surName);
        inputString.add(nic);
        inputString.add(dob);
        inputString.add(gender);
        inputString.add(email);
        inputString.add(pwd);
        inputString.add(Auth);
        getText inputs = new getText(completed,inputString);
        return inputs;


    }
    ///////////////////////////////Admin Methods//////////////////////////
    public void onAdminRegisterClick(ActionEvent event) throws SQLException { //register as admin
        getText input = getAdmin();
        ArrayList<String> inputs = input.inputs;
        if (input.complete.equals(true)&&inputs.get(8).equals("12345"))
        {
            Admin addAdmin = new Admin(inputs.get(0),inputs.get(1),inputs.get(2),inputs.get(3),inputs.get(4),inputs.get(5),inputs.get(6),inputs.get(7));
            Data.addAdmin(addAdmin);
            String first=null;
            if (inputs.get(5).equals("male"))
            {
                first = "Mr. ";
            }
            else
            {
                first ="Mrs. ";
            }
            String massage = first+inputs.get(1)+" "+inputs.get(2)+"has been added as an Admin";
            warnings.register(massage);
            adminClear();
            adminTxtId.setText("A"+Data.getNextId("admins"));

        }


    }
    public void onAdminClearClick(ActionEvent event)  // clear fields
    {
        adminClear();
    }
    private void adminClear()
    {

        adminTxtFirstN.clear();
        adminTxtLastN.clear();
        adminTxtNic.clear();
        adminDateDob.getEditor().clear();
        adminDateDob.setValue(null);
        adminChoiceGender.setValue(null);
        adminTxtEmail.clear();
        adminTxtPwd.clear();
        adminTxtRePwd.clear();
        adminTxtAuth.clear();
    }
    public void onAdminShowClick(ActionEvent event)
    {

    }

    private getText getAdmin()
    {
        String firstName = null;
        String surName= null;
        String nic= null;
        String email= null;
        String Auth= null;
        String dob= null;
        String gender= null;
        String pwd = null;

        Boolean completed = true;
        if(adminTxtFirstN.getText().isEmpty())
        {
            completed=false;
            adminTxtFirstN.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else
        {
            adminTxtFirstN.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
            firstName = adminTxtFirstN.getText();
        }
        if(adminTxtLastN.getText().isEmpty())
        {
            completed=false;
            adminTxtLastN.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else
        {
            adminTxtLastN.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
            surName = adminTxtLastN.getText();
        }
        if(adminTxtNic.getText().isEmpty())
        {
            completed=false;
            adminTxtNic.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");

        }
        else
        {
            adminTxtNic.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
            nic = adminTxtNic.getText();
        }
        if(adminDateDob.getValue()==null)
        {
            completed=false;
            adminDateDob.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else
        {
            adminDateDob.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
            dob = adminDateDob.getValue().toString();
        }
        String emailRegx="^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        if(adminTxtEmail.getText().isEmpty()||!adminTxtEmail.getText().matches(emailRegx))
        {
            completed=false;
            adminTxtEmail.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else
        {
            adminTxtEmail.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
            email = adminTxtEmail.getText();
        }





        if (admin.getText().matches(emailRegx))
        {
            completed=true;
        }
        if(adminChoiceGender.getValue()==null)
        {
            completed=false;
            adminChoiceGender.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else
        {
            adminChoiceGender.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
            gender = adminChoiceGender.getValue().toString();
        }
        if(adminTxtAuth.getText().isEmpty())
        {
            completed=false;
            adminTxtAuth.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else
        {
            adminTxtAuth.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
            Auth = adminTxtAuth.getText();
        }
        if(adminTxtPwd.getText().isEmpty())
        {
            completed=false;
            adminTxtPwd.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else
        {
            adminTxtPwd.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");

        }
        if(adminTxtRePwd.getText().isEmpty())
        {
            completed=false;
            adminTxtRePwd.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else
        {
            adminTxtRePwd.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");

        }
        if (adminTxtPwd.getLength() < 6 || adminTxtPwd.getLength() > 10)
        {
            completed=false;
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText(null);
            alert.setContentText("Password must be between 6-10 Characters");

            alert.showAndWait(); // Display the alert and wait for user interaction
        }
        else
        {
            completed=true;
        }
        if(adminTxtPwd.getText().equals(adminTxtRePwd.getText()))
        {
            pwd = adminTxtPwd.getText();
            adminTxtRePwd.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
            adminTxtPwd.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");

        }
        else
        {
            completed =false;
            adminTxtPwd.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            adminTxtRePwd.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");

        }

        ArrayList<String> inputString = new ArrayList<>();
        inputString.add(adminTxtId.getText());
        inputString.add(firstName);
        inputString.add(surName);
        inputString.add(nic);
        inputString.add(dob);
        inputString.add(gender);
        inputString.add(email);
        inputString.add(pwd);
        inputString.add(Auth);
        getText inputs = new getText(completed,inputString);
        return inputs;

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> genders = FXCollections.observableArrayList();
        genders.add("Male");
        genders.add("Female");
        studentChoiceGender.setItems(genders);
        teacherChoiceGender.setItems(genders);
        adminChoiceGender.setItems(genders);
        try {

            String teacherId = Data.getNextId("teachers");
            String studentId = Data.getNextId("students");
            String adminId = Data.getNextId("admins");
            studentTxtId.setText("S"+studentId);
            studentTxtId.setDisable(true);
            teacherTxtID.setText("T"+teacherId);
            teacherTxtID.setDisable(true);
            adminTxtId.setText("A"+adminId);
            adminTxtId.setDisable(true);
        } catch (SQLException e) {
            warnings.SqlWarning();
        }
        adminTxtFirstN.setTextFormatter(new TextFormatter<>(filter));
        adminTxtLastN.setTextFormatter(new TextFormatter<>(filter));
        teacherTxtFirstN.setTextFormatter(new TextFormatter<>(filter));
        teacherTxtLastN.setTextFormatter(new TextFormatter<>(filter));
        studentTxtFirstN.setTextFormatter(new TextFormatter<>(filter));
        studentTxtLastN.setTextFormatter(new TextFormatter<>(filter));


    }
    private void Test()
    {
        if(teapane.isFocused())
        {
            ObservableList<Node> nodes = teapane.getChildren();
            nodes.get(0).setAccessibleText("aa");
        }
    }

    public void onAtest(ActionEvent event)
    {
        ArrayList<String> firstNames = new ArrayList<>();
        firstNames.add("Alice");
        firstNames.add("Bob");
        firstNames.add("Claire");
        firstNames.add("David");
        firstNames.add("Emma");

        // ArrayList for last names
        ArrayList<String> lastNames = new ArrayList<>();
        lastNames.add("Smith");
        lastNames.add("Johnson");
        lastNames.add("Garcia");
        lastNames.add("Brown");
        lastNames.add("Davis");

        ArrayList<String> addresses = new ArrayList<>();
        addresses.add("123 Main St, City, Country");
        addresses.add("456 Elm St, Town, Country");
        addresses.add("789 Oak St, Village, Country");
        addresses.add("101 Pine St, Hamlet, Country");
        addresses.add("210 Cedar St, Suburb, Country");

        // ArrayList for emails
        ArrayList<String> emails = new ArrayList<>();
        emails.add("example1@email.com");
        emails.add("example2@email.com");
        emails.add("example3@email.com");
        emails.add("example4@email.com");
        emails.add("example5@email.com");

        ArrayList<String> id = new ArrayList<>();
        id.add("200219200913");
        id.add("200219200917");
        id.add("200219200916");
        id.add("200219200915");
        id.add("200219200914");

        ArrayList<String> pwd = new ArrayList<>();
        pwd.add("asdf1");
        pwd.add("asdf2");
        pwd.add("asdf3");
        pwd.add("asdf4");
        pwd.add("asdf5");

        Random rand = new Random();
        int i = rand.nextInt(0,5);
        adminTxtFirstN.setText(firstNames.get(i));
        adminTxtLastN.setText(lastNames.get(i));
        adminTxtNic.setText(id.get(i));
        adminTxtEmail.setText(emails.get(i));
        adminTxtPwd.setText(pwd.get(i));
        adminTxtRePwd.setText(pwd.get(i));
        LocalDate d = LocalDate.now();
        adminDateDob.setValue(d);
        adminChoiceGender.setValue("Male");
        adminTxtAuth.setText("12345");
    }

    public void onTtest(ActionEvent event)
    {
        ArrayList<String> firstNames = new ArrayList<>();
        firstNames.add("Alice");
        firstNames.add("Bob");
        firstNames.add("Claire");
        firstNames.add("David");
        firstNames.add("Emma");

        // ArrayList for last names
        ArrayList<String> lastNames = new ArrayList<>();
        lastNames.add("Smith");
        lastNames.add("Johnson");
        lastNames.add("Garcia");
        lastNames.add("Brown");
        lastNames.add("Davis");

        ArrayList<String> addresses = new ArrayList<>();
        addresses.add("123 Main St, City, Country");
        addresses.add("456 Elm St, Town, Country");
        addresses.add("789 Oak St, Village, Country");
        addresses.add("101 Pine St, Hamlet, Country");
        addresses.add("210 Cedar St, Suburb, Country");

        // ArrayList for emails
        ArrayList<String> emails = new ArrayList<>();
        emails.add("example1@email.com");
        emails.add("example2@email.com");
        emails.add("example3@email.com");
        emails.add("example4@email.com");
        emails.add("example5@email.com");

        ArrayList<String> id = new ArrayList<>();
        id.add("200219200913");
        id.add("200219200917");
        id.add("200219200916");
        id.add("200219200915");
        id.add("200219200914");

        ArrayList<String> pwd = new ArrayList<>();
        pwd.add("asdf1");
        pwd.add("asdf2");
        pwd.add("asdf3");
        pwd.add("asdf4");
        pwd.add("asdf5");

        Random rand = new Random();
        int i = rand.nextInt(0,5);
        teacherTxtFirstN.setText(firstNames.get(i));
        teacherTxtLastN.setText(lastNames.get(i));
        teacherTxtNic.setText(id.get(i));
        teacherTxtEmail.setText(emails.get(i));
        teacherTxtPwd.setText(pwd.get(i));
        teacherTxtRePwd.setText(pwd.get(i));
        LocalDate d = LocalDate.now();
        teacherDateDob.setValue(d);
        teacherChoiceGender.setValue("Male");
        teacherTxtAuth.setText("12345");
    }

    public void onStest(ActionEvent event)
    {
        ArrayList<String> firstNames = new ArrayList<>();
        firstNames.add("Alice");
        firstNames.add("Bob");
        firstNames.add("Claire");
        firstNames.add("David");
        firstNames.add("Emma");

        // ArrayList for last names
        ArrayList<String> lastNames = new ArrayList<>();
        lastNames.add("Smith");
        lastNames.add("Johnson");
        lastNames.add("Garcia");
        lastNames.add("Brown");
        lastNames.add("Davis");

        ArrayList<String> addresses = new ArrayList<>();
        addresses.add("123 Main St, City, Country");
        addresses.add("456 Elm St, Town, Country");
        addresses.add("789 Oak St, Village, Country");
        addresses.add("101 Pine St, Hamlet, Country");
        addresses.add("210 Cedar St, Suburb, Country");

        // ArrayList for emails
        ArrayList<String> emails = new ArrayList<>();
        emails.add("example1@email.com");
        emails.add("example2@email.com");
        emails.add("example3@email.com");
        emails.add("example4@email.com");
        emails.add("example5@email.com");

        ArrayList<String> id = new ArrayList<>();
        id.add("200219200913");
        id.add("200219200917");
        id.add("200219200916");
        id.add("200219200915");
        id.add("200219200914");

        ArrayList<String> pwd = new ArrayList<>();
        pwd.add("asdf1");
        pwd.add("asdf2");
        pwd.add("asdf3");
        pwd.add("asdf4");
        pwd.add("asdf5");

        Random rand = new Random();
        int i = rand.nextInt(0,5);
        studentTxtFirstN.setText(firstNames.get(i));
        studentTxtLastN.setText(lastNames.get(i));
        studentTxtNic.setText(id.get(i));
        studentTxtEmail.setText(emails.get(i));
        studentTxtPwd.setText(pwd.get(i));
        studentTxtRePwd.setText(pwd.get(i));
        LocalDate d = LocalDate.now();
        studentDateDob.setValue(d);
        studentChoiceGender.setValue("Male");

    }

    public void onAdminAuthPress(MouseEvent mouseEvent)
    {
        authCodeShow.setText(adminTxtAuth.getText());
        authCodeShow.setVisible(true);
        adminTxtAuth.setVisible(false);
    }

    public void onAdminAuthRelease(MouseEvent mouseEvent)
    {
        authCodeShow.setText(" ");
        authCodeShow.setVisible(false);
        adminTxtAuth.setVisible(true);
    }

    public void teacherAuthPress(MouseEvent mouseEvent) // ath code show
    {
        teacherTxtAuthShow.setText(teacherTxtAuth.getText());
        teacherTxtAuthShow.setVisible(true);
        teacherTxtAuth.setVisible(false);
    }

    public void teacherAuthRelease(MouseEvent mouseEvent)  // auth code unshow
    {
        teacherTxtAuthShow.setText(" ");
        teacherTxtAuthShow.setVisible(false);
        teacherTxtAuth.setVisible(true);
    }

    public void onStudentShowPress(MouseEvent mouseEvent)
    {
        StudenttxtPwdShow.setText(studentTxtPwd.getText());
        StudenttxtRePwdShow.setText(studentTxtRePwd.getText());
        studentTxtRePwd.setVisible(false);
        studentTxtPwd.setVisible(false);
        StudenttxtRePwdShow.setVisible(true);
        StudenttxtPwdShow.setVisible(true);
    }

    public void onStudentShowRelease(MouseEvent mouseEvent)
    {
        StudenttxtPwdShow.setText(" ");
        StudenttxtRePwdShow.setText(" ");
        studentTxtRePwd.setVisible(true);
        studentTxtPwd.setVisible(true);
        StudenttxtRePwdShow.setVisible(false);
        StudenttxtPwdShow.setVisible(false);
    }

    public void onTeacherPwdShowPress(MouseEvent mouseEvent) //
    {
        teacherTxtShowPwd.setText(teacherTxtPwd.getText());
        teacherTxtShowRePwd.setText(teacherTxtRePwd.getText());
        teacherTxtShowRePwd.setVisible(true);
        teacherTxtShowPwd.setVisible(true);
        teacherTxtPwd.setVisible(false);
        teacherTxtRePwd.setVisible(false);
    }

    public void onTeacherPwdShowReleased(MouseEvent mouseEvent)
    {

        teacherTxtShowPwd.setText(" ");
        teacherTxtShowRePwd.setText(" ");
        teacherTxtShowRePwd.setVisible(false);
        teacherTxtShowPwd.setVisible(false);
        teacherTxtPwd.setVisible(true);
        teacherTxtRePwd.setVisible(true);
    }

    public void onAdminShowPressed(MouseEvent mouseEvent)
    {
        adminTxtShowPwd.setText(adminTxtPwd.getText());
        adminTxtShowRePwd.setText(adminTxtRePwd.getText());
        adminTxtShowPwd.setVisible(true);
        adminTxtShowRePwd.setVisible(true);
        adminTxtPwd.setVisible(false);
        adminTxtRePwd.setVisible(false);
    }

    public void onAdminShowReleased(MouseEvent mouseEvent)
    {
        adminTxtShowPwd.setText(" ");
        adminTxtShowRePwd.setText(" ");
        adminTxtShowPwd.setVisible(false);
        adminTxtShowRePwd.setVisible(false);
        adminTxtPwd.setVisible(true);
        adminTxtRePwd.setVisible(true);
    }
    UnaryOperator<TextFormatter.Change> filter = change -> {
        String newText = change.getControlNewText();
        if (newText.matches("[a-zA-Z]*")) {
            return change; // Allow the change
        }
        return null; // Reject the change
    };
//    TextFormatter<String> textFormatter = new TextFormatter<String>(filter);
}