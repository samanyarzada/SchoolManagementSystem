
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class DashboardScreen {

    private Scene scene;

    public DashboardScreen(Stage stage, Admin admin) {

        Label title = new Label("Dashboard");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Label welcome = new Label("Welcome, " + admin.getUsername() + "  |  Admin ID: " + admin.getAdminId());
        welcome.setStyle("-fx-font-size: 13px; -fx-text-fill: #555;");

        // Navigation buttons
        Button studentsBtn    = createNavButton("Students",    "#2e86de");
        Button teachersBtn    = createNavButton("Teachers",    "#10ac84");
        Button classroomsBtn  = createNavButton("Classrooms",  "#ee5a24");
        Button coursesBtn     = createNavButton("Courses",     "#8854d0");
        Button enrollmentBtn  = createNavButton("Enrollment",  "#f9ca24");
        Button attendanceBtn  = createNavButton("Attendance",  "#e55039");
        Button timetableBtn   = createNavButton("Timetable",   "#00b894");
        Button reportsBtn     = createNavButton("Reports",     "#636e72");
        Button logoutBtn      = createNavButton("Logout",      "#b2bec3");

        // Actions
        studentsBtn.setOnAction(e -> stage.setScene(new StudentsScreen(stage, admin).getScene()));
        teachersBtn.setOnAction(e -> stage.setScene(new TeachersScreen(stage, admin).getScene()));
        classroomsBtn.setOnAction(e -> stage.setScene(new ClassroomsScreen(stage, admin).getScene()));
        coursesBtn.setOnAction(e -> stage.setScene(new CoursesScreen(stage, admin).getScene()));
        enrollmentBtn.setOnAction(e -> stage.setScene(new EnrollmentScreen(stage, admin).getScene()));
        attendanceBtn.setOnAction(e -> stage.setScene(new AttendanceScreen(stage, admin).getScene()));
        timetableBtn.setOnAction(e -> stage.setScene(new TimetableScreen(stage, admin).getScene()));
        reportsBtn.setOnAction(e -> stage.setScene(new ReportsScreen(stage, admin).getScene()));
        logoutBtn.setOnAction(e -> stage.setScene(new LoginScreen(stage, admin).getScene()));

        GridPane grid = new GridPane();
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setAlignment(Pos.CENTER);

        grid.add(studentsBtn,   0, 0); grid.add(teachersBtn,   1, 0); grid.add(classroomsBtn, 2, 0);
        grid.add(coursesBtn,    0, 1); grid.add(enrollmentBtn, 1, 1); grid.add(attendanceBtn, 2, 1);
        grid.add(timetableBtn,  0, 2); grid.add(reportsBtn,    1, 2); grid.add(logoutBtn,     2, 2);

        VBox root = new VBox(18, title, welcome, new Separator(), grid);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(40));
        root.setStyle("-fx-background-color: #f0f4f8;");

        scene = new Scene(root, 900, 650);
    }

    private Button createNavButton(String text, String color) {
        Button btn = new Button(text);
        btn.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white; " +
                     "-fx-font-size: 14px; -fx-font-weight: bold; -fx-pref-width: 200; -fx-pref-height: 70; -fx-background-radius: 8;");
        return btn;
    }

    public Scene getScene() {
        return scene;
    }
}