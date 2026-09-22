
public class Course {

    private String courseId;
    private String name;
    private String description;
    private int credits;

    private Classroom classroom;
    private Teacher teacher;

    private static final int MAX_STUDENTS = 50;
    private Student[] students;
    private int studentCount;

    public Course(String courseId, String name, String description, int credits, Classroom classroom) throws Exception {

        if (courseId == null || courseId.trim().isEmpty()) {
            throw new Exception("Course ID cannot be empty.");
        }

        if (name == null || name.trim().isEmpty()) {
            throw new Exception("Course name cannot be empty.");
        }

        if (credits <= 0) {
            throw new Exception("Credits must be greater than zero.");
        }

        if (classroom == null) {
            throw new Exception("Classroom cannot be null.");
        }

        this.courseId = courseId;
        this.name = name;
        this.description = description;
        this.credits = credits;
        this.classroom = classroom;

        this.students = new Student[MAX_STUDENTS];
        this.studentCount = 0;
        this.teacher = null;
    }

    // Add student if not already enrolled
    public void addStudent(Student student) throws Exception {

        if (student == null) {
            throw new Exception("Student cannot be null.");
        }

        for (int i = 0; i < studentCount; i++) {
            if (students[i].equals(student)) {
                throw new Exception("Student already enrolled.");
            }
        }

        if (studentCount >= MAX_STUDENTS) {
            throw new Exception("Course is full.");
        }

        students[studentCount++] = student;
    }

    // Remove student from course
    public void removeStudent(Student student) throws Exception {

        if (student == null) {
            throw new Exception("Student cannot be null.");
        }

        int index = -1;

        for (int i = 0; i < studentCount; i++) {
            if (students[i].equals(student)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            throw new Exception("Student not found in course.");
        }

        for (int i = index; i < studentCount - 1; i++) {
            students[i] = students[i + 1];
        }

        students[--studentCount] = null;
    }

    // Get enrolled students
    public Student[] getStudents() {
        Student[] result = new Student[studentCount];
        for (int i = 0; i < studentCount; i++) {
            result[i] = students[i];
        }
        return result;
    }

    // Assign teacher
    public void setTeacher(Teacher teacher) throws Exception {
        if (teacher == null) {
            throw new Exception("Teacher cannot be null.");
        }
        this.teacher = teacher;
    }

    // Getters
    public String getCourseId() {
        return courseId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCredits() {
        return credits;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    // Display summary
    public void displayInfo() {
        System.out.println("Course ID: " + courseId);
        System.out.println("Name: " + name);
        System.out.println("Credits: " + credits);
        System.out.println("Students: " + studentCount);
    }
}