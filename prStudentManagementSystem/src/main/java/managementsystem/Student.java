package managementsystem;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private String studentId;
    List<String> enrolledCourses;

    public Student(String name, String studentId) {
        if (name.isBlank() || studentId.isBlank()) {
            throw new IllegalArgumentException("Neither the name can be blank or the student ID can be incorrect");
        }
        this.name = name;
        this.studentId = studentId;
        this.enrolledCourses = new ArrayList<>();
    }

    //Getters and Setters:

    public String getName() {
        return name;
    }

    public String getStudentId() {
        return studentId;
    }

    public List<String> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void enrollCourse(String code){
        enrolledCourses.add(code);
    }

    public void withdrawCourse(String code){
        enrolledCourses.remove(code);
    }
}