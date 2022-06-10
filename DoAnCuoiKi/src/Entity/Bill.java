package Entity;

public class Bill {
	private String ID;
	private String IDCustomer;
	private String IDCar;
	private float totalPrice;
	
	public Bill() {
		
	}
	public Bill(String id, String idcus, String idCar, float tPrice) {
		this.ID = id;
		this.IDCustomer = idcus;
		this.IDCar = idCar;
		this.totalPrice = tPrice;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getIDCustomer() {
		return IDCustomer;
	}
	public void setIDCustomer(String iDCustomer) {
		IDCustomer = iDCustomer;
	}
	public String getIDCar() {
		return IDCar;
	}
	public void setIDCar(String iDCar) {
		IDCar = iDCar;
	}
	public float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
}
