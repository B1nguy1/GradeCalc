package gradeApp;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GradeCalcApp extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Grade calculator");
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("GradeCalc.fxml"))));
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
