
public class Classroom {

    private String classroomId;
    private String name;
    private int capacity;
    private String floor;
    private String description;
    private Teacher teacher;

    private Student[] students;
    private int studentCount;

    public Classroom(String classroomId, String name, int capacity, String floor, String description) throws Exception {

        if (classroomId == null || classroomId.trim().isEmpty()) {
            throw new Exception("Classroom ID cannot be empty.");
        }

        if (name == null || name.trim().isEmpty()) {
            throw new Exception("Classroom name cannot be empty.");
        }

        if (capacity <= 0) {
            throw new Exception("Capacity must be greater than zero.");
        }

        this.classroomId = classroomId;
        this.name = name;
        this.capacity = capacity;
        this.floor = floor;
        this.description = description;

        this.students = new Student[capacity];
        this.studentCount = 0;
    }

    // Add student if not already present and capacity allows
    public void addStudent(Student student) throws Exception {

        if (student == null) {
            throw new Exception("Student cannot be null.");
        }

        for (int i = 0; i < studentCount; i++) {
            if (students[i].equals(student)) {
                throw new Exception("Student already exists in classroom.");
            }
        }

        if (studentCount >= capacity) {
            throw new Exception("Classroom capacity reached.");
        }

        students[studentCount++] = student;
    }

    // Remove student from classroom
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
            throw new Exception("Student not found.");
        }

        for (int i = index; i < studentCount - 1; i++) {
            students[i] = students[i + 1];
        }

        students[--studentCount] = null;
    }

    // Assign teacher
    public void setTeacher(Teacher teacher) throws Exception {
        if (teacher == null) {
            throw new Exception("Teacher cannot be null");
        }
        this.teacher = teacher;
    }

    // Get all students
    public Student[] getStudents() {
        Student[] result = new Student[studentCount];
        for (int i = 0; i < studentCount; i++) {
            result[i] = students[i];
        }
        return result;
    }

    // Getters
    public Teacher getTeacher() {
        return teacher;
    }

    public String getClassroomId() {
        return classroomId;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getFloor() {
        return floor;
    }

    public String getDescription() {
        return description;
    }

    // Display classroom summary
    public void displayInfo() {
        System.out.println("Classroom ID: " + classroomId);
        System.out.println("Name: " + name);
        System.out.println("Capacity: " + capacity);
        System.out.println("Floor: " + floor);
        System.out.println("Description: " + description);
        System.out.println("Students: " + studentCount);
    }
}