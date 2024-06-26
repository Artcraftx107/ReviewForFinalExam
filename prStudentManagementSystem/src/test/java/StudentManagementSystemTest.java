import managementsystem.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class StudentManagementSystemTest {
    private StudentManagementSystem sms;
    private static final String STUDENTS_FILE = "students_test.txt";
    private static final String COURSES_FILE = "courses_test.txt";

    @BeforeEach
    public void setUp() {
        sms = new StudentManagementSystem();
    }

    @Test
    public void testAddAndSearchStudent() {
        Student student = new Student("Alice", "S001");
        sms.addStudent(student);

        Student found = sms.searchStudent("S001");
        assertNotNull(found);
        assertEquals("Alice", found.getName());
    }

    @Test
    public void testUpdateStudent() {
        Student student = new Student("Alice", "S001");
        sms.addStudent(student);

        student.setName("Alice Updated");
        sms.updateStudent(student);

        Student found = sms.searchStudent("S001");
        assertNotNull(found);
        assertEquals("Alice Updated", found.getName());
    }

    @Test
    public void testDeleteStudent() {
        Student student = new Student("Alice", "S001");
        sms.addStudent(student);

        sms.deleteStudent("S001");
        Student found = sms.searchStudent("S001");
        assertNull(found);
    }

    @Test
    public void testAddAndSearchCourse() {
        Course course = new Course("Math 101", "MATH101", "Introduction to Mathematics");
        sms.addCourse(course);

        Course found = sms.searchCourse("MATH101");
        assertNotNull(found);
        assertEquals("Math 101", found.getCourseName());
    }

    @Test
    public void testUpdateCourse() {
        Course course = new Course("Math 101", "MATH101", "Introduction to Mathematics");
        sms.addCourse(course);

        course.setCourseName("Math 101 Updated");
        sms.updateCourse(course);

        Course found = sms.searchCourse("MATH101");
        assertNotNull(found);
        assertEquals("Math 101 Updated", found.getCourseName());
    }

    @Test
    public void testDeleteCourse() {
        Course course = new Course("Math 101", "MATH101", "Introduction to Mathematics");
        sms.addCourse(course);

        sms.deleteCourse("MATH101");
        Course found = sms.searchCourse("MATH101");
        assertNull(found);
    }

    @Test
    public void testEnrollAndWithdrawStudentFromCourse() {
        Student student = new Student("Alice", "S001");
        Course course = new Course("Math 101", "MATH101", "Introduction to Mathematics");
        sms.addStudent(student);
        sms.addCourse(course);

        sms.enrollStudentInCourse("S001", "MATH101");

        Student found = sms.searchStudent("S001");
        List<String> enrolledCourses = found.getEnrolledCourses();
        assertTrue(enrolledCourses.contains("MATH101"));

        sms.withdrawStudentFromCourse("S001", "MATH101");

        found = sms.searchStudent("S001");
        enrolledCourses = found.getEnrolledCourses();
        assertFalse(enrolledCourses.contains("MATH101"));
    }

    @Test
    public void testSaveAndLoadStudents() throws IOException {
        Student student = new Student("Alice", "S001");
        student.enrollCourse("MATH101");
        sms.addStudent(student);
        sms.saveStudentsToFile(STUDENTS_FILE);

        StudentManagementSystem loadedSms = new StudentManagementSystem();
        loadedSms.loadStudentsFromFile(STUDENTS_FILE);
        //Code to delete the used file for testing after loading from it
        File auxFile = new File(STUDENTS_FILE);
        auxFile.delete();

        Student found = loadedSms.searchStudent("S001");
        assertNotNull(found);
        assertEquals("Alice", found.getName());
        assertTrue(found.getEnrolledCourses().contains("MATH101"));

    }

    @Test
    public void testSaveAndLoadCourses() throws IOException {
        Course course = new Course("Math 101", "MATH101", "Introduction to Mathematics");
        sms.addCourse(course);
        sms.saveCoursesToFile(COURSES_FILE);

        StudentManagementSystem loadedSms = new StudentManagementSystem();
        loadedSms.loadCoursesFromFile(COURSES_FILE);
        //Code to delete the used file for testing after loading from it
        File auxFile = new File(COURSES_FILE);
        auxFile.delete();

        Course found = loadedSms.searchCourse("MATH101");
        assertNotNull(found);
        assertEquals("Math 101", found.getCourseName());
    }
}
