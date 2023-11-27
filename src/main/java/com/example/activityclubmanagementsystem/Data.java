package com.example.activityclubmanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.sql.*;

public class Data {
    private static final ObservableList<Teacher> teacherList = FXCollections.observableArrayList();
    private  static final ObservableList<Student> studentList = FXCollections.observableArrayList();
    private static final ObservableList<Admin> adminList = FXCollections.observableArrayList();
    private static final ObservableList<club> clubList =FXCollections.observableArrayList();
    private static  ObservableList<day> dayList = FXCollections.observableArrayList();

    public Data() throws SQLException {
        updateTeacherList();
    }
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/clubmanager",
                "root",
                ""
        );
    }
    public static void addStudent(Student addStudent) throws SQLException {
        studentList.add(addStudent);
        Connection connection = getConnection();
        String query5 ="INSERT INTO `students` (student) VALUES(?);";
        try(PreparedStatement statement = connection.prepareStatement(query5))
        {
            ByteArrayOutputStream BOut = new ByteArrayOutputStream();
            ObjectOutputStream ObjOut = new ObjectOutputStream(BOut);
            ObjOut.writeObject(addStudent);
            ObjOut.close();
            byte[] serializedObject = BOut.toByteArray();
            statement.setBytes(1,serializedObject);
            statement.executeUpdate();

        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

    }
    public static void updateStudent(Student updateStudent) throws SQLException {
        for (Student student:studentList)
        {
            if (student.getId().equals(updateStudent.getId()))
            {
                studentList.set(studentList.indexOf(student),updateStudent);
            }
        }
        Connection connection = getConnection();

        String id = updateStudent.getId();
        System.out.println(id);

        String updateStudentQuery = "UPDATE `students` SET student=? WHERE student_id="+id.substring(1)+";";
        try(PreparedStatement statement = connection.prepareStatement(updateStudentQuery))
        {
            ByteArrayOutputStream BOut = new ByteArrayOutputStream();
            ObjectOutputStream ObjOut = new ObjectOutputStream(BOut);
            ObjOut.writeObject(updateStudent);
            ObjOut.close();
            byte[] serializedObject = BOut.toByteArray();

            statement.setBytes(1,serializedObject);
            statement.executeUpdate();

        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
    public static void updateStudentList() throws SQLException {
        Connection connection = getConnection();
        String query = "SELECT * FROM `students`;";
        try(PreparedStatement statement = connection.prepareStatement(query))
        {
            ResultSet results = statement.executeQuery();
            while (results.next())
            {
                byte[] serializedObject = results.getBytes("student");
                ByteArrayInputStream BInput = new ByteArrayInputStream(serializedObject);
                ObjectInputStream OInput = new ObjectInputStream(BInput);
                OInput.close();
                Student read = (Student) OInput.readObject();
                studentList.add(read);
            }

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static void addTeacher(Teacher addTeacher) {
        teacherList.add(addTeacher);
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/clubmanager",
                    "root",
                    ""
            );
            String query5 ="INSERT INTO `teachers` (teacher) VALUES(?);";
            try(PreparedStatement statement = connection.prepareStatement(query5))
            {
                ByteArrayOutputStream BOut = new ByteArrayOutputStream();
                ObjectOutputStream ObjOut = new ObjectOutputStream(BOut);
                ObjOut.writeObject(addTeacher);
                ObjOut.close();
                byte[] serializedObject = BOut.toByteArray();
                statement.setBytes(1,serializedObject);
                statement.executeUpdate();

            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void updateTeacherList() throws SQLException {
        Connection connection = getConnection();
        String query = "SELECT * FROM `teachers`;";
        try(PreparedStatement statement = connection.prepareStatement(query))
        {
            ResultSet results = statement.executeQuery();
            while (results.next())
            {
                byte[] serializedObject = results.getBytes("teacher");
                ByteArrayInputStream BInput = new ByteArrayInputStream(serializedObject);
                ObjectInputStream OInput = new ObjectInputStream(BInput);
                OInput.close();
                Teacher read = (Teacher) OInput.readObject();
                teacherList.add(read);
            }

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static void addAdmin(Admin addAdmin) throws SQLException {
        adminList.add(addAdmin);

        Connection connection = getConnection();
        String query5 ="INSERT INTO `admins` (admin) VALUES(?);";
        try(PreparedStatement statement = connection.prepareStatement(query5))
        {
            ByteArrayOutputStream BOut = new ByteArrayOutputStream();
            ObjectOutputStream ObjOut = new ObjectOutputStream(BOut);
            ObjOut.writeObject(addAdmin);
            ObjOut.close();
            byte[] serializedObject = BOut.toByteArray();
            statement.setBytes(1,serializedObject);
            statement.executeUpdate();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
    public static void updateAdmin(Admin updateAdmin) {
        for (Admin admin:adminList)
        {
            if (admin.getId().equals(updateAdmin.getId()))
            {
                adminList.set(adminList.indexOf(admin),updateAdmin);
            }
        }
        try {
            Connection connection = getConnection();
            String id = updateAdmin.getId();
            System.out.println(id);

            String updateStudentQuery = "UPDATE `admins` SET admin=? WHERE admin_id="+id.substring(1)+";";
            try(PreparedStatement statement = connection.prepareStatement(updateStudentQuery))
            {
                ByteArrayOutputStream BOut = new ByteArrayOutputStream();
                ObjectOutputStream ObjOut = new ObjectOutputStream(BOut);
                ObjOut.writeObject(updateAdmin);
                ObjOut.close();
                byte[] serializedObject = BOut.toByteArray();

                statement.setBytes(1,serializedObject);
                statement.executeUpdate();

            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
        catch (SQLException e)
        {
            warnings.SqlWarning();
        }
    }
    public static void updateAdminList() throws SQLException {
        Connection connection = getConnection();
        String query = "SELECT * FROM `admins`;";
        try(PreparedStatement statement = connection.prepareStatement(query))
        {
            ResultSet results = statement.executeQuery();
            while (results.next())
            {
                byte[] serializedObject = results.getBytes("admin");
                ByteArrayInputStream BInput = new ByteArrayInputStream(serializedObject);
                ObjectInputStream OInput = new ObjectInputStream(BInput);
                OInput.close();
                Admin read = (Admin) OInput.readObject();
                adminList.add(read);
            }

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static ObservableList<Student> getStudentList() {
        return studentList;
    }
    public static ObservableList<Admin> getAdminList() {
        return adminList;
    }
    public static ObservableList<Teacher> getTeacherList()
    {
        return teacherList;
    }
    public static void printTeacher() {
        for (Teacher t:teacherList)
        {
            System.out.println(t.toString());
        }
    }


    public static String getNextId(String table) throws SQLException {

        Connection connection = getConnection();
        String id = null;
        String query ="SELECT `auto_increment` From INFORMATION_SCHEMA.TABLES WHERE table_name = '"+table+"';";
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            while (result.next())
            {
                id =result.getString("auto_increment");

            }

        }
        catch (SQLException e)
        {
            warnings.SqlWarning();
        }


        connection.close();
        return id;
    }


    public static void addClub(club addclub) throws SQLException {
        clubList.add(addclub);
        Connection connection = getConnection();
        String query5 ="INSERT INTO `clubs1` (club) VALUES(?);";
        try(PreparedStatement statement = connection.prepareStatement(query5))
        {
            ByteArrayOutputStream BOut = new ByteArrayOutputStream();
            ObjectOutputStream ObjOut = new ObjectOutputStream(BOut);
            ObjOut.writeObject(addclub);
            ObjOut.close();
            byte[] serializedObject = BOut.toByteArray();
            statement.setBytes(1,serializedObject);
            statement.executeUpdate();

        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        Teacher incharge = addclub.getInCharge();
        for (Teacher teacher:teacherList)
        {
            if (teacher.getId().equals(incharge.getId()))
            {
                String id = teacher.getId();
                String id1 = id.substring(1);
                teacher.setInCharge(addclub);
                String updateTeacher = "UPDATE `teachers` SET teacher=? WHERE teacher_id="+id1+";";
                try(PreparedStatement statement = connection.prepareStatement(updateTeacher))
                {
                    ByteArrayOutputStream BOut = new ByteArrayOutputStream();
                    ObjectOutputStream ObjOut = new ObjectOutputStream(BOut);
                    ObjOut.writeObject(teacher);
                    ObjOut.close();
                    byte[] serializedObject = BOut.toByteArray();

                    statement.setBytes(1,serializedObject);
                    statement.executeUpdate();

                }
                catch (IOException e)
                {
                    throw new RuntimeException(e);
                }

            }
        }
        printTeacher();

    }
    public static void updateTEacher(Teacher updateTeacher) throws SQLException {
        for (Teacher teach:teacherList)
        {
            if (teach.getId().equals(updateTeacher.getId()))
            {
                teacherList.set(teacherList.indexOf(teach),updateTeacher);
            }

        }
        Connection connection = getConnection();

        String id = updateTeacher.getId();
        System.out.println(id);

        String updateStudentQuery = "UPDATE teachers SET teacher=? WHERE teacher_id="+id.substring(1)+";";
        try(PreparedStatement statement = connection.prepareStatement(updateStudentQuery))
        {
            ByteArrayOutputStream BOut = new ByteArrayOutputStream();
            ObjectOutputStream ObjOut = new ObjectOutputStream(BOut);
            ObjOut.writeObject(updateTeacher);
            ObjOut.close();
            byte[] serializedObject = BOut.toByteArray();

            statement.setBytes(1,serializedObject);
            statement.executeUpdate();

        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }


    }
    public static ObservableList<day> getDayList()
    {
        return dayList;
    }
    public static void updateDay(day update) throws SQLException {
        for (day Day:dayList)
        {
            if (Day.getDay().equals(update.getDay()))
            {
                dayList.set(dayList.indexOf(Day),update);
            }
        }
        Connection connection = getConnection();
        int id = update.getId();

        String updateTeacher = "UPDATE `days` SET day=? WHERE day_id="+String.valueOf(id)+";";
        try(PreparedStatement statement = connection.prepareStatement(updateTeacher))
        {
            ByteArrayOutputStream BOut = new ByteArrayOutputStream();
            ObjectOutputStream ObjOut = new ObjectOutputStream(BOut);
            ObjOut.writeObject(update);
            ObjOut.close();
            byte[] serializedObject = BOut.toByteArray();

            statement.setBytes(1,serializedObject);
            statement.executeUpdate();

        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }


    }
    public static void addDay(day addDay) throws SQLException {
        dayList.add(addDay);
        Connection connection = getConnection();
        String query5 ="INSERT INTO `days` (day) VALUES(?);";
        try(PreparedStatement statement = connection.prepareStatement(query5))
        {
            ByteArrayOutputStream BOut = new ByteArrayOutputStream();
            ObjectOutputStream ObjOut = new ObjectOutputStream(BOut);
            ObjOut.writeObject(addDay);
            ObjOut.close();
            byte[] serializedObject = BOut.toByteArray();
            statement.setBytes(1,serializedObject);
            statement.executeUpdate();

        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
    public static int getmeetingID()
    {
        BufferedReader reader = null;
        String line="";
        String[] row = new String[0];
        try {
            reader = new BufferedReader(new FileReader("src/meetingId.csv"));
            while ((line=reader.readLine())!=null){
                row=line.split(",");
            }
            reader.close();
        }
        catch (IOException e){e.printStackTrace();}

        return Integer.parseInt(row[0]);
    }
    public static void incrementMeetingID()
    {
        int newId = getmeetingID()+1;
        try {
            FileWriter csvfile = new FileWriter("src/meetingId.csv");
            PrintWriter write = new PrintWriter(csvfile);
            StringBuilder build = new StringBuilder();
            build.append(newId);
            build.append(",");
            write.println(build);
            write.close();
        }
        catch (IOException e){}

    }

}