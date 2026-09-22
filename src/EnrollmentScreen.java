
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.Date;

public class EnrollmentScreen {

    private Scene scene;
    private TableView<Enrollment> table = new TableView<>();
    private ObservableList<Enrollment> data = FXCollections.observableArrayList();
    private static ObservableList<Enrollment> allEnrollments = FXCollections.observableArrayList();

    public EnrollmentScreen(Stage stage, Admin admin) {

        Label title = new Label("Enrollment");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Button backBtn = new Button("Back to Dashboard");
        backBtn.setOnAction(e -> stage.setScene(new DashboardScreen(stage, admin).getScene()));

        TableColumn<Enrollment, String> idCol      = new TableColumn<>("Enrollment ID");
        idCol.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getEnrollmentId()));

        TableColumn<Enrollment, String> studentCol = new TableColumn<>("Student");
        studentCol.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getStudent().getName()));

        TableColumn<Enrollment, String> courseCol  = new TableColumn<>("Course");
        courseCol.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getCourse().getName()));

        TableColumn<Enrollment, String> statusCol  = new TableColumn<>("Status");
        statusCol.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getStatus()));

        TableColumn<Enrollment, String> dateCol    = new TableColumn<>("Date");
        dateCol.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getEnrollmentDate().toString()));

        table.getColumns().addAll(idCol, studentCol, courseCol, statusCol, dateCol);
        table.setItems(data);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        data.setAll(allEnrollments);

        TextField idField = new TextField(); idField.setPromptText("Enrollment ID (e.g. ENR-01)");

        ComboBox<Student> studentBox = new ComboBox<>();
        studentBox.setPromptText("Select Student");
        studentBox.getItems().addAll(admin.getStudents());
        studentBox.setConverter(new javafx.util.StringConverter<>() {
            public String toString(Student s) { return s == null ? "" : s.getName(); }
            public Student fromString(String s) { return null; }
        });

        ComboBox<Course> courseBox = new ComboBox<>();
        courseBox.setPromptText("Select Course");
        courseBox.getItems().addAll(CoursesScreen.getAllCourses());
        courseBox.setConverter(new javafx.util.StringConverter<>() {
            public String toString(Course c) { return c == null ? "" : c.getName(); }
            public Course fromString(String s) { return null; }
        });

        ComboBox<String> statusBox = new ComboBox<>();
        statusBox.getItems().addAll("ACTIVE", "INACTIVE", "COMPLETED");
        statusBox.setPromptText("Status");

        Label errorLabel = new Label("");
        errorLabel.setStyle("-fx-text-fill: red;");

        Button enrollBtn = new Button("Enroll");
        enrollBtn.setStyle("-fx-background-color: #f9ca24; -fx-text-fill: #333; -fx-font-size: 13px;");

        enrollBtn.setOnAction(e -> {
            try {
                if (studentBox.getValue() == null || courseBox.getValue() == null || statusBox.getValue() == null) {
                    errorLabel.setText("Please fill in all fields.");
                    return;
                }
                Student s = studentBox.getValue();
                Course  c = courseBox.getValue();

                c.addStudent(s);
                s.attendCourse(c);

                Enrollment enr = new Enrollment(
                    idField.getText().trim(), s, c, new Date(), statusBox.getValue()
                );
                allEnrollments.add(enr);
                data.setAll(allEnrollments);
                idField.clear();
                studentBox.setValue(null);
                courseBox.setValue(null);
                statusBox.setValue(null);
                errorLabel.setText("");
            } catch (Exception ex) {
                errorLabel.setText(ex.getMessage());
            }
        });

        GridPane form = new GridPane();
        form.setHgap(10); form.setVgap(8);
        form.addRow(0, idField, studentBox, courseBox, statusBox);

        VBox root = new VBox(12, title, backBtn, new Separator(), table, new Separator(),
                new Label("New Enrollment:"), form, errorLabel, enrollBtn);
        root.setPadding(new Insets(25));
        root.setStyle("-fx-background-color: #f0f4f8;");

        scene = new Scene(root, 900, 650);
    }

    public Scene getScene() { return scene; }
}