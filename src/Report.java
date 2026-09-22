
import java.util.Date;

// Represents system-generated reports (student, teacher, attendance)
public class Report {

    private String reportId;
    private String title;
    private Date generatedDate;

    public Report(String reportId, String title, Date generatedDate) throws Exception {

        if (reportId == null || reportId.trim().isEmpty()) {
            throw new Exception("Report ID cannot be empty.");
        }

        if (title == null || title.trim().isEmpty()) {
            throw new Exception("Title cannot be empty.");
        }

        if (generatedDate == null) {
            throw new Exception("Generated date cannot be null.");
        }

        this.reportId = reportId;
        this.title = title;
        this.generatedDate = generatedDate;
    }

    // Student report
    public void generateStudentReport() {
        System.out.println("Student Report");
        System.out.println("Report ID: " + reportId);
        System.out.println("Date: " + generatedDate);
    }

    // Teacher report
    public void generateTeacherReport() {
        System.out.println("Teacher Report");
        System.out.println("Report ID: " + reportId);
        System.out.println("Date: " + generatedDate);
    }

    // Attendance report for a course within date range
    public void generateAttendanceReport(Course course, Date from, Date to) throws Exception {

        if (course == null) {
            throw new Exception("Course cannot be null.");
        }

        if (from == null || to == null) {
            throw new Exception("Date range cannot be null.");
        }

        if (from.after(to)) {
            throw new Exception("Invalid date range.");
        }

        System.out.println("Attendance Report");
        System.out.println("Course: " + course.getName());
        System.out.println("From: " + from);
        System.out.println("To: " + to);
    }

    // Getters
    public String getReportId() {
        return reportId;
    }

    public String getTitle() {
        return title;
    }

    public Date getGeneratedDate() {
        return generatedDate;
    }

    // Display summary
    public void displayInfo() {
        System.out.println("Report ID: " + reportId);
        System.out.println("Title: " + title);
        System.out.println("Date: " + generatedDate);
    }
}