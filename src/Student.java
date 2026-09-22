
import java.util.Date;


public class Student extends Person {

	private String grade;
	private String studentNumber;
	private Date enrollmentDate;
	private Classroom classroom;
	private static final int MAX_COURSES = 20;
	private Course[] courses;
    private int courseCount;
	
	public Student(String ID, String name, int age, String gender, String address, String phone, String email,
                   String grade, String studentNumber, Date enrollmentDate, Classroom classroom) throws Exception {
		super(ID, name, age, gender, address, phone, email);
		
		if (grade == null || grade.trim().isEmpty()) {
        
            throw new Exception("Grade cannot be empty.");
        }

        if (studentNumber == null || studentNumber.trim().isEmpty()) {
       
            throw new Exception("Student number cannot be empty.");
        }

        if (enrollmentDate == null) {
         
            throw new Exception("Enrollment date cannot be null.");
        }

        if (classroom == null) {
        	
            throw new Exception("Classroom cannot be null.");
        }
        
        this.grade = grade;
        this.studentNumber = studentNumber;
        this.enrollmentDate = enrollmentDate;
        this.classroom = classroom;
        this.courses = new Course[MAX_COURSES];
        this.courseCount = 0;
	}
	
	public void attendCourse(Course course) throws Exception{
	  
	  if (course == null) {
		  throw new Exception("Course cannot be null.");
	  }
	  
	  for(int i = 0; i < courseCount; i++) {
		  if(courses[i].equals(course)) {
			  throw new Exception("Student is already attending this course.");
		  }
	  }
	  
	  if(courseCount >= MAX_COURSES) {
		  throw new Exception("Cannot attend more courses, maximum limit reached.");
	  }
	  
	  courses[courseCount] = course;
	  courseCount++;
  }
  
	public Course[] viewCourses() {
		
		Course[] result = new Course[courseCount];
		
		for(int i = 0; i < courseCount; i++) {
			result[i] = courses[i];
			
		}
		
		return result;
	}
	
    public Classroom getClassroom() {
        return classroom;
    }

    public String getGrade() {
        return grade;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }
    
    public void displayInfo() {
    	super.displayInfo();
    	System.out.println("Grade: " + grade + " Student Number: " + studentNumber + " Enrollment Date: " + enrollmentDate);
    }
	
}
