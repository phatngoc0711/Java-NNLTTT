package UI.MainForm;
import DAO.*;
import Entity.Employee;
import Model.EmployeeModel;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.io.IOException;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class Login extends JFrame{
	private JFrame frame;
    private JTextField tfUsername;
    private JPasswordField tfPassword;
    private EmployeeModel employeeModel= new EmployeeModel();
    
	public Login() throws IOException {
		//Setting frame
		frame = new JFrame("Login");
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 24));
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(300,100,700,500);
		
		//List panel
	    JPanel panel = new JPanel();
	    panel.setBackground(Color.WHITE);
	    panel.setBounds(95, 149, 481, 179);
	    frame.getContentPane().add(panel);
	    panel.setLayout(null);
		
		//List image
		Icon logo = new ImageIcon( getClass().getResource( "image\\logo.png" ) );
        frame.getContentPane().setLayout(null);
		
		//List label
        JLabel lblogo = new JLabel(logo);
	    frame.getContentPane().add(lblogo);
	    lblogo.setBounds(0,0,200,140);
	    
	    JLabel lbCompany = new JLabel("QUẢN LÝ GARA XE");
	    lbCompany.setFont(new Font("Segoe UI", Font.BOLD, 30));
	    lbCompany.setBounds(242, 26, 500, 100);
	    frame.getContentPane().add(lbCompany);
	    
	    JLabel lbLogin = new JLabel("Đăng Nhập");
	    lbLogin.setBounds(170, 18, 178, 40);
	    lbLogin.setFont(new Font("Palatino Linotype", Font.BOLD, 30));
	    panel.add(lbLogin);
	    
	    JLabel lbUserName = new JLabel("Tên đăng nhập :");
	    lbUserName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
	    lbUserName.setBounds(10, 70, 143, 25);
	    panel.add(lbUserName);
	    
	    JLabel lbPassword = new JLabel("Mật khẩu :");
	    lbPassword.setFont(new Font("Times New Roman", Font.PLAIN, 20));
	    lbPassword.setBounds(53, 110, 100, 25);
	    panel.add(lbPassword);
	    
	    JLabel lbContact = new JLabel("Liên hệ admin :");
	    lbContact.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
	    lbContact.setBounds(382, 411, 86, 14);
	    frame.getContentPane().add(lbContact);
	    
	    JLabel lbPhone = new JLabel("Điện thoại : 0123456789");
	    lbPhone.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
	    lbPhone.setBounds(464, 424, 148, 14);
	    frame.getContentPane().add(lbPhone);
	    
	    JLabel lbeMail = new JLabel("eMail : ongchuphat@gmail.com");
	    lbeMail.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
	    lbeMail.setBounds(464, 436, 169, 14);
	    frame.getContentPane().add(lbeMail);
	    
	    //List Text Field
	    tfUsername = new JTextField();
	    tfUsername.setHorizontalAlignment(SwingConstants.CENTER);
	    tfUsername.setFont(new Font("Times New Roman", Font.PLAIN, 20));
	    tfUsername.setBounds(170, 69, 240, 25);
	    panel.add(tfUsername);
	    tfUsername.setColumns(10);
	    
	    tfPassword = new JPasswordField();
	    tfPassword.setHorizontalAlignment(SwingConstants.CENTER);
	    tfPassword.setFont(new Font("Times New Roman", Font.PLAIN, 20));
	    tfPassword.setColumns(10);
	    tfPassword.setBounds(170, 109, 240, 25);
	    panel.add(tfPassword);
	    
	    //List button
	    JButton btnLogin = new JButton("Đăng nhập");
	    btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	    		String username = tfUsername.getText();
	    		String password = new String(tfPassword.getPassword());
	    		if(username.equals("")||password.equals("")) {
	    			JOptionPane.showMessageDialog(null, "Tên đăng nhập hoặc mật khẩu không được bỏ trống !",
		  	                  "Lỗi Đăng Nhập", JOptionPane.ERROR_MESSAGE);
	    		}
	    		else
	    		{
	    			if(employeeModel.checkUser(username, password)) {
	    				frame.dispose();
	    				try {
							Manager manager = new Manager();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		    		}
		    		else {
		    			JOptionPane.showMessageDialog(null, "Sai tên đăng nhập hoặc mật khẩu",
		  	                  "Lỗi Đăng Nhập", JOptionPane.ERROR_MESSAGE);
					}
	    		}
			}
	    });
	    btnLogin.setFont(new Font("Times New Roman", Font.BOLD, 20));
	    btnLogin.setBounds(50, 340, 131, 60);
	    frame.getContentPane().add(btnLogin);
	    
	    JButton btnCancel = new JButton("Thoát");
	    btnCancel.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		System.exit(0);
	    	}
	    });
	    btnCancel.setFont(new Font("Times New Roman", Font.BOLD, 20));
	    btnCancel.setBounds(510, 340, 120, 60);
	    frame.getContentPane().add(btnCancel);
        frame.setVisible(true);
	}
}
