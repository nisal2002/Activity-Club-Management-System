package com.example.activityclubmanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.sql.*;

public class Data {
    private static final ObservableList<Teacher> teacherList = FXCollections.observableArrayList();
    private  static final ObservableList<Student> studentList = FXCollections.observableArrayList();
    private static final ObservableList<Admin> adminList = FXCollections.observableArrayList();

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
}