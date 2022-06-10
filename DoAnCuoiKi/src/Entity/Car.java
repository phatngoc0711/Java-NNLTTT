package Entity;

import java.sql.Date;

public class Car {
	private String ID;
	private String Brand;
	private String Decription;
	private String Color;
	private String Status;
	private Date DateIn;
	private Date DateOut;
	
	public Car() {
	}
	public Car(String id, String brand, String decription, String color, String status, Date datein, Date dateout) {
		this.ID = id;
		this.Brand = brand;
		this.Decription = decription;
		this.Color = color;
		this.Status = status;
		this.DateIn = datein;
		this.DateOut = dateout;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getBrand() {
		return Brand;
	}
	public void setBrand(String brand) {
		Brand = brand;
	}
	public String getDecription() {
		return Decription;
	}
	public void setDecription(String decription) {
		Decription = decription;
	}
	public String getColor() {
		return Color;
	}
	public void setColor(String color) {
		Color = color;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getStatus() {
		return Status;
	}
	public Date getDateIn() {
		return DateIn;
	}
	public void setDateIn(Date dateIn) {
		DateIn = dateIn;
	}
	public Date getDateOut() {
		return DateOut;
	}
	public void setDateOut(Date dateOut) {
		DateOut = dateOut;
	}
}
