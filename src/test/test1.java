import com.example.activityclubmanagementsystem.Data;
import com.example.activityclubmanagementsystem.Student;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class test1
{
    @Test
    void addStudent()
    {
        try {
            String id = "S"+Data.getNextId("students");
//            Student newStudent = new Student(id,"alex","albon","202214567")
        }
        catch (SQLException e)

        {
            fail();
        }

    }
}
