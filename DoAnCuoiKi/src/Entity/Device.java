package Entity;

public class Device {
	
	private String ID;
	private String Name;
	private String Unit;
	private int Amount;
	private float Value;
	
	public Device() {
		
	}
	public Device(String id, String name, int amount, String unit, float value) {
		this.ID = id;
		this.Name = name;
		this.Amount = amount;
		this.Unit = unit;
		this.Value = value;
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
	public String getUnit() {
		return Unit;
	}
	public void setUnit(String unit) {
		Unit = unit;
	}
	public int getAmount() {
		return Amount;
	}
	public void setAmount(int amount) {
		Amount = amount;
	}
	public float getValue() {
		return Value;
	}
	public void setValue(float value) {
		Value = value;
	}
}
