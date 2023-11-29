package com.example.activityclubmanagementsystem;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ConnectException;
import java.sql.SQLException;


public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException {
//        ProcessBuilder P1 =new ProcessBuilder("D:\\Program Files for D\\XAMPP\\mysql_start.bat");
//        P1.start();
//        ProcessBuilder P2 =new ProcessBuilder("D:\\Program Files for D\\XAMPP\\APACHE_start.bat");
//        P2.start();
//        try {
//            ProcessBuilder P1 = new ProcessBuilder("D:\\Program Files for D\\XAMPP\\mysql_start.bat");
//            P1.start();
//            ProcessBuilder P2 = new ProcessBuilder("D:\\Program Files for D\\XAMPP\\APACHE_start.bat");
//            P2.start();
//        }
//        catch (ConnectException e)
//        {
//            e.printStackTrace();
//        }
        Data.updateTeacherList();
        Data.updateDayList();
        Data.updateClubList();
        Data.updateStudentList();
        Data.updateAdminList();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("OOD");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}