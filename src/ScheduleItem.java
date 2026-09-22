
// Represents a timetable slot for a course
public class ScheduleItem {

    private String day;
    private String startTime;
    private String endTime;
    private Course course;

    public ScheduleItem(String day, String startTime, String endTime, Course course) throws Exception {

        if (day == null || day.trim().isEmpty()) {
            throw new Exception("Day cannot be empty.");
        }

        if (startTime == null || startTime.trim().isEmpty()) {
            throw new Exception("Start time cannot be empty.");
        }

        if (endTime == null || endTime.trim().isEmpty()) {
            throw new Exception("End time cannot be empty.");
        }

        if (course == null) {
            throw new Exception("Course cannot be null.");
        }

        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.course = course;
    }

    // Getters
    public String getDay() {
        return day;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public Course getCourse() {
        return course;
    }

    // Display schedule slot info
    public void displayInfo() {
        System.out.println("Day: " + day);
        System.out.println("Time: " + startTime + " - " + endTime);
        System.out.println("Course: " + course.getName());
    }
}