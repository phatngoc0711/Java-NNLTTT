package UI.CarForm;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

import Entity.Car;
import Entity.Employee;
import Model.CarModel;
import Model.DateTimeModel;
import Model.EmployeeModel;
import Model.GlobalModel;
import Model.ImageHelper;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class ManagerCarForm {

	private JFrame frame;
	private JTable table;
	private JTextField tfIDCar;
	private JTextField tfDecription;
	private JTextField tfColorCar;
	private JTextField tfSearchCar;
	private CarModel carModel = new CarModel();
	private DateTimeModel dateTimeModel = new DateTimeModel();
	
	private EmployeeModel employeeModel = new EmployeeModel();
	ArrayList<Car> listcar = new ArrayList<Car>();
	private byte[] personalImgae = null;
	public ManagerCarForm() throws ClassNotFoundException, ParseException {
		frame = new JFrame();
		frame.setBounds(100, 100, 850, 550);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setTitle("Quản lí Xe");
		frame.getContentPane().setLayout(null);
		
		JLabel Picture = new JLabel("");
		Picture.setBounds(645, 64, 140, 160);
		Picture.setFont(new Font("Tahoma", Font.PLAIN, 13));
		frame.getContentPane().add(Picture);
		
		ImageIcon icon = new ImageIcon(getClass().getResource("image//car.jpg"));
		Picture.setIcon(icon);

		JLabel lbInfoCar = new JLabel("Thông tin Xe");
		lbInfoCar.setBounds(28, 22, 200, 28);
		lbInfoCar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(lbInfoCar);
		
		JLabel lbIDCar = new JLabel("Mã Xe");
		lbIDCar.setBounds(57, 75, 89, 20);
		lbIDCar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		frame.getContentPane().add(lbIDCar);
		
		JLabel lbBrandCar = new JLabel("Hãng xe");
		lbBrandCar.setBounds(57, 106, 89, 20);
		lbBrandCar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		frame.getContentPane().add(lbBrandCar);
		
		JLabel lbDecaptionCar = new JLabel("Mô tả");
		lbDecaptionCar.setBounds(57, 179, 89, 20);
		lbDecaptionCar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		frame.getContentPane().add(lbDecaptionCar);
		
		JLabel Color = new JLabel("Màu sắc");
		Color.setBounds(57, 137, 100, 20);
		Color.setFont(new Font("Tahoma", Font.PLAIN, 13));
		frame.getContentPane().add(Color);
		
		JLabel lbStatusCar = new JLabel("Trạng thái");
		lbStatusCar.setBounds(317, 75, 100, 20);
		lbStatusCar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		frame.getContentPane().add(lbStatusCar);
		
		JLabel lbDateIn = new JLabel("Ngày vào");
		lbDateIn.setBounds(317, 106, 100, 20);
		lbDateIn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		frame.getContentPane().add(lbDateIn);
		
		JLabel lbDateOut = new JLabel("Ngày xuất");
		lbDateOut.setBounds(317, 137, 100, 20);
		lbDateOut.setFont(new Font("Tahoma", Font.PLAIN, 13));
		frame.getContentPane().add(lbDateOut);
		
		UtilDateModel modelin = new UtilDateModel();
		JDatePanelImpl datePanelin = new JDatePanelImpl(modelin);
		JDatePickerImpl datePickerin = new JDatePickerImpl(datePanelin);
		datePickerin.setBounds(397, 106, 172, 28);
		datePickerin.getModel().setSelected(true);
		frame.getContentPane().add(datePickerin);
		
		UtilDateModel modelout = new UtilDateModel();
		JDatePanelImpl datePanelout = new JDatePanelImpl(modelout);
		JDatePickerImpl datePickerout = new JDatePickerImpl(datePanelout);
		datePickerout.getModel().addDay(10);
		datePickerout.getModel().setSelected(true);
		datePickerout.setBounds(397, 141, 172, 28);
		frame.getContentPane().add(datePickerout);
		
		String status[] = {"Chưa hoàn thành","Đã hoàn thành","Sắp tới hạn","Trễ hạn"};
		JComboBox cbStatusCar = new JComboBox(status);
		cbStatusCar.setBounds(397, 75, 146, 22);
		frame.getContentPane().add(cbStatusCar);
		
		ButtonGroup bg = new ButtonGroup();
        
		
		tfIDCar = new JTextField();
		tfIDCar.setBounds(127, 76, 146, 20);
		tfIDCar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		frame.getContentPane().add(tfIDCar);
		tfIDCar.setColumns(10);
		
		tfDecription = new JTextField();
		tfDecription.setBounds(127, 179, 416, 20);
		tfDecription.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfDecription.setColumns(10);
		frame.getContentPane().add(tfDecription);
		
		tfColorCar = new JTextField();
		tfColorCar.setBounds(127, 137, 146, 20);
		tfColorCar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfColorCar.setColumns(10);
		frame.getContentPane().add(tfColorCar);
		
		tfSearchCar = new JTextField();
		tfSearchCar.setBounds(127, 234, 146, 20);
		tfSearchCar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfSearchCar.setColumns(10);
		frame.getContentPane().add(tfSearchCar);
		
		JComboBox cbBrandCar = new JComboBox(carModel.getAllBrandCar().toArray());
		cbBrandCar.setBounds(127, 106, 146, 22);
		frame.getContentPane().add(cbBrandCar);
		
		String[] columnNames = { "Biển số Xe", "Hãng", "Mô tả", "Màu", "Trạng thái","Ngày vào","Ngày ra"};
		DefaultTableModel dtm = new DefaultTableModel(columnNames,0);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 265, 814, 183);
		frame.getContentPane().add(scrollPane);
		table = new JTable(dtm);
		listcar = carModel.getAllCar(); 
		for (Car car : listcar) {
			Object[] row = {car.getID(), car.getBrand(), car.getDecription(), car.getColor(),car.getStatus(),car.getDateIn(),car.getDateOut()};
			dtm.addRow(row);
		}

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				tfIDCar.setText(table.getValueAt(index, 0).toString());
				cbBrandCar.setSelectedItem(table.getValueAt(index, 1).toString());
				tfDecription.setText(table.getValueAt(index, 2).toString());
				tfColorCar.setText(table.getValueAt(index, 3).toString());
				cbStatusCar.setSelectedItem(table.getValueAt(index, 4).toString());
				Date date = Date.valueOf(table.getValueAt(index, 5).toString());
				datePickerin.getModel().setDay(date.getDate());
				datePickerin.getModel().setMonth(date.getMonth());
				datePickerin.getModel().setYear(1900 + date.getYear());
				Date date1 = Date.valueOf(table.getValueAt(index, 6).toString());
				datePickerout.getModel().setDay(date1.getDate());
				datePickerout.getModel().setMonth(date1.getMonth());
				datePickerout.getModel().setYear(1900 + date1.getYear());
			}
		});
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		scrollPane.setViewportView(table);
		
		JButton btnAddEmployee = new JButton("Thêm");
		btnAddEmployee.setBounds(74, 459, 109, 41);
		btnAddEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfIDCar.getText().equals("")
						||tfDecription.getText().equals("")
						||tfColorCar.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Các mục không được bỏ trống",
		  	                  "Lỗi", JOptionPane.ERROR_MESSAGE);
				}else if(!dateTimeModel.compareDate(datePickerin, datePickerout)) {
					JOptionPane.showMessageDialog(null, "Ngày ra dự kiến không được trước ngày vào",
		  	                  "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
				else {
					
					String dateinString = datePickerin.getModel().getYear() + "-" + (datePickerin.getModel().getMonth()+1) + "-" + datePickerin.getModel().getDay() ;
					System.out.print(dateinString);
					Date datein = Date.valueOf(dateinString);
					Car car = new Car(tfIDCar.getText(),cbBrandCar.getSelectedItem().toString(),tfDecription.getText(),tfColorCar.getText(),cbStatusCar.getSelectedItem().toString(),datein,datein);
					try {
						if(carModel.addCar(car)) {
							JOptionPane.showMessageDialog(null, "Đã thêm xe vào xưởng",
						              "Thêm xe", JOptionPane.INFORMATION_MESSAGE);
							Object[] row = {car.getID(), car.getBrand(), car.getDecription(), car.getColor(), car.getStatus(),car.getDateIn(),car.getDateOut()};
							dtm.addRow(row);
						}
						else {
							JOptionPane.showMessageDialog(null, "Thêm xe không thành công",
						              "Thêm xe", JOptionPane.ERROR_MESSAGE);
						}
							
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnAddEmployee.setFont(new Font("Tahoma", Font.PLAIN, 13));
		frame.getContentPane().add(btnAddEmployee);
		JButton btnEditEmployee = new JButton("Sửa");
		btnEditEmployee.setBounds(260, 459, 109, 42);
		btnEditEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfIDCar.getText().equals("")
						||tfDecription.getText().equals("")
						||tfColorCar.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Các mục không được bỏ trống",
		  	                  "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
				else {
					Employee employee = new Employee();
					employee.setID(tfIDCar.getText());
					employee.setName(tfDecription.getText());
					employee.setAddress(tfColorCar.getText());
					employee.setPhone(tfSearchCar.getText());
					employee.setType(cbStatusCar.getSelectedItem().toString());
					employee.setPicture(personalImgae);
					try {
						if(employeeModel.Update_Employee(employee)) {
							for(int i = 0; i < table.getRowCount(); i++) {
								if(tfIDCar.getText().equals(table.getValueAt(i, 0))) {
									dtm.removeRow(i);
									Object[] row = {employee.getID(), employee.getName(), employee.getGender(), employee.getPhone(),employee.getAddress(),employee.getSalary(),employee.getType()};
									dtm.insertRow(i, row);
								}
							}
							JOptionPane.showMessageDialog(null, "Cập nhật thành công",
						              "Sửa thông tin", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(null, "Cập nhật không thành công",
						              "Sửa thông tin", JOptionPane.ERROR_MESSAGE);
						}
					} catch (HeadlessException | ClassNotFoundException | SQLException | IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnEditEmployee.setFont(new Font("Tahoma", Font.PLAIN, 13));
		frame.getContentPane().add(btnEditEmployee);
		JButton btnDeleteEmployee = new JButton("Xóa");
		btnDeleteEmployee.setBounds(478, 459, 109, 41);
		btnDeleteEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfIDCar.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Mã xe không được bỏ trống",
		  	                  "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
				else {
					if(JOptionPane.showConfirmDialog(null, "Xóa xe") == 0) {
						try {
							if(carModel.deleteCar(tfIDCar.getText())) {
								JOptionPane.showMessageDialog(null, "Đã xóa",
					  	                  "Xóa xe", JOptionPane.INFORMATION_MESSAGE);
							}
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(null, "Xe chưa thực hiện thanh toán",
				  	                  "Lỗi", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
		btnDeleteEmployee.setFont(new Font("Tahoma", Font.PLAIN, 13));
		frame.getContentPane().add(btnDeleteEmployee);
		
		JLabel lbSearch = new JLabel("Tìm kiếm");
		lbSearch.setBounds(28, 214, 89, 20);
		lbSearch.setFont(new Font("Tahoma", Font.PLAIN, 13));
		frame.getContentPane().add(lbSearch);
		
		JLabel lbKey = new JLabel("Từ khóa");
		lbKey.setBounds(57, 234, 89, 20);
		lbKey.setFont(new Font("Tahoma", Font.PLAIN, 13));
		frame.getContentPane().add(lbKey);
		
		JButton btnSearch = new JButton("Tìm");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSearch.setBounds(283, 234, 72, 20);
		frame.getContentPane().add(btnSearch);
		
		JButton btnExit = new JButton("Thoát");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnExit.setBounds(662, 459, 109, 41);
		frame.getContentPane().add(btnExit);
		frame.setVisible(true);
	}
}
