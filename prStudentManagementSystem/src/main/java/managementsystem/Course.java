package managementsystem;

public class Course {
    private String courseName;
    private String courseCode;
    private String description;

    public Course(String courseName, String courseCode, String description){
        if(courseName.isBlank()||description.isBlank()||courseCode.isBlank()){
            throw new IllegalArgumentException("Neither course name, course code or description can be blank");
        }
        this.courseName=courseName;
        this.description=description;
        this.courseCode=courseCode;
    }

    //Getters and Setters

    public String getDescription() {
        return description;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}