package com.example.activityclubmanagementsystem;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class attendanceEventController implements Initializable

{
    @FXML
    private Button btnGenerateReport;
    @FXML
    private TableView<Student> attendanceTable;
    @FXML
    private TableColumn<Student,String> studentId;
    @FXML
    private TableColumn<Student,String> FirstName;
    @FXML
    private TableColumn<Student,String> LastName;
    @FXML
    private TableColumn<Student,String> ISMember;
    @FXML
    private TableColumn<Student,String> Email;
    @FXML
    private Label lblEventName;
    @FXML
    private Label lblHeldBy;
    @FXML
    private Label lblVenue;
    @FXML
    private Label lblStart;
    @FXML
    private Label lblEnd;
    @FXML
    private Label lblDesc;
    @FXML
    private Label lblDate;
    event CurrentEvent;

    public attendanceEventController(event currentEvent)
    {
        CurrentEvent = currentEvent;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

        lblEventName.setText(CurrentEvent.getEventName());
        lblHeldBy.setText(CurrentEvent.getHeldByClub().getClubName());
        lblVenue.setText(CurrentEvent.getVenue());
        lblDate.setText(CurrentEvent.getDate().toString());
        lblStart.setText(CurrentEvent.getStartTime().toString());
        lblEnd.setText(CurrentEvent.getEndTime().toString());
        lblDesc.setText(CurrentEvent.getDescription());
        ObservableList<Student> studentList = FXCollections.observableArrayList(CurrentEvent.getStudents());
        studentId.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Student, String> studentStringCellDataFeatures) {
                return new ReadOnlyObjectWrapper<>(studentStringCellDataFeatures.getValue().getId());
            }
        });
        FirstName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Student, String> studentStringCellDataFeatures) {
                return new ReadOnlyObjectWrapper<>(studentStringCellDataFeatures.getValue().getFirstName());
            }
        });
        LastName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Student, String> studentStringCellDataFeatures) {
                return new ReadOnlyObjectWrapper<>(studentStringCellDataFeatures.getValue().getSurName());
            }
        });
        ISMember.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Student, String> studentStringCellDataFeatures) {
                return new ReadOnlyObjectWrapper<>(studentStringCellDataFeatures.getValue().isMember(CurrentEvent.getHeldByClub().getClubId()));
            }
        });
        Email.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Student, String> studentStringCellDataFeatures) {
                return new ReadOnlyObjectWrapper<>(studentStringCellDataFeatures.getValue().getEmail());
            }
        });
        attendanceTable.setItems(studentList);




    }

    public void onReportClick(ActionEvent event)
    {
        DirectoryChooser directory = new DirectoryChooser();
        directory.setInitialDirectory(new File("C:\\"));
        File selected = directory.showDialog(new Stage());
        try {



            int length1 = CurrentEvent.getHeldByClub().getClubName().length() + 11;
            int length2 = CurrentEvent.getHeldByClub().getInCharge().getFullName().length() + 19;
            String string1 = null;
            String string2 = null;
            if (length1 > length2) {
                string1 = "Club Name: " + CurrentEvent.getHeldByClub().getClubName() + "Club ID: " + CurrentEvent.getHeldByClub().getClubId() + "\n";
                string2 = "Teacher-In-Charge: " + CurrentEvent.getHeldByClub().getInCharge().getFullName() + " ".repeat(length1 - length2) +
                        "Teacher ID: " + CurrentEvent.getHeldByClub().getInCharge().getId() + "\n";
            } else {
                string1 = "Club Name: " + CurrentEvent.getHeldByClub().getClubName() + "Club ID: " + CurrentEvent.getHeldByClub().getClubId() + "\n";
                string2 = "Teacher-In-Charge: " + CurrentEvent.getHeldByClub().getInCharge().getFullName() + " ".repeat(length2 - length1) +
                        "Teacher ID: " + CurrentEvent.getHeldByClub().getInCharge().getId() + "\n";
            }
            String string3 = "Venue:                   " + CurrentEvent.getVenue()+"\n";
            String string4 = "Date:                    " + CurrentEvent.getDate().toString();
            String string5 = "Start Time:              " + CurrentEvent.getStartTime().toString() + "\n";
            String string6 = "End Time                 " + CurrentEvent.getEndTime().toString() + "\n";

            String string8 = "Number Attended:         " + String.valueOf(CurrentEvent.getAttendance().size()) + "\n";
            String divider = null;
            if (string1.length() >= string2.length()) {
                divider = "-".repeat(string1.length()) + "\n";
            } else {
                divider = "-".repeat(string2.length()) + "\n";
            }
            selected.toPath().toFile().createNewFile();
            String fileName = "Event " + CurrentEvent.getHeldByClub() + " " + CurrentEvent.getDate().toString() + ".txt";
            File file = new File(selected, fileName);
            file.createNewFile();
            Path filePath = Paths.get(file.getPath());
            Files.write(filePath, string1.getBytes(), StandardOpenOption.APPEND);
            Files.write(filePath, string2.getBytes(), StandardOpenOption.APPEND);
            Files.write(filePath, string3.getBytes(), StandardOpenOption.APPEND);
            Files.write(filePath, string4.getBytes(), StandardOpenOption.APPEND);
            Files.write(filePath, string5.getBytes(), StandardOpenOption.APPEND);
            Files.write(filePath, string6.getBytes(), StandardOpenOption.APPEND);

            Files.write(filePath, string8.getBytes(), StandardOpenOption.APPEND);
            Files.write(filePath, divider.getBytes(), StandardOpenOption.APPEND);
            Files.write(filePath, "  \n".getBytes(), StandardOpenOption.APPEND);
            Files.write(filePath, "Attended Students\n".getBytes(), StandardOpenOption.APPEND);
            String header1 = "|Student ID | Student Name                            | Grade | Email                     |" + "\n";
            String header2 = "-------------------------------------------------------------------------------------------" + "\n";
            Files.write(filePath, header2.getBytes(), StandardOpenOption.APPEND);
            Files.write(filePath, header1.getBytes(), StandardOpenOption.APPEND);
            Files.write(filePath, header2.getBytes(), StandardOpenOption.APPEND);

            for (Student student : CurrentEvent.getStudents()) {
                String StudentString = "|" + student.getId() + getSpaces(11, student.getId()) + "| " + student.getFullName() + getSpaces(40, student.getFullName()) + "| " + student.getGrade() + getSpaces(6, String.valueOf(student.getGrade())) +
                        "| " + student.getEmail() + getSpaces(26, student.getEmail()) + "|" + "\n";
                Files.write(filePath, StudentString.getBytes(), StandardOpenOption.APPEND);
                Files.write(filePath, header2.getBytes(), StandardOpenOption.APPEND);
            }
            Files.write(filePath, "  \n".getBytes(), StandardOpenOption.APPEND);
            String didntAttend = " Absent Members" + "\n";
            Files.write(filePath, didntAttend.getBytes(), StandardOpenOption.APPEND);

            Files.write(filePath, header2.getBytes(), StandardOpenOption.APPEND);
            Files.write(filePath, header1.getBytes(), StandardOpenOption.APPEND);
            Files.write(filePath, header2.getBytes(), StandardOpenOption.APPEND);
            club current = null;
            for (club Club : Data.getClubList()) {
                if (Club.getClubId().equals(CurrentEvent.getHeldByClub().getClubId())) {
                    current = Club;
                    break;
                }
            }
            ArrayList<Student> absent = new ArrayList<>();
            for (Student student : current.getMembers()) {
                if (!CurrentEvent.getAttendance().contains(student.getId())) {
                    absent.add(student);
                }
            }
            for (Student student : absent) {
                String StudentString = "|" + student.getId() + getSpaces(11, student.getId()) + "| " + student.getFullName() + getSpaces(40, student.getFullName()) + "| " + student.getGrade() + getSpaces(6, String.valueOf(student.getGrade())) +
                        "| " + student.getEmail() + getSpaces(26, student.getEmail()) + "|" + "\n";
                Files.write(filePath, StudentString.getBytes(), StandardOpenOption.APPEND);
                Files.write(filePath, header2.getBytes(), StandardOpenOption.APPEND);
            }

            Files.write(filePath, "  \n".getBytes(), StandardOpenOption.APPEND);
            Files.write(filePath, "  \n".getBytes(), StandardOpenOption.APPEND);
            String generated = "Generated on: " + LocalDate.now().toString() + " At: " + LocalTime.now();
            Files.write(filePath, generated.getBytes(), StandardOpenOption.APPEND);

        }
        catch (IOException e)
        {
            warnings.FileError();
        }
    }
    private String getSpaces(int max,String string)
    {
        int difference= max-string.length();
        return " ".repeat(difference);
    }
}
