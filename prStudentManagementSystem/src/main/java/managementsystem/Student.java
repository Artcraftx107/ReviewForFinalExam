package managementsystem;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private int studentId;
    List<Integer> enrolledCourses;

    public Student(String name, int studentId) {
        if (name.isBlank() || studentId < 0) {
            throw new IllegalArgumentException("Neither the name can be blank or the student ID can be negative");
        }
        this.name = name;
        this.studentId = studentId;
        this.enrolledCourses = new ArrayList<>();
    }

    //Getters and Setters:

    public String getName() {
        return name;
    }

    public int getStudentId() {
        return studentId;
    }

    public List<Integer> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void enrollCourse(String code){
        try {
            int courseCode = Integer.parseInt(code);
            enrolledCourses.add(courseCode);
        }catch (NumberFormatException e){
            throw new NumberFormatException("Course code "+code+" is not valid");
        }
    }

    public void withdrawCourse(String code){
        try{
            int courseCode = Integer.parseInt(code);
            enrolledCourses.remove(courseCode);
        }catch (NumberFormatException e){
            throw new NumberFormatException("Course code "+code+" is not valid");
        }
    }
}