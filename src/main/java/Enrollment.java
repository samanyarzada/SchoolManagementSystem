
import java.util.Date;

// Represents relationship between Student and Course
public class Enrollment {

    private String enrollmentId;
    private Student student;
    private Course course;
    private Date enrollmentDate;
    private String status;

    public Enrollment(String enrollmentId, Student student, Course course, Date enrollmentDate, String status) throws Exception {

        if (enrollmentId == null || enrollmentId.trim().isEmpty()) {
            throw new Exception("Enrollment ID cannot be empty.");
        }

        if (student == null) {
            throw new Exception("Student cannot be null.");
        }

        if (course == null) {
            throw new Exception("Course cannot be null.");
        }

        if (enrollmentDate == null) {
            throw new Exception("Enrollment date cannot be null.");
        }

        if (status == null || status.trim().isEmpty()) {
            throw new Exception("Status cannot be empty.");
        }

        this.enrollmentId = enrollmentId;
        this.student = student;
        this.course = course;
        this.enrollmentDate = enrollmentDate;
        this.status = status;
    }

    // Update enrollment status
    public void setStatus(String status) throws Exception {
        if (status == null || status.trim().isEmpty()) {
            throw new Exception("Status cannot be empty.");
        }
        this.status = status;
    }

    // Getters
    public String getEnrollmentId() {
        return enrollmentId;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public String getStatus() {
        return status;
    }

    // Display summary
    public void displayInfo() {
        System.out.println("Enrollment ID: " + enrollmentId);
        System.out.println("Student: " + student.getName());
        System.out.println("Course: " + course.getName());
        System.out.println("Date: " + enrollmentDate);
        System.out.println("Status: " + status);
    }
}