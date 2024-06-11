package managementsystem;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class StudentManagementSystem {
    private Map<String, Student> students;
    private Map<String, Course> courses;

    public StudentManagementSystem(){
        students=new TreeMap<>();
        courses=new TreeMap<>();
    }

    //Student Management Methods
    public void addStudent(Student student){
        students.put(student.getStudentId(), student);
    }

    public void updateStudent(Student student){
        students.put(student.getStudentId(), student);
    }

    public void deleteStudent(String studentId){
        students.remove(studentId);
    }

    public Student searchStudent(String studentId){
        return students.get(studentId);
    }

    //Course Management methods

    public void addCourse(Course course){
        courses.put(course.getCourseCode(), course);
    }

    public void updateCourse(Course course){
        courses.put(course.getCourseCode(), course);
    }

    public void deleteCourse(String courseCode){
        courses.remove(courseCode);
    }

    public Course searchCourse(String courseCode){
        return courses.get(courseCode);
    }

    //Enrollment Methods

    public void enrollStudentInCourse(String studentId, String courseId){
        Student student = students.get(studentId);
        if(student!=null && courses.containsKey(courseId)){
            student.enrollCourse(courseId);
        }
    }

    public void withdrawStudentFromCourse(String studentID, String courseID){
        Student student = students.get(studentID);
        if(student!=null){
            student.withdrawCourse(courseID);
        }
    }

    // File I/O methods

    public void saveStudentsToFile(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Student student : students.values()) {
                writer.write(student.getName() + "," + student.getStudentId() + "," + String.join(";", student.getEnrolledCourses()));
                writer.newLine();
            }
        }
    }

    public void loadStudentsFromFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Student student = new Student(parts[0], parts[1]);
                if (parts.length > 2) {  // Check if there are any enrolled courses
                    String[] enrolledCourses = parts[2].split(";");
                    for (String course : enrolledCourses) {
                        student.enrollCourse(course);
                    }
                }
                students.put(student.getStudentId(), student);
            }
        }
    }

    public void saveCoursesToFile(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Course course : courses.values()) {
                writer.write(course.getCourseName() + "," + course.getCourseCode() + "," + course.getDescription());
                writer.newLine();
            }
        }
    }

    public void loadCoursesFromFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Course course = new Course(parts[0], parts[1], parts[2]);
                courses.put(course.getCourseCode(), course);
            }
        }
    }
}