package com.example.activityclubmanagementsystem;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class teacherController implements Initializable
{
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
    private DatePicker teacherDateSelect;
    private String teacherID;
    private meeting removeMeeting;
    private LocalDate focus;
    private LocalDate today;
    ArrayList<TextField> fields;

    //Sevindu
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
            LocalDate d = focus.minus(j, ChronoUnit.DAYS);
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

    //Sevindu
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
                        Alert alert = new Alert(Alert.AlertType.WARNING,"no meeting to remove", ButtonType.OK);
                        alert.showAndWait();
                    }
                }


            });


        });


    }

    //Sevindu
    private void scheduleMeeting(int row,int coloumn,String coloumnid) throws IOException {
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

    //Sevindu
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

    //Sevindu
    public void onSelect(ActionEvent event)
    {
        focus = teacherDateSelect.getValue();
        populateSchedule();


    }

    //Sevindu
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
    }
}
