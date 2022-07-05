package gradeApp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GradesFileManagerTest {

    private GradesLogic gradesLogic = new GradesLogic();
    private GradeFileManager gradeFileManager = new GradeFileManager();

    @BeforeEach
    public void setUpGradesFileManagerTest() {
        gradesLogic.addGrades(new Grade(5));
        gradesLogic.addGrades(new Grade(6));
        gradesLogic.addGrades((new Grade(4)));

        gradesLogic.calculateAvgGrade(gradesLogic.getGrades());
        gradesLogic.calculateMedianGrade(gradesLogic.getGrades());
    }

    @Test
    @DisplayName("Tester om en gyldig fil blir lastet inn")
    public void testLoadGradesFile() {
        GradesLogic gl;
        try {
            gl = gradeFileManager.load("gradess");
        } catch (FileNotFoundException e) {
            fail("Finner ikke den gitte filen!");
            return;
        }
        assertEquals(gradesLogic.getAvgGrade(), gl.getAvgGrade());
        assertEquals(gradesLogic.calculateMedianGrade(gradesLogic.getGrades()),
                gl.calculateMedianGrade(gl.getGrades()));
        assertEquals(gradesLogic.getAmountGrades(), gl.getAmountGrades());
        for (int i = 0; i < gradesLogic.getAmountGrades(); i++) {
            assertEquals(gradesLogic.getGrades().get(i).getGradess(), gl.getGrades().get(i).getGradess());
        }
    }

    @Test
    @DisplayName("Tester om en ikke gyldig fil blir lastet inn")
    public void testLoadInvalidGradesFile() {
        assertThrows(FileNotFoundException.class, () -> {
            gradesLogic = gradeFileManager.load("oop");
        }, "The file is invalid!");
    }

}