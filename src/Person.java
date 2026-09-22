
public abstract class Person {

	private String ID;
	private String name;
	private int age;
	private String gender;
	private String address;
	private String phone;
	private String email;
	
	public Person(String ID, String name, int age, String gender, String address, String phone, String email) throws Exception {

        if (ID == null || ID.trim().isEmpty()) {
            throw new Exception("ID cannot be empty.");
        }

        if (name == null || name.trim().isEmpty()) {
            throw new Exception("Name cannot be empty.");
        }

        if (age <= 0) {
            throw new Exception("Invalid age.");
        }
		this.ID = ID;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}
	
	public String getID() {
		return this.ID;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int age() {
		return this.age;
	}
	
	public String getGender() {
		return this.gender;
	}
	
	public String getAdderss() {
		return this.address;
	}
	
	public String getPhone() {
		return this.phone;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void displayInfo() {
		
		System.out.println("ID: " + ID + " Name: " + name + " Age: " + age + " Gender: " + gender +
				" Address: " + address + " Phone: " + phone + " Email: " + email);
	}
	
}
