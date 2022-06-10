package Entity;

public class Customer extends Person{
	private String iDcar;
	public String getiDcar() {
		return iDcar;
	}
	public void setiDcar(String iDcar) {
		this.iDcar = iDcar;
	}
	public Customer() {
		
	}
	public Customer(String id, String name, String gender, String phone,String address,byte[] pic, String idCar) {
		super.setID(id);
		super.setName(name);
		super.setGender(gender);
		super.setPhone(phone);
		super.setAddress(address);
		super.setPicture(pic);
		this.iDcar = idCar;
	}
}
