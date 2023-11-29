package com.example.activityclubmanagementsystem;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;


import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.UnaryOperator;

public class studentController implements Initializable {
    @FXML
    private TextField checkClb;
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
    private TableColumn<scheduleRow,String> meetingsMonday;
    @FXML
    private TableColumn<scheduleRow,meeting> meetingsMonday1;
    @FXML
    private TableColumn<scheduleRow,meeting> meetingsMonday2;
    @FXML
    private TableColumn<scheduleRow,meeting> meetingsTuesday;
    @FXML
    private TableColumn<scheduleRow,meeting> meetingsTuesday1;
    @FXML
    private TableColumn<scheduleRow,meeting> meetingsTuesday2;
    @FXML
    private TableColumn<scheduleRow,meeting> meetingsWednesday;
    @FXML
    private TableColumn<scheduleRow,meeting> meetingsWednesday1;
    @FXML
    private TableColumn<scheduleRow,meeting> meetingsWednesday2;
    @FXML
    private TableColumn<scheduleRow,meeting> meetingsThursday;
    @FXML
    private TableColumn<scheduleRow,meeting> meetingsThursday1;
    @FXML
    private TableColumn<scheduleRow,meeting> meetingsThursday2;
    @FXML
    private TableColumn<scheduleRow,meeting> meetingsFriday;
    @FXML
    private TableColumn<scheduleRow,meeting> meetingsFriday1;
    @FXML
    private TableColumn<scheduleRow,meeting> meetingsFriday2;
    @FXML
    private TableView<scheduleRow> studentTableMeetings;
    @FXML
    private TableView<club> tblClb;
    @FXML
    private TableColumn<club,String> joinClubId;
    @FXML
    private TableColumn<club,String> joinClubName;
    @FXML
    private TableColumn<club,String> joinTeacherId;
    @FXML
    private TableColumn<club,String> joinTeacherName;
    @FXML
    private TableColumn<club,String> joinClubLeader;
    @FXML
    private TableColumn<club,String> joinDescription;
    @FXML
    private TextField joinClbID;
    @FXML
    private TextField joinClbName;
    @FXML
    private Button joinBtn;
    @FXML
    private Button resetBtn;
    @FXML
    private TextField profileTxtID;
    @FXML
    private TextField profileTxtFirstN;
    @FXML
    private TextField profileTxtLastN;
    @FXML
    private TextField profileTxtNic;
    @FXML
    private DatePicker profileDateDob;
    @FXML
    private TextField profileTxtEmail;
    @FXML
    private Button profileBtnSave;
    @FXML
    private ChoiceBox<String> profileChoiceGender;
    @FXML
    private TableView<event> eventTable;
    @FXML
    private TableColumn<event,String> eventsTblDate;
    @FXML
    private TableColumn<event,String> eventTblClub;
    @FXML
    private TableColumn<event,String> eventTblName;
    @FXML
    private TableColumn<event,String> eventTblStart;
    @FXML
    private TableColumn<event,String> eventTblEnd;
    @FXML
    private TableColumn<event,String> eventTblDesc;
    @FXML
    private PasswordField profileTxtPwd;
    @FXML
    private PasswordField profileTxtRePwd;
    @FXML
    private TextField seePwd;
    @FXML
    private TextField seeRePwd;
    @FXML
    private Button profileBtnSee;
    @FXML
    private TableView<scheduleRow> activitiesTable;
    @FXML
    private TableColumn<scheduleRow,meeting> activitiesSaturday;
    @FXML
    private TableColumn<scheduleRow,meeting> activitiesSaturday1;
    @FXML
    private TableColumn<scheduleRow,meeting> activitiesSaturday2;
    @FXML
    private TableColumn<scheduleRow,meeting> activitiesSaturday3;
    @FXML
    private TableColumn<scheduleRow,meeting> activitiesSunday;
    @FXML
    private TableColumn<scheduleRow,meeting> activitiesSunday1;
    @FXML
    private TableColumn<scheduleRow,meeting> activitiesSunday2;
    @FXML
    private TableColumn<scheduleRow,meeting> activitiesSunday3;
    @FXML
    private TableView<club> tableViewClb;
    @FXML
    private TableColumn<club,String> viewTblClubId;
    @FXML
    private TableColumn<club,String> viewTblClubName;
    @FXML
    private TableColumn<club,String> viewTblTeacherId;
    @FXML
    private TableColumn<club,String> viewTblTeacherName;
    @FXML
    private TableColumn<club,String> viewTblPres;
    @FXML
    private TableColumn<club,String> viewTblDesc;

    private final  String studentID;
    private Student currentStudent;
    private club joinClub;
    LocalDate now = LocalDate.now();
    ArrayList<LocalTime> timeList = new ArrayList<>();
    //    private ObservableList<Student> studentList = FXCollections.observableArrayList(Data.getStudentList());
    private final ObservableList<day> dayList = FXCollections.observableArrayList(Data.getDayList());
    //   private final ObservableList<club> clubList = FXCollections.observableArrayList(Data.getClubList());

    private event selectedEvent;
    public studentController() throws IOException
    {
        this.studentID = Data.getUserId();
        //   this.studentID="S10";
        for (Student student:Data.getStudentList())
        {
            if(student.getId().equals(studentID))
            {
                currentStudent = student;
                break;
            }
        }
        if (now.getDayOfWeek().equals(DayOfWeek.SATURDAY))
        {
            now.plus(2,ChronoUnit.DAYS);
        }
        if (now.getDayOfWeek().equals(DayOfWeek.SUNDAY))
        {
            now.plus(1,ChronoUnit.DAYS);
        }

        Data.clearCSV();
        LocalTime time1_begin= LocalTime.parse("07:30:00");
        LocalTime time1_end= LocalTime.parse("08:05:00");
        LocalTime time2_begin= LocalTime.parse("08:05:00");
        LocalTime time2_end= LocalTime.parse("08:40:00");
        LocalTime time3_begin= LocalTime.parse("08:40:00");
        LocalTime time3_end= LocalTime.parse("09:15:00");
        LocalTime time4_begin= LocalTime.parse("09:15:00");
        LocalTime time4_end= LocalTime.parse("09:50:00");
        LocalTime time5_begin= LocalTime.parse("09:50:00");
        LocalTime time5_end= LocalTime.parse("10:25:00");
        LocalTime time6_begin= LocalTime.parse("10:25:00");
        LocalTime time6_end= LocalTime.parse("11:00:00");
        LocalTime time7_begin= LocalTime.parse("11:00:00");
        LocalTime time7_end= LocalTime.parse("11:45:00");
        LocalTime time8_begin= LocalTime.parse("11:45:00");
        LocalTime time8_end= LocalTime.parse("12:30:00");
        LocalTime time9_begin= LocalTime.parse("12:30:00");
        LocalTime time9_end= LocalTime.parse("13:15:00");

        timeList.add(time1_begin);
        timeList.add(time1_end);
        timeList.add(time2_begin);
        timeList.add(time2_end);
        timeList.add(time3_begin);
        timeList.add(time3_end);
        timeList.add(time4_begin);
        timeList.add(time4_end);
        timeList.add(time5_begin);
        timeList.add(time5_end);
        timeList.add(time6_begin);
        timeList.add(time6_end);
        timeList.add(time7_begin);
        timeList.add(time7_end);
        timeList.add(time8_begin);
        timeList.add(time8_end);
        timeList.add(time9_begin);
        timeList.add(time9_end);
        // System.out.println(currentStudent.toString());
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
    public void selectClick(ActionEvent actionEvent) {  //select pane for function
        if (actionEvent.getSource() == joinClbBtn) {
            joinClbPane.toFront();
        }
        if (actionEvent.getSource() == viewClbBtn) {
            viewClbPane.toFront();
            populateJoinedClubs();
        }
        if (actionEvent.getSource() == attendBtn) {
            attendPane.toFront();
        }
        if (actionEvent.getSource() == profileBtn) {
            profilePane.toFront();
        }
    }
    private void populateMeetings()
    {

//        for (day D:Data.getDayList())
//        {
//            if (D.getDay().isBefore(now))
//            {
//
//            }
//        }

        ArrayList<club> clubs = currentStudent.getClubs();
        ObservableList<scheduleRow> rows = FXCollections.observableArrayList();
        ObservableList<day> filteredDay = FXCollections.observableArrayList();
        ArrayList<LocalDate> days = new ArrayList<>();
        ArrayList<LocalDate> daysinWeek = getWeek();
        for(int i=0;i<5;i++)
        {
            days.add(daysinWeek.get(i));
        }
        for(int i =0;i<9;i++)
        {
            scheduleRow row = new scheduleRow(i);
            rows.add(row);
        }
        for (day Day:dayList)
        {
            if (days.contains(Day.getDay()))
            {

                filteredDay.add(Day);
            }
        }

        ArrayList<String> daysInWeek1= new ArrayList<>();
        daysInWeek1.add("MONDAY");
        daysInWeek1.add("TUESDAY");
        daysInWeek1.add("WEDNESDAY");
        daysInWeek1.add("THURSDAY");
        daysInWeek1.add("FRIDAY");

        for (day eachDay:filteredDay)
        {
            ArrayList<timeSlot> slots = eachDay.getSlots();
            for(int i=0;i<9;i++)
            {
                timeSlot slot = slots.get(i);
                if (slot.getVenue1()!=null)
                {
                    for (club Club:clubs)
                    {
                        if (Club.getClubId().equals(slot.getVenue1().getClub().getClubId()))
                        {

                            String date = slot.date.getDayOfWeek().toString();
                            int l = 2*daysInWeek1.indexOf(date);
                            scheduleRow row = rows.get(i);
                            row.list.set(l,slot.getVenue1());
                        }
                    }
                }
                if (slot.getVenue2()!=null)
                {
                    for (club Club:clubs)
                    {
                        if (Club.getClubId().equals(slot.getVenue2().getClub().getClubId()))
                        {
                            String date = slot.date.getDayOfWeek().toString();
                            int l = 2*daysInWeek1.indexOf(date)+1;
                            scheduleRow row = rows.get(i);
                            row.list.set(l,slot.getVenue2());
                        }
                    }
                }
            }
            meetingsMonday1.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<meeting>(cellData.getValue().list.get(0)));
            meetingsMonday2.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<meeting>(cellData.getValue().list.get(1)));
            meetingsTuesday1.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<meeting>(cellData.getValue().list.get(2)));
            meetingsTuesday2.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<meeting>(cellData.getValue().list.get(3)));
            meetingsWednesday1.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<meeting>(cellData.getValue().list.get(4)));
            meetingsWednesday2.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<meeting>(cellData.getValue().list.get(5)));
            meetingsThursday1.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<meeting>(cellData.getValue().list.get(6)));
            meetingsThursday2.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<meeting>(cellData.getValue().list.get(7)));
            meetingsFriday1.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<meeting>(cellData.getValue().list.get(8)));
            meetingsFriday2.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<meeting>(cellData.getValue().list.get(9)));
            meetingsMonday1.setCellFactory(coloumn ->
            {
                return new TableCell<scheduleRow,meeting>()
                {
                    @Override
                    protected void updateItem(meeting meeting,boolean empty)
                    {
                        super.updateItem(meeting,empty);
                        if (meeting==null||empty)
                        {
                            setText("_");
                        }
                        else
                        {
                            setText(meeting.getClubName());
                        }
                    }
                };


            });
            meetingsMonday2.setCellFactory(coloumn ->
            {
                return new TableCell<scheduleRow,meeting>()
                {
                    @Override
                    protected void updateItem(meeting meeting,boolean empty)
                    {
                        super.updateItem(meeting,empty);
                        if (meeting==null||empty)
                        {
                            setText("_");
                        }
                        else
                        {
                            setText(meeting.getClubName());
                        }
                    }
                };


            });
            meetingsTuesday1.setCellFactory(coloumn ->
            {
                return new TableCell<scheduleRow,meeting>()
                {
                    @Override
                    protected void updateItem(meeting meeting,boolean empty)
                    {
                        super.updateItem(meeting,empty);
                        if (meeting==null||empty)
                        {
                            setText("_");
                        }
                        else
                        {
                            setText(meeting.getClubName());
                        }
                    }
                };


            });
            meetingsTuesday2.setCellFactory(coloumn ->
            {
                return new TableCell<scheduleRow,meeting>()
                {
                    @Override
                    protected void updateItem(meeting meeting,boolean empty)
                    {
                        super.updateItem(meeting,empty);
                        if (meeting==null||empty)
                        {
                            setText("_");
                        }
                        else
                        {
                            setText(meeting.getClubName());
                        }
                    }
                };


            });
            meetingsWednesday1.setCellFactory(coloumn ->
            {
                return new TableCell<scheduleRow,meeting>()
                {
                    @Override
                    protected void updateItem(meeting meeting,boolean empty)
                    {
                        super.updateItem(meeting,empty);
                        if (meeting==null||empty)
                        {
                            setText("_");
                        }
                        else
                        {
                            setText(meeting.getClubName());
                        }
                    }
                };


            });
            meetingsWednesday2.setCellFactory(coloumn ->
            {
                return new TableCell<scheduleRow,meeting>()
                {
                    @Override
                    protected void updateItem(meeting meeting,boolean empty)
                    {
                        super.updateItem(meeting,empty);
                        if (meeting==null||empty)
                        {
                            setText("_");
                        }
                        else
                        {
                            setText(meeting.getClubName());
                        }
                    }
                };


            });
            meetingsThursday1.setCellFactory(coloumn ->
            {
                return new TableCell<scheduleRow,meeting>()
                {
                    @Override
                    protected void updateItem(meeting meeting,boolean empty)
                    {
                        super.updateItem(meeting,empty);
                        if (meeting==null||empty)
                        {
                            setText("_");
                        }
                        else
                        {
                            setText(meeting.getClubName());
                        }
                    }
                };


            });
            meetingsThursday2.setCellFactory(coloumn ->
            {
                return new TableCell<scheduleRow,meeting>()
                {
                    @Override
                    protected void updateItem(meeting meeting,boolean empty)
                    {
                        super.updateItem(meeting,empty);
                        if (meeting==null||empty)
                        {
                            setText("_");
                        }
                        else
                        {
                            setText(meeting.getClubName());
                        }
                    }
                };


            });
            meetingsFriday1.setCellFactory(coloumn ->
            {
                return new TableCell<scheduleRow,meeting>()
                {
                    @Override
                    protected void updateItem(meeting meeting,boolean empty)
                    {
                        super.updateItem(meeting,empty);
                        if (meeting==null||empty)
                        {
                            setText("_");
                        }
                        else
                        {
                            setText(meeting.getClubName());
                        }
                    }
                };


            });
            meetingsFriday2.setCellFactory(coloumn ->
            {
                return new TableCell<scheduleRow,meeting>()
                {
                    @Override
                    protected void updateItem(meeting meeting,boolean empty)
                    {
                        super.updateItem(meeting,empty);
                        if (meeting==null||empty)
                        {
                            setText("_");
                        }
                        else
                        {
                            setText(meeting.getClubName());
                        }
                    }
                };


            });





            studentTableMeetings.setItems(rows);
            studentTableMeetings.getSelectionModel().selectedItemProperty().addListener((ObservableValue,scheduleRow,SR )->
            {

                if(studentTableMeetings.getSelectionModel().getSelectedItem()!=null)
                {

                    TableView.TableViewSelectionModel<scheduleRow> model = studentTableMeetings.getSelectionModel();
                    ObservableList selected = model.getSelectedCells();
                    TablePosition position=(TablePosition) selected.get(0);
                    try {
                        attendMeeting(model.getSelectedIndex(),position.getColumn(),position.getTableColumn().getId());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }

    }
    private void attendMeeting(int row,int coloumn,String coloumnid) throws IOException {
        // now  =LocalDate.parse("2023-11-24");// cooment for final keep for viva
        club meetingClub = null;
        LocalTime nowTime = LocalTime.now();
        //LocalTime nowTime=timeList.get(4).plus(10,ChronoUnit.MINUTES);
        meeting currentMeeting;
        String venue;
        day currentDay = null;
        LocalDate date = getSelectedDate(coloumnid);
//            System.out.println(date);
        // date = LocalDate.parse("2023-11-23");//comment for final keep it for viva
        for (day Day:Data.getDayList())
        {
//                System.out.println(Day.getDay().toString());
            if (Day.getDay().equals(date))
            {
                currentDay=Day;
                break;
            }
        }
        if (currentDay!=null)
        {
            if (coloumnid.endsWith("1")) {
                venue = "1";
                currentMeeting = currentDay.getSlots().get(row).getVenue1();
            } else {
                venue = "2";
                currentMeeting = currentDay.getSlots().get(row).getVenue2();
            }
            boolean isAMember=false;
            if (currentMeeting!=null) {
                for (club C : Data.getClubList()) {
                    if (C.getClubId().equals(currentMeeting.getClub().getClubId())) {
                        for (Student St : C.getMembers()) {
                            if (St.getId().equals(currentStudent.getId())) {
                                isAMember = true;
                            }
                        }
                    }
                }
            }


            if (nowTime.isAfter(timeList.get(row * 2)) && nowTime.isBefore(timeList.get(row * 2 + 1)) && currentDay.getDay().equals(now)&&isAMember)
            {
                FXMLLoader fxmlLoader = new FXMLLoader(teacherController.class.getResource("markAttendance.fxml"));
                fxmlLoader.setControllerFactory(attendenceController -> new markAttendanceController(currentMeeting, currentStudent));

                Parent parent = fxmlLoader.load();
                Scene scene = new Scene(parent, 700, 470);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.showAndWait();

            }
            else
            {
                warnings.attendanceMeetingWarning(currentMeeting,row);
            }
//                if (isAMember)
//                {
//                    FXMLLoader fxmlLoader = new FXMLLoader(teacherController.class.getResource("markAttendance.fxml"));
//                    fxmlLoader.setControllerFactory(attendenceController -> new markAttendanceController(currentMeeting, currentStudent));
//                    Parent parent = fxmlLoader.load();
//                    Scene scene = new Scene(parent, 700, 470);
//                    Stage stage = new Stage();
//                    stage.setScene(scene);
//                    stage.showAndWait();
//                }
        }




    }
    private LocalDate getSelectedDate(String coloumnId)
    {
        LocalDate focus = now;
        String focusDay = focus.getDayOfWeek().toString().toLowerCase();
        String selected = coloumnId.substring(8,coloumnId.length()-1).toLowerCase();
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
    private void populateJoinClub(ObservableList<club> clubList) //joining club
    {
        ObservableList<String> clubId = FXCollections.observableArrayList();
        ObservableList<club> clubs= FXCollections.observableArrayList();
        for (club Club:currentStudent.getClubs())
        {
            clubId.add(Club.getClubId());
        }
        try {
            for (club Club :clubList)
            {

                if (!clubId.contains(Club.getClubId()))
                {
                    clubs.add(Club);
                }

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        joinClubId.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<club, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<club, String> club) {
                return new ReadOnlyObjectWrapper<>(club.getValue().getClubId());
            }
        });
        joinClubName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<club, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<club, String> club) {
                return new ReadOnlyObjectWrapper<>(club.getValue().getClubName());
            }
        });
        joinTeacherId.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<club, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<club, String> club) {
                return new ReadOnlyObjectWrapper<>(club.getValue().getInCharge().getId());
            }
        });
        joinTeacherName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<club, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<club, String> club) {
                String Gender = club.getValue().getInCharge().getGender();
                String first =null;
                if (Gender.equals("Male"))
                {
                    first="Mr.";
                }
                else
                {
                    first ="Mrs.";
                }
                return new ReadOnlyObjectWrapper<>(first+club.getValue().getInCharge().getFirstName()+" "+club.getValue().getInCharge().getSurName());
            }
        });
        joinClubLeader.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<club, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<club, String> club) {
                if (club.getValue().getBoardMembers().get(0)==null)
                {
                    return new ReadOnlyObjectWrapper<>("_");
                }
                else
                {
                    return new ReadOnlyObjectWrapper<>(club.getValue().getBoardMembers().get(0).getFullName());
                }
            }
        });
        joinDescription.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<club, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<club, String> club) {
                return new ReadOnlyObjectWrapper<>(club.getValue().getDescription());
            }
        });
        tblClb.getSelectionModel().selectedItemProperty().addListener((ObservableValue,club,Club)->
        {
            if (Club!=null)
            {
                joinClbID.setText(Club.getClubId());
                joinClbName.setText(Club.getClubName());
                joinClub = Club;
            }
        });
        try {
            tblClb.setItems(clubs);
        }
        catch (NullPointerException e)
        {

        }

    }
    private ArrayList<LocalDate> getWeek()
    {
        // now = LocalDate.parse("2023-11-27");

        String s = now.getDayOfWeek().toString();
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
            LocalDate d = now.minus(j,ChronoUnit.DAYS);
            dates.add(d);
        }
        dates.add(now);
        for (int j=1;j<7-i;j++)
        {
            LocalDate d = now.plus(j,ChronoUnit.DAYS);
            dates.add(d);
        }
        return dates;

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateJoinClub(Data.getClubList());
        populateActivities();
        populateEvents();
        if (currentStudent.getClubs()!=null) {
            populateMeetings();
        }
        ObservableList<String> genders = FXCollections.observableArrayList();
        genders.add("Male");
        genders.add("Female");
        profileChoiceGender.setItems(genders);
        profileTxtID.setText(currentStudent.getId());
        profileTxtID.setDisable(true);
        profileTxtFirstN.setText(currentStudent.getFirstName());
        profileTxtLastN.setText(currentStudent.getSurName());
        profileTxtNic.setText(currentStudent.getNic());
        profileDateDob.setValue(LocalDate.parse(currentStudent.getDob()));
        profileChoiceGender.setValue(currentStudent.getGender());
        profileTxtEmail.setText(currentStudent.getEmail());
        profileTxtFirstN.setTextFormatter(new TextFormatter<>(filter));
        profileTxtLastN.setTextFormatter(new TextFormatter<>(filter));
//        for (club C:currentStudent.getClubs())
//        {
//            System.out.println(C.toString());
//        }
//        for (club C:Data.getClubList())
//        {
//            for (Student S:C.getMembers())
//            {
//                System.out.println(S);
//            }
//
//        }


//
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
        eventsTblDate.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<event, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<event, String> event)
            {
                if (event != null)
                {
                    return new ReadOnlyObjectWrapper<>(event.getValue().getDate().toString());
                }
                else
                {
                    return new ReadOnlyObjectWrapper<>("_");
                }

            }
        });
        eventTblClub.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<event, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<event, String> event) {
                if (event!=null){
                    return new ReadOnlyObjectWrapper<>(event.getValue().getHeldByClub().getClubName());
                }
                else
                {
                    return new ReadOnlyObjectWrapper<>("_");
                }

            }
        });
        eventTblName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<event, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<event, String> event) {
                if (event!=null){return new ReadOnlyObjectWrapper<>(event.getValue().getEventName());}
                else {return new ReadOnlyObjectWrapper<>("_");}

            }
        });
        eventTblStart.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<event, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<event, String> event) {
                if (event!=null){return new ReadOnlyObjectWrapper<>(event.getValue().getStartTime().toString());}
                else {return new ReadOnlyObjectWrapper<>("_");}

            }
        });
        eventTblEnd.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<event, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<event, String> event) {
                if (event!=null){return new ReadOnlyObjectWrapper<>(event.getValue().getEndTime().toString());}
                else {return new ReadOnlyObjectWrapper<>("_");}

            }
        });
        eventTblDesc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<event, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<event, String> event) {
                if (event!=null){return new ReadOnlyObjectWrapper<>(event.getValue().getDescription());}
                else {return new ReadOnlyObjectWrapper<>("_");}

            }
        });
        eventTable.getSelectionModel().selectedItemProperty().addListener((ObservableValue,event,Ev)->
        {
            selectedEvent =Ev;
        });
        eventTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount()==2&&selectedEvent!=null)
                {
                    try {
                        attendEvent(selectedEvent);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        eventTable.setItems(orderedDaysWithEvents);


    }
    private void attendEvent(event Event) throws IOException {
        LocalTime nowTime = LocalTime.now();
        if (Event.getDate().equals(now)&&nowTime.isAfter(Event.getStartTime())&&nowTime.isBefore(Event.getEndTime()))
        {

            FXMLLoader fxmlLoader = new FXMLLoader(teacherController.class.getResource("markAttendance.fxml"));
            fxmlLoader.setControllerFactory(eventsAttendanceController->new eventsAttendanceController(Event,currentStudent));
            Parent parent = fxmlLoader.load();
            Scene scene = new Scene(parent, 700, 470);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.showAndWait();

        }
        else
        {
            warnings.attendanceWarning(Event);
        }


    }
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

        activitiesTable.getSelectionModel().selectedItemProperty().addListener((ObservableValue,scheduleRow,SR)->
        {
            if (activitiesTable.getSelectionModel().getSelectedItem()!=null)
            {
                TableView.TableViewSelectionModel<scheduleRow> model = activitiesTable.getSelectionModel();
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
                attendActivity(currentMeeting);
//                System.out.println(currentMeeting.getValue().getMeetingID());
//                System.out.println(currentMeeting.getValue().getMeetingID());
//                System.out.println(currentMeeting.getValue().getDescription());


            }
        });
        activitiesTable.setItems(rows);

    }
    private void attendActivity(ObservableValue<meeting> meeting)
    {
        boolean isAMember = false;
//        now=LocalDate.parse("2023-11-25");
        LocalTime nowTime = LocalTime.now();
//        nowTime=LocalTime.parse("07:00:00");
        meeting currentMeeting = null;
        for (day Day:Data.getDayList())
        {

            if (Day.getDay().equals(meeting.getValue().getDay()))
            {
                timeSlot slot = Day.getSlots().get(meeting.getValue().getTimeSlot());
                if (meeting.getValue().getVenue().equals("1"))
                {
                    if (slot.getVenue1().getMeetingID()==meeting.getValue().getMeetingID())
                    {
                        currentMeeting=slot.getVenue1();
                    }
                }
                else if (meeting.getValue().getVenue().equals("2"))
                {
                    if (slot.getVenue2().getMeetingID()==meeting.getValue().getMeetingID())
                    {
                        currentMeeting=slot.getVenue1();
                    }
                }
                else if (meeting.getValue().getVenue().equals("3"))
                {
                    if (slot.getVenue3().getMeetingID()==meeting.getValue().getMeetingID())
                    {
                        currentMeeting=slot.getVenue1();
                    }
                }
            }
        }
        if (currentMeeting!=null) {
            for (club C : Data.getClubList()) {
                if (C.getClubId().equals(currentMeeting.getClub().getClubId())) {
                    for (Student St : C.getMembers()) {
                        if (St.getId().equals(currentStudent.getId())) {
                            isAMember = true;
                        }
                    }
                }
            }
            LocalTime begin = LocalTime.parse("06:00:00");

            try {
                if (nowTime.isAfter(begin.plus(30L *currentMeeting.getTimeSlot(),ChronoUnit.MINUTES)) && nowTime.isBefore(begin.plus(30L *(currentMeeting.getTotalSlots()+currentMeeting.getTimeSlot()),ChronoUnit.MINUTES)) &&
                        currentMeeting.getDay().equals(now)&&isAMember) {
                    FXMLLoader fxmlLoader = new FXMLLoader(teacherController.class.getResource("markAttendance.fxml"));
                    com.example.activityclubmanagementsystem.meeting finalCurrentMeeting = currentMeeting;
                    fxmlLoader.setControllerFactory(attendenceController -> new markAttendanceController(finalCurrentMeeting, currentStudent));
                    Parent parent = fxmlLoader.load();
                    Scene scene = new Scene(parent, 700, 470);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.showAndWait();

                }
                else
                {
                    warnings.attendanceActivityWarning(currentMeeting);
                }
            }
            catch (IOException e)
            {
                warnings.SqlWarning();
            }
        }






//        int startIndex;
//        int endIndex;
//        LocalDate nowDate = LocalDate.now();
//        club meetingClub = null;
//        LocalTime nowTime = LocalTime.now();
//        meeting currentMeeting = null;
//        day CurrentDay = null;
//        LocalDate saturday = getWeek().get(5);
//        LocalDate sunday = getWeek().get(6);
//        LocalDate date=null;
//        if (coloumn==0||coloumn==1||coloumn==2)
//        {
//            date=saturday;
//        }
//        else
//        {
//            date = sunday;
//        }
//        for (day Day:Data.getDayList())
//        {
//            if (Day.getDay().equals(date))
//            {
//                CurrentDay=Day;
//                break;
//            }
//        }
//        if (CurrentDay!=null)
//        {
//            if (coloumnid.endsWith("1"))
//            {
//                for (int i=row;i>0;i--)
//                {
//                    if (CurrentDay.getSlots().get(i).getVenue1()!=null)
//                    {
//                        startIndex = i;
//                        currentMeeting = CurrentDay.getSlots().get(i).getVenue1();
//                    }
//                }
//            }
//            else if(coloumnid.endsWith("2"))
//            {
//                for (int i=row;i>0;i--)
//                {
//                    if (CurrentDay.getSlots().get(i).getVenue2()!=null)
//                    {
//                        currentMeeting = CurrentDay.getSlots().get(i).getVenue2();
//                    }
//                }
//            }
//
//            else
//            {
//                for (int i=row;i>0;i--)
//                {
//                    if (CurrentDay.getSlots().get(i).getVenue3()!=null)
//                    {
//                        currentMeeting = CurrentDay.getSlots().get(i).getVenue3();
//                    }
//                }
//            }
//            boolean isAMember=false;
//            if (currentMeeting!=null) {
//                for (club C : Data.getClubList()) {
//                    if (C.getClubId().equals(currentMeeting.getClub().getClubId())) {
//                        for (Student St : C.getMembers()) {
//                            if (St.getId().equals(currentStudent.getId())) {
//                                isAMember = true;
//                            }
//                        }
//                    }
//                }
//            }
//            LocalTime begin = LocalTime.parse("06:00:00");
//            LocalTime beginTime = begin.plus(30*row,ChronoUnit.MINUTES);





    }

    public void onJoinCheck(KeyEvent event)
    {
        String search = checkClb.getText();
        ObservableList<club> filteredList = FXCollections.observableArrayList();
        for (club Club:Data.getClubList())
        {
            if (Club.getClubId().startsWith(search))
            {
                filteredList.add(Club);
            }
            if (Club.getClubName().startsWith(search))
            {
                filteredList.add(Club);
            }
        }
        tblClb.getItems().clear();
        populateJoinClub(filteredList);
    }
    public void onJoinClick(ActionEvent event) throws SQLException {
        Boolean alreadMember=false;

        if (joinClub!=null)
        {

            for (club Club : currentStudent.getClubs())
            {
                if (Club.getClubId().equals(joinClub.getClubId())) {
                    alreadMember = true;
                    break;
                }
            }

            if (!alreadMember)
            {
                currentStudent.addClub(joinClub);
                for (club Club:Data.getClubList())
                {
                    if (Club.getClubId().equals(joinClub.getClubId()))
                    {
                        Club.getMembers().add(currentStudent);
                        Data.updateClub(Club);
                        break;
                    }
                }
            }

            Data.updateStudent(currentStudent);
//            Data.updateClub(joinClub);

//            Teacher teach = joinClub.getInCharge();
//            teach.setInCharge(joinClub);
//            Data.updateTEacher(teach);
        }
        populateMeetings();
        populateJoinClub(Data.getClubList());
    }
    public void onResetClick(ActionEvent event)
    {
        joinClbID.clear();
        joinClbName.clear();
    }
    public void onProfileSaveClick(ActionEvent event)
    {
        getText input = getProfileInputs();
        if (input.complete)
        {
            ArrayList<String> inputs = input.inputs;
            Student student = null;
            if (inputs.get(7)==null)
            {
                student = new Student(inputs.get(0),inputs.get(1),inputs.get(2),inputs.get(3),inputs.get(4),inputs.get(5),inputs.get(6),currentStudent.getPassword());
            }
            else
            {
                student = new Student(inputs.get(0),inputs.get(1),inputs.get(2),inputs.get(3),inputs.get(4),inputs.get(5),inputs.get(6),inputs.get(7));

            }
            try {
                Data.updateStudent(student);
                for (club Club:Data.getClubList())
                {

                    for (Student St:Club.getMembers())
                    {
                        Student newSt = St;
                        if (newSt.getId().equals(student.getId()))
                        {
                            Club.getMembers().set(Club.getMembers().indexOf(St),student);
                            Data.updateClub(Club);
                        }
                    }
                }
            }
            catch (SQLException e)
            {

            }


        }

    }
    public getText getProfileInputs()
    {
        Boolean completed = true;
        String FirstN=null;
        String LastN=null;
        String Nic=null;
        String Dob=null;
        String Gender=null;
        String Email = null;
        String password = null;
        if (profileTxtFirstN.getText().isEmpty())
        {
            profileTxtFirstN.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            completed=false;
        }
        else
        {
            profileTxtFirstN.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");

            FirstN = profileTxtFirstN.getText();
        }

        if (profileTxtLastN.getText().isEmpty())
        {
            profileTxtLastN.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            completed=false;
        }
        else
        {
            profileTxtLastN.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");

            LastN = profileTxtLastN.getText();
        }

        if (profileTxtNic.getText().isEmpty())
        {
            profileTxtNic.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            completed=false;
        }
        else
        {
            profileTxtNic.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");

            Nic = profileTxtNic.getText();
        }

        if (profileDateDob.getValue()==null)
        {
            profileDateDob.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            completed=false;
        }
        else
        {
            profileDateDob.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
            Dob = profileDateDob.getValue().toString();
        }

        if (profileChoiceGender.getValue()==null)
        {
            profileChoiceGender.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            completed=false;
        }
        else
        {
            profileChoiceGender.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");

            Gender = profileChoiceGender.getValue().toString();
        }
        String emailRegx="^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        if (profileTxtEmail.getText().isEmpty()||!profileTxtEmail.getText().matches(emailRegx))
        {
            profileTxtEmail.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            completed=false;
        }
        else
        {
            profileTxtEmail.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");

            Email = profileTxtEmail.getText();
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
            alert.setTitle("Alert");
            alert.setHeaderText(null);
            alert.setContentText("Password must be between 6-10 Characters");

            alert.showAndWait(); // Display the alert and wait for user interaction
        }
        else
        {
            completed=true;
        }
        ArrayList<String> inputs = new ArrayList<>();
        inputs.add(profileTxtID.getText());
        inputs.add(FirstN);
        inputs.add(LastN);
        inputs.add(Nic);
        inputs.add(Dob);
        inputs.add(Gender);
        inputs.add(Email);
        inputs.add(password);
        getText input = new getText(completed,inputs);
        return input;

    }

    public void onMousePressed(MouseEvent mouseEvent) //click password
    {
        seePwd.setText(profileTxtPwd.getText());
        seeRePwd.setText(profileTxtRePwd.getText());
        seeRePwd.setVisible(true);
        seePwd.setVisible(true);
        profileTxtPwd.setVisible(false);
        profileTxtRePwd.setVisible(false);
    }

    public void onMouseReleased(MouseEvent mouseEvent)  //release mouse to unshow password
    {
        seeRePwd.setVisible(false);
        seePwd.setVisible(false);
        profileTxtPwd.setVisible(true);
        profileTxtRePwd.setVisible(true);
    }
    public void populateJoinedClubs()
    {
        ObservableList<club> clubs = FXCollections.observableArrayList();
        for (club Club:currentStudent.getClubs())
        {
            clubs.add(Club);
        }
        viewTblClubId.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<club, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<club, String> clubStringCellDataFeatures) {
                return new ReadOnlyObjectWrapper<>(clubStringCellDataFeatures.getValue().getClubId());
            }
        });
        viewTblClubName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<club, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<club, String> clubStringCellDataFeatures) {
                return new ReadOnlyObjectWrapper<>(clubStringCellDataFeatures.getValue().getClubName());
            }
        });
        viewTblTeacherId.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<club, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<club, String> clubStringCellDataFeatures) {
                return new ReadOnlyObjectWrapper<>(clubStringCellDataFeatures.getValue().getInCharge().getId());
            }
        });
        viewTblTeacherName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<club, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<club, String> clubStringCellDataFeatures) {
                return new ReadOnlyObjectWrapper<>(clubStringCellDataFeatures.getValue().getInCharge().getFullName());
            }
        });
        viewTblPres.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<club, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<club, String> clubStringCellDataFeatures) {
                if (clubStringCellDataFeatures.getValue().getBoardMembers().get(0)==null)
                {
                    return new ReadOnlyObjectWrapper<>("_");
                }
                else
                {
                    return new ReadOnlyObjectWrapper<>(clubStringCellDataFeatures.getValue().getBoardMembers().get(0).getFullName());

                }

            }
        });
        viewTblDesc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<club, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<club, String> clubStringCellDataFeatures) {
                return new ReadOnlyObjectWrapper<>(clubStringCellDataFeatures.getValue().getDescription());
            }
        });
        tableViewClb.setItems(clubs);

    }
    UnaryOperator<TextFormatter.Change> filter = change -> {
        String newText = change.getControlNewText();
        if (newText.matches("[a-zA-Z]*")) {
            return change; // Allow the change
        }
        return null; // Reject the change
    };
}
