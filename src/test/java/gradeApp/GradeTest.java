package gradeApp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GradeTest {

    private Grade grade;

    public void initializeGrade() {
        this.grade = new Grade(6);
    }

    @BeforeEach
    public void setUpGradeTest() {
        initializeGrade();
    }

    @Test
    @DisplayName("Tester om Grade konstruktoer blir riktig opprettet")
    public void testGrade() {

        // Tests that the constructor contains correct grade
        assertEquals(6, grade.getGradess());

        // Tests to initialize an object with a grade higher than 6
        assertThrows(IllegalArgumentException.class, () -> {
            grade = new Grade(7);
        }, "Karakteren må være mellom 1 og 6");

        // Tests to initialize an object with a grade lower than 1.
        assertThrows(IllegalArgumentException.class, () -> {
            grade = new Grade(0);
        }, "Karakteren må være mellom 1 og 6");
    }

    @Test
    @DisplayName("Tester om karakteren blir riktig oppdatert ved set-metoden")
    public void testSetGrade() {
        assertEquals(6, grade.getGradess());
        grade.setGrade(4);
        assertEquals(4, grade.getGradess());
    }

}
