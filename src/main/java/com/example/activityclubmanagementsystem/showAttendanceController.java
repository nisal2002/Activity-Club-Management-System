package com.example.activityclubmanagementsystem;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
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

public class showAttendanceController implements Initializable
{
    meeting currentMeeting;
    boolean ifMeeting;
    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student,String> studentId;
    @FXML
    private TableColumn<Student,String> studentFirstN;
    @FXML
    private TableColumn<Student,String> studentLastN;
    @FXML
    private TableColumn<Student,String> studentGender;
    @FXML
    private TableColumn<Student,String> studentEmail;
    @FXML
    private Label attendanceLblClub;
    @FXML
    private Label attendanceLblDate;
    @FXML
    private Label attendanceLblSlot;
    @FXML
    private Label attendanceLblNumber;
    @FXML
    private Label lblTimeSlot;
    @FXML
    private Label lblTotal;

    @FXML
    private Button attendanceBtnReport;
    @FXML
    private Label LblEndTime;
    @FXML
    private Label endTime;


    public showAttendanceController(meeting currentMeeting, boolean ifmeeting) {

        this.currentMeeting = currentMeeting;
        this.ifMeeting=ifmeeting;
    }
    public void onGenerateReport(ActionEvent event)
    {
        DirectoryChooser directory = new DirectoryChooser();
        directory.setInitialDirectory(new File("C:\\"));
        File selected = directory.showDialog(new Stage());
        if (!ifMeeting) {

            int length1 = currentMeeting.getClub().getClubName().length() + 11;
            int length2 = currentMeeting.getClub().getInCharge().getFullName().length() + 19;
            String string1 = null;
            String string2 = null;
            if (length1 > length2) {
                string1 = "Club Name: " + currentMeeting.getClub().getClubName() + "Club ID: " + currentMeeting.getClub().getClubId() + "\n";
                string2 = "Teacher-In-Charge: " + currentMeeting.getClub().getInCharge().getFullName() + " ".repeat(length1 - length2) +
                        "Teacher ID: " + currentMeeting.getClub().getInCharge().getId() + "\n";
            } else {
                string1 = "Club Name: " + currentMeeting.getClub().getClubName() + " ".repeat(length2 - length1) + "Club ID: " + currentMeeting.getClub().getClubId() + "\n";
                string2 = "Teacher-In-Charge: " + currentMeeting.getClub().getInCharge().getFullName() + " ".repeat(length2 - length1) +
                        "Teacher ID: " + currentMeeting.getClub().getInCharge().getId() + "\n";
            }
            String string3 = "Meeting ID:              " + currentMeeting.getMeetingID() + "\n";
            String string4 = "Date:                    " + currentMeeting.getDay().toString();
            String string5 = "Time Slot:               " + String.valueOf(currentMeeting.getTotalSlots()) + "\n";
            String string6 = "Total number of students:" + String.valueOf(currentMeeting.getTotalStudents()) + "\n";
            String string7 = "Number Attended:         " + String.valueOf(currentMeeting.getAttendence().size()) + "\n";
            String divider = null;
            if (string1.length() >= string2.length()) {
                divider = "-".repeat(string1.length()) + "\n";
            } else {
                divider = "-".repeat(string2.length()) + "\n";
            }
            try {
                selected.toPath().toFile().createNewFile();
                String fileName = "meeting " + currentMeeting.getClubName() + " " + currentMeeting.getDay().toString() + ".txt";
                File file = new File(selected, fileName);
                file.createNewFile();
                Path filePath = Paths.get(file.getPath());
                Files.write(filePath, string1.getBytes(), StandardOpenOption.APPEND);
                Files.write(filePath, string2.getBytes(), StandardOpenOption.APPEND);
                Files.write(filePath, string3.getBytes(), StandardOpenOption.APPEND);
                Files.write(filePath, string4.getBytes(), StandardOpenOption.APPEND);
                Files.write(filePath, string5.getBytes(), StandardOpenOption.APPEND);
                Files.write(filePath, string6.getBytes(), StandardOpenOption.APPEND);
                Files.write(filePath, string7.getBytes(), StandardOpenOption.APPEND);
                Files.write(filePath, divider.getBytes(), StandardOpenOption.APPEND);
                Files.write(filePath, "  \n".getBytes(), StandardOpenOption.APPEND);
                Files.write(filePath, "Attended Students\n".getBytes(), StandardOpenOption.APPEND);


                String header1 = "|Student ID | Student Name                            | Grade | Email                     |" + "\n";
                String header2 = "-------------------------------------------------------------------------------------------" + "\n";
                Files.write(filePath, header2.getBytes(), StandardOpenOption.APPEND);
                Files.write(filePath, header1.getBytes(), StandardOpenOption.APPEND);
                Files.write(filePath, header2.getBytes(), StandardOpenOption.APPEND);

                for (Student student : currentMeeting.getStudentList()) {
                    String StudentString = "|" + student.getId() + getSpaces(11, student.getId()) + "| " + student.getFullName() + getSpaces(40, student.getFullName()) + "| " + student.getGrade() + getSpaces(6, String.valueOf(student.getGrade())) +
                            "| " + student.getEmail() + getSpaces(26, student.getEmail()) + "|" + "\n";
                    Files.write(filePath, StudentString.getBytes(), StandardOpenOption.APPEND);
                    Files.write(filePath, header2.getBytes(), StandardOpenOption.APPEND);
                }
                Files.write(filePath, "  \n".getBytes(), StandardOpenOption.APPEND);
                String didntAttend = " Absent Students" + "\n";
                Files.write(filePath, didntAttend.getBytes(), StandardOpenOption.APPEND);

                Files.write(filePath, header2.getBytes(), StandardOpenOption.APPEND);
                Files.write(filePath, header1.getBytes(), StandardOpenOption.APPEND);
                Files.write(filePath, header2.getBytes(), StandardOpenOption.APPEND);
                club current = null;
                for (club Club : Data.getClubList()) {
                    if (Club.getClubId().equals(currentMeeting.getClub().getClubId())) {
                        current = Club;
                        break;
                    }
                }
                ArrayList<Student> absent = new ArrayList<>();
                for (Student student : current.getMembers()) {
                    if (!currentMeeting.getAttendence().contains(student.getId())) {
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

            } catch (IOException e) {
                warnings.FileError();
            }
        }
        else
        {
            try {


                int length1 = currentMeeting.getClub().getClubName().length() + 11;
                int length2 = currentMeeting.getClub().getInCharge().getFullName().length() + 19;
                String string1 = null;
                String string2 = null;
                if (length1 > length2) {
                    string1 = "Club Name: " + currentMeeting.getClub().getClubName() + "Club ID: " + currentMeeting.getClub().getClubId() + "\n";
                    string2 = "Teacher-In-Charge: " + currentMeeting.getClub().getInCharge().getFullName() + " ".repeat(length1 - length2) +
                            "Teacher ID: " + currentMeeting.getClub().getInCharge().getId() + "\n";
                } else {
                    string1 = "Club Name: " + currentMeeting.getClub().getClubName() + " ".repeat(length2 - length1) + "Club ID: " + currentMeeting.getClub().getClubId() + "\n";
                    string2 = "Teacher-In-Charge: " + currentMeeting.getClub().getInCharge().getFullName() + " ".repeat(length2 - length1) +
                            "Teacher ID: " + currentMeeting.getClub().getInCharge().getId() + "\n";
                }
                String string3 = "Meeting ID:              " + currentMeeting.getMeetingID() + "\n";
                String string4 = "Date:                    " + currentMeeting.getDay().toString();
                String string5 = "Start Time:              " + currentMeeting.getStart().toString() + "\n";
                String string6 = "End Time                 " + currentMeeting.getEnd().toString() + "\n";
                String string7 = "Total number of students:" + String.valueOf(currentMeeting.getTotalStudents()) + "\n";
                String string8 = "Number Attended:         " + String.valueOf(currentMeeting.getAttendence().size()) + "\n";
                String divider = null;
                if (string1.length() >= string2.length()) {
                    divider = "-".repeat(string1.length()) + "\n";
                } else {
                    divider = "-".repeat(string2.length()) + "\n";
                }
                selected.toPath().toFile().createNewFile();
                String fileName = "Activity " + currentMeeting.getClubName() + " " + currentMeeting.getDay().toString() + ".txt";
                File file = new File(selected, fileName);
                file.createNewFile();
                Path filePath = Paths.get(file.getPath());
                Files.write(filePath, string1.getBytes(), StandardOpenOption.APPEND);
                Files.write(filePath, string2.getBytes(), StandardOpenOption.APPEND);
                Files.write(filePath, string3.getBytes(), StandardOpenOption.APPEND);
                Files.write(filePath, string4.getBytes(), StandardOpenOption.APPEND);
                Files.write(filePath, string5.getBytes(), StandardOpenOption.APPEND);
                Files.write(filePath, string6.getBytes(), StandardOpenOption.APPEND);
                Files.write(filePath, string7.getBytes(), StandardOpenOption.APPEND);
                Files.write(filePath, string8.getBytes(), StandardOpenOption.APPEND);
                Files.write(filePath, divider.getBytes(), StandardOpenOption.APPEND);
                Files.write(filePath, "  \n".getBytes(), StandardOpenOption.APPEND);
                Files.write(filePath, "Attended Students\n".getBytes(), StandardOpenOption.APPEND);
                String header1 = "|Student ID | Student Name                            | Grade | Email                     |" + "\n";
                String header2 = "-------------------------------------------------------------------------------------------" + "\n";
                Files.write(filePath, header2.getBytes(), StandardOpenOption.APPEND);
                Files.write(filePath, header1.getBytes(), StandardOpenOption.APPEND);
                Files.write(filePath, header2.getBytes(), StandardOpenOption.APPEND);

                for (Student student : currentMeeting.getStudentList()) {
                    String StudentString = "|" + student.getId() + getSpaces(11, student.getId()) + "| " + student.getFullName() + getSpaces(40, student.getFullName()) + "| " + student.getGrade() + getSpaces(6, String.valueOf(student.getGrade())) +
                            "| " + student.getEmail() + getSpaces(26, student.getEmail()) + "|" + "\n";
                    Files.write(filePath, StudentString.getBytes(), StandardOpenOption.APPEND);
                    Files.write(filePath, header2.getBytes(), StandardOpenOption.APPEND);
                }
                Files.write(filePath, "  \n".getBytes(), StandardOpenOption.APPEND);
                String didntAttend = " Absent Students" + "\n";
                Files.write(filePath, didntAttend.getBytes(), StandardOpenOption.APPEND);

                Files.write(filePath, header2.getBytes(), StandardOpenOption.APPEND);
                Files.write(filePath, header1.getBytes(), StandardOpenOption.APPEND);
                Files.write(filePath, header2.getBytes(), StandardOpenOption.APPEND);
                club current = null;
                for (club Club : Data.getClubList()) {
                    if (Club.getClubId().equals(currentMeeting.getClub().getClubId())) {
                        current = Club;
                        break;
                    }
                }
                ArrayList<Student> absent = new ArrayList<>();
                for (Student student : current.getMembers()) {
                    if (!currentMeeting.getAttendence().contains(student.getId())) {
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






    }
    private String getSpaces(int max,String string)
    {
        int difference= max-string.length();
        return " ".repeat(difference);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        attendanceLblClub.setText(currentMeeting.getClubName());
        attendanceLblDate.setText(currentMeeting.getDay().toString());
        if (ifMeeting)
        {
            attendanceLblSlot.setText(String.valueOf(currentMeeting.getTimeSlot()));
            attendanceLblNumber.setText(String.valueOf(currentMeeting.getAttendence().size()));

        }
        else
        {
            lblTimeSlot.setText("Start Time");
            attendanceLblSlot.setText(currentMeeting.getStart().toString());
            lblTotal.setLayoutY(165);
            attendanceLblNumber.setLayoutY(165);
            attendanceLblNumber.setText(String.valueOf(currentMeeting.getAttendence().size()));
            LblEndTime.setLayoutX(36);
            LblEndTime.setLayoutY(130);
            LblEndTime.setVisible(true);
            endTime.setLayoutX(36);
            endTime.setLayoutY(130);
            endTime.setText(currentMeeting.getEnd().toString());
            studentTable.setLayoutY(200);

        }
        populateStudent(currentMeeting.getStudentList());

        

    }
    private void populateStudent(ObservableList<Student> students)
    {
        studentId.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Student, String> student) {
                return new ReadOnlyObjectWrapper<>(student.getValue().getId());
            }
        });
        studentFirstN.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Student, String> student) {
                return new ReadOnlyObjectWrapper<>(student.getValue().getFirstName());
            }
        });
        studentLastN.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Student, String> student) {
                return new ReadOnlyObjectWrapper<>(student.getValue().getSurName());
            }
        });
        studentGender.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Student, String> student) {
                return new ReadOnlyObjectWrapper<>(student.getValue().getGender());
            }
        });
        studentEmail.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Student, String> student) {
                return new ReadOnlyObjectWrapper<>(student.getValue().getEmail());
            }
        });
        studentTable.setItems(students);
        
    }
}
