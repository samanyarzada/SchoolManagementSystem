
import java.util.Date;

public class Teacher extends Person implements Payable  {

	private String employeeID;
	private String subject;
	private String type;
	private double baseSalary;
	private Date hireDate;
	
	public Teacher(String ID, String name, int age, String gender, String address, String phone, String email,
            String employeeID, String subject, String type, double baseSalary, Date hireDate) throws Exception {
		
		super(employeeID, name, age, gender, address, phone, email);
		
		if(employeeID == null || employeeID.trim().isEmpty()) {
			throw new Exception("Employee ID cannot be null or empty");
		}
		

    if (subject == null || subject.trim().isEmpty()) {
        throw new Exception("Subject cannot be empty.");
    }

    if (type == null || type.trim().isEmpty()) {
        throw new Exception("Teacher type cannot be empty.");
    }

    if (baseSalary <= 0) {
        throw new Exception("Base salary must be greater than zero.");
    }

    if (hireDate == null) {
        throw new Exception("Hire date cannot be null.");
    }
	
    this.employeeID = employeeID;
    this.subject = subject;
    this.type = type;
    this.baseSalary = baseSalary;
    this.hireDate = hireDate;
	}
	
	public void teach(Course course) throws Exception{
		if (course == null) {
			throw new Exception("Course cannot be null.");
		}
		
		course.setTeacher(this);
		System.out.println(getName() + " is now teaching the course: " + course.getName());
		
	}
	
	@Override
	public double calculateSalary() {
		return baseSalary;
	}
	
	public String getType() {
		return this.type;
	}
	
	public String getEmployeeId() {
        return this.employeeID;
    }

    public String getSubject() {
        return this.subject;
    }

    public double getBaseSalary() {
        return this.baseSalary;
    }

    public Date getHireDate() {
        return this.hireDate;
    }
    
    public void displayInfo() {
    	super.displayInfo();
    	System.out.println(" Employee ID: " + employeeID +" Subject: " + subject + " Type: " + type + " Base Salary: " + baseSalary + " Hire Date: " + hireDate);
    }
	
}

