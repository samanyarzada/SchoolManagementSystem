
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class CoursesScreen {

    private Scene scene;
    private TableView<Course> table = new TableView<>();
    private ObservableList<Course> data = FXCollections.observableArrayList();

    // Admin holds all courses — we maintain our own list here
    private static ObservableList<Course> allCourses = FXCollections.observableArrayList();

    public static ObservableList<Course> getAllCourses() { return allCourses; }

    public CoursesScreen(Stage stage, Admin admin) {

        Label title = new Label("Courses");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Button backBtn = new Button("Back to Dashboard");
        backBtn.setOnAction(e -> stage.setScene(new DashboardScreen(stage, admin).getScene()));

        TableColumn<Course, String> idCol      = new TableColumn<>("Course ID");
        idCol.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getCourseId()));

        TableColumn<Course, String> nameCol    = new TableColumn<>("Name");
        nameCol.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getName()));

        TableColumn<Course, String> creditsCol = new TableColumn<>("Credits");
        creditsCol.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(
                String.valueOf(d.getValue().getCredits())));

        TableColumn<Course, String> roomCol    = new TableColumn<>("Classroom");
        roomCol.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(
                d.getValue().getClassroom().getName()));

        TableColumn<Course, String> descCol    = new TableColumn<>("Description");
        descCol.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getDescription()));

        table.getColumns().addAll(idCol, nameCol, creditsCol, roomCol, descCol);
        table.setItems(data);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        data.setAll(allCourses);

        TextField idField   = new TextField(); idField.setPromptText("Course ID (e.g. CRS-01)");
        TextField nameField = new TextField(); nameField.setPromptText("Course Name");
        TextField credField = new TextField(); credField.setPromptText("Credits");
        TextField descField = new TextField(); descField.setPromptText("Description");

        ComboBox<Classroom> classroomBox = new ComboBox<>();
        classroomBox.setPromptText("Select Classroom");
        classroomBox.getItems().addAll(admin.getClassrooms());
        classroomBox.setConverter(new javafx.util.StringConverter<>() {
            public String toString(Classroom c) { return c == null ? "" : c.getName(); }
            public Classroom fromString(String s) { return null; }
        });

        Label errorLabel = new Label("");
        errorLabel.setStyle("-fx-text-fill: red;");

        Button addBtn = new Button("Add Course");
        addBtn.setStyle("-fx-background-color: #8854d0; -fx-text-fill: white; -fx-font-size: 13px;");

        addBtn.setOnAction(e -> {
            try {
                if (classroomBox.getValue() == null) {
                    errorLabel.setText("Please select a classroom.");
                    return;
                }
                int credits = Integer.parseInt(credField.getText().trim());
                Course c = new Course(
                    idField.getText().trim(),
                    nameField.getText().trim(),
                    descField.getText().trim(),
                    credits,
                    classroomBox.getValue()
                );
                allCourses.add(c);
                data.setAll(allCourses);
                clearFields(idField, nameField, credField, descField);
                classroomBox.setValue(null);
                errorLabel.setText("");
            } catch (NumberFormatException ex) {
                errorLabel.setText("Credits must be a number.");
            } catch (Exception ex) {
                errorLabel.setText(ex.getMessage());
            }
        });

        GridPane form = new GridPane();
        form.setHgap(10); form.setVgap(8);
        form.addRow(0, idField, nameField, credField);
        form.addRow(1, descField, classroomBox);

        VBox root = new VBox(12, title, backBtn, new Separator(), table, new Separator(),
                new Label("Add New Course:"), form, errorLabel, addBtn);
        root.setPadding(new Insets(25));
        root.setStyle("-fx-background-color: #f0f4f8;");

        scene = new Scene(root, 900, 650);
    }

    private void clearFields(TextField... fields) {
        for (TextField f : fields) f.clear();
    }

    public Scene getScene() { return scene; }
}