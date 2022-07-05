package gradeApp;

import java.io.FileNotFoundException;

public interface IFileManager {

    public void write(String fileName, GradesLogic gl) throws FileNotFoundException;

    public GradesLogic load(String filename) throws FileNotFoundException;
}
