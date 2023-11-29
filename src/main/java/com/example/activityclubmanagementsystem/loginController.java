package com.example.activityclubmanagementsystem;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class loginController  {
    @FXML
    private TextField loginID;
    @FXML
    private PasswordField loginPassword;
    @FXML
    private Label loginMsg;
    @FXML
    private TextField txtShowPwd;
    @FXML
    private Button loginBtn;

    @FXML
    public void onLoginClick(ActionEvent event) throws IOException {
        boolean completed=true;
        if (loginID.getText().isEmpty())
        {
            completed=false;
            loginID.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");

        }
        else
        {
            loginID.setStyle("-fx-border-color: red ; -fx-border-width: 0px ;");
        }
        if (loginPassword.getText().isEmpty())
        {
            completed=false;
            loginPassword.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");

        }
        else
        {
            loginPassword.setStyle("-fx-border-color: red ; -fx-border-width: 0px ;");
        }
        if (completed)
        {
            String id = loginID.getText();
            String pwd = loginPassword.getText();
            try {


                if (id.contains("S"))
                {
                    boolean isCorrect=false;
                    ObservableList<Student> stu = Data.getStudentList();
                    for (Student S : stu) {
                        if (S.getId().equals(id) && S.getPassword().equals(pwd)) {
                            writeID(id);
                            isCorrect=true;
                            Parent currentPage = FXMLLoader.load(getClass().getResource("student.fxml"));
                            Scene currentpageScene = new Scene(currentPage);
                            Stage addStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            addStage.setScene(currentpageScene);
                            addStage.show();
                        }

                    }
                    if (!isCorrect)
                    {
                        loginMsg.setText("incorrect Credentials");
                    }
                }
                else if (id.contains("T"))
                {
                    boolean isCorrect=false;
                    for (Teacher T : Data.getTeacherList()) {
                        if (T.getId().equals(id) && T.getPassword().equals(pwd)) {
                            writeID(id);
                            isCorrect=true;
                            Parent currentPage = FXMLLoader.load(getClass().getResource("teacher.fxml"));
                            Scene currentpageScene = new Scene(currentPage);
                            Stage addStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            addStage.setScene(currentpageScene);
                            addStage.show();

                        }
                    }
                    if (!isCorrect)
                    {
                        loginMsg.setText("incorrect Credentials");
                    }
                }
                else if (id.contains("A"))
                {
                    boolean isCorrect=false;
                    for (Admin A : Data.getAdminList()) {
//                        System.out.println(A.getId());
//                        System.out.println(A.getPassword());
                        if (A.getId().equals(id) && A.getPassword().equals(pwd)) {
                            isCorrect=true;
                            writeID(id);
                            Parent currentPage = FXMLLoader.load(getClass().getResource("admin.fxml"));
                            Scene currentpageScene = new Scene(currentPage);
                            Stage addStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            addStage.setScene(currentpageScene);
                            addStage.show();
                        }
                    }
                    if (!isCorrect)
                    {
                        loginMsg.setText("incorrect Credentials");
                    }
                }
                else {
                    loginMsg.setText("incorrect Credentials");

                }


            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    private void onLogin()
    {

    }
    private void writeID(String id)
    {
        try {
            FileWriter csvfile = new FileWriter("src/id.csv");
            PrintWriter write = new PrintWriter(csvfile);
            StringBuilder build = new StringBuilder();
            build.append(id);
            build.append(",");
            write.println(build);
            write.close();
        }
        catch (IOException e){}
    }

    @FXML
    public void onRegisterClick(ActionEvent event) throws IOException {  //clik to register
        Parent currentPage= FXMLLoader.load(getClass().getResource("register.fxml"));
        Scene currentpageScene = new Scene(currentPage);
        Stage addStage =(Stage)((Node)event.getSource()).getScene().getWindow();
        addStage.setScene(currentpageScene);
        addStage.show();
    }

    public void onShowPwdpress(MouseEvent mouseEvent)  //click btn to show pswd
    {
        txtShowPwd.setText(loginPassword.getText());
        loginPassword.setVisible(false);
        txtShowPwd.setVisible(true);

    }

    public void onShowPwdRelease(MouseEvent mouseEvent)  // release will unshow password
    {
        txtShowPwd.setText(" ");
        txtShowPwd.setVisible(false);
        loginPassword.setVisible(true);


    }


    public void onLoginKey(KeyEvent event) throws IOException {
        if (event.getCode()== KeyCode.ENTER)
        {
            loginBtn.fire();
        }
    }

    public void onPasswordKey(KeyEvent event) throws IOException { //when clicking enter user can login

        if (event.getCode().equals( KeyCode.ENTER))
        {
            loginBtn.fire();
        }
    }
}