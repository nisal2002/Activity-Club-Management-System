package com.example.activityclubmanagementsystem;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

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
}
