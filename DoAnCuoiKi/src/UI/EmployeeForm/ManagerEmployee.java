package UI.EmployeeForm;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Component;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;

import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

import Entity.Employee;
import Model.EmployeeModel;
import Model.GlobalModel;
import Model.ImageHelper;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ManagerEmployee {

	private JFrame frame;
	private JTable table;
	private JTextField tfIDEmployee;
	private JTextField tfNameEmployee;
	private JTextField tfAddressEmployee;
	private JTextField tfPhoneEmployee;
	private JTextField tfSalaryEmployee;
	
	private EmployeeModel employeeModel = new EmployeeModel();
	ArrayList<Employee> listEmployee = new ArrayList<Employee>();
	private byte[] personalImgae = null;
	
	public ManagerEmployee() throws ClassNotFoundException, IOException {
		frame = new JFrame();
		frame.setBounds(100, 100, 850, 451);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Quản lí Nhân viên");
		
		JLabel Picture = new JLabel("");
		Picture.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Picture.setBounds(645, 11, 140, 160);
		frame.getContentPane().add(Picture);
		
		ImageIcon icon = new ImageIcon(getClass().getResource("image//user.jpg"));
		Picture.setIcon(icon);

		JLabel lbInfoEmployee = new JLabel("Thông tin Nhân viên");
		lbInfoEmployee.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbInfoEmployee.setBounds(28, 22, 200, 28);
		frame.getContentPane().add(lbInfoEmployee);
		
		JLabel lbIDEmployee = new JLabel("Mã Nhân viên");
		lbIDEmployee.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbIDEmployee.setBounds(28, 75, 89, 20);
		frame.getContentPane().add(lbIDEmployee);
		
		JLabel lbNameEmployee = new JLabel("Tên Nhân Viên");
		lbNameEmployee.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbNameEmployee.setBounds(28, 106, 89, 20);
		frame.getContentPane().add(lbNameEmployee);
		
		JLabel lbGenderEmployee = new JLabel("Giới tính");
		lbGenderEmployee.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbGenderEmployee.setBounds(28, 137, 89, 20);
		frame.getContentPane().add(lbGenderEmployee);
		
		JLabel lbAddressEmployee = new JLabel("Địa chỉ");
		lbAddressEmployee.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbAddressEmployee.setBounds(28, 169, 100, 20);
		frame.getContentPane().add(lbAddressEmployee);
		
		JLabel lbPhoneEmployee = new JLabel("Điện thoại");
		lbPhoneEmployee.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbPhoneEmployee.setBounds(317, 75, 100, 20);
		frame.getContentPane().add(lbPhoneEmployee);
		
		JLabel lbCVEmployee = new JLabel("Chức vụ");
		lbCVEmployee.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbCVEmployee.setBounds(317, 106, 100, 20);
		frame.getContentPane().add(lbCVEmployee);
		
		JLabel lbSalaryEmployee = new JLabel("Lương");
		lbSalaryEmployee.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbSalaryEmployee.setBounds(317, 137, 100, 20);
		frame.getContentPane().add(lbSalaryEmployee);
		
		String chucvu[] = {"Thợ Máy","Quản Lí", "Thu Ngân",};
		JComboBox cbCVEmployee = new JComboBox(chucvu);
		cbCVEmployee.setBounds(397, 106, 146, 22);
		frame.getContentPane().add(cbCVEmployee);
		
		JPanel panel = new JPanel();
		panel.setBounds(126, 129, 166, 28);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		JRadioButton rdMale = new JRadioButton("Nam");
		JRadioButton rdFemale = new JRadioButton("Nữ");
		rdMale.setBounds(6, 7, 66, 23);
		panel.add(rdMale);
		rdFemale.setBounds(74, 7, 73, 23);
		panel.add(rdFemale);
		
		JButton btnUpload = new JButton("Tải lên");
		btnUpload.setBounds(645,173,140,20);
		frame.getContentPane().add(btnUpload);
        btnUpload.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JFileChooser chooser = new JFileChooser();
        		chooser.setFileFilter(new FileFilter() {
					@Override
					public String getDescription() {
						return "Image File (*.jpg)";
					}
					@Override
					public boolean accept(File f) {
						if(f.isDirectory()) {
							return true;
						}
						else {
							return f.getName().toLowerCase().endsWith(".jpg");
						}
					}
				});
        		Component parentForm = null;
				if(chooser.showOpenDialog(parentForm) == JFileChooser.CANCEL_OPTION) {
        			return;
        		}
				File file = chooser.getSelectedFile();
				try {
					ImageIcon icon = new ImageIcon(file.getPath());
					Image img = ImageHelper.resize(icon.getImage(), 140, 160);
					ImageIcon resizedIcon = new ImageIcon(img);
					Picture.setIcon(resizedIcon);
					personalImgae = ImageHelper.toByteArray(img, "jpg");
				} catch (Exception e2) {
				}
        	}
        });
		
		ButtonGroup bg = new ButtonGroup();
        bg.add(rdMale);
        bg.add(rdFemale);
        
		
		tfIDEmployee = new JTextField();
		tfIDEmployee.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfIDEmployee.setBounds(127, 76, 146, 20);
		frame.getContentPane().add(tfIDEmployee);
		tfIDEmployee.setColumns(10);
		
		tfNameEmployee = new JTextField();
		tfNameEmployee.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfNameEmployee.setColumns(10);
		tfNameEmployee.setBounds(127, 107, 146, 20);
		frame.getContentPane().add(tfNameEmployee);
		
		tfAddressEmployee = new JTextField();
		tfAddressEmployee.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfAddressEmployee.setColumns(10);
		tfAddressEmployee.setBounds(127, 169, 146, 20);
		frame.getContentPane().add(tfAddressEmployee);
		
		tfPhoneEmployee = new JTextField();
		tfPhoneEmployee.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfPhoneEmployee.setColumns(10);
		tfPhoneEmployee.setBounds(397, 75, 146, 20);
		frame.getContentPane().add(tfPhoneEmployee);
		
		tfSalaryEmployee = new JTextField();
		tfSalaryEmployee.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfSalaryEmployee.setColumns(10);
		tfSalaryEmployee.setBounds(397, 137, 146, 20);
		frame.getContentPane().add(tfSalaryEmployee);
		
		String[] columnNames = { "Mã Nhân viên", "Tên Nhân viên", "Giới tính", "Điện thoại", "Địa chỉ","Lương","Chức vụ"};
		DefaultTableModel dtm = new DefaultTableModel(columnNames,0);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 217, 814, 183);
		frame.getContentPane().add(scrollPane);
		table = new JTable(dtm);
		listEmployee = employeeModel.getAllEmployee(); 
		for (Employee employee : listEmployee) {
			Object[] row = {employee.getID(), employee.getName(), employee.getGender(), employee.getPhone(),employee.getAddress(),employee.getSalary(),employee.getType()};
			dtm.addRow(row);
		}

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				tfIDEmployee.setText(table.getValueAt(index, 0).toString());
				tfNameEmployee.setText(table.getValueAt(index, 1).toString());
				tfPhoneEmployee.setText(table.getValueAt(index, 3).toString());
				tfAddressEmployee.setText(table.getValueAt(index, 4).toString());
				tfSalaryEmployee.setText(table.getValueAt(index, 5).toString());
				byte[] pic = employeeModel.getPictureByID(table.getValueAt(index, 0).toString());
				if(pic != null) {
					Image img;
					try {
						img = ImageHelper.createImageFromByteArray(pic, "jpg");
						Picture.setIcon(new ImageIcon(img));
						personalImgae = ImageHelper.toByteArray(img, "jpg");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				else {
					ImageIcon icon = new ImageIcon(getClass().getResource("image//user.jpg"));
					Picture.setIcon(icon);
				}
				if(table.getValueAt(index, 2).toString().equals("Nam")) {
					rdMale.doClick();
				}
				else {
					rdFemale.doClick();
				}
				if(table.getValueAt(index, 6).equals("Quản Lí")){
					cbCVEmployee.setSelectedIndex(1);
				}
				else if(table.getValueAt(index, 6).equals("Thu Ngân")) {
					cbCVEmployee.setSelectedIndex(2);
				}
				else{
					cbCVEmployee.setSelectedIndex(0);
				}
			}
		});
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		scrollPane.setViewportView(table);
		
		JLabel lbPicture = new JLabel("Hình ảnh");
		lbPicture.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbPicture.setBounds(563, 79, 100, 20);
		frame.getContentPane().add(lbPicture);
		
		JButton btnAddEmployee = new JButton("Thêm");
		btnAddEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfIDEmployee.getText().equals("")
						||tfNameEmployee.getText().equals("")
						||tfAddressEmployee.getText().equals("")
						||tfPhoneEmployee.getText().equals("")
						||tfSalaryEmployee.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Các mục không được bỏ trống",
		  	                  "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
				else {
					Employee employee = new Employee();
					employee.setID(tfIDEmployee.getText());
					employee.setName(tfNameEmployee.getText());
					employee.setAddress(tfAddressEmployee.getText());
					employee.setPhone(tfPhoneEmployee.getText());
					employee.setType(cbCVEmployee.getSelectedItem().toString());
					employee.setSalary(Float.parseFloat(tfSalaryEmployee.getText()));
					employee.setPicture(personalImgae);
					if(rdMale.isSelected()) {
						employee.setGender("Nam");
					}
					else if(rdFemale.isSelected()) {
						employee.setGender("Nữ");
					}
					try {
						if(employeeModel.addEmployee(employee)) {
							JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công",
						              "Thêm nhân viên", JOptionPane.INFORMATION_MESSAGE);
							Object[] row = {employee.getID(), employee.getName(), employee.getGender(), employee.getPhone(),employee.getAddress(),employee.getSalary(),employee.getType()};
							dtm.addRow(row);
						}
						else {
							JOptionPane.showMessageDialog(null, "Thêm nhân viên không thành công",
						              "Thêm nhân viên", JOptionPane.ERROR_MESSAGE);
						}
							
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnAddEmployee.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAddEmployee.setBounds(300, 165, 72, 28);
		frame.getContentPane().add(btnAddEmployee);
		JButton btnEditEmployee = new JButton("Sửa");
		btnEditEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfIDEmployee.getText().equals("")
						||tfNameEmployee.getText().equals("")
						||tfAddressEmployee.getText().equals("")
						||tfPhoneEmployee.getText().equals("")
						||tfSalaryEmployee.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Các mục không được bỏ trống",
		  	                  "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
				else {
					Employee employee = new Employee();
					employee.setID(tfIDEmployee.getText());
					employee.setName(tfNameEmployee.getText());
					employee.setAddress(tfAddressEmployee.getText());
					employee.setPhone(tfPhoneEmployee.getText());
					employee.setType(cbCVEmployee.getSelectedItem().toString());
					employee.setSalary(Float.parseFloat(tfSalaryEmployee.getText()));
					employee.setPicture(personalImgae);
					if(rdMale.isSelected()) {
						employee.setGender("Nam");
					}
					else if(rdFemale.isSelected()) {
						employee.setGender("Nữ");
					}
					try {
						if(employeeModel.Update_Employee(employee)) {
							for(int i = 0; i < table.getRowCount(); i++) {
								if(tfIDEmployee.getText().equals(table.getValueAt(i, 0))) {
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
		btnEditEmployee.setBounds(382, 164, 79, 30);
		frame.getContentPane().add(btnEditEmployee);
		JButton btnDeleteEmployee = new JButton("Xóa");
		btnDeleteEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfIDEmployee.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Mã nhân viên không được bỏ trống",
		  	                  "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
				else {
					if(JOptionPane.showConfirmDialog(null, "Xóa nhân viên") == 0) {
						try {
							if(tfIDEmployee.getText().equals(GlobalModel.globalIDUser)) {
								JOptionPane.showMessageDialog(null, "Bạn không thể xóa chính mình !",
							              "Xóa nhân viên", JOptionPane.ERROR_MESSAGE);
							}
							else if(employeeModel.removeUser(tfIDEmployee.getText()) && employeeModel.deleteEmployee(tfIDEmployee.getText())) {
								for(int i = 0; i < table.getRowCount(); i++) {
									if(tfIDEmployee.getText().equals(table.getValueAt(i, 0))) {
										dtm.removeRow(i);
									}
								}
								JOptionPane.showMessageDialog(null, "Đã Xóa",
							              "Xóa nhân viên", JOptionPane.INFORMATION_MESSAGE);
							}
							else {
								JOptionPane.showMessageDialog(null, "Xóa không thành công",
							              "Xóa nhân viên", JOptionPane.ERROR_MESSAGE);
							}
						} catch (HeadlessException | ClassNotFoundException | SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btnDeleteEmployee.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDeleteEmployee.setBounds(471, 165, 72, 28);
		frame.getContentPane().add(btnDeleteEmployee);
		
		frame.setVisible(true);
	}
}
