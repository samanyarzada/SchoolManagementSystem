
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.Date;

public class TeachersScreen {

    private Scene scene;
    private TableView<Teacher> table = new TableView<>();
    private ObservableList<Teacher> data = FXCollections.observableArrayList();

    public TeachersScreen(Stage stage, Admin admin) {

        Label title = new Label("Teachers");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Button backBtn = new Button("Back to Dashboard");
        backBtn.setOnAction(e -> stage.setScene(new DashboardScreen(stage, admin).getScene()));

        // Table columns
        TableColumn<Teacher, String> idCol      = new TableColumn<>("ID");
        idCol.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getID()));

        TableColumn<Teacher, String> nameCol    = new TableColumn<>("Name");
        nameCol.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getName()));

        TableColumn<Teacher, String> subjectCol = new TableColumn<>("Subject");
        subjectCol.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getSubject()));

        TableColumn<Teacher, String> typeCol    = new TableColumn<>("Type");
        typeCol.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getType()));

        TableColumn<Teacher, String> salaryCol  = new TableColumn<>("Monthly Salary");
        salaryCol.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(
                String.format("%.2f", d.getValue().calculateSalary())));

        table.getColumns().addAll(idCol, nameCol, subjectCol, typeCol, salaryCol);
        table.setItems(data);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        refreshTable(admin);

        // Form fields
        TextField idField       = new TextField(); idField.setPromptText("Teacher ID");
        TextField nameField     = new TextField(); nameField.setPromptText("Full Name");
        TextField ageField      = new TextField(); ageField.setPromptText("Age");
        TextField genderField   = new TextField(); genderField.setPromptText("Gender");
        TextField addressField  = new TextField(); addressField.setPromptText("Address");
        TextField phoneField    = new TextField(); phoneField.setPromptText("Phone");
        TextField emailField    = new TextField(); emailField.setPromptText("Email");
        TextField empIdField    = new TextField(); empIdField.setPromptText("Employee ID");
        TextField subjectField  = new TextField(); subjectField.setPromptText("Subject");
        TextField salaryField   = new TextField(); salaryField.setPromptText("Base Salary");
        TextField bonusField    = new TextField(); bonusField.setPromptText("Annual Bonus (Full-Time only)");
        TextField hourlyField   = new TextField(); hourlyField.setPromptText("Hourly Rate (Part-Time only)");
        TextField hoursField    = new TextField(); hoursField.setPromptText("Hours/Week (Part-Time only)");

        ToggleGroup typeGroup = new ToggleGroup();
        RadioButton fullTimeRb = new RadioButton("Full-Time");
        RadioButton partTimeRb = new RadioButton("Part-Time");
        fullTimeRb.setToggleGroup(typeGroup);
        partTimeRb.setToggleGroup(typeGroup);
        fullTimeRb.setSelected(true);

        Label errorLabel = new Label("");
        errorLabel.setStyle("-fx-text-fill: red;");

        Button addBtn = new Button("Add Teacher");
        addBtn.setStyle("-fx-background-color: #10ac84; -fx-text-fill: white; -fx-font-size: 13px;");

        addBtn.setOnAction(e -> {
            try {
                int age        = Integer.parseInt(ageField.getText().trim());
                double salary  = Double.parseDouble(salaryField.getText().trim());
                boolean isFullTime = fullTimeRb.isSelected();

                Teacher t;
                if (isFullTime) {
                    double bonus = Double.parseDouble(bonusField.getText().trim());
                    t = new FullTimeTeacher(
                        idField.getText().trim(), nameField.getText().trim(), age,
                        genderField.getText().trim(), addressField.getText().trim(),
                        phoneField.getText().trim(), emailField.getText().trim(),
                        empIdField.getText().trim(), subjectField.getText().trim(),
                        "FULL_TIME", salary, new Date(), bonus);
                } else {
                    double hourly = Double.parseDouble(hourlyField.getText().trim());
                    int hours     = Integer.parseInt(hoursField.getText().trim());
                    t = new PartTimeTeacher(
                        idField.getText().trim(), nameField.getText().trim(), age,
                        genderField.getText().trim(), addressField.getText().trim(),
                        phoneField.getText().trim(), emailField.getText().trim(),
                        empIdField.getText().trim(), subjectField.getText().trim(),
                        "PART_TIME", salary, new Date(), hourly, hours);
                }

                admin.addTeacher(t);
                refreshTable(admin);
                clearFields(idField, nameField, ageField, genderField, addressField,
                            phoneField, emailField, empIdField, subjectField,
                            salaryField, bonusField, hourlyField, hoursField);
                errorLabel.setText("");

            } catch (NumberFormatException ex) {
                errorLabel.setText("Please enter valid numeric values.");
            } catch (Exception ex) {
                errorLabel.setText(ex.getMessage());
            }
        });

        GridPane form = new GridPane();
        form.setHgap(10); form.setVgap(8);
        form.addRow(0, idField, nameField, ageField);
        form.addRow(1, genderField, addressField, phoneField);
        form.addRow(2, emailField, empIdField, subjectField);
        form.addRow(3, salaryField, bonusField, new HBox(15, fullTimeRb, partTimeRb));
        form.addRow(4, hourlyField, hoursField);

        VBox root = new VBox(12, title, backBtn, new Separator(), table, new Separator(),
                new Label("Add New Teacher:"), form, errorLabel, addBtn);
        root.setPadding(new Insets(25));
        root.setStyle("-fx-background-color: #f0f4f8;");

        scene = new Scene(root, 900, 650);
    }

    private void refreshTable(Admin admin) {
        data.clear();
        for (Teacher t : admin.getTeachers()) data.add(t);
    }

    private void clearFields(TextField... fields) {
        for (TextField f : fields) f.clear();
    }

    public Scene getScene() { return scene; }
}