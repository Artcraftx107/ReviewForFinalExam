package managementsystem;

public class Course {
    private String courseName;
    private int courseCode;
    private String description;

    public Course(String courseName, int courseCode, String description){
        if(courseName.isBlank()||description.isBlank()){
            throw new IllegalArgumentException("Neither course name or description can be blank");
        } else if (courseCode<0) {
            throw new IllegalArgumentException("The course's code cannot be negative");
        }
        this.courseName=courseName;
        this.description=description;
        this.courseCode=courseCode;
    }

    //Getters and Setters

    public String getDescription() {
        return description;
    }

    public int getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCourseCode(int courseCode) {
        this.courseCode = courseCode;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(courseName+": (").append("Code: "+courseCode).append(" Description: "+description+")");
        return "["+sb+"]";
    }
}
