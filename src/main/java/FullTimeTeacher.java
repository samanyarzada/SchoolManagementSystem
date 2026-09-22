
import java.util.Date;

public class FullTimeTeacher extends Teacher {

    private double annualBonus;

    public FullTimeTeacher(String ID, String name, int age, String gender, String address, String phone, String email,
                           String employeeID, String subject, String type, double baseSalary, Date hireDate,
                           double annualBonus) throws Exception {

        super(ID, name, age, gender, address, phone, email, employeeID, subject, type, baseSalary, hireDate);

        if (annualBonus < 0) {
            throw new Exception("Annual bonus cannot be negative.");
        }

        this.annualBonus = annualBonus;
    }

    // Salary = base salary + monthly share of bonus
    @Override
    public double calculateSalary() {
        return getBaseSalary() + (annualBonus / 12);
    }

    public double getAnnualBonus() {
        return annualBonus;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Annual Bonus: " + annualBonus);
        System.out.println("Monthly Salary: " + calculateSalary());
    }
}