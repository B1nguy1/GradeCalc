package gradeApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class GradeFileManager implements IFileManager {

    @Override
    public void write(String fileName, GradesLogic gl) {
        try (PrintWriter writer = new PrintWriter(getPath(fileName))) {
            writer.println(gl.getAvgGrade());
            writer.println(gl.calculateMedianGrade(gl.getGrades()));
            writer.println(gl.getGrades());
            // Clean ethvert element som kan være med eller som ikke er med inne i "writer"
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public GradesLogic load(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(getPath(filename)));
        GradesLogic gl = new GradesLogic();
        // Leser en hel linje fra strømmen
        String avgGrade = scanner.nextLine();
        String medianGrade = scanner.nextLine();
        String allGrades = scanner.nextLine();

        gl.setAvgGrade(Double.parseDouble((avgGrade)));
        gl.setMedianGrade(Integer.parseInt(medianGrade));

        String[] gradeList = allGrades.split(",");
        for (String sGrade : gradeList) {
            // Inspiration from:
            // https://stackoverflow.com/questions/12383774/replace-all-and-in-a-string-in-java
            Grade grade = new Grade(
                    Integer.parseInt(sGrade.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(" ", "")));
            gl.addGrades(grade);
        }
        scanner.close();
        return gl;
    }

    public String getPath(String filename) {
        return "src/main/java/gradeApp/textfiles/" + filename + ".txt";
    }
}
