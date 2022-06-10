package Entity;


public class Person {
	private String ID;
	private String Name;
	private String Gender;
	private String Phone;
	private String Address;
	private byte[] Picture;
	
	public byte[] getPicture() {
		return Picture;
	}
	public void setPicture(byte[] bs) {
		Picture = bs;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	
}
