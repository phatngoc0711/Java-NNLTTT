package UI.SystemForm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;

import com.mysql.cj.protocol.a.NativeConstants.StringLengthDataType;

import Entity.Employee;
import Model.EmployeeModel;
import Model.GlobalModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChangePasswordForm {

	private JFrame frame;
	private JTextField tfUsername;
	private JPasswordField tfoldPassword;
	private JPasswordField tfNewPassword;
	private JPasswordField tfConfimNewPassword;
	private EmployeeModel employeeModel = new EmployeeModel();
	public ChangePasswordForm() {
		frame = new JFrame();
		frame.setTitle("Đổi mật khẩu");
		frame.setBounds(400, 200, 300, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lbChangePassword = new JLabel("Đổi Mật Khẩu");
		lbChangePassword.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lbChangePassword.setBounds(71, 11, 270, 49);
		frame.getContentPane().add(lbChangePassword);
		
		JLabel lbUsername = new JLabel("Tên đăng nhập");
		lbUsername.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbUsername.setBounds(10, 73, 98, 21);
		frame.getContentPane().add(lbUsername);
		
		JLabel lbOldPassword = new JLabel("Mật khẩu cũ");
		lbOldPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbOldPassword.setBounds(10, 105, 114, 21);
		frame.getContentPane().add(lbOldPassword);
		
		JLabel lbNewPasswordd = new JLabel("Mật khẩu mới");
		lbNewPasswordd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbNewPasswordd.setBounds(10, 137, 134, 21);
		frame.getContentPane().add(lbNewPasswordd);
		
		JButton btnChange = new JButton("Đổi");
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String oldpass = new String(tfoldPassword.getPassword());
				String newpass = new String(tfNewPassword.getPassword());
				String confimpass = new String(tfConfimNewPassword.getPassword());
				if(oldpass.equals("")
						||newpass.equals("")
						||confimpass.equals("")) {
					JOptionPane.showMessageDialog(null, "Các mục không được bỏ trống",
		  	                  "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
				else if(!employeeModel.checkUser(tfUsername.getText(), oldpass)){
					JOptionPane.showMessageDialog(null, "Mật khẩu cũ không đúng",
		  	                  "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
				else {
					if(newpass.equals(confimpass)) {
						employeeModel.changePassword(GlobalModel.globalIDUser, newpass);
						JOptionPane.showMessageDialog(null, "Đã đổi mật khẩu",
			  	                  "Đổi mật khẩu", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(null, "Nhập lại mật khẩu không đúng",
			  	                  "Lỗi", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnChange.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnChange.setBounds(26, 207, 98, 43);
		frame.getContentPane().add(btnChange);
		
		JButton btnExit = new JButton("Thoát");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnExit.setBounds(149, 207, 98, 43);
		frame.getContentPane().add(btnExit);
		
		tfUsername = new JTextField(employeeModel.getUserNamebyID(GlobalModel.globalIDUser));
		tfUsername.setEditable(false);
		tfUsername.setBounds(118, 74, 144, 20);
		frame.getContentPane().add(tfUsername);
		tfUsername.setColumns(10);
		
		JLabel lbConfimNewPassword = new JLabel("Nhập lại");
		lbConfimNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbConfimNewPassword.setBounds(10, 175, 74, 21);
		frame.getContentPane().add(lbConfimNewPassword);
		
		tfoldPassword = new JPasswordField();
		tfoldPassword.setColumns(10);
		tfoldPassword.setBounds(118, 106, 144, 20);
		frame.getContentPane().add(tfoldPassword);
		
		tfNewPassword = new JPasswordField();
		tfNewPassword.setColumns(10);
		tfNewPassword.setBounds(118, 138, 144, 20);
		frame.getContentPane().add(tfNewPassword);
		
		tfConfimNewPassword = new JPasswordField();
		tfConfimNewPassword.setColumns(10);
		tfConfimNewPassword.setBounds(118, 176, 144, 20);
		frame.getContentPane().add(tfConfimNewPassword);
		frame.setVisible(true);
	}

}
