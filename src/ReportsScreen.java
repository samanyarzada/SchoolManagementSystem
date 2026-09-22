
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.Date;

public class ReportsScreen {

    private Scene scene;

    public ReportsScreen(Stage stage, Admin admin) {

        Label title = new Label("Reports");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Button backBtn = new Button("Back to Dashboard");
        backBtn.setOnAction(e -> stage.setScene(new DashboardScreen(stage, admin).getScene()));

        TextArea output = new TextArea();
        output.setEditable(false);
        output.setPrefHeight(350);
        output.setStyle("-fx-font-family: monospace; -fx-font-size: 13px;");

        Button studentReportBtn = new Button("Generate Student Report");
        studentReportBtn.setStyle("-fx-background-color: #2e86de; -fx-text-fill: white;");

        Button teacherReportBtn = new Button("Generate Teacher Report");
        teacherReportBtn.setStyle("-fx-background-color: #10ac84; -fx-text-fill: white;");

        Button fullReportBtn = new Button("Generate Full Report");
        fullReportBtn.setStyle("-fx-background-color: #636e72; -fx-text-fill: white;");

        studentReportBtn.setOnAction(e -> {
            StringBuilder sb = new StringBuilder();
            sb.append("===== STUDENT REPORT =====\n");
            sb.append("Generated: ").append(new Date()).append("\n\n");
            Student[] students = admin.getStudents();
            if (students.length == 0) {
                sb.append("No students registered.\n");
            } else {
                for (Student s : students) {
                    sb.append("ID: ").append(s.getID())
                      .append(" | Name: ").append(s.getName())
                      .append(" | Grade: ").append(s.getGrade())
                      .append(" | Student No: ").append(s.getStudentNumber())
                      .append("\n");
                }
            }
            sb.append("\nTotal Students: ").append(students.length);
            output.setText(sb.toString());
        });

        teacherReportBtn.setOnAction(e -> {
            StringBuilder sb = new StringBuilder();
            sb.append("===== TEACHER REPORT =====\n");
            sb.append("Generated: ").append(new Date()).append("\n\n");
            Teacher[] teachers = admin.getTeachers();
            if (teachers.length == 0) {
                sb.append("No teachers registered.\n");
            } else {
                for (Teacher t : teachers) {
                    sb.append("ID: ").append(t.getID())
                      .append(" | Name: ").append(t.getName())
                      .append(" | Subject: ").append(t.getSubject())
                      .append(" | Type: ").append(t.getType())
                      .append(" | Salary: ").append(String.format("%.2f", t.calculateSalary()))
                      .append("\n");
                }
            }
            sb.append("\nTotal Teachers: ").append(teachers.length);
            output.setText(sb.toString());
        });

        fullReportBtn.setOnAction(e -> {
            StringBuilder sb = new StringBuilder();
            sb.append("===== FULL SCHOOL REPORT =====\n");
            sb.append("Generated: ").append(new Date()).append("\n");
            sb.append("Admin: ").append(admin.getUsername()).append(" (").append(admin.getAdminId()).append(")\n\n");

            sb.append("--- Students (").append(admin.getStudents().length).append(") ---\n");
            for (Student s : admin.getStudents()) {
                sb.append("  • ").append(s.getName()).append(" | ").append(s.getGrade()).append("\n");
            }

            sb.append("\n--- Teachers (").append(admin.getTeachers().length).append(") ---\n");
            for (Teacher t : admin.getTeachers()) {
                sb.append("  • ").append(t.getName()).append(" | ").append(t.getSubject())
                  .append(" | ").append(t.getType()).append("\n");
            }

            sb.append("\n--- Classrooms (").append(admin.getClassrooms().length).append(") ---\n");
            for (Classroom c : admin.getClassrooms()) {
                sb.append("  • ").append(c.getName()).append(" | Floor: ").append(c.getFloor())
                  .append(" | Capacity: ").append(c.getCapacity()).append("\n");
            }

            sb.append("\n--- Courses (").append(CoursesScreen.getAllCourses().size()).append(") ---\n");
            for (Course c : CoursesScreen.getAllCourses()) {
                sb.append("  • ").append(c.getName()).append(" | Credits: ").append(c.getCredits()).append("\n");
            }

            output.setText(sb.toString());
        });

        HBox buttons = new HBox(12, studentReportBtn, teacherReportBtn, fullReportBtn);
        buttons.setAlignment(Pos.CENTER_LEFT);

        VBox root = new VBox(12, title, backBtn, new Separator(), buttons, output);
        root.setPadding(new Insets(25));
        root.setStyle("-fx-background-color: #f0f4f8;");

        scene = new Scene(root, 900, 650);
    }

    public Scene getScene() { return scene; }
}