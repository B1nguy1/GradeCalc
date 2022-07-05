package gradeApp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GradesLogicTest {

    private GradesLogic gradesLogic;

    @BeforeEach
    public void setUpGradesLogicTest() {
        gradesLogic = new GradesLogic();
    }

    @Test
    @DisplayName("Test for å legge til karakter")
    public void testAddGrade() {
        Grade grade1 = new Grade(2);
        Grade grade2 = new Grade(4);

        gradesLogic.addGrades(grade1);
        gradesLogic.addGrades(grade2);

        assertEquals(2, gradesLogic.getAmountGrades());
        assertThrows(IllegalArgumentException.class, () -> {
            gradesLogic.addGrades(grade1);
        }, "Grade1 er allerede blitt lagret!");
    }

    @Test
    @DisplayName("Test for å legge til fag")
    public void testAddCourse() {
        Course course1 = new Course("TDT4100", "OOP");
        Course course2 = new Course("TDT4300", "Datavarehus");

        gradesLogic.addCourse(course1);
        gradesLogic.addCourse(course2);

        assertEquals(2, gradesLogic.getCoursesLength());
        assertThrows(IllegalArgumentException.class, () -> {
            gradesLogic.addCourse(course2);
        }, "Course2 er allerede blitt lagt til!");
    }

    @Test
    @DisplayName("Test for å finne riktig dårligste karakter")
    public void testLowestGrade() {
        Grade grade1 = new Grade(2);
        Grade grade2 = new Grade(4);
        Grade grade3 = new Grade(3);
        gradesLogic.addGrades(grade1);
        gradesLogic.addGrades(grade2);
        gradesLogic.addGrades(grade3);
        assertEquals(2, gradesLogic.getLowestGrade(gradesLogic.getGrades()));
    }

    @Test
    @DisplayName("Test for å finne riktig beste karakter")
    public void testHighestGrade() {
        Grade grade1 = new Grade(2);
        Grade grade2 = new Grade(4);
        Grade grade3 = new Grade(3);
        gradesLogic.addGrades(grade1);
        gradesLogic.addGrades(grade2);
        gradesLogic.addGrades(grade3);
        assertEquals(4, gradesLogic.getHighestGrade(gradesLogic.getGrades()));
    }

    @Test
    @DisplayName("Test om kalkulasjoner av gjennomsnittet er korrekt")
    public void testAvgGrade() {
        Grade grade1 = new Grade(2);
        Grade grade2 = new Grade(4);
        Grade grade3 = new Grade(3);
        double avgGrade = 3.0;
        gradesLogic.addGrades(grade1);
        gradesLogic.addGrades(grade2);
        gradesLogic.addGrades(grade3);
        gradesLogic.calculateAvgGrade(gradesLogic.getGrades());
        assertEquals(avgGrade, gradesLogic.getAvgGrade());
    }

    @Test
    @DisplayName("Test for å finne ut om medianen er riktig kalkulert")
    public void testMedianGrade() {
        Grade grade1 = new Grade(2);
        Grade grade2 = new Grade(4);
        Grade grade3 = new Grade(3);
        int medianGrade = 3;
        gradesLogic.addGrades(grade1);
        gradesLogic.addGrades(grade2);
        gradesLogic.addGrades(grade3);
        gradesLogic.calculateMedianGrade(gradesLogic.getGrades());
        assertEquals(medianGrade, gradesLogic.getMedianGrade());
    }

    @Test
    @DisplayName("Test for å sjekke om alle karakterene blir tømt fra lista")
    public void testRemoveGrades() {
        Grade grade1 = new Grade(2);
        Grade grade2 = new Grade(4);
        Grade grade3 = new Grade(3);
        gradesLogic.addGrades(grade1);
        gradesLogic.addGrades(grade2);
        gradesLogic.addGrades(grade3);
        gradesLogic.removeGrades(gradesLogic.getGrades());
        assertEquals(0, gradesLogic.getAmountGrades());
    }

    @Test
    @DisplayName("Test for å sjekke om alle fag blir tømt fra lista")
    public void testRemoveCourses() {
        Course course1 = new Course("TDT4100", "OOP");
        Course course2 = new Course("TDT4300", "Datavarehus");
        Course course3 = new Course("TDT4110", "ITGK");
        gradesLogic.addCourse(course1);
        gradesLogic.addCourse(course2);
        gradesLogic.addCourse(course3);
        gradesLogic.removeCourses(gradesLogic.getCourses());
        assertEquals(0, gradesLogic.getCoursesLength());
    }
}