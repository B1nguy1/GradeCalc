package gradeApp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CourseTest {

    private Course course;

    @BeforeEach
    public void setUpCourseTest() {
        this.course = new Course("TDT4100", "Objektorientert programmering");
    }

    @Test
    @DisplayName("Tester om konstruktoeren for Course blir riktig opprettet")
    public void testCourse() {
        assertEquals("TDT4100", course.getCourseID());
        assertEquals("Objektorientert programmering", course.getCourseName());
        assertThrows(IllegalArgumentException.class, () -> {
            this.course = new Course("IT", "OOP");
        }, "Lengden på courseID tilfredsstiller ikke!");

        assertThrows(IllegalArgumentException.class, () -> {
            this.course = new Course(null, null);
        }, "Course kan ikke være null");
    }

}
