package com.example.activityclubmanagementsystem;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.UnaryOperator;

//import static javafx.scene.control.cell.CellUtils.updateItem;

public class teacherController implements Initializable {
    @FXML
    private TextField profTeachID;
    @FXML
    private TextField profFname;
    @FXML
    private TextField profSname;
    @FXML
    private TextField profNic;
    @FXML
    private DatePicker profDob;
    @FXML
    private ComboBox<String> profGender;
    @FXML
    private TextField profEmail;
    @FXML
    private Button viewClbBtn;
    @FXML
    private Button sheduleBtn;
    @FXML
    private Button attendanceBtn;
    @FXML
    private Button profileBtn;
    @FXML
    private Pane viewClbPane;
    @FXML
    private Pane shedulePane;
    @FXML
    private Pane attendancePane;
    @FXML
    private Pane profilePane;
    @FXML
    private DatePicker teacherDateSelect;

    @FXML
    private TableView<scheduleRow> teacherTblSchedule;

    @FXML
    private TableColumn<scheduleRow,String> monday;
    @FXML
    private TableColumn<scheduleRow,String> monday1;
    @FXML
    private TableColumn<scheduleRow,String> monday2;
    @FXML
    private TableColumn<scheduleRow,String> tuesday;
    @FXML
    private TableColumn<scheduleRow,String> tuesday1;
    @FXML
    private TableColumn<scheduleRow,String> tuesday2;
    @FXML
    private TableColumn<scheduleRow,String> wednesday;
    @FXML
    private TableColumn<scheduleRow,String> wednesday1;
    @FXML
    private TableColumn<scheduleRow,String> wednesday2;
    @FXML
    private TableColumn<scheduleRow,String> thursday;
    @FXML
    private TableColumn<scheduleRow,String> thursday1;
    @FXML
    private TableColumn<scheduleRow,String> thursday2;
    @FXML
    private TableColumn<scheduleRow,String> friday;
    @FXML
    private TableColumn<scheduleRow,String> friday1;
    @FXML
    private TableColumn<scheduleRow,String> friday2;
    @FXML
    private DatePicker eventDatePick;
    @FXML
    private ChoiceBox<String> eventStartHrs;
    @FXML
    private ChoiceBox<String> eventStartMin;
    @FXML
    private ChoiceBox<String> eventEndHrs;
    @FXML
    private ChoiceBox<String> eventEndMin;
    @FXML
    private TextField eventTxtName;
    @FXML
    private TextField eventTxtDesc;
    @FXML
    private TextField eventTxtVenue;
    @FXML
    private TableView<event> eventTbl;
    @FXML
    private TableColumn<event,String> eventTblDate;
    @FXML
    private TableColumn<event,String> eventTblClub;
    @FXML
    private TableColumn<event,String> eventTblEvent;
    @FXML
    private TableColumn<event,String> eventTblStart;
    @FXML
    private TableColumn<event,String> eventTblEnd;
    @FXML
    private TableColumn<event,String> eventTblDesc;
    @FXML
    private Button eventBtnSchedule;
    @FXML
    private TabPane teacherTabPane;
    @FXML
    private TableView<scheduleRow> activitiesTable;
    @FXML
    private TableColumn<scheduleRow,meeting> activitiesSaturday1;
    @FXML
    private TableColumn<scheduleRow,meeting> activitiesSaturday2;
    @FXML
    private TableColumn<scheduleRow,meeting> activitiesSaturday3;
    @FXML
    private TableColumn<scheduleRow,meeting> activitiesSunday1;
    @FXML
    private TableColumn<scheduleRow,meeting> activitiesSunday2;
    @FXML
    private TableColumn<scheduleRow,meeting> activitiesSunday3;
    @FXML
    private DatePicker ActivitiesDatePicker;
    @FXML
    private ChoiceBox<String> activitiesStarHrs;
    @FXML
    private ChoiceBox<String> activitiesStartMin;
    @FXML
    private ChoiceBox<String> activitiesEndMin;
    @FXML
    private ChoiceBox<String> activitiesEndHrs;
    @FXML
    private TextArea activitiesTxtDesc;
    @FXML
    private Button activitiesBtnSchedule;
    @FXML
    private ChoiceBox<String> activitiesChoiceVenue;
    @FXML
    private TableView<Student> tableViewStudents;
    @FXML
    private TableColumn<Student,String> viewId;
    @FXML
    private TableColumn<Student,String> viewFirstN;
    @FXML
    private TableColumn<Student,String> fviewLastN;
    @FXML
    private TableColumn<Student,String> viewClubs;
    @FXML
    private TableColumn<Student,String>viewEmail;
    @FXML
    private Button profileBtnSave;
    @FXML
    private PasswordField profileTxtPwd;
    @FXML
    private PasswordField profileTxtRePwd;
    @FXML
    private Button ProfileBtnshow;
    @FXML
    private TextField seePwd;
    @FXML
    private TextField seeRePwd;

    @FXML
    private DatePicker attendanceDateMeeting;
    @FXML
    private TableView<meeting> attendanceTblMeeting;
    @FXML
    private TableColumn<meeting,String> attendanceDate;
    @FXML
    private TableColumn<meeting,String> attendanceDay;
    @FXML
    private TableColumn<meeting,String> attendanceTimeSlot;
    @FXML
    private TableColumn<meeting,String> attendanceVenue;
    @FXML
    private TableColumn<meeting,String> attendanceDesc;
    @FXML
    private TableView<scheduleRow> attendanceTblActivities;
    @FXML
    private TableColumn<scheduleRow,meeting> activitiesSat1;
    @FXML
    private TableColumn<scheduleRow,meeting> activitiesSat2;
    @FXML
    private TableColumn<scheduleRow,meeting> activitiesSat3;
    @FXML
    private TableColumn<scheduleRow,meeting> activitiesSun1;
    @FXML
    private TableColumn<scheduleRow,meeting> activitiesSun2;
    @FXML
    private TableColumn<scheduleRow,meeting> activitiesSun3;
    @FXML
    private TabPane checkAttendanceTab;
    @FXML
    private TableView<event> attendanceTblEvents;
    @FXML
    private TableColumn<event,String> eventDate;
    @FXML
    private TableColumn<event,String> eventName;
    @FXML
    private TableColumn<event,String> eventHeldBy;
    @FXML
    private TableColumn<event,String> eventStart;
    @FXML
    private TableColumn<event,String> eventEnd;
    @FXML
    private TableColumn<event,String> eventDesc;
    @FXML
    private TextField txtPres;
    @FXML
    private TextField txtVicePres;
    @FXML
    private TextField txtSec;
    @FXML
    private TextField txtTre;
    @FXML
    private TextField txtViceSec;
    @FXML
    private TextField txtViceTre;
    @FXML
    private Button viewBtnChange;
    private int boardMemberSelect;

    private String teacherID;
    // private ObservableList<day> dayList = FXCollections.observableArrayList(Data.getDayList());
    private LocalDate focus;
    private LocalDate today;
    ArrayList<TextField> fields;

    int colorIndex=0;

    Teacher teacher;

    ArrayList<String> colorHexList = new ArrayList<>();

    Student selectedForRemove;
    ObservableList<String> hrs1 = FXCollections.observableArrayList();
    ObservableList<String> min1 = FXCollections.observableArrayList();
    meeting attendanceCheck;
    event attendanceEvent;
    Student seletedForBoard;
    @FXML
    private MenuItem remove;
    private meeting removeMeeting;
    private ObservableValue<meeting> removeActivity;

    public teacherController()
    {

        this.teacherID = Data.getUserId();
        Data.clearCSV();

        Data.getTeacherList();
        for(Teacher teacher : Data.getTeacherList()){
            if (teacherID .equals( teacher.getId())){
                this.teacher = teacher;
                break;
            }
        }


        colorHexList.add("#FF0000"); // Red
        colorHexList.add("#00FF00"); // Green
        colorHexList.add("#0000FF"); // Blue
        colorHexList.add("#FFFF00"); // Yellow
        colorHexList.add("#00FFFF"); // Cyan
        colorHexList.add("#FF00FF"); // Magenta
        colorHexList.add("#FFA500"); // Orange
        colorHexList.add("#FF69B4"); // Pink
        colorHexList.add("#808080"); // Gray
        colorHexList.add("#000000"); // Black
        colorHexList.add("#FFFFFF"); // White
        colorHexList.add("#800080"); // Purple
        colorHexList.add("#008080"); // Teal
        colorHexList.add("#8B4513"); // Saddle Brown
        colorHexList.add("#A0522D"); // Sienna
        colorHexList.add("#228B22"); // Forest Green
        colorHexList.add("#D2691E"); // Chocolate
        colorHexList.add("#4682B4"); // Steel Blue
        colorHexList.add("#2E8B57"); // Sea Green
        colorHexList.add("#CD5C5C"); // Indian Red



    }

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
        if (actionEvent.getSource() == viewClbBtn) {
            viewClbPane.toFront();
            setBoarMembers();

        }
        if (actionEvent.getSource() == sheduleBtn)
        {
            shedulePane.toFront();
        }
        if (actionEvent.getSource() == attendanceBtn)
        {
            attendancePane.toFront();
        }
        if (actionEvent.getSource() == profileBtn)
        {
            profilePane.toFront();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

        ObservableList<String> genders = FXCollections.observableArrayList();
        genders.add("Male");
        genders.add("Female");
        profGender.setItems(genders);
        profTeachID.setText(teacher.getId());
        profTeachID.setDisable(true);
        profFname.setText(teacher.getFirstName());
        profSname.setText(teacher.getSurName());
        profNic.setText(teacher.getNic());
        profDob.setValue(LocalDate.parse(teacher.getDob()));
        profGender.setValue(teacher.getGender());
        profEmail.setText(teacher.getEmail());
        focus = LocalDate.now();
//        focus=LocalDate.parse("20-11-2023");
        today = LocalDate.now();
        fields = new ArrayList<>();
        if (today.getDayOfWeek().equals(DayOfWeek.SATURDAY))
        {
            focus=today.plusDays(2);

        }
        if (today.getDayOfWeek().equals(DayOfWeek.SUNDAY))
        {
            focus=today.plusDays(1);
        }
        teacherDateSelect.setValue(focus);


        teacherDateSelect.setDayCellFactory(picker ->
        {
            return new DateCell()
            {
                @Override
                public void updateItem(LocalDate date,boolean empty)
                {
                    super.updateItem(date,empty);
                    if (date.getDayOfWeek().equals(DayOfWeek.SATURDAY)||date.getDayOfWeek().equals(DayOfWeek.SUNDAY))
                    {
                        setDisable(true);
                    }
                }
            };
        });
        ActivitiesDatePicker.setDayCellFactory(picker ->
        {
            return new DateCell()
            {
                @Override
                public void updateItem(LocalDate date,boolean empty)
                {
                    super.updateItem(date,empty);
                    if (!(date.getDayOfWeek().equals(DayOfWeek.SATURDAY)||date.getDayOfWeek().equals(DayOfWeek.SUNDAY)))
                    {
                        setDisable(true);
                    }
                }
            };
        });
        shedulePane.toFront();
        populateSchedule();
        populateClubMembers(null,false);
        populateAttendanceMeeting();
        profFname.setTextFormatter(new TextFormatter<>(filter));
        profSname.setTextFormatter(new TextFormatter<>(filter));





    }


    public void onSelect(ActionEvent event)
    {
        focus = teacherDateSelect.getValue();
        populateSchedule();


    }
    private void populateAttendanceMeeting()
    {
        ArrayList<LocalDate> weekDates = getWeek();
        ObservableList<day> filteredDay = FXCollections.observableArrayList();
        for (day Day:Data.getDayList())
        {
            if (weekDates.contains(Day.getDay()))
            {
                filteredDay.add(Day);
            }
        }
        for(int i=0;i<filteredDay.size();i++)
        {
            for (int j=0; j< filteredDay.size()-1;j++)
            {
                if (filteredDay.get(j).getDay().isAfter(filteredDay.get(j+1).getDay()))
                {
                    day Day1 = filteredDay.get(j);
                    day Day2 = filteredDay.get(j+1);
                    filteredDay.set(j,Day2);
                    filteredDay.set(j+1,Day1);
                }
            }
        }
        ObservableList<meeting> meetings = FXCollections.observableArrayList();
        for(day Day:filteredDay)
        {
            ArrayList<timeSlot> slots = Day.getSlots();
            for (timeSlot slot:slots)

            {
                if (slot.getVenue1()!=null)
                {
                    if (slot.getVenue1().getClub().getInCharge().getId().equals(teacher.getId())) {
                        meetings.add(slot.getVenue1());
                    }
                }
                else if(slot.getVenue2()!=null)
                {
                    if (slot.getVenue2().getClub().getInCharge().getId().equals(teacher.getId())) {
                        meetings.add(slot.getVenue2());
                    }
                }
            }
        }
        attendanceDate.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<meeting, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<meeting, String> meeting)
            {
                return  new ReadOnlyObjectWrapper<String>(meeting.getValue().getDay().toString());
            }
        });
        attendanceDay.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<meeting, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<meeting, String> meeting) {
                return new ReadOnlyObjectWrapper<String>(meeting.getValue().getDay().getDayOfWeek().toString());
            }
        });
        attendanceTimeSlot.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<meeting, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<meeting, String> meeting) {
                return new ReadOnlyObjectWrapper<>(String.valueOf(meeting.getValue().getTimeSlot()));
            }
        });
        attendanceVenue.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<meeting, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<meeting, String> meeting) {
                return new ReadOnlyObjectWrapper<>(meeting.getValue().getVenue());
            }
        });
        attendanceDesc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<meeting, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<meeting, String> meeting) {
                return new ReadOnlyObjectWrapper<>(meeting.getValue().getDescription());
            }
        });
        attendanceTblMeeting.getSelectionModel().selectedItemProperty().addListener((ObservableValue,meeting,Meeting)->
        {
            attendanceCheck=Meeting;
        });
        attendanceTblMeeting.setOnMouseClicked((MouseEvent mouseEvet)->
        {
            if (mouseEvet.getClickCount()==2)
            {
                try {


                    FXMLLoader fxmlLoader = new FXMLLoader(teacherController.class.getResource("showAttendance.fxml"));
                    meeting Meeting = attendanceCheck;
                    fxmlLoader.setControllerFactory(showAttendance -> new showAttendanceController(Meeting,"1",null));
                    Parent parent = fxmlLoader.load();
                    Scene scene = new Scene(parent, 620, 470);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.showAndWait();
                }
                catch (IOException e)
                {
                    warnings.SqlWarning();
                }
            }
        });
        attendanceTblMeeting.setItems(meetings);




    }
    private void populateAttendanceActivity()
    {

        ArrayList<LocalDate> dates = getWeek();
        LocalDate saturday = dates.get(dates.size()-2);
        LocalDate sunday = dates.get(dates.size()-1);
        ObservableList<scheduleRow> rows = FXCollections.observableArrayList();
        AtomicInteger clicks = new AtomicInteger();

        for (int i=0;i<24;i++)
        {
            scheduleRow row = new scheduleRow(i);
            rows.add(row);
        }
        for (day Day:Data.getDayList())
        {
            if (Day.getDay().equals(saturday))
            {
                ArrayList<timeSlot> slots = Day.getSlots();
                for (int i=0;i<24;i++)
                {
                    if (slots.get(i).getVenue1()!=null)
                    {
                        if(slots.get(i).getVenue1().getClub().getClubId().equals(teacher.getInCharge().getClubId()))
                        {
                            int j = slots.get(i).getVenue1().getTotalSlots();
                            for (int k = i; k < j + i; k++) {
                                rows.get(k).sat1 = new ReadOnlyObjectWrapper<>(slots.get(i).getVenue1());
                            }
                        }

                        //rows.get(i).sat1=new ReadOnlyObjectWrapper<meeting>(slots.get(i).getVenue1());
                    }
                    if (slots.get(i).getVenue2() !=null)
                    {
                        if(slots.get(i).getVenue2().getClub().getClubId().equals(teacher.getInCharge().getClubId()))
                        {
                            int j = slots.get(i).getVenue2().getTotalSlots();
                            for (int k = i; k < j + i; k++) {
                                rows.get(k).sat2 = new ReadOnlyObjectWrapper<>(slots.get(i).getVenue2());
                            }
                        }
                        // rows.get(i).sat2 = new ReadOnlyObjectWrapper<meeting>(slots.get(i).getVenue2());
                    }
                    if (slots.get(i).getVenue3()!=null)
                    {
                        if(slots.get(i).getVenue3().getClub().getClubId().equals(teacher.getInCharge().getClubId()))
                        {
                            int j = slots.get(i).getVenue3().getTotalSlots();
                            for (int k = i; k < j + i; k++) {
                                rows.get(k).sat3 = new ReadOnlyObjectWrapper<>(slots.get(i).getVenue3());
                            }
                        }
                        //rows.get(i).sat3=new ReadOnlyObjectWrapper<meeting>(slots.get(i).getVenue2());
                    }
                }
            } else if (Day.getDay().equals(sunday))
            {


                ArrayList<timeSlot> slots = Day.getSlots();
                for (int i=0;i<24;i++)
                {
                    if (slots.get(i).getVenue1()!=null)
                    {
                        if(slots.get(i).getVenue1().getClub().getClubId().equals(teacher.getInCharge().getClubId()))
                        {
                            //rows.get(i).sun1=new ReadOnlyObjectWrapper<meeting>(slots.get(i).getVenue1());
                            int j = slots.get(i).getVenue1().getTotalSlots();
                            for (int k = i; k < j + i; k++) {
                                rows.get(k).sun1 = new ReadOnlyObjectWrapper<>(slots.get(i).getVenue1());
                            }
                        }
                    }
                    if (slots.get(i).getVenue2() !=null)
                    {
                        if(slots.get(i).getVenue1().getClub().getClubId().equals(teacher.getInCharge().getClubId()))
                        {
                            // rows.get(i).sun2 = new ReadOnlyObjectWrapper<meeting>(slots.get(i).getVenue2());
                            int j = slots.get(i).getVenue2().getTotalSlots();
                            for (int k = i; k < j + i; k++) {
                                rows.get(k).sun2 = new ReadOnlyObjectWrapper<>(slots.get(i).getVenue2());
                            }
                        }
                    }
                    if (slots.get(i).getVenue3()!=null)
                    {
                        if(slots.get(i).getVenue1().getClub().getClubId().equals(teacher.getInCharge().getClubId()))
                        {
                            int j = slots.get(i).getVenue3().getTotalSlots();
                            for (int k = i; k < j + i; k++) {
                                rows.get(k).sun3 = new ReadOnlyObjectWrapper<>(slots.get(i).getVenue3());
                            }
                        }
                        //rows.get(i).sun3 = new ReadOnlyObjectWrapper<meeting>(slots.get(i).getVenue3());
                    }
                }
            }
        }
        activitiesSat1.setCellValueFactory(cellData->  cellData.getValue().sat1);
        activitiesSat2.setCellValueFactory(cellData-> cellData.getValue().sat2);
        activitiesSat3.setCellValueFactory(cellData->  cellData.getValue().sat3);
        activitiesSun1.setCellValueFactory(cellData->  cellData.getValue().sun1);
        activitiesSun2.setCellValueFactory(cellData-> cellData.getValue().sun2);
        activitiesSun3.setCellValueFactory(cellData-> cellData.getValue().sun3);
        attendanceTblActivities.getSelectionModel().selectedItemProperty().addListener((ObservableValue,meeting,Meeting)->
        {
            TableView.TableViewSelectionModel<scheduleRow> model = attendanceTblActivities.getSelectionModel();
            ObservableList selected = model.getSelectedCells();
            TablePosition position = (TablePosition) selected.get(0);
            int coloumn = position.getColumn();
            ObservableValue<meeting> currentMeeting = null;

            if (coloumn==0)
            {
                currentMeeting = rows.get(model.getSelectedIndex()).sat1;

            }
            else if (coloumn==1)
            {
                currentMeeting =  rows.get(model.getSelectedIndex()).sat2;
            }
            else if (coloumn==2)
            {
                currentMeeting = rows.get(model.getSelectedIndex()).sat3;

            }
            else if (coloumn==3)
            {
                currentMeeting =  rows.get(model.getSelectedIndex()).sun1;
            }
            else if (coloumn==4)
            {
                currentMeeting =  rows.get(model.getSelectedIndex()).sun2;
            }
            else if (coloumn==5)
            {
                currentMeeting =  rows.get(model.getSelectedIndex()).sat3;
            }
            if (clicks.get()==2)
            {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(teacherController.class.getResource("showAttendance.fxml"));
                    com.example.activityclubmanagementsystem.meeting m=currentMeeting.getValue();
                    fxmlLoader.setControllerFactory(showAttendance -> new showAttendanceController(m,"2",null));
                    Parent parent = fxmlLoader.load();
                    Scene scene = new Scene(parent, 620, 470);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.showAndWait();
                }
                catch (IOException e)
                {

                }
                clicks.set(0);
            }
        });
        attendanceTblActivities.setOnMouseClicked((MouseEvent Mevent)->
        {
            if (Mevent.getClickCount()==2)
            {
                clicks.set(2);
            }
        });



        activitiesSat1.setCellFactory(coloumn ->
        {
            return new TableCell<scheduleRow, meeting>() {
                @Override
                protected void updateItem(meeting meeting, boolean empty) {
                    super.updateItem(meeting, empty);

                    if (meeting == null || empty) {
                        setText("_");
                        setStyle("");
                    } else {

                        setText(meeting.getClubName());
                        setStyle("-fx-background-color:" + meeting.code);
//                            if (meeting.code.equals("00") || meeting.code.equals("11")) {
//
//                                colorIndex += 1;
//                            }
                    }

                }
            };
        });
        activitiesSat2.setCellFactory(coloumn ->
        {
            return new TableCell<scheduleRow, meeting>() {
                @Override
                protected void updateItem(meeting meeting, boolean empty) {
                    super.updateItem(meeting, empty);
                    if (meeting == null || empty) {
                        setText("_");
                        setStyle("");
                    } else {

                        setText(meeting.getClubName());
                        setStyle("-fx-background-color:" + meeting.code);
//                            if (meeting.code.equals("00") || meeting.code.equals("11")) {
//
//                                colorIndex += 1;
//                            }
                    }

                }
            };
        });
        activitiesSat3.setCellFactory(coloumn ->
        {
            return new TableCell<scheduleRow, meeting>() {
                @Override
                protected void updateItem(meeting meeting, boolean empty) {
                    super.updateItem(meeting, empty);
                    if (meeting == null || empty) {
                        setText("_");
                        setStyle("");
                    } else
                    {
                        setStyle("-fx-background-color:" + meeting.code);
                        setText(meeting.getClubName());
                        setStyle("-fx-background-color:" + meeting.code);
//                            if (meeting.code.equals("00") || meeting.code.equals("11")) {
//
//                                colorIndex += 1;
//                            }
                    }

                }
            };
        });
        activitiesSun1.setCellFactory(coloumn ->
        {
            return new TableCell<scheduleRow, meeting>() {
                @Override
                protected void updateItem(meeting meeting, boolean empty) {
                    super.updateItem(meeting, empty);
                    if (meeting == null || empty) {
                        setText("_");
                        setStyle("");
                    } else {

                        setText(meeting.getClubName());
                        setStyle("-fx-background-color:" + meeting.code);
//                            if (meeting.code.equals("00") || meeting.code.equals("11")) {
//
//                                colorIndex += 1;
//                            }


                    }

                }
            };
        });
        activitiesSun2.setCellFactory(coloumn ->
        {
            return new TableCell<scheduleRow, meeting>() {
                @Override
                protected void updateItem(meeting meeting, boolean empty) {
                    super.updateItem(meeting, empty);
                    if (meeting == null || empty) {
                        setText("_");
                        setStyle("");
                    } else {
                        setText(meeting.getClubName());
                        setStyle("-fx-background-color:" + meeting.code);
//                            if (meeting.code.equals("00")) {
//                                setStyle("-fx-background-color:" + colorHexList.get(nextIndex()));
//                                colorIndex++;
//                            } else if (meeting.code.equals("01")) {
//                                setStyle("-fx-background-color:" + colorHexList.get(nextIndex()));
//                            } else if (meeting.code.equals("11")) {
//                                setStyle("-fx-background-color:" + colorHexList.get(nextIndex()));
////                            colorIndex++;
//                            }
                    }

                }
            };
        });
        activitiesSun3.setCellFactory(coloumn ->
        {
            return new TableCell<scheduleRow, meeting>() {
                @Override
                protected void updateItem(meeting meeting, boolean empty) {
                    super.updateItem(meeting, empty);
                    if (meeting == null || empty) {
                        setText("_");
                        setStyle("");
                    } else {
                        setText(meeting.getClubName());
                        setStyle("-fx-background-color:" + meeting.code);
//                        setStyle("-fx-background-color:"+colorHexList.get(nextIndex()));
//                            if (meeting.code.equals("00")) {
//                                setStyle("-fx-background-color:" + colorHexList.get(nextIndex()));
//                                colorIndex++;
//                            } else if (meeting.code.equals("01")) {
//                                setStyle("-fx-background-color:" + colorHexList.get(nextIndex()));
//                            } else if (meeting.code.equals("11")) {
//                                setStyle("-fx-background-color:" + colorHexList.get(nextIndex()));
////                            colorIndex++;
//                            }
//                        if (meeting.code.equals("00")||meeting.code.equals("11"))
//                        {
//
//                            colorIndex+=1;
//                        }
                    }

                }
            };
        });

        attendanceTblActivities.setItems(rows);
    }
    private void populateSchedule ()
    {
        ArrayList<LocalDate> daysInWeek = getWeek();
        ObservableList<scheduleRow> rows = FXCollections.observableArrayList();
        for(int i =0;i<9;i++)
        {
            scheduleRow row = new scheduleRow(i);
            rows.add(row);
        }

        ObservableList<day> filteredDay = FXCollections.observableArrayList();

        for (day day:Data.getDayList())
        {
            if (daysInWeek.contains(day.getDay())&&!day.getDay().equals(getWeek().get(5))&&!day.getDay().equals(getWeek().get(6)))
            {
                filteredDay.add(day);
            }
        }




        ArrayList<String> daysInWeek1= new ArrayList<>();
        daysInWeek1.add("MONDAY");
        daysInWeek1.add("TUESDAY");
        daysInWeek1.add("WEDNESDAY");
        daysInWeek1.add("THURSDAY");
        daysInWeek1.add("FRIDAY");
        daysInWeek1.add("SATURDAY");
        daysInWeek1.add("SUNDAY");

        for(day eachDay:filteredDay)
        {
            ArrayList<timeSlot> slots = eachDay.getSlots();
            for(int i=0;i<9;i++)
            {
                timeSlot slot = slots.get(i);

                if (slot.getVenue1()!=null)
                {
                    String date = slot.date.getDayOfWeek().toString();
                    int l = 2*daysInWeek1.indexOf(date) ;
                    scheduleRow row = rows.get(i);

                    row.list.set(l,slot.getVenue1());



                }
                if (slot.getVenue2()!=null)
                {
                    String date = slot.date.getDayOfWeek().toString();
                    int l = 2*daysInWeek1.indexOf(date) +1;
                    scheduleRow row = rows.get(i);

                    row.list.set(l,slot.getVenue2());

                }
            }
        }




        monday1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<scheduleRow, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<scheduleRow, String> row) {
                if (row.getValue().list.get(0)==null)
                {

                    return new ReadOnlyObjectWrapper<String>("_");

                }
                else
                {
                    return new ReadOnlyObjectWrapper<String>( row.getValue().list.get(0).getClubName());
                }
            }
        });
        monday2.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<scheduleRow, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<scheduleRow, String> row) {
                if (row.getValue().list.get(1)==null)
                {
                    return new ReadOnlyObjectWrapper<String>("_");
                }
                else
                {
                    return new ReadOnlyObjectWrapper<String>(  row.getValue().list.get(1).getClubName());

                }
            }
        });
        tuesday1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<scheduleRow, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<scheduleRow, String> row) {
                if (row.getValue().list.get(2)==null)
                {
                    return new ReadOnlyObjectWrapper<String>("_");
                }
                else
                {
                    return new ReadOnlyObjectWrapper<String>( row.getValue().list.get(2).getClubName());
                }
            }
        });
        tuesday2.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<scheduleRow, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<scheduleRow, String> row) {
                if (row.getValue().list.get(3)==null)
                {
                    return new ReadOnlyObjectWrapper<String>("_");
                }
                else
                {
                    return new ReadOnlyObjectWrapper<String>( row.getValue().list.get(3).getClubName());
                }
            }
        });
        wednesday1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<scheduleRow, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<scheduleRow, String> row) {
                if (row.getValue().list.get(4)==null)
                {
                    return new ReadOnlyObjectWrapper<String>("_");
                }
                else
                {
                    return new ReadOnlyObjectWrapper<String>( row.getValue().list.get(4).getClubName());
                }
            }
        });
        wednesday2.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<scheduleRow, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<scheduleRow, String> row) {
                if (row.getValue().list.get(5)==null)
                {
                    return new ReadOnlyObjectWrapper<String>("_");
                }
                else
                {
                    return new ReadOnlyObjectWrapper<String>( row.getValue().list.get(5).getClubName());
                }
            }
        });
        thursday1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<scheduleRow, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<scheduleRow, String> row) {
                if (row.getValue().list.get(6)==null)
                {
                    return new ReadOnlyObjectWrapper<String>("_");
                }
                else
                {
                    return new ReadOnlyObjectWrapper<String>( row.getValue().list.get(6).getClubName());
                }
            }
        });
        thursday2.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<scheduleRow, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<scheduleRow, String> row) {
                if (row.getValue().list.get(7)==null)
                {
                    return new ReadOnlyObjectWrapper<String>("_");
                }
                else
                {
                    return new ReadOnlyObjectWrapper<String>( row.getValue().list.get(7).getClubName());
                }
            }
        });
        friday1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<scheduleRow, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<scheduleRow, String> row) {
                if (row.getValue().list.get(8)==null)
                {
                    return new ReadOnlyObjectWrapper<String>("_");
                }
                else
                {
                    return new ReadOnlyObjectWrapper<String>( row.getValue().list.get(8).getClubName());
                }
            }
        });
        friday2.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<scheduleRow, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<scheduleRow, String> row) {
                if (row.getValue().list.get(9)==null)
                {
                    return new ReadOnlyObjectWrapper<String>("_");
                }
                else
                {
                    return new ReadOnlyObjectWrapper<String>( row.getValue().list.get(9).getClubName());
                }
            }
        });
        teacherTblSchedule.setItems(rows);


        teacherTblSchedule.getSelectionModel().selectedItemProperty().addListener((ObservableValue, scheduleRow,SR)->
        {

            teacherTblSchedule.setOnMouseClicked(Mevent->
            {
                if (Mevent.getButton().equals(MouseButton.PRIMARY))
                {
                    if (Mevent.getClickCount()==1)
                    {
                        TableView.TableViewSelectionModel<scheduleRow> model = teacherTblSchedule.getSelectionModel();

                        ObservableList selected;
                        TablePosition position = null;


                        try {
                            selected = model.getSelectedCells();
                            position = (TablePosition) selected.get(0);



                        }
                        catch (IndexOutOfBoundsException e)
                        {
                            e.printStackTrace();
                        }
                        int coloumn = position.getColumn();
                        if (SR.list.get(coloumn)==null)
                        {
                            try {
                                scheduleMeeting(model.getSelectedIndex(),position.getColumn(),position.getTableColumn().getId());
                            } catch (IOException e)
                            {
                                throw new RuntimeException(e);
                            }
                        }
                        else
                        {

                            warnings.register(SR.list.get(coloumn).getClub().getClubName()+" has reserved this venue");
                        }


                    }
                }
                else
                {
                    TableView.TableViewSelectionModel<scheduleRow> model = teacherTblSchedule.getSelectionModel();
                    ObservableList selected;
                    TablePosition position = null;


                    try {
                        selected = model.getSelectedCells();
                        position = (TablePosition) selected.get(0);
                        if (SR.list.get(position.getColumn())!=null)
                        {
                            removeMeeting = SR.list.get(position.getColumn());


                        }


                    }
                    catch (NullPointerException e)
                    {
                        Alert alert = new Alert(Alert.AlertType.WARNING,"no meeting to remove",ButtonType.OK);
                        alert.showAndWait();
                    }
                }


            });


        });


    }

    private void scheduleMeeting(int row,int coloumn,String coloumnid) throws IOException { //row = which period colmn id, colmn id = which colmn
        LocalDate selected =getSelectedDate(coloumnid);
        club meetingClub=null;
        String venue;
        Boolean hasclub =false;
        for (Teacher teach:Data.getTeacherList())
        {
            if (teach.getId().equals(teacherID)&&teach.getInCharge().getClubName()!=null)
            {
                hasclub = true;
                meetingClub =teach.getInCharge();
            }
        }
        if (hasclub&&selected.isAfter(today))
        {
            if (coloumnid.endsWith("1"))
            {
                venue="1";
            }
            else
            {
                venue="2";
            }
            FXMLLoader fxmlLoader = new FXMLLoader(teacherController.class.getResource("scheduleMeeting.fxml"));
            club finalMeetingClub = meetingClub;
            fxmlLoader.setControllerFactory(scheduleController -> new scheduleMeetingController(Data.getmeetingID(),selected, finalMeetingClub,venue,row));
            Parent parent = fxmlLoader.load();
            Scene scene = new Scene(parent,620,470);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.showAndWait();
            teacherTblSchedule.getSelectionModel().clearSelection();
            populateSchedule();

        } else
        {
            venue = null;
        }


    }
    private LocalDate getSelectedDate(String coloumnId)
    {
        String focusDay = focus.getDayOfWeek().toString().toLowerCase();
        String selected = coloumnId.substring(0,coloumnId.length()-1);
        ArrayList<String> weekDays = new ArrayList<>();
        LocalDate selectedDate=null;
        weekDays.add("monday");
        weekDays.add("tuesday");
        weekDays.add("wednesday");
        weekDays.add("thursday");
        weekDays.add("friday");
        weekDays.add("saturday");
        weekDays.add("sunday");
        int i = weekDays.indexOf(focusDay);
        int j = weekDays.indexOf(selected);
        if (i==j)
        {
            selectedDate= focus;
        }
        else if (i-j<0)
        {
            selectedDate= focus.plus((i-j)*-1,ChronoUnit.DAYS);
        }
        else if (i-j>0)
        {
            selectedDate= focus.minus(i-j,ChronoUnit.DAYS);

        }
        return selectedDate;


    }

    private ArrayList<LocalDate> getWeek()
    {
        String s = focus.getDayOfWeek().toString();
        ArrayList<String> daysInWeek= new ArrayList<>();
        ArrayList<LocalDate> dates = new ArrayList<>();
        daysInWeek.add("MONDAY");
        daysInWeek.add("TUESDAY");
        daysInWeek.add("WEDNESDAY");
        daysInWeek.add("THURSDAY");
        daysInWeek.add("FRIDAY");
        daysInWeek.add("SATURDAY");
        daysInWeek.add("SUNDAY");
        int i = daysInWeek.indexOf(s);
        for (int j=i; j>0;j--)
        {
            LocalDate d = focus.minus(j,ChronoUnit.DAYS);
            dates.add(d);
        }
        dates.add(focus);
        for (int j=1;j<7-i;j++)
        {
            LocalDate d = focus.plus(j,ChronoUnit.DAYS);
            dates.add(d);
        }
        return dates;


    }
    public void onEventScheduleClick(ActionEvent event) throws SQLException {
        getText input = getEventScheduleInputs();
        boolean hasday=false;
        boolean hasEvent=false;
        day currentDay = null;
        if (input.complete)
        {
            ArrayList<String> inputs = input.inputs;
            LocalDate date = LocalDate.parse(inputs.get(1));
            for (day Day:Data.getDayList())
            {
                if (Day.getDay().equals(date))
                {
                    hasday=true;
                    currentDay =Day;
                    if (Day.getEvent()!=null)
                    {
                        hasEvent=true;
                    }
                }
            }
            if (!hasEvent)
            {
                LocalTime startTime = LocalTime.parse(inputs.get(2)+":"+inputs.get(3)+":00");
                LocalTime endTime = LocalTime.parse(inputs.get(4)+":"+inputs.get(5)+":00");
                event Event = new event(teacher.getInCharge(),inputs.get(0),startTime,endTime,date,inputs.get(6),inputs.get(7));
                if (!hasday)
                {
                    int num ;
                    if (date.getDayOfWeek().equals(DayOfWeek.SATURDAY)||date.getDayOfWeek().equals(DayOfWeek.SUNDAY))
                    {
                        num = 24;
                    }
                    else
                    {
                        num =9;
                    }
                    day Day = new day(date,Integer.parseInt(Data.getNextId("days")),num);
                    Day.setEvent(Event);
                    Data.addDay(Day);
                }
                else
                {
                    currentDay.setEvent(Event);
                    Data.updateDay(currentDay);
                }
            }

        }
        populateEvents();

    }
    @FXML
    private void onEvents()
    {
//        for (day D:Data.getDayList())
//        {
//                ArrayList<timeSlot> slots = D.getSlots();
//                for (timeSlot t:slots)
//                {
//                    if (t!=null)
//                    {
//                        System.out.println(t.getVenue3().toString());
//                    }
//                }
//        }
        int selected= teacherTabPane.getSelectionModel().getSelectedIndex();
        int tab =-1;
        if (selected==0&&selected!=tab)
        {
            focus = LocalDate.now();
            populateSchedule();
            tab=0;
        } else if (selected==1&&selected!=tab)
        {
            populateActivities();
            ObservableList<String> hrs = FXCollections.observableArrayList();

            for (int i=6;i<19;i++)
            {
                if (i<10)
                {
                    hrs.add("0"+String.valueOf(i));
                    hrs1.add("0"+String.valueOf(i));
                }
                else
                {
                    hrs.add(String.valueOf(i));
                    if (i!=18)
                    {
                        hrs1.add(String.valueOf(i));
                    }
                }
            }
            ObservableList<String> min = FXCollections.observableArrayList();

            min.add("00");
            min.add("30");
            ObservableList<String> venues = FXCollections.observableArrayList();
            venues.add("1");
            venues.add("2");
            venues.add("3");
            activitiesStarHrs.setItems(hrs);
            activitiesEndHrs.setItems(hrs);
            activitiesEndMin.setItems(min);
            activitiesStartMin.setItems(min);
            activitiesChoiceVenue.setItems(venues);




            tab=1;

        } else if (selected==2&&selected!=tab)
        {
            ObservableList<String> hours = FXCollections.observableArrayList();
            ObservableList<String> minutes = FXCollections.observableArrayList();
            for (int i=6;i<19;i++)
            {
                if (i<10)
                {
                    hours.add("0"+String.valueOf(i));
                }
                else
                {
                    hours.add(String.valueOf(i));
                }
            }
            for (int i=1;i<7;i++)
            {

                minutes.add(String.valueOf(10*i));
            }
            eventStartHrs.setItems(hours);
            eventEndHrs.setItems(hours);
            eventStartMin.setItems(minutes);
            eventEndMin.setItems(minutes);
            populateEvents();
            tab=2;

        }
    }
    Boolean scroll=false;
    private void populateActivities()
    {
        ArrayList<LocalDate> dates = getWeek();
        LocalDate saturday = dates.get(dates.size()-2);
        LocalDate sunday = dates.get(dates.size()-1);
        ObservableList<scheduleRow> rows = FXCollections.observableArrayList();


        for (int i=0;i<24;i++)
        {
            scheduleRow row = new scheduleRow(i);
            rows.add(row);
        }
        for (day Day:Data.getDayList())
        {
            if (Day.getDay().equals(saturday))
            {
                ArrayList<timeSlot> slots = Day.getSlots();
                for (int i=0;i<24;i++)
                {
                    if (slots.get(i).getVenue1()!=null)
                    {
                        int j= slots.get(i).getVenue1().getTotalSlots();
                        for (int k=i;k<j+i;k++)
                        {
                            rows.get(k).sat1 = new ReadOnlyObjectWrapper<>(slots.get(i).getVenue1());
                        }

                        //rows.get(i).sat1=new ReadOnlyObjectWrapper<meeting>(slots.get(i).getVenue1());
                    }
                    if (slots.get(i).getVenue2() !=null)
                    {
                        int j= slots.get(i).getVenue2().getTotalSlots();
                        for (int k=i;k<j+i;k++)
                        {
                            rows.get(k).sat2 = new ReadOnlyObjectWrapper<>(slots.get(i).getVenue2());
                        }
                        // rows.get(i).sat2 = new ReadOnlyObjectWrapper<meeting>(slots.get(i).getVenue2());
                    }
                    if (slots.get(i).getVenue3()!=null)
                    {
                        int j= slots.get(i).getVenue3().getTotalSlots();
                        for (int k=i;k<j+i;k++)
                        {
                            rows.get(k).sat3 = new ReadOnlyObjectWrapper<>(slots.get(i).getVenue3());
                        }
                        //rows.get(i).sat3=new ReadOnlyObjectWrapper<meeting>(slots.get(i).getVenue2());
                    }
                }
            } else if (Day.getDay().equals(sunday))
            {


                ArrayList<timeSlot> slots = Day.getSlots();
                for (int i=0;i<24;i++)
                {
                    if (slots.get(i).getVenue1()!=null)
                    {
                        //rows.get(i).sun1=new ReadOnlyObjectWrapper<meeting>(slots.get(i).getVenue1());
                        int j= slots.get(i).getVenue1().getTotalSlots();
                        for (int k=i;k<j+i;k++)
                        {
                            rows.get(k).sun1 = new ReadOnlyObjectWrapper<>(slots.get(i).getVenue1());
                        }
                    }
                    if (slots.get(i).getVenue2() !=null)
                    {
                        // rows.get(i).sun2 = new ReadOnlyObjectWrapper<meeting>(slots.get(i).getVenue2());
                        int j= slots.get(i).getVenue2().getTotalSlots();
                        for (int k=i;k<j+i;k++)
                        {
                            rows.get(k).sun2 = new ReadOnlyObjectWrapper<>(slots.get(i).getVenue2());
                        }
                    }
                    if (slots.get(i).getVenue3()!=null)
                    {
                        int j= slots.get(i).getVenue3().getTotalSlots();
                        for (int k=i;k<j+i;k++)
                        {
                            rows.get(k).sun3 = new ReadOnlyObjectWrapper<>(slots.get(i).getVenue3());
                        }
                        //rows.get(i).sun3 = new ReadOnlyObjectWrapper<meeting>(slots.get(i).getVenue3());
                    }
                }
            }
        }
        activitiesSaturday1.setCellValueFactory(cellData->  cellData.getValue().sat1);
        activitiesSaturday2.setCellValueFactory(cellData-> cellData.getValue().sat2);
        activitiesSaturday3.setCellValueFactory(cellData->  cellData.getValue().sat3);
        activitiesSunday1.setCellValueFactory(cellData->  cellData.getValue().sun1);
        activitiesSunday2.setCellValueFactory(cellData-> cellData.getValue().sun2);
        activitiesSunday3.setCellValueFactory(cellData-> cellData.getValue().sun3);
        activitiesTable.getSelectionModel().selectedItemProperty().addListener((ObservableValue,scheduleRow,SR)->
        {
            activitiesTable.setOnMouseClicked((MouseEvent mEvent)->
            {
                if (mEvent.getButton().equals(MouseButton.SECONDARY))
                {
                    TableView.TableViewSelectionModel<scheduleRow> model = teacherTblSchedule.getSelectionModel();
                    ObservableList selected;
                    TablePosition position = null;


                    try {
                        selected = model.getSelectedCells();
                        position = (TablePosition) selected.get(0);
                        if (SR.list.get(position.getColumn())!=null)
                        {
                            int pos = position.getColumn();
                            if (pos==0)
                            {
                                removeActivity= SR.sat1;
                            }
                            else if (pos==1)
                            {
                                removeActivity= SR.sat2;
                            }
                            else if (pos==2)
                            {
                                removeActivity= SR.sat3;
                            }
                            else if (pos==3)
                            {
                                removeActivity= SR.sun1;
                            }
                            else if (pos==4)
                            {
                                removeActivity= SR.sun2;
                            }
                            else if (pos==5)
                            {
                                removeActivity= SR.sun3;
                            }



                        }


                    }
                    catch (NullPointerException e)
                    {
                        Alert alert = new Alert(Alert.AlertType.WARNING,"no meeting to remove",ButtonType.OK);
                        alert.showAndWait();
                    }
                }
            });
        });



        activitiesSaturday1.setCellFactory(coloumn ->
        {
            return new TableCell<scheduleRow, meeting>() {
                @Override
                protected void updateItem(meeting meeting, boolean empty) {
                    super.updateItem(meeting, empty);

                    if (meeting == null || empty) {
                        setText("_");
                        setStyle("");
                    } else {

                        setText(meeting.getClubName());
                        setStyle("-fx-background-color:" + meeting.code);
//                            if (meeting.code.equals("00") || meeting.code.equals("11")) {
//
//                                colorIndex += 1;
//                            }
                    }

                }
            };
        });
        activitiesSaturday2.setCellFactory(coloumn ->
        {
            return new TableCell<scheduleRow, meeting>() {
                @Override
                protected void updateItem(meeting meeting, boolean empty) {
                    super.updateItem(meeting, empty);
                    if (meeting == null || empty) {
                        setText("_");
                        setStyle("");
                    } else {

                        setText(meeting.getClubName());
                        setStyle("-fx-background-color:" + meeting.code);
//                            if (meeting.code.equals("00") || meeting.code.equals("11")) {
//
//                                colorIndex += 1;
//                            }
                    }

                }
            };
        });
        activitiesSaturday3.setCellFactory(coloumn ->
        {
            return new TableCell<scheduleRow, meeting>() {
                @Override
                protected void updateItem(meeting meeting, boolean empty) {
                    super.updateItem(meeting, empty);
                    if (meeting == null || empty) {
                        setText("_");
                        setStyle("");
                    } else
                    {
                        setStyle("-fx-background-color:" + meeting.code);
                        setText(meeting.getClubName());
                        setStyle("-fx-background-color:" + meeting.code);
//                            if (meeting.code.equals("00") || meeting.code.equals("11")) {
//
//                                colorIndex += 1;
//                            }
                    }

                }
            };
        });
        activitiesSunday1.setCellFactory(coloumn ->
        {
            return new TableCell<scheduleRow, meeting>() {
                @Override
                protected void updateItem(meeting meeting, boolean empty) {
                    super.updateItem(meeting, empty);
                    if (meeting == null || empty) {
                        setText("_");
                        setStyle("");
                    } else {

                        setText(meeting.getClubName());
                        setStyle("-fx-background-color:" + meeting.code);
//                            if (meeting.code.equals("00") || meeting.code.equals("11")) {
//
//                                colorIndex += 1;
//                            }


                    }

                }
            };
        });
        activitiesSunday2.setCellFactory(coloumn ->
        {
            return new TableCell<scheduleRow, meeting>() {
                @Override
                protected void updateItem(meeting meeting, boolean empty) {
                    super.updateItem(meeting, empty);
                    if (meeting == null || empty) {
                        setText("_");
                        setStyle("");
                    } else {
                        setText(meeting.getClubName());
                        setStyle("-fx-background-color:" + meeting.code);
//                            if (meeting.code.equals("00")) {
//                                setStyle("-fx-background-color:" + colorHexList.get(nextIndex()));
//                                colorIndex++;
//                            } else if (meeting.code.equals("01")) {
//                                setStyle("-fx-background-color:" + colorHexList.get(nextIndex()));
//                            } else if (meeting.code.equals("11")) {
//                                setStyle("-fx-background-color:" + colorHexList.get(nextIndex()));
////                            colorIndex++;
//                            }
                    }

                }
            };
        });
        activitiesSunday3.setCellFactory(coloumn ->
        {
            return new TableCell<scheduleRow, meeting>() {
                @Override
                protected void updateItem(meeting meeting, boolean empty) {
                    super.updateItem(meeting, empty);
                    if (meeting == null || empty) {
                        setText("_");
                        setStyle("");
                    } else {
                        setText(meeting.getClubName());
                        setStyle("-fx-background-color:" + meeting.code);
//                        setStyle("-fx-background-color:"+colorHexList.get(nextIndex()));
//                            if (meeting.code.equals("00")) {
//                                setStyle("-fx-background-color:" + colorHexList.get(nextIndex()));
//                                colorIndex++;
//                            } else if (meeting.code.equals("01")) {
//                                setStyle("-fx-background-color:" + colorHexList.get(nextIndex()));
//                            } else if (meeting.code.equals("11")) {
//                                setStyle("-fx-background-color:" + colorHexList.get(nextIndex()));
////                            colorIndex++;
//                            }
//                        if (meeting.code.equals("00")||meeting.code.equals("11"))
//                        {
//
//                            colorIndex+=1;
//                        }
                    }

                }
            };
        });


        activitiesTable.setItems(rows);

    }
    private int nextIndex()
    {
        Random rand = new Random();
        int randInt = rand.nextInt(0,20);
        if (randInt!=colorIndex)
        {
            colorIndex=randInt;

        }
        else
        {
            nextIndex();
        }
        return randInt;

//        if (colorIndex==19)
//        {
//            colorIndex=0;
//        }
//        return colorIndex;
    }
    private void incrementIndex()
    {
        if (colorIndex<19)
        {
            colorIndex++;
        }
        else
        {
            colorIndex=0;
        }
    }


    private void populateEvents()
    {
        ObservableList<event> daysWithEvents = FXCollections.observableArrayList();
        ArrayList<LocalDate> dates = new ArrayList<>();
        ObservableList<event> orderedDaysWithEvents = FXCollections.observableArrayList();

        for (day Day:Data.getDayList())
        {
            if (Day.getEvent()!=null)
            {
                daysWithEvents.add(Day.getEvent());
                dates.add(Day.getDay());
            }
        }
        int size = dates.size();
        for (int i=0;i<size;i++)
        {
            LocalDate max= getMax(dates);
            orderedDaysWithEvents.add(daysWithEvents.get(dates.indexOf(max)));
            daysWithEvents.remove(daysWithEvents.get(dates.indexOf(max)));
            dates.remove(max);
        }
        eventTblDate.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<event, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<event, String> event) {
                return new ReadOnlyObjectWrapper<>(event.getValue().getDate().toString());
            }
        });
        eventTblClub.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<event, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<event, String> event) {
                return new ReadOnlyObjectWrapper<>(event.getValue().getHeldByClub().getClubName());
            }
        });
        eventTblEvent.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<event, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<event, String> event) {
                return new ReadOnlyObjectWrapper<>(event.getValue().getEventName());
            }
        });
        eventTblStart.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<event, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<event, String> event) {
                return new ReadOnlyObjectWrapper<>(event.getValue().getStartTime().toString());
            }
        });
        eventTblEnd.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<event, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<event, String> event) {
                return new ReadOnlyObjectWrapper<>(event.getValue().getEndTime().toString());
            }
        });
        eventTblDesc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<event, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<event, String> event) {
                return new ReadOnlyObjectWrapper<>(event.getValue().getDescription());
            }
        });
        eventTbl.setItems(orderedDaysWithEvents);
    }

    private LocalDate getMax(ArrayList<LocalDate> date)
    {
        LocalDate max = date.get(0);
        for (LocalDate D:date)
        {
            if (D.isAfter(max))
            {
                max =D;
            }
        }
        return max;
    }

    private getText getEventScheduleInputs()
    {
        boolean completed = true;
        String date=null;
        String name=null;
        String starHrs =null;
        String startMin=null;
        String endHrs = null;
        String endMin=null;
        String Desc = null;
        String venue=null;

        if (eventDatePick.getValue()==null)
        {
            completed=false;
            eventDatePick.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else
        {
            eventDatePick.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
            date = eventDatePick.getValue().toString();
        }
        if (eventStartHrs.getValue()==null)
        {
            completed=false;
            eventStartHrs.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else
        {
            starHrs= eventStartHrs.getValue();
            eventStartHrs.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
        }
        if (eventEndHrs.getValue()==null)
        {
            completed=false;
            eventEndHrs.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else
        {
            endHrs= eventEndHrs.getValue();
            eventEndHrs.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
        }
        if (eventStartMin.getValue()==null)
        {
            completed=false;
            eventStartMin.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else
        {
            startMin= eventStartMin.getValue();
            eventStartMin.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
        }
        if (eventEndMin.getValue()==null)
        {
            completed=false;
            eventEndMin.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else
        {
            endMin= eventEndMin.getValue();
            eventEndMin.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
        }
        if (eventTxtDesc.getText().isEmpty())
        {
            completed=false;
            eventTxtDesc.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else
        {
            Desc= eventTxtName.getText();
            eventTxtDesc.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
        }
        if (eventTxtVenue.getText().isEmpty())
        {
            eventTxtVenue.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            completed=false;

        }
        else
        {
            eventTxtVenue.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
            venue = eventTxtVenue.getText();
        }
        ArrayList<String> inputs = new ArrayList<>();
        inputs.add(name);
        inputs.add(date);
        inputs.add(starHrs);
        inputs.add(startMin);
        inputs.add(endHrs);
        inputs.add(endMin);
        inputs.add(Desc);
        inputs.add(venue);
        return new getText(completed,inputs);

    }
    private getText getActivityScheduleInputs()
    {
        String date=null;
        String startHrs=null;
        String startMin=null;
        String endHrs=null;
        String endMin=null;
        String desc=null;
        String venue =null;
        boolean completed = true;
        if (ActivitiesDatePicker.getValue()==null)
        {
            completed=false;
            ActivitiesDatePicker.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else
        {
            ActivitiesDatePicker.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
            date = ActivitiesDatePicker.getValue().toString();
        }
        if (activitiesStarHrs.getValue()==null)
        {
            completed=false;
            activitiesStarHrs.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else
        {
            startHrs= activitiesStarHrs.getValue();
            activitiesStarHrs.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
        }
        if (activitiesEndHrs.getValue()==null)
        {
            completed=false;
            activitiesEndHrs.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else
        {
            endHrs =activitiesEndHrs.getValue();
            activitiesEndHrs.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
        }
        if (activitiesStartMin.getValue()==null)
        {
            completed=false;
            activitiesStartMin.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else
        {
            startMin= activitiesStartMin.getValue();
            activitiesStartMin.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
        }
        if (activitiesEndMin.getValue()==null)
        {
            completed=false;
            activitiesEndMin.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else
        {
            endMin= activitiesEndMin.getValue();
            activitiesEndMin.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
        }
        if (activitiesTxtDesc.getText().isEmpty())
        {
            completed=false;
            activitiesTxtDesc.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else
        {
            desc= activitiesTxtDesc.getText();
            activitiesTxtDesc.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
        }
        if (activitiesChoiceVenue.getValue()==null)
        {
            completed=false;
            activitiesChoiceVenue.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else
        {
            activitiesChoiceVenue.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
            venue = activitiesChoiceVenue.getValue();
        }
        String start = startHrs+endHrs;
        String end = endHrs+endMin;
        if (start.equals(end)||Integer.parseInt(end)<Integer.parseInt(start))
        {
            completed =false;
            activitiesStarHrs.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            activitiesStartMin.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            activitiesEndHrs.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            activitiesEndMin.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");

        }
        else
        {
            activitiesStarHrs.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
            activitiesStartMin.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
            activitiesEndHrs.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
            activitiesEndMin.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
        }
        ArrayList<String> inputs = new ArrayList<>();

        inputs.add(date);
        inputs.add(startHrs);
        inputs.add(startMin);
        inputs.add(endHrs);
        inputs.add(endMin);
        inputs.add(date);
        inputs.add(desc);
        inputs.add(venue);
        return new getText(completed,inputs);


    }
    public void onActivityScheduleClick(ActionEvent event) throws SQLException {
        getText input = getActivityScheduleInputs();
        if (input.complete)
        {
            ArrayList<String> inputs = input.inputs;
            LocalTime start = LocalTime.parse(inputs.get(1)+":"+inputs.get(2)+":00");
            LocalTime end = LocalTime.parse(inputs.get(3)+":"+inputs.get(4)+":00");
            LocalDate date = LocalDate.parse(inputs.get(0));
            ArrayList<LocalTime> times = new ArrayList<>();
            LocalTime begin = LocalTime.parse("06:00:00");
            String venue = inputs.get(7);
            times.add(begin);
            for (int i=0;i<24;i++)
            {
                LocalTime time = begin.plus(30,ChronoUnit.MINUTES);
                times.add(time);
//                System.out.println(time.toString());
                begin=time;
            }
            day currentDay = null;
            boolean hasDay=false;
            for (day D:Data.getDayList())
            {
                if (D.getDay().equals(date))
                {
                    hasDay=true;
                    currentDay=D;
                    break;
                }
            }
            boolean isOccupied = isOccupied(start,end,currentDay,venue);
            if (!isOccupied)
            {
                if (!hasDay)
                {
                    currentDay = new day(date,Integer.parseInt(Data.getNextId("days")),24);
                }
                Data.incrementMeetingID();
                int id = Data.getmeetingID();
                int beginIndex = times.indexOf(start);
                int endIndex = times.indexOf(end);
                ArrayList<meeting> meetingList = new ArrayList<>();
                if (venue.equals("1"))
                {
                    meeting M = new meeting(id,date,teacher.getInCharge(),venue,beginIndex);
                    M.setTotalSlots(endIndex-beginIndex);
                    M.setStart(start);
                    M.setEnd(end);
                    M.code=colorHexList.get(nextIndex());
                    currentDay.getSlots().get(beginIndex).setVenue1(M);
                    for (int i=beginIndex;i<endIndex;i++)
                    {
                        currentDay.getVenue1().set(i,1);
                    }
                    incrementIndex();
                }
                else if (venue.equals("2"))
                {
//                        for (int i=beginIndex;i<endIndex;i++)
//                        {
//                            meeting M = new meeting(id,date,teacher.getInCharge(),venue,i);
//
//                            M.code=colorHexList.get(nextIndex());
//
//
//                            currentDay.getSlots().get(i).setVenue2(M);
//
//                        }
                    meeting M = new meeting(id,date,teacher.getInCharge(),venue,beginIndex);
                    M.setStart(start);
                    M.setEnd(end);
                    M.setTotalSlots(endIndex-beginIndex);
                    M.code=colorHexList.get(nextIndex());
                    currentDay.getSlots().get(beginIndex).setVenue2(M);
                    for (int i=beginIndex;i<endIndex;i++)
                    {
                        currentDay.getVenue2().set(i,1);
                    }

                    incrementIndex();
                }
                else
                {
//                        for (int i=beginIndex;i<endIndex;i++)
//                        {
//                            meeting M = new meeting(id,date,teacher.getInCharge(),venue,i);
//
//                            M.code=colorHexList.get(nextIndex());
//
//                            currentDay.getSlots().get(i).setVenue3(M);
//
//                        }
//                        incrementIndex();
                    meeting M = new meeting(id,date,teacher.getInCharge(),venue,beginIndex);
                    M.setTotalSlots(endIndex-beginIndex);
                    M.setStart(start);
                    M.setEnd(end);
                    M.code=colorHexList.get(nextIndex());
                    currentDay.getSlots().get(beginIndex).setVenue3(M);
                    for (int i=beginIndex;i<endIndex;i++)
                    {
                        currentDay.getVenue3().set(i,1);
                    }
                    incrementIndex();
                }
                if (hasDay)
                {
                    Data.updateDay(currentDay);
                }
                else
                {
                    Data.addDay(currentDay);
                }



            }
            else
            {
                warnings.register("timeslot is not available");
            }
            populateActivities();



        }

    }
    private boolean isOccupied(LocalTime start,LocalTime end,day Day,String venue)
    {
        if (Day==null)
        {
            return false;
        }
        ArrayList<LocalTime> times = new ArrayList<>();
        LocalTime begin = LocalTime.parse("06:00:00");
        times.add(begin);
        boolean isOccupied = false;
        for (int i=0;i<24;i++)
        {
            LocalTime time = begin.plus(30,ChronoUnit.MINUTES);
            times.add(time);
            begin=time;

        }
        int beginIndex = times.indexOf(start);
        int endIndex = times.indexOf(end);

        if (venue.equals("1"))
        {
//            if (Day.getSlots().get(beginIndex).getVenue1()!=null||Day.getSlots().get(endIndex).getVenue1()!=null)
//            {
//                isOccupied = true;
//            }
            if (Day.getVenue1()==null)
            {
                return false;
            }
            else
            {
                boolean occupied =false;
                for (int i=beginIndex;i<endIndex;i++)
                {
                    if (Day.getVenue1().get(i).equals(1))
                    {
                        occupied=true;
                    }
                }
                return occupied;
            }
        }
        else if (venue.equals("2"))
        {
//            if (Day.getSlots().get(beginIndex).getVenue2()!=null||Day.getSlots().get(endIndex).getVenue2()!=null)
//            {
//                isOccupied = true;
//            }
            if (Day.getVenue2()==null)
            {
                return false;
            }
            else
            {
                boolean occupied =false;
                for (int i=beginIndex;i<endIndex;i++)
                {
                    if (Day.getVenue2().get(i).equals(1))
                    {
                        occupied=true;
                    }
                }
                return occupied;
            }
        }
        else
        {
//            if (Day.getSlots().get(beginIndex).getVenue3()!=null||Day.getSlots().get(endIndex).getVenue3()!=null)
//            {
//                isOccupied = true;
//            }
            if (Day.getVenue3()==null)
            {
                return false;
            }
            else
            {
                boolean occupied =false;
                for (int i=beginIndex;i<endIndex;i++)
                {
                    if (Day.getVenue3().get(i).equals(1))
                    {
                        occupied=true;
                    }
                }
                return occupied;
            }

        }


    }

    public void onActivitiesDateSelect(ActionEvent event)
    {
        focus = ActivitiesDatePicker.getValue();
        populateActivities();
    }

    public void onEventsDateSelect(ActionEvent event)
    {
        focus = eventDatePick.getValue();
        populateEvents();
    }
    public getText getprofileinputs(){
        String firstname= null;
        String surname = null;
        String nic = null;
        String dob = null;
        String gender = null;
        String email = null;
        String password = null;

        Boolean completed = true;
        if(profFname.getText().isEmpty()) {
            completed=false;
            profFname.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }else {
            profFname.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
            firstname = profFname.getText();
        }
        if(profSname.getText().isEmpty()) {
            completed=false;
            profSname.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        } else {
            profSname.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
            surname = profSname.getText();
        }
        if(profNic.getText().isEmpty()) {
            completed=false;
            profNic.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        } else {
            profNic.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
            nic = profNic.getText();
        }
        if(profDob.getValue()==null) {
            completed=false;
            profDob.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        } else {
            profDob.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
            dob = profDob.getValue().toString();
        }
        if(profGender.getValue()==null) {
            completed=false;
            profGender.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        } else {
            profGender.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
            gender = profGender.getValue().toString();
        }
        String emailRegx="^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        if(profEmail.getText().isEmpty()||!profEmail.getText().matches(emailRegx)) {
            completed=false;
            profEmail.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        } else {
            profEmail.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
            email = profEmail.getText();
        }
        if (!(profileTxtPwd.getText().isEmpty()&&profileTxtRePwd.getText().isEmpty()))
        {

            if (!Objects.equals(profileTxtPwd.getText(), profileTxtRePwd.getText()))
            {
                completed=false;
                profileTxtPwd.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
                profileTxtRePwd.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            }
            else
            {
                password = profileTxtPwd.getText();
                profileTxtPwd.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
                profileTxtRePwd.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
            }
        }
        if (profileTxtPwd.getLength() < 6 || profileTxtPwd.getLength() > 10)
        {
            completed=false;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Password must be between 6-10 Characters");

            alert.showAndWait(); // Display the alert and wait for user interaction
        }
        else
        {
            completed=true;
        }

        ArrayList<String> inputString = new ArrayList<>();
        inputString.add(profTeachID.getText());
        inputString.add(firstname);
        inputString.add(surname);
        inputString.add(nic);
        inputString.add(dob);
        inputString.add(gender);
        inputString.add(email);
        inputString.add(password);
        getText inputs = new getText(completed,inputString);
        return inputs;
    }

    public void onSaveClick(ActionEvent event) throws SQLException
    {
        getText input = getprofileinputs();
        if (input.complete)
        {
            Teacher updatedTeacher=null;
            ArrayList<String> inputs = input.inputs;
            if (inputs.get(7)==null)
            {
                updatedTeacher = new Teacher(inputs.get(0),inputs.get(1),inputs.get(2),inputs.get(3),inputs.get(4),inputs.get(5),inputs.get(6),teacher.getPassword());
            }
            else
            {
                updatedTeacher = new Teacher(inputs.get(0),inputs.get(1),inputs.get(2),inputs.get(3),inputs.get(4),inputs.get(5),inputs.get(6),inputs.get(7));

            }

            Data.updateTEacher(updatedTeacher);
            for (club Club:Data.getClubList())
            {
                if (Club.getClubId().equals(updatedTeacher.getInCharge().getClubId()))
                {
                    Club.setInCharge(updatedTeacher);
                }
            }


        }
    }
    private void populateClubMembers(ObservableList<Student> studentlist,boolean search)
    {
        ObservableList<Student> studentsList = FXCollections.observableArrayList();
        if (!search)
        {
            for (club Club : Data.getClubList()) {
                if (teacher.getInCharge()!=null)
                {
                    if (Club.getClubId().equals(teacher.getInCharge().getClubId())) {
                        studentsList.addAll(Club.getMembers());
                    }
                }
            }
        }

        viewId.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Student, String> student)
            {
                return new ReadOnlyObjectWrapper<>(student.getValue().getId());
            }
        });
        viewFirstN.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Student, String> student)
            {
                return new ReadOnlyObjectWrapper<>(student.getValue().getFirstName());
            }
        });
        fviewLastN.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Student, String> student)
            {
                return new ReadOnlyObjectWrapper<>(student.getValue().getSurName());
            }
        });
        viewClubs.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Student, String> student)
            {
                ArrayList<club> clubs = student.getValue().getClubs();
                StringBuilder build = new StringBuilder();
                for (club C :clubs)
                {
                    build.append(C.getClubName());
                    build.append(",");
                }

                return new ReadOnlyObjectWrapper<>(build.toString());
            }
        });
        viewEmail.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Student, String> student)
            {
                return new ReadOnlyObjectWrapper<>(student.getValue().getEmail());
            }
        });
        if (!search)
        {
            tableViewStudents.setItems(studentsList);

        }
        else
        {
            tableViewStudents.setItems(studentlist);
        }
        tableViewStudents.getSelectionModel().selectedItemProperty().addListener((ObservableValue,Student,student)->
        {
            selectedForRemove = student;
            seletedForBoard=student;
            if (boardMemberSelect==0)
            {
                txtPres.setText(seletedForBoard.getFullName());
            }
            else if (boardMemberSelect==1)
            {
                txtVicePres.setText(seletedForBoard.getFullName());
            }
            else if (boardMemberSelect==2)
            {
                txtSec.setText(seletedForBoard.getFullName());
            }
            else if (boardMemberSelect==3)
            {
                txtViceSec.setText(seletedForBoard.getFullName());
            }
            else if (boardMemberSelect==4)
            {
                txtTre.setText(seletedForBoard.getFullName());
            }
            else if (boardMemberSelect==5)
            {
                txtViceTre.setText(seletedForBoard.getFullName());
            }
        });
    }


    public void onRemoveClick(ActionEvent event) throws SQLException {
        if (selectedForRemove!=null) {
            for (club Club : Data.getClubList()) {
                if (Club.getClubId().equals(teacher.getInCharge().getClubId())) {
                    club updateClub = Club;
                    for (Student student : updateClub.getMembers()) {
                        if (student.getId().equals(selectedForRemove.getId()))
                        {
                            updateClub.getMembers().remove(student);
                            Data.updateClub(updateClub);
                            break;
                        }
                    }
                    break;
                }
            }
            for (Student St:Data.getStudentList())
            {
                if (St.getId().equals(selectedForRemove.getId()))
                {
                    for (club Club:St.getClubs())
                    {
                        if (Club.getClubId().equals(teacher.getInCharge().getClubId()))
                        {
                            St.getClubs().remove(Club);
                            Data.updateStudent(St);
                            break;

                        }
                    }
                    break;
                }
            }
        }
        populateClubMembers(null,false);
    }

    public void onPwdMousePressed(MouseEvent mouseEvent)
    {
        seePwd.setText(profileTxtPwd.getText());
        seeRePwd.setText(profileTxtRePwd.getText());
        seePwd.setVisible(true);
        seeRePwd.setVisible(true);
        profileTxtPwd.setVisible(false);
        profileTxtRePwd.setVisible(false);

    }

    public void onPwdMouseReleased(MouseEvent mouseEvent)
    {
        seePwd.setVisible(false);
        seeRePwd.setVisible(false);
        profileTxtPwd.setVisible(true);
        profileTxtRePwd.setVisible(true);
    }

    public void onEndMinClick(MouseEvent mouseEvent)
    {
        if (activitiesEndHrs.getValue().equals("18"))
        {
            activitiesEndMin.setItems(min1);
        }

    }

    public void onEndHrsClck(MouseEvent mouseEvent)
    {
        if (activitiesEndMin.getValue().equals("30"))
        {
            activitiesEndHrs.setItems(hrs1);
        }
    }

    public void onAttendanceDatePick(ActionEvent event)
    {
        focus= attendanceDateMeeting.getValue();
        populateAttendanceMeeting();
    }
    @FXML
    public void onEvent(Event event)

    {
        int selected= checkAttendanceTab.getSelectionModel().getSelectedIndex();
        int tab =-1;
        if (selected==0&&selected!=tab)
        {
            focus = LocalDate.now();
            populateAttendanceMeeting();
            tab=0;
        } else if (selected==1&&selected!=tab)
        {
            populateAttendanceActivity();

            tab=1;

        } else if (selected==2&&selected!=tab)
        {

            populateAttendanceEvent();
            tab=2;

        }
    }
    private void populateAttendanceEvent()
    {
        AtomicInteger click= new AtomicInteger();
        ObservableList<event> events = FXCollections.observableArrayList();
        for (day Day:Data.getDayList())
        {
            if (Day.getEvent()!=null)
            {
                events.add(Day.getEvent());
            }
        }
        eventDate.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<event, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<event, String> eventStringCellDataFeatures) {
                return new ReadOnlyObjectWrapper<>(eventStringCellDataFeatures.getValue().getDate().toString());
            }
        });
        eventHeldBy.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<event, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<event, String> eventStringCellDataFeatures) {
                return new ReadOnlyObjectWrapper<>(eventStringCellDataFeatures.getValue().getHeldByClub().getClubName());
            }
        });
        eventName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<event, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<event, String> eventStringCellDataFeatures) {
                return new ReadOnlyObjectWrapper<>(eventStringCellDataFeatures.getValue().getEventName());
            }
        });
        eventStart.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<event, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<event, String> eventStringCellDataFeatures) {
                return new ReadOnlyObjectWrapper<>(eventStringCellDataFeatures.getValue().getStartTime().toString());
            }
        });
        eventEnd.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<event, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<event, String> eventStringCellDataFeatures) {
                return new ReadOnlyObjectWrapper<>(eventStringCellDataFeatures.getValue().getEndTime().toString());
            }
        });
        eventDesc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<event, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<event, String> eventStringCellDataFeatures) {
                return new ReadOnlyObjectWrapper<>(eventStringCellDataFeatures.getValue().getDescription());
            }
        });
        attendanceTblEvents.getSelectionModel().selectedItemProperty().addListener((ObservableValue,event,event1)->
        {
            attendanceEvent = event1;
        });
        attendanceTblEvents.setOnMouseClicked((MouseEvent event)->
        {
            if (event.getClickCount()==2)
            {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(teacherController.class.getResource("showAttendance.fxml"));
                    fxmlLoader.setControllerFactory(showAttendance -> new showAttendanceController(null,"3",attendanceEvent));
                    Parent parent = fxmlLoader.load();
                    Scene scene = new Scene(parent, 620, 470);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.showAndWait();
                }
                catch (IOException e)
                {

                }
            }

        });
//        attendanceTblEvents.setOnMouseClicked((MouseEvent event)->
//        {
//            if (event.getClickCount()==2)
//            {
//                click.set(2);
//            }
//        });
//        attendanceTblEvents.getSelectionModel().selectedItemProperty().addListener((ObservableValue,event,Event)->
//        {
//            if (click.get()==2)
//            {
//                attendanceEvent = Event;
//            }
//            click.set(0);
//        });
        attendanceTblEvents.setItems(events);
    }

    public void onEventsDayClicked(ActionEvent event) {
    }
    public void onChangePress(ActionEvent event)
    {
        boolean isAlready=false;
        if (seletedForBoard!=null)
        {
            for (Student student:teacher.getInCharge().getBoardMembers())
            {
                if (student!=null)
                {
                    if (seletedForBoard.getId().equals(student.getId()))
                    {
                        isAlready=true;
                    }
                }

            }
        }
        if (!isAlready)
        {
            teacher.getInCharge().getBoardMembers().set(boardMemberSelect,seletedForBoard);
            try {
                Data.updateClub(teacher.getInCharge());
            }
            catch (SQLException e)
            {
                warnings.SqlWarning();
            }
            setBoarMembers();


        }
        else
        {
            warnings.register("selected Student is Already a board Member ");
        }
    }

    public void onPresClicked(MouseEvent mouseEvent)
    {
        boardMemberSelect=0;
        disableOtherBoarders(boardMemberSelect);
        txtPres.setStyle("-fx-border-color: black ; -fx-border-width: 2px ;");
    }

    public void onVicePreClicked(MouseEvent mouseEvent)
    {
        boardMemberSelect=1;
        disableOtherBoarders(boardMemberSelect);
        txtVicePres.setStyle("-fx-border-color: black ; -fx-border-width: 2px ;");
    }

    public void onSecClicked(MouseEvent mouseEvent)
    {
        boardMemberSelect=2;
        disableOtherBoarders(boardMemberSelect);
        txtSec.setStyle("-fx-border-color: black ; -fx-border-width: 2px ;");
    }

    public void onTreClicked(MouseEvent mouseEvent)
    {
        boardMemberSelect=4;
        disableOtherBoarders(boardMemberSelect);
        txtTre.setStyle("-fx-border-color: black ; -fx-border-width: 2px ;");
    }

    public void onViceSecClicked(MouseEvent mouseEvent)
    {
        boardMemberSelect=3;
        disableOtherBoarders(boardMemberSelect);
        txtViceSec.setStyle("-fx-border-color: black ; -fx-border-width: 2px ;");
    }

    public void onViceTreClicked(MouseEvent mouseEvent)
    {
        boardMemberSelect=5;
        disableOtherBoarders(boardMemberSelect);
        txtViceTre.setStyle("-fx-border-color: black ; -fx-border-width: 2px ;");
    }
    private void disableOtherBoarders(int active)
    {
        if (active!=0)
        {
            txtPres.setStyle("-fx-border-color: black ; -fx-border-width: 0px ;");
        }
        if (active!=1)
        {
            txtVicePres.setStyle("-fx-border-color: black ; -fx-border-width: 0px ;");
        }
        if (active!=2)
        {
            txtSec.setStyle("-fx-border-color: black ; -fx-border-width: 0px ;");
        }
        if (active!=3)
        {
            txtViceSec.setStyle("-fx-border-color: black ; -fx-border-width: 0px ;");
        }
        if (active!=4)
        {
            txtTre.setStyle("-fx-border-color: black ; -fx-border-width: 0px ;");
        }
        if (active!=5)
        {
            txtViceTre.setStyle("-fx-border-color: black ; -fx-border-width: 0px ;");
        }
    }

    public void onpres(KeyEvent event)
    {

        ObservableList<Student> students = FXCollections.observableArrayList();
        for (club Club:Data.getClubList())
        {
            if (Club.getClubId().equals(teacher.getInCharge().getClubId()))
            {
                for (Student student:Club.getMembers())
                {
                    if (student.getFirstName().startsWith(txtPres.getText())||student.getSurName().startsWith(txtPres.getText()))
                    {
                        students.add(student);
                    }
                }
            }
        }

        populateClubMembers(students,true);
    }

    public void onVicePres(KeyEvent event)
    {
        ObservableList<Student> students = FXCollections.observableArrayList();
        for (club Club:Data.getClubList())
        {
            if (Club.getClubId().equals(teacher.getInCharge().getClubId()))
            {
                for (Student student:Club.getMembers())
                {
                    if (student.getFirstName().startsWith(txtViceTre.getText())||student.getSurName().startsWith(txtViceTre.getText()))
                    {
                        students.add(student);
                    }
                }
            }
        }

        populateClubMembers(students,true);

    }

    public void onSec(KeyEvent event)
    {
        ObservableList<Student> students = FXCollections.observableArrayList();
        for (club Club:Data.getClubList())
        {
            if (Club.getClubId().equals(teacher.getInCharge().getClubId()))
            {
                for (Student student:Club.getMembers())
                {
                    if (student.getFirstName().startsWith(txtSec.getText())||student.getSurName().startsWith(txtSec.getText()))
                    {
                        students.add(student);
                    }
                }
            }
        }

        populateClubMembers(students,true);

    }

    public void onTre(KeyEvent event) {
        ObservableList<Student> students = FXCollections.observableArrayList();
        for (club Club:Data.getClubList())
        {
            if (Club.getClubId().equals(teacher.getInCharge().getClubId()))
            {
                for (Student student:Club.getMembers())
                {
                    if (student.getFirstName().startsWith(txtTre.getText())||student.getSurName().startsWith(txtTre.getText()))
                    {
                        students.add(student);
                    }
                }
            }
        }

        populateClubMembers(students,true);
    }

    public void onViceSec(KeyEvent event)
    {
        ObservableList<Student> students = FXCollections.observableArrayList();
        for (club Club:Data.getClubList())
        {
            if (Club.getClubId().equals(teacher.getInCharge().getClubId()))
            {
                for (Student student:Club.getMembers())
                {
                    if (student.getFirstName().startsWith(txtViceSec.getText())||student.getSurName().startsWith(txtViceSec.getText()))
                    {
                        students.add(student);
                    }
                }
            }
        }

        populateClubMembers(students,true);
    }
    public void onViceTre(KeyEvent event)

    {
        ObservableList<Student> students = FXCollections.observableArrayList();
        for (club Club:Data.getClubList())
        {
            if (Club.getClubId().equals(teacher.getInCharge().getClubId()))
            {
                for (Student student:Club.getMembers())
                {
                    if (student.getFirstName().startsWith(txtViceTre.getText())||student.getSurName().startsWith(txtViceTre.getText()))
                    {
                        students.add(student);
                    }
                }
            }
        }

        populateClubMembers(students,true);
    }
    private void setBoarMembers()
    {
        if (teacher.getInCharge().getBoardMembers().get(0)!=null)
        {
            txtPres.setText(teacher.getInCharge().getBoardMembers().get(0).getFullName());
        }
        else
        {
            txtPres.setText("_");
        }
        if (teacher.getInCharge().getBoardMembers().get(1)!=null)
        {
            txtVicePres.setText(teacher.getInCharge().getBoardMembers().get(1).getFullName());
        }
        else
        {
            txtVicePres.setText("_");
        }
        if (teacher.getInCharge().getBoardMembers().get(2)!=null)
        {
            txtSec.setText(teacher.getInCharge().getBoardMembers().get(2).getFullName());
        }
        else
        {
            txtSec.setText("_");
        }
        if (teacher.getInCharge().getBoardMembers().get(3)!=null)
        {
            txtViceSec.setText(teacher.getInCharge().getBoardMembers().get(3).getFullName());
        }
        else
        {
            txtViceSec.setText("_");
        }
        if (teacher.getInCharge().getBoardMembers().get(4)!=null)
        {
            txtTre.setText(teacher.getInCharge().getBoardMembers().get(4).getFullName());
        }
        else
        {
            txtTre.setText("_");
        }
        if (teacher.getInCharge().getBoardMembers().get(5)!=null)
        {
            txtViceTre.setText(teacher.getInCharge().getBoardMembers().get(5).getFullName());
        }
        else
        {
            txtViceTre.setText("_");
        }
    }

    public void onMeetingRemove(ActionEvent event)
    {
        if (teacher.getInCharge()!=null)
        {
            if (removeMeeting != null&&removeMeeting.getClub().getInCharge().getId().equals(teacher.getId()))
            {
                for (day Day : Data.getDayList()) {
                    if (Day.getDay().equals(removeMeeting.getDay())) {
                        if (removeMeeting.getVenue().equals("1")) {
                            Day.getSlots().get(removeMeeting.getTimeSlot()).setVenue1(null);
                        } else {
                            Day.getSlots().get(removeMeeting.getTimeSlot()).setVenue2(null);
                        }
                        try {
                            Data.updateDay(Day);
                        }
                        catch (SQLException e)
                        {
                            warnings.SqlWarning();
                        }

                    }
                }
                populateSchedule();
            }
        }
    }

    public void onRemoveActivityClick(ActionEvent event)
    {
//        if (removeActivity!=null)
//        {
//            meeting remove=removeActivity.getValue();
//
//        }
    }
    UnaryOperator<TextFormatter.Change> filter = change -> {
        String newText = change.getControlNewText();
        if (newText.matches("[a-zA-Z]*")) {
            return change; // Allow the change
        }
        return null; // Reject the change
    };
}

