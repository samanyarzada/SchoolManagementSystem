

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class LoginScreen {

    private Scene scene;

    public LoginScreen(Stage stage, Admin admin) {

        // Title
        Label title = new Label("School Management System");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        Label subtitle = new Label("Please log in to continue");
        subtitle.setStyle("-fx-font-size: 13px; -fx-text-fill: #666;");

        // Username field
        Label userLabel = new Label("Username:");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter username");
        usernameField.setMaxWidth(280);

        // Password field
        Label passLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter password");
        passwordField.setMaxWidth(280);

        // Error label
        Label errorLabel = new Label("");
        errorLabel.setStyle("-fx-text-fill: red; -fx-font-size: 12px;");

        // Login button
        Button loginBtn = new Button("Login");
        loginBtn.setStyle("-fx-background-color: #2e86de; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 8 30;");
        loginBtn.setDefaultButton(true);

        loginBtn.setOnAction(e -> {
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();

            if (username.isEmpty() || password.isEmpty()) {
                errorLabel.setText("Please fill in all fields.");
                return;
            }

            if (username.equals(admin.getUsername()) && password.equals("1234")) {
                DashboardScreen dashboard = new DashboardScreen(stage, admin);
                stage.setScene(dashboard.getScene());
            } else {
                errorLabel.setText("Invalid username or password.");
            }
        });

        // Layout
        VBox box = new VBox(12, title, subtitle, new Separator(),
                userLabel, usernameField,
                passLabel, passwordField,
                errorLabel, loginBtn);
        box.setAlignment(Pos.CENTER_LEFT);
        box.setPadding(new Insets(50));
        box.setMaxWidth(380);
        box.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-effect: dropshadow(gaussian, #ccc, 10, 0, 0, 2);");

        StackPane root = new StackPane(box);
        root.setStyle("-fx-background-color: #f0f4f8;");

        scene = new Scene(root, 900, 650);
    }

    public Scene getScene() {
        return scene;
    }
}