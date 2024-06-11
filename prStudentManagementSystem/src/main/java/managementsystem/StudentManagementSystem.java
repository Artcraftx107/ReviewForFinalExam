package managementsystem;

import java.util.Map;
import java.util.TreeMap;

public class StudentManagementSystem {
    private Map<Integer, Student> students;
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

    public void deleteStudent(int studentId){
        students.remove(studentId);
    }

    public Student searchStudent(int studentId){
        return students.get(studentId);
    }

    //Course Management methods

    
}
