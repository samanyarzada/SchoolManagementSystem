
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ClassroomsScreen {

    private Scene scene;
    private TableView<Classroom> table = new TableView<>();
    private ObservableList<Classroom> data = FXCollections.observableArrayList();

    public ClassroomsScreen(Stage stage, Admin admin) {

        Label title = new Label("Classrooms");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Button backBtn = new Button("Back to Dashboard");
        backBtn.setOnAction(e -> stage.setScene(new DashboardScreen(stage, admin).getScene()));

        TableColumn<Classroom, String> idCol    = new TableColumn<>("ID");
        idCol.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getClassroomId()));

        TableColumn<Classroom, String> nameCol  = new TableColumn<>("Name");
        nameCol.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getName()));

        TableColumn<Classroom, String> capCol   = new TableColumn<>("Capacity");
        capCol.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(
                String.valueOf(d.getValue().getCapacity())));

        TableColumn<Classroom, String> floorCol = new TableColumn<>("Floor");
        floorCol.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getFloor()));

        TableColumn<Classroom, String> descCol  = new TableColumn<>("Description");
        descCol.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getDescription()));

        table.getColumns().addAll(idCol, nameCol, capCol, floorCol, descCol);
        table.setItems(data);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        refreshTable(admin);

        TextField idField    = new TextField(); idField.setPromptText("Classroom ID (e.g. C-101)");
        TextField nameField  = new TextField(); nameField.setPromptText("Room Name");
        TextField capField   = new TextField(); capField.setPromptText("Capacity");
        TextField floorField = new TextField(); floorField.setPromptText("Floor");
        TextField descField  = new TextField(); descField.setPromptText("Description");

        Label errorLabel = new Label("");
        errorLabel.setStyle("-fx-text-fill: red;");

        Button addBtn = new Button("Add Classroom");
        addBtn.setStyle("-fx-background-color: #ee5a24; -fx-text-fill: white; -fx-font-size: 13px;");

        addBtn.setOnAction(e -> {
            try {
                int capacity = Integer.parseInt(capField.getText().trim());
                Classroom c = new Classroom(
                    idField.getText().trim(),
                    nameField.getText().trim(),
                    capacity,
                    floorField.getText().trim(),
                    descField.getText().trim()
                );
                admin.createClassroom(c);
                refreshTable(admin);
                clearFields(idField, nameField, capField, floorField, descField);
                errorLabel.setText("");
            } catch (NumberFormatException ex) {
                errorLabel.setText("Capacity must be a number.");
            } catch (Exception ex) {
                errorLabel.setText(ex.getMessage());
            }
        });

        GridPane form = new GridPane();
        form.setHgap(10); form.setVgap(8);
        form.addRow(0, idField, nameField, capField);
        form.addRow(1, floorField, descField);

        VBox root = new VBox(12, title, backBtn, new Separator(), table, new Separator(),
                new Label("Add New Classroom:"), form, errorLabel, addBtn);
        root.setPadding(new Insets(25));
        root.setStyle("-fx-background-color: #f0f4f8;");

        scene = new Scene(root, 900, 650);
    }

    private void refreshTable(Admin admin) {
        data.clear();
        for (Classroom c : admin.getClassrooms()) data.add(c);
    }

    private void clearFields(TextField... fields) {
        for (TextField f : fields) f.clear();
    }

    public Scene getScene() { return scene; }
}
