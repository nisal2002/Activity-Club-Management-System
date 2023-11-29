package com.example.activityclubmanagementsystem;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;

public class adminController implements Initializable {
    @FXML
    private Button createClbBtn;
    @FXML
    private Button viewClbBtn;
    @FXML
    private Button profileBtn;
    @FXML
    private Pane createClbPane;
    @FXML
    private Pane viewClbPane;
    @FXML
    private Pane profilePane;
    @FXML
    private TextField createTxtclubId;
    @FXML
    private TextField createTxtName;
    @FXML
    private TextField createTxtTeacherId;
    @FXML
    private TextField createTxtTeacherFirstN;
    @FXML
    private TextField createTxtTeacherLastN;

    @FXML
    private TextArea createTxtDesc;
    @FXML
    private Button createBtnCreate;
    @FXML
    private Button createBtnClear;
    @FXML
    private Button createBtnTest;
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
    @FXML
    private TextField profileTxtId;
    @FXML
    private TextField profileTxtFrstN;
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
    private PasswordField profileTxtPwd;
    @FXML
    private PasswordField profileTxtRePwd;
    @FXML
    private TextField seePwd;
    @FXML
    private TextField seeRePwd;
    @FXML
    private TableView<club> tableViewClb;
    @FXML
    private TableColumn<club,String> viewClubId;
    @FXML
    private TableColumn<club,String> ViewClubName;
    @FXML
    private TableColumn<club,String> viewTeacherId;
    @FXML
    private TableColumn<club,String> viewTeacherName;
    @FXML
    private TableColumn<club,String> viewPresident;
    @FXML
    private TableColumn<club,String> viewDesc;
    @FXML
    private Button viewBtnRemoveTeacher;
    private Teacher incharge;
    private Admin currentAdmin=null;
    private club toRemoveTeacher;
    private boolean isRemoving=false;

    ObservableList<Teacher> teacherList = FXCollections.observableArrayList(Data.getTeacherList());


    private String adminID;

    public adminController()
    {
        adminID =Data.getUserId();
        for (Admin admin :Data.getAdminList())
        {
            if (admin.getId().equals(adminID))
            {
                currentAdmin=admin;
            }
        }
        Data.clearCSV();
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
        if (actionEvent.getSource() == createClbBtn) {
            createClbPane.toFront();
            createBtnCreate.setText("create");
            isRemoving=false;
        }
        if (actionEvent.getSource() == viewClbBtn) {
            viewClbPane.toFront();
        }
        if (actionEvent.getSource() == viewClbBtn) {
            populateViewClub();
            viewClbPane.toFront();
        }
        if (actionEvent.getSource() == profileBtn) {
            profilePane.toFront();
        }
    }
    public void onCreate(ActionEvent event) throws SQLException {


    }
    public void onclear(ActionEvent event)
    {
        createTxtName.clear();
        createTxtTeacherId.clear();
        createTxtTeacherFirstN.clear();
        createTxtTeacherLastN.clear();
        createTxtDesc.clear();
    }
    public void onTest(ActionEvent event)
    {
        ArrayList<String> clubNames = new ArrayList<>();
        clubNames.add("Chess Club");
        clubNames.add("Photography Society");
        clubNames.add("Debate Team");
        clubNames.add("Coding Club");
        clubNames.add("Art Club");

        // ArrayList for club descriptions
        ArrayList<String> clubDescriptions = new ArrayList<>();
        clubDescriptions.add("The Chess Club promotes strategic thinking and friendly competition.");
        clubDescriptions.add("The Photography Society focuses on capturing moments and honing photography skills.");
        clubDescriptions.add("The Debate Team encourages critical thinking and effective argumentation.");
        clubDescriptions.add("The Coding Club explores programming concepts and collaborates on projects.");
        clubDescriptions.add("The Art Club nurtures creativity and artistic expression through various mediums.");

        Random rand = new Random();
        int i = rand.nextInt(0,5);
        createTxtName.setText(clubNames.get(i));
        createTxtDesc.setText(clubDescriptions.get(i));
    }
    public void onClubCreate(ActionEvent event) throws SQLException {
        if (!isRemoving)
        {
            getText input = getClubDetails();
            if (input.complete.equals(true)) {
                if (incharge.getInCharge() == null) {
                    boolean isRepeated=false;
                    for (club Club:Data.getClubList())
                    {
                        if (Club.getClubName().equals(input.inputs.get(1)))
                        {
                            isRepeated=true;
                        }
                    }
                    if (!isRepeated) {
                        ArrayList<String> inputs = input.inputs;

                        club addClub = new club(inputs.get(0), inputs.get(1), incharge, inputs.get(2));
                        Data.addClub(addClub);
                        String first = null;
                        if (incharge.getGender().equals("Male")) {
                            first = "Mr.";
                        } else {
                            first = "Mrs.";
                        }
                        String massage = inputs.get(1) + " has been added under the supervision of " + first + incharge.getFirstName() + " " + incharge.getSurName();
                        warnings.register(massage);
                        createTxtclubId.setText("C" + Data.getNextId("clubs1"));
                        populateTeachersTable(1, "");
                    }
                    else
                    {
                        warnings.register("a club with the same name exists please change the name ");
                    }
                } else {
                    warnings.register("teacher already has a club that he/she is responsible for");
                }


            }
        }
        else
        {
            createTxtclubId.setText(toRemoveTeacher.getClubId());
            createTxtName.setText(toRemoveTeacher.getClubName());
            createTxtTeacherId.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            createTxtTeacherFirstN.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            createTxtTeacherLastN.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            if (incharge!=null)
            {
                if (incharge.getInCharge()==null)
                {
                    Teacher teach =toRemoveTeacher.getInCharge();
                    teach.setInCharge(null);
                    toRemoveTeacher.setInCharge(incharge);
                    incharge.setInCharge(toRemoveTeacher);
                    Data.updateTEacher(incharge);
                    Data.updateTEacher(teach);
                    Data.updateClub(toRemoveTeacher);
                    isRemoving=false;
                    createTxtTeacherId.setStyle("-fx-border-color: red ; -fx-border-width: 0px ;");
                    createTxtTeacherFirstN.setStyle("-fx-border-color: red ; -fx-border-width: 0px ;");
                    createTxtTeacherLastN.setStyle("-fx-border-color: red ; -fx-border-width: 0px ;");
                    warnings.register("teacher successfully replaced");
                }
                else {
                    warnings.register("teacher already has a club that he/she is responsible for");
                }

            }
            else
            {
                warnings.register("please Select a Teacher to Replace");
            }
        }
    }
    public void onTeacherLastNEnter(KeyEvent event)
    {
        createTblTeachers.getSelectionModel().select(null);
        String input = createTxtTeacherLastN.getText();
        populateTeachersTable(4,input);

    }

    public void onTeacherFirstNEnter(KeyEvent event)
    {
        createTblTeachers.getSelectionModel().select(null);
        String input = createTxtTeacherFirstN.getText();
        populateTeachersTable(3,input);
    }

    public void onIdEnter(KeyEvent event)
    {
        createTblTeachers.getSelectionModel().select(null);
        String input = createTxtTeacherId.getText();
        populateTeachersTable(2,input);
    }

    private getText getClubDetails()
    {
        Boolean completed = true;
        String ID=null;
        String clubName =null;
        String description=null;
        if(createTxtName.getText().isEmpty())
        {
            completed=false;
            createTxtName.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else
        {
            createTxtName.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
            clubName = createTxtName.getText();
        }
        try {
            String id =incharge.getId();
            createTxtclubId.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
            createTxtTeacherFirstN.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
            createTxtTeacherLastN.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");


        }
        catch (NullPointerException e)
        {

            completed=false;
            createTxtName.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            createTxtTeacherFirstN.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            createTxtTeacherLastN.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");


        }
        if(createTxtDesc.getText().isEmpty())
        {
            completed=false;
            createTxtDesc.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else
        {
            createTxtDesc.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
            description = createTxtDesc.getText();
        }
        ArrayList<String> inputs = new ArrayList<>();
        inputs.add(createTxtclubId.getText());
        inputs.add(clubName);
        inputs.add(description);
        return new getText(completed,inputs);



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        ;
        createTxtclubId.setDisable(true);
        try {
            String clubId= Data.getNextId("clubs1");
            createTxtclubId.setText("C"+clubId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        populateTeachersTable(1," ");
        ObservableList<String> genders = FXCollections.observableArrayList();
        genders.add("Male");
        genders.add("Female");
        profileChoiceGender.setItems(genders);
        profileTxtId.setText(currentAdmin.getId());
        profileTxtId.setDisable(true);
        profileTxtFrstN.setText(currentAdmin.getFirstName());
        profileTxtLastN.setText(currentAdmin.getSurName());
        profileTxtNic.setText(currentAdmin.getNic());
        profileDateDob.setValue(LocalDate.parse(currentAdmin.getDob()));
        profileChoiceGender.setValue(currentAdmin.getGender());
        profileTxtEmail.setText(currentAdmin.getEmail());
    }
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
    private getText gerAdminProfileInputs()
    {
        Boolean completed = true;
        String FirstN=null;
        String LastN=null;
        String Nic=null;
        String Dob=null;
        String Gender=null;
        String Email = null;
        String password = null;
        if (profileTxtFrstN.getText().isEmpty())
        {
            profileTxtFrstN.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            completed=false;
        }
        else
        {
            profileTxtFrstN.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");

            FirstN = profileTxtFrstN.getText();
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

        if (profileTxtEmail.getText().isEmpty())
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
        ArrayList<String> inputs = new ArrayList<>();
        inputs.add(profileTxtId.getText());
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


    public void onSaveClick(ActionEvent event)
    {
        getText input = gerAdminProfileInputs();
        if (input.complete)
        {
            Admin newAdmin = null;
            ArrayList<String> inputs = input.inputs;
            if (inputs.get(7)==null)
            {
                newAdmin = new Admin(inputs.get(0),inputs.get(1),inputs.get(2),inputs.get(3),inputs.get(4),inputs.get(5),inputs.get(6),currentAdmin.getPassword());
            }
            else
            {
                newAdmin = new Admin(inputs.get(0),inputs.get(1),inputs.get(2),inputs.get(3),inputs.get(4),inputs.get(5),inputs.get(6),inputs.get(7));
            }
            for (int i=0;i<Data.getAdminList().size();i++)
            {
                if (Data.getAdminList().get(i).getId().equals(newAdmin.getId()))
                {
                    Data.updateAdmin(newAdmin);

                }
            }
        }
    }

    public void onMousePressed(MouseEvent mouseEvent)
    {
        seePwd.setText(profileTxtPwd.getText());
        seeRePwd.setText(profileTxtRePwd.getText());
        seeRePwd.setVisible(true);
        seePwd.setVisible(true);
        profileTxtPwd.setVisible(false);
        profileTxtRePwd.setVisible(false);
    }

    public void onMouseReleased(MouseEvent mouseEvent)
    {
        seeRePwd.setVisible(false);
        seePwd.setVisible(false);
        profileTxtPwd.setVisible(true);
        profileTxtRePwd.setVisible(true);
    }
    public void onTeacherRemove(ActionEvent event)
    {
        if (toRemoveTeacher!=null)
        {
            createBtnCreate.setText("Replace Teacher");
            isRemoving=true;
            createClbPane.toFront();
        }
//        populateTeachersTable(0,"");
    }
    private void populateViewClub()
    {
        viewClubId.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<club, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<club, String> clubStringCellDataFeatures) {
                return new ReadOnlyObjectWrapper<>(clubStringCellDataFeatures.getValue().getClubId());
            }
        });
        ViewClubName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<club, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<club, String> clubStringCellDataFeatures) {
                return new ReadOnlyObjectWrapper<>(clubStringCellDataFeatures.getValue().getClubName());
            }
        });
        viewTeacherId.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<club, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<club, String> clubStringCellDataFeatures) {
                return new ReadOnlyObjectWrapper<>(clubStringCellDataFeatures.getValue().getInCharge().getId());
            }
        });
        viewTeacherName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<club, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<club, String> clubStringCellDataFeatures) {
                return new ReadOnlyObjectWrapper<>(clubStringCellDataFeatures.getValue().getInCharge().getFullName());
            }
        });
        viewPresident.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<club, String>, ObservableValue<String>>() {
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
        viewDesc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<club, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<club, String> clubStringCellDataFeatures) {
                return new ReadOnlyObjectWrapper<>(clubStringCellDataFeatures.getValue().getDescription());
            }
        });
        tableViewClb.getSelectionModel().selectedItemProperty().addListener((ObservableValue,club,Club)->
        {
            toRemoveTeacher=Club;
        });
        tableViewClb.setItems(Data.getClubList());
    }
}