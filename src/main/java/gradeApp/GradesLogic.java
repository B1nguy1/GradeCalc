package gradeApp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

// Class to handle courses and grades

public class GradesLogic {

    private List<Grade> grades = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();
    private HashMap<Integer, Integer> gradesMap = new HashMap<>();
    private double avgGrade = 0;
    private int medianGrade = 0;

    // Adds a grade object if it does not already exists
    public void addGrades(Grade grade) {
        if (!grades.contains(grade)) {
            grades.add(grade);
        } else {
            throw new IllegalArgumentException("Finnes allerede!");
        }
    }

    // Adds a Course object if it does not already exists
    public void addCourse(Course course) {
        if (!courses.contains(course)) {
            courses.add(course);
        } else {
            throw new IllegalArgumentException("Eksisterer allerede!");
        }
    }

    /**
     * Removes all courses from a course list
     * 
     * @param allCourses a list that contains all courses
     */
    public void removeCourses(List<Course> allCourses) {
        courses.removeAll(allCourses);
    }

    /**
     * Removes all grades from a grade list
     * 
     * @param allGrades a list that contains all grades
     */
    public void removeGrades(List<Grade> allGrades) {
        grades.removeAll(allGrades);
    }

    // Updates current average of grades
    public void setAvgGrade(double avgGrade) {
        this.avgGrade = avgGrade;
    }

    public double getAvgGrade() {
        return avgGrade;
    }

    public List<Course> getCourses() {
        return new ArrayList<>(courses);
    }

    public List<Grade> getGrades() {
        return new ArrayList<>(grades);
    }

    public void setMedianGrade(int medianGrade) {
        this.medianGrade = medianGrade;
    }

    public int getMedianGrade() {
        return medianGrade;
    }

    public int getAmountGrades() {
        return grades.size();
    }

    /**
     * Calculates and finds the lowest number in a given list
     * 
     * @param grades list of grades
     * @return an int which is the lowest number (grade)
     */
    public int getLowestGrade(List<Grade> grades) {
        List<Integer> copyGrades = new ArrayList<>();
        for (Grade gradesss : grades) {
            Integer gradeInteger = Integer.valueOf(gradesss.getGradess());
            copyGrades.add(gradeInteger);
        }
        int minGrade = copyGrades.get(0);
        for (int i : copyGrades) {
            minGrade = minGrade < i ? minGrade : i;
        }
        return minGrade;
    }

    /**
     * Calculates and finds the highest number (grade) in a given list
     * 
     * @param grades list of grades
     * @return a integer which is the highest grade
     */
    public int getHighestGrade(List<Grade> grades) {
        List<Integer> copyGrades = new ArrayList<>();
        for (Grade gradesss : grades) {
            Integer gradeInteger = Integer.valueOf(gradesss.getGradess());
            copyGrades.add(gradeInteger);
        }
        return Collections.max(copyGrades);
    }

    /**
     * Calculates and find average of all grades. Returns a number which
     * represents average of all grades.
     * 
     * @param grades list of grades
     * @return a double which is the result of the calculation of average
     */
    public double calculateAvgGrade(List<Grade> grades) {
        if (grades.size() > 0) {
            double sumOfGrades = grades.stream().mapToDouble(Grade::getGradess).sum();
            this.avgGrade = sumOfGrades / getAmountGrades();
            return this.avgGrade;
        }
        return 0;
    }

    /**
     * Find occurences of each grade that satisfies list of valid grades
     * 
     * @param grades list of grades
     * @return a HashMap containing grades as keys and occurences as values.
     */

    public HashMap<Integer, Integer> getGradesMap(List<Grade> grades) {
        List<Integer> copyGrades = new ArrayList<>();
        for (Grade gradesss : grades) {
            Integer gradeInteger = Integer.valueOf(gradesss.getGradess());
            copyGrades.add(gradeInteger);
        }
        for (Integer grade : new HashSet<>(copyGrades)) {
            gradesMap.put(grade, Collections.frequency(copyGrades, grade));
        }
        return gradesMap;
    }

    /**
     * Calculates and find median of all grades.
     * 
     * @param grades list of all grades
     * @return an int which is the result of median of grade list
     */
    public int calculateMedianGrade(List<Grade> grades) {
        List<Integer> copyGrades = new ArrayList<>();
        for (Grade grade : grades) {
            Integer gradeInteger = Integer.valueOf(grade.getGradess());
            copyGrades.add(gradeInteger);
            // Inbuilt function to sort grades in ascending order
            Collections.sort(copyGrades);
        }
        // Gets the number of grades in copyGrades list
        int gradesLength = copyGrades.size();
        // Calculates the middle of the list
        int middle = gradesLength / 2;

        /**
         * If gradesLength is an even number
         * Inspiration from:
         * https://stackoverflow.com/questions/3691940/finding-the-median-value-of-an-array
         */

        if (gradesLength % 2 == 0) {
            medianGrade = (copyGrades.get(middle - 1) + (copyGrades.get(middle)) / 2);
            return medianGrade;
        } else {
            // If gradesLength is an odd number
            medianGrade = copyGrades.get(middle);
            return medianGrade;
        }
    }

    public int getCoursesLength() {
        return courses.size();
    }
}
