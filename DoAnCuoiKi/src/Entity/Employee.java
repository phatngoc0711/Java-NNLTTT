package Entity;


public class Employee extends Person{
	private float Salary;
	private String Type;
	
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public Employee() {
		
	}
	public Employee(String id, String name, String gender, String phone, String address, float salary, String type,byte[] pic)
	{
		super.setID(id);
		super.setName(name);
		super.setGender(gender);
		super.setPhone(phone);
		super.setAddress(address);
		super.setPicture(pic);
		this.Salary = salary;
		this.Type = type;
	}
	
	public float getSalary() {
		return Salary;
	}

	public void setSalary(float salary) {
		Salary = salary;
	}	
}
