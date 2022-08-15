package gradeApp;

import javafx.fxml.FXML;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Map;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

public class GradeCalcController {

    private GradeFileManager gradeFileManager = new GradeFileManager();
    private GradesLogic gl = new GradesLogic();
    private Map<Integer, Integer> gradesMap;
    Alert alert = new Alert(AlertType.NONE);

    @FXML
    private TableView<UserObj> courseAndGradeTable;

    @FXML
    private TableColumn<UserObj, Integer> gradeColumn;

    @FXML
    private TableColumn<UserObj, String> courseIDColumn, courseNameColumn;

    @FXML
    private TextField courseIDField, courseNameField, gradeField, saveTextField;

    @FXML
    private TextArea avgTextArea, medianTextArea, highestGradeTextArea, lowestGradeTextArea;

    @FXML
    private BarChart<String, Integer> gradeChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private void initialize() {
        try {
            courseIDColumn.setCellValueFactory(new PropertyValueFactory<>("courseID"));
            courseNameColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));
            gradeColumn.setCellValueFactory(new PropertyValueFactory<>("gradess"));
            ObservableList<UserObj> observableList = FXCollections.observableArrayList();
            courseAndGradeTable.setPlaceholder(new Label("Legg til fag og karakterer"));
            courseAndGradeTable.setItems(observableList);

            gradeChart.setTitle("Statistikk over antall karakterer");
            xAxis.setLabel("Karakterer");
            yAxis.setLabel("Antall");
            xAxis.setTickLabelFill(Color.WHITE);
            yAxis.setTickLabelFill(Color.WHITE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean checkEmptyFields() {
        return !courseIDField.getText().isBlank() && !courseNameField.getText().isBlank()
                && !gradeField.getText().isBlank();
    }

    @FXML
    private void handleAction(ActionEvent event) {
        try {
            if (checkEmptyFields()) {
                Course course = new Course(courseIDField.getText(), courseNameField.getText());
                Grade grade = new Grade(Integer.parseInt(gradeField.getText()));
                UserObj userObj = new UserObj(grade, course);
                gl.addCourse(course);
                gl.addGrades(grade);
                courseAndGradeTable.getItems().add(userObj);
            } else {
                alert.setAlertType(AlertType.INFORMATION);
                alert.setHeaderText("FEILMELDING");
                alert.setContentText("Vennligst fyll ut alle feltene!");
                alert.showAndWait();
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    // Displays amount of each grade in a given bar chart
    private void displayBarChart() {
        gradesMap = gl.getGradesMap(gl.getGrades());
        XYChart.Series<String, Integer> series1 = new XYChart.Series<String, Integer>();
        for (Map.Entry<Integer, Integer> gradeEntry : gradesMap.entrySet()) {
            String gradeKey = String.valueOf(gradeEntry.getKey());
            Integer amountOfGrade = gradeEntry.getValue();
            XYChart.Data<String, Integer> gradeData = new XYChart.Data<String, Integer>(gradeKey, amountOfGrade);
            gradeData.nodeProperty().addListener(new ChangeListener<Node>() {
                @Override
                public void changed(ObservableValue<? extends Node> observable, Node oldGradeData, Node newGradeData) {
                    newGradeData.setStyle("-fx-bar-fill:#AAFF00");
                }
            });
            series1.getData().add(gradeData);
        }
        gradeChart.getData().clear();
        gradeChart.getData().add(series1);
        gradeChart.setAnimated(false);
    }

    // Calculates average, median, lowest and highest grade, and shows statistics
    // of grades
    @FXML
    private void calculateAction() {
        try {
            NumberFormat twoDPlaces = new DecimalFormat("##.##");
            avgTextArea.setText(String.valueOf(twoDPlaces.format(gl.calculateAvgGrade(gl.getGrades()))));
            medianTextArea.setText(String.valueOf(gl.calculateMedianGrade(gl.getGrades())));
            highestGradeTextArea.setText(String.valueOf(gl.getHighestGrade(gl.getGrades())));
            lowestGradeTextArea.setText(String.valueOf(gl.getLowestGrade(gl.getGrades())));
            gl.setAvgGrade(gl.calculateAvgGrade(gl.getGrades()));
            displayBarChart();
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    // Saves .txt file to textfiles folder
    @FXML
    private void handleSaveAction() throws FileNotFoundException {
        if (!saveTextField.getText().toLowerCase().isBlank()) {
            gradeFileManager.write(saveTextField.getText(), gl);
            alert.setAlertType(AlertType.CONFIRMATION);
            alert.setHeaderText("Lagring av fil");
            alert.setContentText("Fil " + saveTextField.getText() + ".txt" + " er lagret!");
            alert.showAndWait();
        } else {
            alert.setAlertType(AlertType.ERROR);
            alert.setTitle("Feilmld om tekstfil");
            alert.setHeaderText("Feilmelding:");
            alert.setContentText("Vennligst skriv inn filnavn.");
            alert.showAndWait();
        }
    }

    // Loads file to the application
    @FXML
    private void handleLoadAction() {
        try {
            if (!saveTextField.getText().toLowerCase().isBlank()) {
                gl = gradeFileManager.load(saveTextField.getText());
                avgTextArea.setText(String.valueOf(gl.getAvgGrade()));
                medianTextArea.setText(String.valueOf(gl.getMedianGrade()));
                gl.setAvgGrade(gl.calculateAvgGrade(gl.getGrades()));
                gl.setMedianGrade(gl.calculateMedianGrade(gl.getGrades()));

                displayBarChart();
                gradeField.setText(null);
                courseIDField.setText(null);
                courseNameField.setText(null);
            } else {
                alert.setAlertType(AlertType.ERROR);
                alert.setHeaderText("FEILMELDING");
                alert.setContentText("Vennligst skriv inn et filnavn");
                alert.showAndWait();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            alert.setAlertType(AlertType.ERROR);
            alert.setHeaderText("FEILMELDING");
            alert.setContentText("Fil " + saveTextField.getText() + " eksisterer ikke");
            alert.showAndWait();
        }
    }

    // Clear all text fields and areas
    private void resetTexts() {
        courseIDField.setText(null);
        courseNameField.setText(null);
        gradeField.setText(null);
        avgTextArea.setText(null);
        medianTextArea.setText(null);
        highestGradeTextArea.setText(null);
        lowestGradeTextArea.setText(null);
    }

    // Clears all data
    @FXML
    private void clearData() {
        try {
            for (int i = 0; i < courseAndGradeTable.getItems().size(); i++) {
                courseAndGradeTable.getItems().clear();
            }
            gradeChart.getData().clear();
            gl.removeGrades(gl.getGrades());
            gl.removeCourses(gl.getCourses());
            gradeChart.setAnimated(false);
            gradesMap.clear();
            resetTexts();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
