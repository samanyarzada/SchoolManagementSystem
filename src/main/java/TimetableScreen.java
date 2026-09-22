
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class TimetableScreen {

    private Scene scene;
    private TableView<ScheduleItem> table = new TableView<>();
    private ObservableList<ScheduleItem> data = FXCollections.observableArrayList();
    private static ObservableList<Timetable> allTimetables = FXCollections.observableArrayList();

    public TimetableScreen(Stage stage, Admin admin) {

        Label title = new Label("Timetable");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Button backBtn = new Button("Back to Dashboard");
        backBtn.setOnAction(e -> stage.setScene(new DashboardScreen(stage, admin).getScene()));

        TableColumn<ScheduleItem, String> dayCol    = new TableColumn<>("Day");
        dayCol.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getDay()));

        TableColumn<ScheduleItem, String> startCol  = new TableColumn<>("Start");
        startCol.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getStartTime()));

        TableColumn<ScheduleItem, String> endCol    = new TableColumn<>("End");
        endCol.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getEndTime()));

        TableColumn<ScheduleItem, String> courseCol = new TableColumn<>("Course");
        courseCol.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getCourse().getName()));

        table.getColumns().addAll(dayCol, startCol, endCol, courseCol);
        table.setItems(data);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TextField ttIdField    = new TextField(); ttIdField.setPromptText("Timetable ID (e.g. TT-01)");
        TextField dayField     = new TextField(); dayField.setPromptText("Day (e.g. MONDAY)");
        TextField startField   = new TextField(); startField.setPromptText("Start Time (e.g. 09:00)");
        TextField endField     = new TextField(); endField.setPromptText("End Time (e.g. 10:30)");

        ComboBox<Classroom> classroomBox = new ComboBox<>();
        classroomBox.setPromptText("Select Classroom");
        classroomBox.getItems().addAll(admin.getClassrooms());
        classroomBox.setConverter(new javafx.util.StringConverter<>() {
            public String toString(Classroom c) { return c == null ? "" : c.getName(); }
            public Classroom fromString(String s) { return null; }
        });

        ComboBox<Course> courseBox = new ComboBox<>();
        courseBox.setPromptText("Select Course");
        courseBox.getItems().addAll(CoursesScreen.getAllCourses());
        courseBox.setConverter(new javafx.util.StringConverter<>() {
            public String toString(Course c) { return c == null ? "" : c.getName(); }
            public Course fromString(String s) { return null; }
        });

        Label errorLabel = new Label("");
        errorLabel.setStyle("-fx-text-fill: red;");

        Button addBtn = new Button("Add Lesson");
        addBtn.setStyle("-fx-background-color: #00b894; -fx-text-fill: white; -fx-font-size: 13px;");

        addBtn.setOnAction(e -> {
            try {
                if (classroomBox.getValue() == null || courseBox.getValue() == null) {
                    errorLabel.setText("Please select classroom and course.");
                    return;
                }

                Timetable tt = getTimetableForClassroom(classroomBox.getValue(), ttIdField.getText().trim());
                tt.addLesson(dayField.getText().trim(), startField.getText().trim(),
                             endField.getText().trim(), courseBox.getValue());

                // Refresh table with all lessons across all timetables
                data.clear();
                for (Timetable t : allTimetables) {
                    for (ScheduleItem item : t.getSchedule()) {
                        data.add(item);
                    }
                }

                clearFields(ttIdField, dayField, startField, endField);
                classroomBox.setValue(null);
                courseBox.setValue(null);
                errorLabel.setText("");

            } catch (Exception ex) {
                errorLabel.setText(ex.getMessage());
            }
        });

        GridPane form = new GridPane();
        form.setHgap(10); form.setVgap(8);
        form.addRow(0, ttIdField, classroomBox, courseBox);
        form.addRow(1, dayField, startField, endField);

        VBox root = new VBox(12, title, backBtn, new Separator(), table, new Separator(),
                new Label("Add Lesson:"), form, errorLabel, addBtn);
        root.setPadding(new Insets(25));
        root.setStyle("-fx-background-color: #f0f4f8;");

        scene = new Scene(root, 900, 650);
    }

    private Timetable getTimetableForClassroom(Classroom classroom, String id) throws Exception {
        for (Timetable t : allTimetables) {
            if (t.getClassroom().equals(classroom)) return t;
        }
        Timetable newTt = new Timetable(id, classroom);
        allTimetables.add(newTt);
        return newTt;
    }

    private void clearFields(TextField... fields) {
        for (TextField f : fields) f.clear();
    }

    public Scene getScene() { return scene; }
}