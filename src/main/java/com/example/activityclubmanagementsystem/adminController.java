package com.example.activityclubmanagementsystem;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;


public class adminController {

    @FXML
    private TextField createTxtTeacherId;
    @FXML
    private TextField createTxtTeacherFirstN;
    @FXML
    private TextField createTxtTeacherLastN;

    @FXML
    private TableColumn<Teacher,String> teachersId;
    @FXML
    private TableColumn<Teacher,String> teachersFirstN;
    @FXML
    private TableColumn<Teacher,String> teachersLastN;
    @FXML
    private TableColumn<Teacher,String> teachersInCharge;
    @FXML
    private TableView<Teacher> createTblTeachers;
    private Teacher incharge;

    ObservableList<Teacher> teacherList = FXCollections.observableArrayList(Data.getTeacherList());
    private void populateTeachersTable(int field, String input)
    {
        /// field =1 all teachers| field =2 filtered by id | field =3 filtered by first name| field =4 filtered by last name
        ObservableList<Teacher> filteredList = FXCollections.observableArrayList();
        if(field==1)
        {
            for(Teacher teacher:teacherList)
            {
                if(teacher.getInCharge()==null)
                {
                    filteredList.add(teacher);
                }
            }
        }
        else if (field==2)
        {
            for (Teacher teacher:teacherList)
            {
                if (teacher.getId().startsWith(input) && teacher.getInCharge()==null)
                {
                    filteredList.add(teacher);
                }
            }
        }
        else if (field ==3)
        {
            for (Teacher teacher:teacherList)
            {
                if (teacher.getFirstName().startsWith(input) && teacher.getInCharge()==null)
                {
                    filteredList.add(teacher);
                }
            }
        }
        else if (field ==4)
        {
            for (Teacher teacher:teacherList)
            {
                if (teacher.getSurName().startsWith(input) && teacher.getInCharge()==null)
                {
                    filteredList.add(teacher);
                }
            }
        }
//        for (Teacher teach:filteredList)
//        {
//            if (teach.getInCharge()!=null)
//            {
//                createTblTeachers.setRowFactory(teacherTableView ->
//                {
//                    TableRow<Teacher> row = new TableRow<>();
//                    BooleanBinding hasClub = row.itemProperty().isEqualTo(teach).and(row.itemProperty().isNotNull());
//                    row.styleProperty().bind(Bindings.when(hasClub).then("-fx-background-color: #d71313 ;"))
//                            .otherWise("");
//                    return row;
//                });
//            }
//        }
        teachersId.setCellValueFactory(new PropertyValueFactory<>("id"));
        teachersFirstN.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        teachersLastN.setCellValueFactory(new PropertyValueFactory<>("SurName"));
        try {
            teachersInCharge.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Teacher, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Teacher, String> teacherStringCellDataFeatures) {
                    if(teacherStringCellDataFeatures.getValue().getInCharge()==null)
                    {

                        return new ReadOnlyObjectWrapper("_");

                    }
                    else
                    {
                        return new ReadOnlyObjectWrapper(teacherStringCellDataFeatures.getValue().getInCharge().getClubName());

                    }
                }
            });
        }
        catch (NullPointerException ignored)
        {
            teachersInCharge.setText("_");
        }


//        teachersInCharge.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<club,String>, ObservableValue<String >>()
//        {
//            public ObservableValue<String> call(TableColumn.CellDataFeatures<club,String> p)
//            {
//                return new ReadOnlyObjectWrapper( p.getValue().getClubName());
//            }
//        });
        //  teachersInCharge.setCellValueFactory(new PropertyValueFactory<>("club"));
        createTblTeachers.setItems(filteredList);
        createTblTeachers.getSelectionModel().selectedItemProperty().addListener(((observableValue, teacher, t1) ->
        {
            createTxtTeacherId.setText(t1.getId());
            createTxtTeacherFirstN.setText(t1.getFirstName());
            createTxtTeacherLastN.setText(t1.getSurName());
            incharge=t1;


        }));


    }
}
