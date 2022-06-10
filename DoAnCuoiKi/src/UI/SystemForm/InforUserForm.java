package UI.SystemForm;

import java.awt.Component;
import java.awt.Font;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

import com.mysql.cj.jdbc.Blob;

import Entity.Employee;
import Model.EmployeeModel;
import Model.GlobalModel;
import Model.ImageHelper;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class InforUserForm {

	private JFrame frame;
	private EmployeeModel employeeModel = new EmployeeModel();
	private JTextField tfIDEmployee;
	private JTextField tfNameEmployee;
	private JTextField tfAddressEmployee;
	private JTextField tfPhoneEmployee;
	private JTextField tfCVEployee;
	private JTextField tfSalaryEmployee;
	private byte[] personalImgae;
	public InforUserForm() throws IOException {
		frame = new JFrame();
		frame.setTitle("Thông tin người dùng");
		frame.setBounds(400, 100, 400, 600);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JLabel lblNewLabel = new JLabel("Thông tin của bạn");
		lblNewLabel.setBounds(113, 11, 181, 34);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lbIDUser = new JLabel("Mã Nhân viên");
		lbIDUser.setBounds(29, 56, 95, 21);
		lbIDUser.setFont(new Font("Tahoma", Font.PLAIN, 13));
		frame.getContentPane().add(lbIDUser);
		
		JLabel lbImage = new JLabel("");
        lbImage.setBounds(148, 280, 140, 160);
        frame.getContentPane().add(lbImage);
		
		JLabel lbNameUser = new JLabel("Tên Nhân viên");
		lbNameUser.setBounds(29, 88, 95, 21);
		lbNameUser.setFont(new Font("Tahoma", Font.PLAIN, 13));
		frame.getContentPane().add(lbNameUser);
		
		JLabel lbGenderUser = new JLabel("Giới tính");
		lbGenderUser.setBounds(52, 120, 95, 21);
		lbGenderUser.setFont(new Font("Tahoma", Font.PLAIN, 13));
		frame.getContentPane().add(lbGenderUser);
		
		JLabel lbAddressUser = new JLabel("Địa chỉ");
		lbAddressUser.setBounds(62, 152, 95, 21);
		lbAddressUser.setFont(new Font("Tahoma", Font.PLAIN, 13));
		frame.getContentPane().add(lbAddressUser);
		
		JLabel lbPhoneUser = new JLabel("Điện thoại");
		lbPhoneUser.setBounds(52, 184, 95, 21);
		lbPhoneUser.setFont(new Font("Tahoma", Font.PLAIN, 13));
		frame.getContentPane().add(lbPhoneUser);
		
		JLabel lbCVUser = new JLabel("Chức vụ");
		lbCVUser.setBounds(62, 216, 95, 21);
		lbCVUser.setFont(new Font("Tahoma", Font.PLAIN, 13));
		frame.getContentPane().add(lbCVUser);
		
		JLabel lbSalaryUser = new JLabel("Lương");
		lbSalaryUser.setBounds(72, 248, 95, 21);
		lbSalaryUser.setFont(new Font("Tahoma", Font.PLAIN, 13));
		frame.getContentPane().add(lbSalaryUser);
		
		tfIDEmployee = new JTextField();
		tfIDEmployee.setBounds(123, 56, 200, 20);
		tfIDEmployee.setFont(new Font("Tahoma", Font.PLAIN, 13));
		frame.getContentPane().add(tfIDEmployee);
		tfIDEmployee.setColumns(10);
		
		tfNameEmployee = new JTextField();
		tfNameEmployee.setBounds(123, 88, 200, 20);
		tfNameEmployee.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfNameEmployee.setColumns(10);
		frame.getContentPane().add(tfNameEmployee);
		
		tfAddressEmployee = new JTextField();
		tfAddressEmployee.setBounds(123, 152, 200, 20);
		tfAddressEmployee.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfAddressEmployee.setColumns(10);
		frame.getContentPane().add(tfAddressEmployee);
		
		tfPhoneEmployee = new JTextField();
		tfPhoneEmployee.setBounds(123, 184, 200, 20);
		tfPhoneEmployee.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfPhoneEmployee.setColumns(10);
		frame.getContentPane().add(tfPhoneEmployee);
		
		tfCVEployee = new JTextField();
		tfCVEployee.setBounds(123, 216, 200, 20);
		tfCVEployee.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfCVEployee.setColumns(10);
		frame.getContentPane().add(tfCVEployee);
		
		tfSalaryEmployee = new JTextField();
		tfSalaryEmployee.setBounds(123, 248, 200, 20);
		tfSalaryEmployee.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfSalaryEmployee.setColumns(10);
		frame.getContentPane().add(tfSalaryEmployee);
		JRadioButton rdMale = new JRadioButton("Nam");
		rdMale.setBounds(133, 120, 95, 23);
		frame.getContentPane().add(rdMale);
		
		JRadioButton rdFemale = new JRadioButton("Nữ");
		rdFemale.setBounds(241, 120, 109, 23);
		frame.getContentPane().add(rdFemale);
		ButtonGroup bg = new ButtonGroup();
        bg.add(rdMale);
        bg.add(rdFemale);
		
		JButton btnUpdate = new JButton("Cập nhật");
		btnUpdate.setBounds(52, 494, 107, 45);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfIDEmployee.getText().equals("")
						||tfNameEmployee.getText().equals("")
						||tfAddressEmployee.getText().equals("")
						||tfCVEployee.getText().equals("")
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
					employee.setType(tfCVEployee.getText());
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
							JOptionPane.showMessageDialog(null, "Cập nhật thành công",
				  	                  "Sửa thông tin", JOptionPane.INFORMATION_MESSAGE);
						}
							
					} catch (ClassNotFoundException | SQLException | IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frame.getContentPane().add(btnUpdate);

        Employee employee = employeeModel.getInfoEmployee(GlobalModel.globalIDUser);
        tfIDEmployee.setText(employee.getID());
        tfNameEmployee.setText(employee.getName());
        tfAddressEmployee.setText(employee.getAddress());
        tfPhoneEmployee.setText(employee.getPhone());
        tfCVEployee.setText(employee.getType());
        String slartString = "" +employee.getSalary();
        tfSalaryEmployee.setText(slartString);
        if(employee.getPicture() != null) {
        	Image img = ImageHelper.createImageFromByteArray(employee.getPicture(), "jpg");
        	lbImage.setIcon(new ImageIcon(img));
        	personalImgae = employee.getPicture();
        }else {
        	ImageIcon icon = new ImageIcon(getClass().getResource("image//user.jpg"));
    		lbImage.setIcon(icon);
		}
        
        JButton btnExit = new JButton("Thoát");
        btnExit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		frame.dispose();
        	}
        });
        btnExit.setBounds(243, 494, 107, 45);
        btnExit.setFont(new Font("Tahoma", Font.PLAIN, 15));
        frame.getContentPane().add(btnExit);
        
        JLabel lbSalaryUser_1 = new JLabel("Hình ảnh");
        lbSalaryUser_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lbSalaryUser_1.setBounds(62, 280, 95, 21);
        frame.getContentPane().add(lbSalaryUser_1);
        
        JButton btnUpload = new JButton("Tải lên");
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
					lbImage.setIcon(resizedIcon);
					personalImgae = ImageHelper.toByteArray(img, "jpg");
				} catch (Exception e2) {
					// TODO: handle exception
				}
        	}
        });
        btnUpload.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnUpload.setBounds(144, 451, 150, 23);
        frame.getContentPane().add(btnUpload);
        if(employee.getGender().equals("Nam")) {
        	rdMale.doClick();
        }
        else if(employee.getGender().equals("Nữ")){
			rdFemale.doClick();
		}
		if(GlobalModel.globalTypeUser != 1) {
			tfIDEmployee.setEditable(false);
			tfCVEployee.setEditable(false);
			tfSalaryEmployee.setEditable(false);
		}
		frame.setVisible(true);
	}

}
