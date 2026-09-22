
// Admin class represents an administrator of the system
public class Admin {

    private String adminId;
    private String username;
    private String password;

    private static final int MAX_STUDENTS = 200;
    private static final int MAX_TEACHERS = 50;
    private static final int MAX_CLASSROOMS = 30;

    private Student[] students;
    private int studentCount;

    private Teacher[] teachers;
    private int teacherCount;

    private Classroom[] classrooms;
    private int classroomCount;

    public Admin(String adminId, String username, String password) throws Exception {

        if (adminId == null || adminId.trim().isEmpty()) {
            throw new Exception("Admin ID cannot be empty.");
        }

        if (username == null || username.trim().isEmpty()) {
            throw new Exception("Username cannot be empty.");
        }

        if (password == null || password.length() < 4) {
            throw new Exception("Password must be at least 4 characters long.");
        }

        this.adminId = adminId;
        this.username = username;
        this.password = password;

        this.students = new Student[MAX_STUDENTS];
        this.studentCount = 0;

        this.teachers = new Teacher[MAX_TEACHERS];
        this.teacherCount = 0;

        this.classrooms = new Classroom[MAX_CLASSROOMS];
        this.classroomCount = 0;
    }

    public void addStudent(Student student) throws Exception {
        if (student == null) {
            throw new Exception("Student cannot be null.");
        }
        if (studentCount >= MAX_STUDENTS) {
            throw new Exception("Cannot add student, maximum capacity reached.");
        }

        students[studentCount++] = student;
        System.out.println("Student registered: " + student.getName());
    }

    public void addTeacher(Teacher teacher) throws Exception {
        if (teacher == null) {
            throw new Exception("Teacher cannot be null.");
        }
        if (teacherCount >= MAX_TEACHERS) {
            throw new Exception("Cannot add teacher, maximum capacity reached.");
        }

        teachers[teacherCount++] = teacher;
        System.out.println("Teacher registered: " + teacher.getName());
    }

    public void createClassroom(Classroom classroom) throws Exception {
        if (classroom == null) {
            throw new Exception("Classroom cannot be null.");
        }
        if (classroomCount >= MAX_CLASSROOMS) {
            throw new Exception("Cannot create classroom, maximum capacity reached.");
        }

        classrooms[classroomCount++] = classroom;
        System.out.println("Classroom created: " + classroom.getName());
    }

    public void generateReports() throws Exception {
        Report report = new Report("R-" + adminId, "General School Report", new java.util.Date());
        report.generateStudentReport();
        report.generateTeacherReport();
        System.out.println("Reports generated.");
    }

    public Student[] getStudents() {
        Student[] result = new Student[studentCount];
        for (int i = 0; i < studentCount; i++) {
            result[i] = students[i];
        }
        return result;
    }

    public Teacher[] getTeachers() {
        Teacher[] result = new Teacher[teacherCount];
        for (int i = 0; i < teacherCount; i++) {
            result[i] = teachers[i];
        }
        return result;
    }

    public Classroom[] getClassrooms() {
        Classroom[] result = new Classroom[classroomCount];
        for (int i = 0; i < classroomCount; i++) {
            result[i] = classrooms[i];
        }
        return result;
    }

    public String getAdminId() {
        return adminId;
    }

    public String getUsername() {
        return username;
    }

    public void displayInfo() {
        System.out.println("Admin ID: " + adminId);
        System.out.println("Username: " + username);
        System.out.println("Students: " + studentCount);
        System.out.println("Teachers: " + teacherCount);
        System.out.println("Classrooms: " + classroomCount);
    }
}