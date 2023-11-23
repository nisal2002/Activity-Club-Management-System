module com.example.activityclubmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.activityclubmanagementsystem to javafx.fxml;
    exports com.example.activityclubmanagementsystem;
}