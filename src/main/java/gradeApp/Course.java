package gradeApp;

public class Course {

    private String courseID;
    private String courseName;

    /**
     * Initializes a Course object containing courseID and courseName
     * 
     * @param courseID   the courseID of the course
     * @param courseName the CourseName of the course
     * @throws IllegalArgumentException if courseID or courseName is invalid
     */
    public Course(String courseID, String courseName) {
        if (courseID == null || courseName == null) {
            throw new IllegalArgumentException("CourseID eller courseName kan ikke være null");
        }
        if (checkCourseLength(courseID, courseName)) {
            throw new IllegalArgumentException("Lengden på courseID eller courseName er litt for kort");
        }
        if (!checkOnlyLetters(courseName)) {
            throw new IllegalArgumentException("CourseName kan bare inneholde bokstaver");
        }
        this.courseID = courseID;
        this.courseName = courseName;
    }

    /**
     * Source:
     * https://www.codegrepper.com/code-examples/java/check+if+string+does+not+contain+numbers+java
     * Checks if courseName only contains letters
     * 
     * @param courseName courseName
     * @return boolean true based on valid or invalid courseName
     */
    private boolean checkOnlyLetters(String courseName) {
        return !courseName.matches(".*\\d.*");
    }

    /**
     * Check if courseID or courseName satisfies the required length
     * 
     * @param courseID   the courseID
     * @param courseName the courseName
     * @return a boolean based on validation of courseID and courseName
     */
    private boolean checkCourseLength(String courseID, String courseName) {
        return (courseID.length() < 4 || courseName.length() < 3);
    }

    public String getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    @Override
    public String toString() {
        return getCourseID() + ":" + getCourseName();
    }
}
