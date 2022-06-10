package Model;

import net.sourceforge.jdatepicker.impl.JDatePickerImpl;

public class DateTimeModel {
	
	public DateTimeModel() {
		
	}
	public boolean compareDate(JDatePickerImpl datepiker1, JDatePickerImpl datePicker2) {
		if(datepiker1.getModel().getYear() < datePicker2.getModel().getYear())
			return true;
		else if(datepiker1.getModel().getYear() == datePicker2.getModel().getYear())
			if(datepiker1.getModel().getMonth() < datePicker2.getModel().getMonth())
				return true;
			else if(datepiker1.getModel().getMonth() == datePicker2.getModel().getMonth())
				if(datepiker1.getModel().getDay() <= datePicker2.getModel().getDay())
					return true;
		return false;
	}
}
