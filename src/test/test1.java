import com.example.activityclubmanagementsystem.*;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class test1
{
    int index =5;
    day newDay;
    int id;
    meeting newMeeting;
    int meetingId;
    club newClub;
    LocalDate date = LocalDate.now().plus(2,ChronoUnit.MONTHS);

    void init()
    {
        try {
            id = Integer.parseInt(Data.getNextId("days"));
            meetingId=Data.getmeetingID();
            Teacher newTeacher1 = new Teacher(Data.getNextId("teachers"),"sergio","perez","202234567","2023-11-20","Male","sergio@gmail.com","mexico");
             newClub = new club(Data.getNextId("clubs"),"aviation",newTeacher1,"planes");
            newDay = new day(date,id,9);
            Data.addDay(newDay);

        }
        catch (SQLException e)
        {

        }
    }

    @Test
    void addStudent()
    {
        try {
            String id = "S"+Data.getNextId("students");
            Student newStudent = new Student(id,"alex","albon","202214567","2023-11-23","Male","sa@12","asdf");
            Data.addStudent(newStudent);
            Data.updateStudentList();
            ObservableList<Student> studentList = Data.getStudentList();
            Student checkStudent = null;
            for (Student student:studentList)
            {
                if (student.getId().equals(id))
                {
                    checkStudent=student;
                    break;
                }
            }
            assertEquals(newStudent.getId(),checkStudent.getId());
            assertEquals(newStudent.getFirstName(),checkStudent.getFirstName());
            assertEquals(newStudent.getSurName(),checkStudent.getSurName());
            assertEquals(newStudent.getNic(),checkStudent.getNic());
            assertEquals(newStudent.getDob(),checkStudent.getDob());
            assertEquals(newStudent.getGender(),checkStudent.getGender());
            assertEquals(newStudent.getEmail(),checkStudent.getEmail());
            assertEquals(newStudent.getPassword(),checkStudent.getPassword());


        }
        catch (SQLException e)

        {
            fail();
        }

    }
    @Test
    void addTeacher()
    {
        try {
            String id = "T"+Data.getNextId("teachers");
            Teacher newTeacher = new Teacher(id,"sergio","perez","202234567","2023-11-20","Male","sergio@gmail.com","mexico");
            Data.addTeacher(newTeacher);
            Data.updateTeacherList();
            ObservableList<Teacher> teachers = Data.getTeacherList();
            for (Teacher teacher:teachers)
            {
                if (teacher.getId().equals(id))

                {
                    assertEquals(newTeacher.getId(),teacher.getId());
                    assertEquals(newTeacher.getFirstName(),teacher.getFirstName());
                    assertEquals(newTeacher.getSurName(),teacher.getSurName());
                    assertEquals(newTeacher.getNic(),teacher.getNic());
                    assertEquals(newTeacher.getDob(),teacher.getDob());
                    assertEquals(newTeacher.getGender(),teacher.getGender());
                    assertEquals(newTeacher.getEmail(),teacher.getEmail());
                    assertEquals(newTeacher.getPassword(),teacher.getPassword());
                }
            }



        }
        catch (SQLException e)
        {
            fail();
        }

    }
    @Test
    void addAdmin()
    {
        try {
            String id = "A"+Data.getNextId("admins");
            Admin newAdmin = new Admin(id,"sergio","perez","202234567","2023-11-20","Male","sergio@gmail.com","mexico");
            Data.addAdmin(newAdmin);
            Data.updateAdminList();
            ObservableList<Admin> admins = Data.getAdminList();
            for (Admin admin:admins)
            {
                if (admin.getId().equals(id))

                {
                    assertEquals(newAdmin.getId(),admin.getId());
                    assertEquals(newAdmin.getFirstName(),admin.getFirstName());
                    assertEquals(newAdmin.getSurName(),admin.getSurName());
                    assertEquals(newAdmin.getNic(),admin.getNic());
                    assertEquals(newAdmin.getDob(),admin.getDob());
                    assertEquals(newAdmin.getGender(),admin.getGender());
                    assertEquals(newAdmin.getEmail(),admin.getEmail());
                    assertEquals(newAdmin.getPassword(),admin.getPassword());
                }
            }



        }
        catch (SQLException e)
        {
            fail();
        }

    }
    @Test
    void updateStudent()
    {
        try {
            Random rand = new Random();
            int index = rand.nextInt(0, Data.getStudentList().size());
            Student student = Data.getStudentList().get(index);
            String id = student.getId();
            String firstN = student.getFirstName();
            String lastN = student.getSurName();
            String nic = student.getNic();
            String dob = student.getDob();
            String gender = student.getGender();
            String email = student.getEmail();
            String pwd = student.getPassword();
            boolean male=true;
            student.setFirstName(firstN + "new");
            student.setSurName(lastN+"new");
            student.setDob(LocalDate.parse(dob).plus(3, ChronoUnit.DAYS).toString());
            if (gender.equals("Male"))
            {
                student.setGender("Female");
            }
            else
            {
                male=false;
                student.setGender("Male");
            }
            student.setNic(nic+"new");
            student.setEmail(email+"new");
            student.setPassword(pwd+"new");
            Data.updateStudent(student);
            Data.updateStudentList();
            for (Student updatedStudent:Data.getStudentList())
            {
                if (updatedStudent.getId().equals(id))
                {
                    assertEquals(updatedStudent.getFirstName(),firstN+"new");
                    assertEquals(updatedStudent.getSurName(),lastN+"new");
                    assertEquals(updatedStudent.getDob(),LocalDate.parse(dob).plus(3, ChronoUnit.DAYS).toString());
                    if (male)
                    {
                        assertEquals(updatedStudent.getGender(),"Female");
                    }
                    else
                    {
                        assertEquals(updatedStudent.getGender(),"Male");
                    }

                    assertEquals(updatedStudent.getNic(),nic+"new");
                    assertEquals(updatedStudent.getEmail(),email+"new");
                    assertEquals(updatedStudent.getPassword(),pwd+"new");

                }
            }

        }
        catch (SQLException e)
        {
            fail();
        }

    }
    @Test
    void updateTeacher()
    {
        try {
            Random rand = new Random();
            int index = rand.nextInt(0, Data.getTeacherList().size());
            Teacher Teacher = Data.getTeacherList().get(index);
            String id = Teacher.getId();
            String firstN = Teacher.getFirstName();
            String lastN = Teacher.getSurName();
            String nic = Teacher.getNic();
            String dob = Teacher.getDob();
            String gender = Teacher.getGender();
            String email = Teacher.getEmail();
            String pwd = Teacher.getPassword();
            boolean male=true;
            Teacher.setFirstName(firstN + "new");
            Teacher.setSurName(lastN+"new");
            Teacher.setDob(LocalDate.parse(dob).plus(3, ChronoUnit.DAYS).toString());
            if (gender.equals("Male"))
            {
                Teacher.setGender("Female");
            }
            else
            {
                male=false;
                Teacher.setGender("Male");
            }
            Teacher.setNic(nic+"new");
            Teacher.setEmail(email+"new");
            Teacher.setPassword(pwd+"new");
            Data.updateTEacher(Teacher);
            Data.updateTeacherList();
            for (Teacher updatedTeacher:Data.getTeacherList())
            {
                if (updatedTeacher.getId().equals(id))
                {
                    assertEquals(updatedTeacher.getFirstName(),firstN+"new");
                    assertEquals(updatedTeacher.getSurName(),lastN+"new");
                    assertEquals(updatedTeacher.getDob(),LocalDate.parse(dob).plus(3, ChronoUnit.DAYS).toString());
                    if (male)
                    {
                        assertEquals(updatedTeacher.getGender(),"Female");
                    }
                    else
                    {
                        assertEquals(updatedTeacher.getGender(),"Male");
                    }

                    assertEquals(updatedTeacher.getNic(),nic+"new");
                    assertEquals(updatedTeacher.getEmail(),email+"new");
                    assertEquals(updatedTeacher.getPassword(),pwd+"new");

                }
            }

        }
        catch (SQLException e)
        {
            fail();
        }

    }
    @Test
    void updateAdmin()
    {
        try {
            Random rand = new Random();
            int index = rand.nextInt(0, Data.getAdminList().size());
            Admin Admin = Data.getAdminList().get(index);
            String id = Admin.getId();
            String firstN = Admin.getFirstName();
            String lastN = Admin.getSurName();
            String nic = Admin.getNic();
            String dob = Admin.getDob();
            String gender = Admin.getGender();
            String email = Admin.getEmail();
            String pwd = Admin.getPassword();
            boolean male=true;
            Admin.setFirstName(firstN + "new");
            Admin.setSurName(lastN+"new");
            Admin.setDob(LocalDate.parse(dob).plus(3, ChronoUnit.DAYS).toString());
            if (gender.equals("Male"))
            {
                Admin.setGender("Female");
            }
            else
            {
                male=false;
                Admin.setGender("Male");
            }
            Admin.setNic(nic+"new");
            Admin.setEmail(email+"new");
            Admin.setPassword(pwd+"new");
            Data.updateAdmin(Admin);
            Data.updateAdminList();
            for (Admin updatedAdmin:Data.getAdminList())
            {
                if (updatedAdmin.getId().equals(id))
                {
                    assertEquals(updatedAdmin.getFirstName(),firstN+"new");
                    assertEquals(updatedAdmin.getSurName(),lastN+"new");
                    assertEquals(updatedAdmin.getDob(),LocalDate.parse(dob).plus(3, ChronoUnit.DAYS).toString());
                    if (male)
                    {
                        assertEquals(updatedAdmin.getGender(),"Female");
                    }
                    else
                    {
                        assertEquals(updatedAdmin.getGender(),"Male");
                    }

                    assertEquals(updatedAdmin.getNic(),nic+"new");
                    assertEquals(updatedAdmin.getEmail(),email+"new");
                    assertEquals(updatedAdmin.getPassword(),pwd+"new");

                }
            }

        }
        catch (SQLException e)
        {
            fail();
        }

    }

    @Test
    void scheduleMeeting()
    {
        init();
        try
        {
            newMeeting = new meeting(meetingId,date,newClub,"1",index);
            newDay.getSlots().get(5).setVenue1(newMeeting);
            Data.updateDay(newDay);

            Data.updateDayList();
            for (day Day:Data.getDayList())
            {
                if (Day.getId()==id)
                {
                    assertEquals(meetingId,Day.getSlots().get(index).getVenue1().getMeetingID());
                }
            }

        }
        catch (SQLException e)
        {
            fail();
        }

    }
    @Test
    void attendMeeting()
    {

        try {
            String id = "S"+Data.getNextId("students");
            Student newStudent = new Student(id,"alex","albon","202214567","2023-11-23","Male","sa@12","asdf");
            for (day Day:Data.getDayList())
            {
                if (Day.getDay().equals(date))
                {
                    Day.getSlots().get(index).getVenue1().addAttendance(newStudent.getId());
                    Data.updateDay(Day);
                    break;
                }
            }
            boolean isAttended=false;
            for (day Day:Data.getDayList())
            {
                if (Day.getDay().equals(date))
                {
                    for (String studentID:Day.getSlots().get(index).getVenue1().getAttendence())
                    {
                        if (studentID.equals(newStudent.getId()))
                        {
                            isAttended=true;
                        }
                    }
                }
            }
            assertTrue(isAttended);



        }
        catch (SQLException e)
        {
            fail();
        }

    }



}
