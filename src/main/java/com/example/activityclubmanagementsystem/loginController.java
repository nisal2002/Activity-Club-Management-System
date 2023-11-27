package com.example.activityclubmanagementsystem;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class loginController
{

    @FXML
    private TextField loginID;
    @FXML
    private PasswordField loginPassword;
    @FXML
    private Label loginMsg;

    @FXML
    public void onLoginClick(ActionEvent event) throws IOException {
        String id = loginID.getText();
        String pwd = loginPassword.getText();
        try {


            if (id.contains("S")) {
                ObservableList<Student> stu = Data.getStudentList();
                for (Student S : stu) {
                    if (S.getId().equals(id) && S.getPassword().equals(pwd)) {
                        writeID(id);
                        Parent currentPage = FXMLLoader.load(getClass().getResource("student.fxml"));
                        Scene currentpageScene = new Scene(currentPage);
                        Stage addStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        addStage.setScene(currentpageScene);
                        addStage.show();
//                                break;
                    }

                }
            } else if (id.contains("T")) {
                for (Teacher T : Data.getTeacherList()) {
                    if (T.getId().equals(id) && T.getPassword().equals(pwd)) {
                        writeID(id);
                        Parent currentPage = FXMLLoader.load(getClass().getResource("teacher.fxml"));
                        Scene currentpageScene = new Scene(currentPage);
                        Stage addStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        addStage.setScene(currentpageScene);
                        addStage.show();
//                                break;
                    }
                }
            } else if (id.contains("A")) {
                for (Admin A : Data.getAdminList()) {
                    if (A.getId().equals(id) && A.getPassword().equals(pwd)) {
                        writeID(id);
                        Parent currentPage = FXMLLoader.load(getClass().getResource("admin.fxml"));
                        Scene currentpageScene = new Scene(currentPage);
                        Stage addStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        addStage.setScene(currentpageScene);
                        addStage.show();
                    }
                }
            } else {
                loginMsg.setText("incorrect Credentials");

            }


        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void writeID(String id) {
        try {
            FileWriter csvfile = new FileWriter("src/id.csv");
            PrintWriter write = new PrintWriter(csvfile);
            StringBuilder build = new StringBuilder();
            build.append(id);
            build.append(",");
            write.println(build);
            write.close();
        } catch (IOException e) {
        }
    }

    @FXML
    public void onRegisterClick(ActionEvent event) throws IOException {
        Parent currentPage = FXMLLoader.load(getClass().getResource("register.fxml"));
        Scene currentpageScene = new Scene(currentPage);
        Stage addStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        addStage.setScene(currentpageScene);
        addStage.show();
    }
}