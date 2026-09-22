
import java.util.Date;

// Represents attendance session for a course
public class Attendance {

    // Basic identification data
    private String attendanceId;
    private Date date;
    private Course course;

    // Fixed capacity for records
    private static final int MAX_RECORDS = 100;

    // Stores attendance records
    private AttendanceRecord[] records;
    private int recordCount;

    public Attendance(String attendanceId, Date date, Course course) throws Exception {

        // Validate inputs
        if (attendanceId == null || attendanceId.trim().isEmpty()) {
            throw new Exception("Attendance ID cannot be empty.");
        }
        if (date == null) {
            throw new Exception("Date cannot be null.");
        }
        if (course == null) {
            throw new Exception("Course cannot be null.");
        }

        this.attendanceId = attendanceId;
        this.date = date;
        this.course = course;

        // Initialize storage
        this.records = new AttendanceRecord[MAX_RECORDS];
        this.recordCount = 0;
    }

    // Add a new attendance record
    public void addRecord(AttendanceRecord record) throws Exception {

        if (record == null) {
            throw new Exception("Attendance record cannot be null.");
        }

        if (recordCount >= MAX_RECORDS) {
            throw new Exception("Maximum capacity reached.");
        }

        records[recordCount++] = record;
    }

    // Get records for a specific student
    public AttendanceRecord[] getAttendanceByStudent(Student student) throws Exception {

        if (student == null) {
            throw new Exception("Student cannot be null.");
        }

        int count = 0;

        // Count matching records
        for (int i = 0; i < recordCount; i++) {
            if (records[i].getStudent().equals(student)) {
                count++;
            }
        }

        AttendanceRecord[] result = new AttendanceRecord[count];
        int index = 0;

        // Collect matching records
        for (int i = 0; i < recordCount; i++) {
            if (records[i].getStudent().equals(student)) {
                result[index++] = records[i];
            }
        }

        return result;
    }

    // Get records for this date
    public AttendanceRecord[] getAttendanceByDate(Date date) throws Exception {

        if (date == null) {
            throw new Exception("Date cannot be null.");
        }

        if (!this.date.equals(date)) {
            return new AttendanceRecord[0];
        }

        AttendanceRecord[] result = new AttendanceRecord[recordCount];

        // Copy all records
        for (int i = 0; i < recordCount; i++) {
            result[i] = records[i];
        }

        return result;
    }

    // Getters
    public String getAttendanceId() {
        return attendanceId;
    }

    public Date getDate() {
        return date;
    }

    public Course getCourse() {
        return course;
    }

    // Display summary info
    public void displayInfo() {
        System.out.println("Attendance ID: " + attendanceId);
        System.out.println("Date: " + date);
        System.out.println("Course: " + course.getName());
        System.out.println("Records: " + recordCount);
    }
}
