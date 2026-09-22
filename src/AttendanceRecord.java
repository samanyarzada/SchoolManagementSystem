
// Represents a single attendance entry for one student
public class AttendanceRecord {

    // Related student
    private Student student;

    // Status: PRESENT, ABSENT, LATE, EXCUSED
    private String status;

    // Optional note (reason, etc.)
    private String note;

    public AttendanceRecord(Student student, String status, String note) throws Exception {

        if (student == null) {
            throw new Exception("Student cannot be null.");
        }

        if (status == null || status.trim().isEmpty()) {
            throw new Exception("Status cannot be empty.");
        }

        this.student = student;
        this.status = status;
        this.note = note;
    }

    public Student getStudent() {
        return student;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) throws Exception {
        if (status == null || status.trim().isEmpty()) {
            throw new Exception("Status cannot be empty.");
        }
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void displayInfo() {
        System.out.println("Student: " + student.getName());
        System.out.println("Status: " + status);
        System.out.println("Note: " + note);
    }
}