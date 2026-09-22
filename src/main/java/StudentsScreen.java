
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.Date;

public class StudentsScreen {

    private Scene scene;
    private TableView<Student> table = new TableView<>();
    private ObservableList<Student> data = FXCollections.observableArrayList();

    public StudentsScreen(Stage stage, Admin admin) {

        Label title = new Label("Students");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Button backBtn = new Button("Back to Dashboard");
        backBtn.setOnAction(e -> stage.setScene(new DashboardScreen(stage, admin).getScene()));

        // Table columns
        TableColumn<Student, String> idCol     = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("ID"));

        TableColumn<Student, String> nameCol   = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Student, Integer> ageCol   = new TableColumn<>("Age");
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));

        TableColumn<Student, String> gradeCol  = new TableColumn<>("Grade");
        gradeCol.setCellValueFactory(new PropertyValueFactory<>("grade"));

        TableColumn<Student, String> numCol    = new TableColumn<>("Student No.");
        numCol.setCellValueFactory(new PropertyValueFactory<>("studentNumber"));

        TableColumn<Student, String> phoneCol  = new TableColumn<>("Phone");
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));

        TableColumn<Student, String> emailCol  = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        table.getColumns().addAll(idCol, nameCol, ageCol, gradeCol, numCol, phoneCol, emailCol);
        table.setItems(data);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        refreshTable(admin);

        // Form fields
        TextField idField      = new TextField(); idField.setPromptText("Student ID (e.g. S-001)");
        TextField nameField    = new TextField(); nameField.setPromptText("Full Name");
        TextField ageField     = new TextField(); ageField.setPromptText("Age");
        TextField gradeField   = new TextField(); gradeField.setPromptText("Grade (e.g. 10th)");
        TextField numField     = new TextField(); numField.setPromptText("Student Number");
        TextField phoneField   = new TextField(); phoneField.setPromptText("Phone");
        TextField emailField   = new TextField(); emailField.setPromptText("Email");
        TextField genderField  = new TextField(); genderField.setPromptText("Gender");
        TextField addressField = new TextField(); addressField.setPromptText("Address");

        Label errorLabel = new Label("");
        errorLabel.setStyle("-fx-text-fill: red;");

        Button addBtn = new Button("Add Student");
        addBtn.setStyle("-fx-background-color: #2e86de; -fx-text-fill: white; -fx-font-size: 13px;");

        addBtn.setOnAction(e -> {
            try {
                // Classroom is required — use the first available or create a placeholder
                Classroom[] classrooms = admin.getClassrooms();
                if (classrooms.length == 0) {
                    errorLabel.setText("Please create a Classroom first.");
                    return;
                }

                Classroom classroom = classrooms[0];
                int age = Integer.parseInt(ageField.getText().trim());

                Student s = new Student(
                    idField.getText().trim(),
                    nameField.getText().trim(),
                    age,
                    genderField.getText().trim(),
                    addressField.getText().trim(),
                    phoneField.getText().trim(),
                    emailField.getText().trim(),
                    gradeField.getText().trim(),
                    numField.getText().trim(),
                    new Date(),
                    classroom
                );
                admin.addStudent(s);
                refreshTable(admin);
                clearFields(idField, nameField, ageField, gradeField, numField, phoneField, emailField, genderField, addressField);
                errorLabel.setText("");
            } catch (NumberFormatException ex) {
                errorLabel.setText("Age must be a number.");
            } catch (Exception ex) {
                errorLabel.setText(ex.getMessage());
            }
        });

        // Form layout
        GridPane form = new GridPane();
        form.setHgap(10); form.setVgap(8);
        form.addRow(0, idField, nameField, ageField);
        form.addRow(1, gradeField, numField, phoneField);
        form.addRow(2, emailField, genderField, addressField);

        VBox root = new VBox(12, title, backBtn, new Separator(), table, new Separator(),
                new Label("Add New Student:"), form, errorLabel, addBtn);
        root.setPadding(new Insets(25));
        root.setStyle("-fx-background-color: #f0f4f8;");

        scene = new Scene(root, 900, 650);
    }

    private void refreshTable(Admin admin) {
        data.clear();
        for (Student s : admin.getStudents()) {
            data.add(s);
        }
    }

    private void clearFields(TextField... fields) {
        for (TextField f : fields) f.clear();
    }

    public Scene getScene() { return scene; }
}