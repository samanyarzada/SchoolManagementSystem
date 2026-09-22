
public class Timetable {

    private String timetableId;
    private Classroom classroom;

    private static final int MAX_SCHEDULE_ITEMS = 50;
    private ScheduleItem[] schedule;
    private int scheduleCount;

    public Timetable(String timetableId, Classroom classroom) throws Exception {

        if (timetableId == null || timetableId.trim().isEmpty()) {
            throw new Exception("Timetable ID cannot be empty.");
        }

        if (classroom == null) {
            throw new Exception("Classroom cannot be null.");
        }

        this.timetableId = timetableId;
        this.classroom = classroom;

        this.schedule = new ScheduleItem[MAX_SCHEDULE_ITEMS];
        this.scheduleCount = 0;
    }

    // Add a lesson if no time conflict exists
    public void addLesson(String day, String startTime, String endTime, Course course) throws Exception {

        if (scheduleCount >= MAX_SCHEDULE_ITEMS) {
            throw new Exception("Timetable is full.");
        }

        for (int i = 0; i < scheduleCount; i++) {
            if (schedule[i].getDay().equalsIgnoreCase(day) &&
                schedule[i].getStartTime().equals(startTime) &&
                schedule[i].getEndTime().equals(endTime)) {

                throw new Exception("Schedule conflict detected.");
            }
        }

        schedule[scheduleCount++] = new ScheduleItem(day, startTime, endTime, course);
    }

    // Get all scheduled lessons
    public ScheduleItem[] getSchedule() {
        ScheduleItem[] result = new ScheduleItem[scheduleCount];

        for (int i = 0; i < scheduleCount; i++) {
            result[i] = schedule[i];
        }

        return result;
    }

    // Getters
    public String getTimetableId() {
        return timetableId;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    // Display summary
    public void displayInfo() {
        System.out.println("Timetable ID: " + timetableId);
        System.out.println("Classroom: " + classroom.getName());
        System.out.println("Scheduled Lessons: " + scheduleCount);
    }
}