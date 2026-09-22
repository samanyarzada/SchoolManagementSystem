
import java.util.Date;

public class PartTimeTeacher extends Teacher{

    private double hourlyRate;
    private int hoursPerWeek;

	public PartTimeTeacher(String ID, String name, int age, String gender, String address, String phone, String email,
			String employeeID, String subject, String type, double baseSalary, Date hireDate, double hourlyRate, int hoursPerWeek) throws Exception {

		super(ID, name, age, gender, address, phone, email, employeeID, subject, type, baseSalary, hireDate);

        if(hourlyRate <= 0){
            throw new Exception("Hourly rate must be greater than zero.");
        }

        if(hoursPerWeek <= 0){
            throw new Exception("Hours per week must be greater than zero.");
        }

        this.hourlyRate = hourlyRate;
        this.hoursPerWeek = hoursPerWeek;
	}

    @Override
    public double calculateSalary(){
        double monthlyHours = hoursPerWeek * 4;

        return hourlyRate + monthlyHours;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public int getHoursPerWeek() {
        return hoursPerWeek;
    }
    
    @Override 
    public void displayInfo(){
       super.displayInfo();

       System.out.println(" Hourly Rate: " + hourlyRate + " Hours Per Week: " + hoursPerWeek + " Calculated Monthly Salary: " + calculateSalary());
    }
}
