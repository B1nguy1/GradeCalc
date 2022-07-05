package gradeApp;

import java.util.Arrays;
import java.util.List;

public class Grade {

    private int gradess;
    static Integer[] grades = { 1, 2, 3, 4, 5, 6 };
    private static List<Integer> validGrades = Arrays.asList(grades);

    /**
     * Initializes a Grade object contains gradess
     * 
     * @param gradess the gradess of the grade
     * @throws IllegalArgumentException if gradess is invalid
     */
    public Grade(int gradess) {
        if (!validateGrade(gradess)) {
            throw new IllegalArgumentException("Vennligst velg en gyldig karakter");
        }
        this.gradess = gradess;
    }

    /**
     * Checks if gradess satisfies list of valid grades
     * 
     * @param gradess int gradess
     * @return a boolean based on validation of gradess param
     */
    private boolean validateGrade(int gradess) {
        return validGrades.contains(gradess);
    }

    public void setGrade(int gradess) {
        if (!validateGrade(gradess)) {
            throw new IllegalArgumentException("Velg gyldig karakter");
        }
        this.gradess = gradess;
    }

    public int getGradess() {
        return gradess;
    }

    @Override
    public String toString() {
        return String.valueOf(getGradess());
    }
}