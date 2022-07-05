package gradeApp;

/*
    A class using the same attributes and getters and setters methods as Course and Grade class
    This class will be used to initialize a table in GradeCalcController that displays CourseID, courseName and Grade. 
*/
public class UserObj {

    private String courseID;
    private String courseName;
    private int gradess;

    /**
     * Initializes a UserObj object with grade and course
     * 
     * @param grade  the Grade object
     * @param course the Course object
     */
    public UserObj(Grade grade, Course course) {
        this.gradess = grade.getGradess();
        this.courseID = course.getCourseID();
        this.courseName = course.getCourseName();
    }

    public String getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getGradess() {
        return gradess;
    }
}
