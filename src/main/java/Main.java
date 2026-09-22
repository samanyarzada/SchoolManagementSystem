
import java.util.Date;

// Main class for testing the system
public class Main {

    public static void main(String[] args) {

        try {

            // Create core objects
            Classroom classroom1 = new Classroom("C-101", "Room A1", 30, "1st Floor", "Main classroom");

            Student student1 = new Student("S-001", "Ali Ahmadi", 16, "Male", "Tehran",
                    "0912xxxxxxx", "ali@example.com", "10th Grade", "ST-1001", new Date(), classroom1);

            FullTimeTeacher teacher1 = new FullTimeTeacher("T-001", "Sara Karimi", 35, "Female", "Tehran",
                    "0913xxxxxxx", "sara@example.com", "EMP-2001", "Mathematics", "FULL_TIME",
                    1500.0, new Date(), 1200.0);

            PartTimeTeacher teacher2 = new PartTimeTeacher("T-002", "Reza Hosseini", 40, "Male", "Tehran",
                    "0914xxxxxxx", "reza@example.com", "EMP-2002", "Physics", "PART_TIME",
                    800.0, new Date(), 15.0, 10);

            Course course1 = new Course("CRS-01", "Algebra I", "Basic algebra course", 3, classroom1);

            // Assign relationships
            teacher1.teach(course1);
            course1.addStudent(student1);
            student1.attendCourse(course1);
            classroom1.addStudent(student1);
            classroom1.setTeacher(teacher1);

            Enrollment enrollment1 = new Enrollment("ENR-01", student1, course1, new Date(), "ACTIVE");

            Attendance attendance1 = new Attendance("ATT-01", new Date(), course1);
            AttendanceRecord record1 = new AttendanceRecord(student1, "PRESENT", "On time");
            attendance1.addRecord(record1);

            Timetable timetable1 = new Timetable("TT-01", classroom1);
            timetable1.addLesson("MONDAY", "09:00", "10:30", course1);

            // Admin actions
            Admin admin1 = new Admin("ADM-01", "admin", "1234");
            admin1.addStudent(student1);
            admin1.addTeacher(teacher1);
            admin1.addTeacher(teacher2);
            admin1.createClassroom(classroom1);

            // Display outputs
            System.out.println("===== Student Info =====");
            student1.displayInfo();

            System.out.println("\n===== Teachers =====");
            teacher1.displayInfo();
            teacher2.displayInfo();

            // Polymorphism demo
            System.out.println("\n===== Salary (Polymorphism) =====");
            Payable p1 = teacher1;
            Payable p2 = teacher2;

            System.out.println("Full-Time Salary: " + p1.calculateSalary());
            System.out.println("Part-Time Salary: " + p2.calculateSalary());

            System.out.println("\n===== Enrollment =====");
            enrollment1.displayInfo();

            System.out.println("\n===== Attendance =====");
            attendance1.displayInfo();

            System.out.println("\n===== Timetable =====");
            timetable1.displayInfo();

            System.out.println("\n===== Admin =====");
            admin1.displayInfo();

            System.out.println("\n===== Reports =====");
            admin1.generateReports();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}