package com.example.activityclubmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class studentController {
    @FXML
    private Pane joinClbPane;
    @FXML
    private Pane viewClbPane;
    @FXML
    private Pane attendPane;
    @FXML
    private Pane profilePane;
    @FXML
    private Button joinClbBtn;
    @FXML
    private Button viewClbBtn;
    @FXML
    private Button attendBtn;
    @FXML
    private Button profileBtn;
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
        if (actionEvent.getSource() == joinClbBtn) {
            joinClbPane.toFront();
        }
        if (actionEvent.getSource() == viewClbBtn) {
            viewClbPane.toFront();
        }
        if (actionEvent.getSource() == attendBtn) {
            attendPane.toFront();
        }
        if (actionEvent.getSource() == profileBtn) {
            profilePane.toFront();
        }
    }
}
