
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {

    private static Admin admin;
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        primaryStage.setTitle("School Management System");
        primaryStage.setWidth(900);
        primaryStage.setHeight(650);
        primaryStage.setResizable(false);

        // Create the admin at startup
        try {
            admin = new Admin("ADM-01", "admin", "1234");
        } catch (Exception e) {
            System.out.println("Failed to create admin: " + e.getMessage());
            return;
        }

        // Show login screen first
        LoginScreen loginScreen = new LoginScreen(primaryStage, admin);
        primaryStage.setScene(loginScreen.getScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}