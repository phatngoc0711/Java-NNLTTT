package UI.EmployeeForm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Model.EmployeeModel;
import Model.GlobalModel;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateAccForm {

	private JFrame frame;
	private JPasswordField tpfConfimpass;
	private JPasswordField tpfpassword;
	private JTextField tfUsername;
	EmployeeModel employeeModel = new EmployeeModel();
	
	public CreateAccForm() throws ClassNotFoundException {
		frame = new JFrame();
		frame.setBounds(300, 200, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lbCreateAcc = new JLabel("Tạo tài khoản");
		lbCreateAcc.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbCreateAcc.setBounds(136, 23, 162, 30);
		frame.getContentPane().add(lbCreateAcc);
		
		JLabel lbselectEmployee = new JLabel("Nhân viên");
		lbselectEmployee.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbselectEmployee.setBounds(22, 73, 86, 23);
		frame.getContentPane().add(lbselectEmployee);
		
		JLabel lbUsername = new JLabel("Tên tài khoản");
		lbUsername.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbUsername.setBounds(22, 107, 86, 23);
		frame.getContentPane().add(lbUsername);
		
		JLabel lbConfimpass = new JLabel("Nhập lại mật khẩu");
		lbConfimpass.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbConfimpass.setBounds(22, 168, 114, 23);
		frame.getContentPane().add(lbConfimpass);
		
		JComboBox comboBoxEmployee;
		comboBoxEmployee = new JComboBox(employeeModel.getIDAccnonExist().toArray());
		comboBoxEmployee.setBounds(146, 74, 252, 22);
		frame.getContentPane().add(comboBoxEmployee);
		
		JButton btnCreate = new JButton("Tạo");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pass = new String(tpfpassword.getPassword());
				String confimpass = new String(tpfConfimpass.getPassword());
				if(tfUsername.getText().equals("")
						||pass.equals("")
						||confimpass.equals("")
						||comboBoxEmployee.getSelectedItem().toString().equals("")) {
					JOptionPane.showMessageDialog(null, "Các mục không được bỏ trống",
		  	                  "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
				else {
					if(pass.equals(confimpass)) {
						if(employeeModel.addAccountEmployee(comboBoxEmployee.getSelectedItem().toString(), tfUsername.getText(), pass))
						JOptionPane.showMessageDialog(null, "Đã thêm tài khoản",
			  	                  "Thêm tài khoản", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(null, "Nhập lại mật khẩu không đúng",
			  	                  "Lỗi", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCreate.setBounds(72, 202, 89, 48);
		frame.getContentPane().add(btnCreate);
		
		JButton btnExit = new JButton("Thoát");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnExit.setBounds(288, 202, 89, 48);
		frame.getContentPane().add(btnExit);
		
		tpfConfimpass = new JPasswordField();
		tpfConfimpass.setBounds(146, 170, 252, 21);
		frame.getContentPane().add(tpfConfimpass);
		
		JLabel lbPassword = new JLabel("Mật khẩu");
		lbPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbPassword.setBounds(22, 136, 86, 23);
		frame.getContentPane().add(lbPassword);
		
		tpfpassword = new JPasswordField();
		tpfpassword.setBounds(146, 138, 252, 21);
		frame.getContentPane().add(tpfpassword);
		
		tfUsername = new JTextField();
		tfUsername.setBounds(146, 109, 252, 20);
		frame.getContentPane().add(tfUsername);
		tfUsername.setColumns(10);
		frame.setVisible(true);
	}
}
