
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.Date;

public class AttendanceScreen {

    private Scene scene;

    public AttendanceScreen(Stage stage, Admin admin) {

        Label title = new Label("Attendance");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Button backBtn = new Button("Back to Dashboard");
        backBtn.setOnAction(e -> stage.setScene(new DashboardScreen(stage, admin).getScene()));

        ComboBox<Course> courseBox = new ComboBox<>();
        courseBox.setPromptText("Select Course");
        courseBox.getItems().addAll(CoursesScreen.getAllCourses());
        courseBox.setConverter(new javafx.util.StringConverter<>() {
            public String toString(Course c) { return c == null ? "" : c.getName(); }
            public Course fromString(String s) { return null; }
        });

        TextField attIdField = new TextField();
        attIdField.setPromptText("Attendance Session ID (e.g. ATT-01)");

        VBox studentRows = new VBox(8);
        Label infoLabel  = new Label("Select a course to load its students.");
        infoLabel.setStyle("-fx-text-fill: #555;");

        Label errorLabel = new Label("");
        errorLabel.setStyle("-fx-text-fill: red;");

        Label successLabel = new Label("");
        successLabel.setStyle("-fx-text-fill: green;");

        // Status options per student row
        String[] statuses = {"PRESENT", "ABSENT", "LATE", "EXCUSED"};

        // Store status combos per student
        ObservableList<ComboBox<String>> statusCombos = FXCollections.observableArrayList();
        ObservableList<Student> loadedStudents        = FXCollections.observableArrayList();

        courseBox.setOnAction(e -> {
            Course selected = courseBox.getValue();
            studentRows.getChildren().clear();
            statusCombos.clear();
            loadedStudents.clear();

            if (selected == null) return;

            Student[] students = selected.getStudents();
            if (students.length == 0) {
                infoLabel.setText("No students enrolled in this course.");
                return;
            }

            infoLabel.setText("Mark attendance for each student:");

            for (Student s : students) {
                ComboBox<String> statusBox = new ComboBox<>();
                statusBox.getItems().addAll(statuses);
                statusBox.setValue("PRESENT");

                HBox row = new HBox(15, new Label(s.getName()), statusBox);
                row.setAlignment(Pos.CENTER_LEFT);
                studentRows.getChildren().add(row);
                statusCombos.add(statusBox);
                loadedStudents.add(s);
            }
        });

        Button saveBtn = new Button("Save Attendance");
        saveBtn.setStyle("-fx-background-color: #e55039; -fx-text-fill: white; -fx-font-size: 13px;");

        saveBtn.setOnAction(e -> {
            try {
                if (courseBox.getValue() == null) {
                    errorLabel.setText("Please select a course.");
                    return;
                }
                if (attIdField.getText().trim().isEmpty()) {
                    errorLabel.setText("Please enter an Attendance Session ID.");
                    return;
                }
                if (loadedStudents.isEmpty()) {
                    errorLabel.setText("No students to mark.");
                    return;
                }

                Attendance attendance = new Attendance(attIdField.getText().trim(), new Date(), courseBox.getValue());

                for (int i = 0; i < loadedStudents.size(); i++) {
                    String status = statusCombos.get(i).getValue();
                    AttendanceRecord record = new AttendanceRecord(loadedStudents.get(i), status, "");
                    attendance.addRecord(record);
                }

                successLabel.setText("Attendance saved for " + loadedStudents.size() + " student(s).");
                errorLabel.setText("");
                attIdField.clear();

            } catch (Exception ex) {
                errorLabel.setText(ex.getMessage());
            }
        });

        ScrollPane scroll = new ScrollPane(studentRows);
        scroll.setFitToWidth(true);
        scroll.setPrefHeight(200);

        VBox root = new VBox(12, title, backBtn, new Separator(),
                new HBox(10, new Label("Course:"), courseBox, new Label("Session ID:"), attIdField),
                infoLabel, scroll, errorLabel, successLabel, saveBtn);
        root.setPadding(new Insets(25));
        root.setStyle("-fx-background-color: #f0f4f8;");

        scene = new Scene(root, 900, 650);
    }

    public Scene getScene() { return scene; }
}