import managementsystem.*;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem();

        // Add some courses
        sms.addCourse(new Course("Math 101", "MATH101", "Introduction to Mathematics"));
        sms.addCourse(new Course("History 101", "HIST101", "Introduction to History"));

        // Add some students
        sms.addStudent(new Student("Alice", "S001"));
        sms.addStudent(new Student("Bob", "S002"));

        // Enroll students in courses
        sms.enrollStudentInCourse("S001", "MATH101");
        sms.enrollStudentInCourse("S002", "HIST101");
        sms.enrollStudentInCourse("S001", "HIST101");

        // Save data to files
        try {
            sms.saveStudentsToFile("students.txt");
            sms.saveCoursesToFile("courses.txt");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        // Load data from files
        StudentManagementSystem sms2 = new StudentManagementSystem();
        try {
            sms2.loadStudentsFromFile("students.txt");
            sms2.loadCoursesFromFile("courses.txt");
            /* If you want the used files to be deleted after loading from them, uncomment this code :)
            File studentsFile = new File("students.txt");
            File coursesFile = new File("courses.txt");
            studentsFile.delete();
            coursesFile.delete();
            */
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        // Search for a student and a course
        Student student = sms2.searchStudent("S001");
        Course course = sms2.searchCourse("MATH101");

        System.out.println("Student: " + student.getName() + ", Enrolled courses: " + student.getEnrolledCourses());
        System.out.println("Course: " + course.getCourseName() + ", Description: " + course.getDescription());
    }
}