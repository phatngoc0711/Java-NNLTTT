package Entity;

import java.sql.Date;

public class Revenue {
	private Date date;
	private String Type;
	private Float Value;
	
	public Revenue() {
		
	}
	public Revenue(Date date, String type, float value) {
		this.date = date;
		this.Type = type;
		this.Value = value;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public Float getValue() {
		return Value;
	}
	public void setValue(Float value) {
		Value = value;
	}
}
