package UI.SystemForm;

import java.awt.EventQueue;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class ContactForm {

	private JFrame frame;
	public ContactForm() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setTitle("Liên hệ");
		frame.setBounds(400, 200, 500, 360);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Icon logo = new ImageIcon( getClass().getResource( "image\\ute.jpg" ) );
		JLabel lblogo = new JLabel(logo);
	    frame.getContentPane().add(lblogo);
	    lblogo.setBounds(0,0,100,100);
	    
	    JLabel lbSchool = new JLabel("TRƯỜNG ĐẠI HỌC SƯ PHẠM KỸ THUẬT");
	    lbSchool.setHorizontalAlignment(SwingConstants.CENTER);
	    lbSchool.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    lbSchool.setBounds(110, 11, 364, 27);
	    frame.getContentPane().add(lbSchool);
	    
	    JLabel lbSchool_1 = new JLabel("THÀNH PHỐ HỒ CHÍ MINH");
	    lbSchool_1.setHorizontalAlignment(SwingConstants.CENTER);
	    lbSchool_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    lbSchool_1.setBounds(110, 28, 364, 27);
	    frame.getContentPane().add(lbSchool_1);
	    
	    JLabel lbMajoy = new JLabel("KHOA CÔNG NGHỆ THÔNG TIN");
	    lbMajoy.setHorizontalAlignment(SwingConstants.CENTER);
	    lbMajoy.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    lbMajoy.setBounds(110, 59, 364, 27);
	    frame.getContentPane().add(lbMajoy);
	    
	    JLabel lbCourse = new JLabel("ĐỒ ÁN NGÔN NGỮ LẬP TRÌNH TIÊN TIẾN");
	    lbCourse.setHorizontalAlignment(SwingConstants.CENTER);
	    lbCourse.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    lbCourse.setBounds(27, 111, 447, 27);
	    frame.getContentPane().add(lbCourse);
	    
	    JLabel lbNameApp = new JLabel("CHƯƠNG TRÌNH QUẢN LÝ ỨNG DỤNG GARA XE");
	    lbNameApp.setHorizontalAlignment(SwingConstants.CENTER);
	    lbNameApp.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    lbNameApp.setBounds(27, 135, 447, 27);
	    frame.getContentPane().add(lbNameApp);
	    
	    JLabel lbgroup = new JLabel("Nhóm sinh viên thực hiện : ");
	    lbgroup.setHorizontalAlignment(SwingConstants.LEFT);
	    lbgroup.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    lbgroup.setBounds(30, 201, 408, 27);
	    frame.getContentPane().add(lbgroup);
	    
	    JLabel lbStudent1 = new JLabel("Dương Văn Ngọc Tín    -    19110472 ");
	    lbStudent1.setHorizontalAlignment(SwingConstants.LEFT);
	    lbStudent1.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    lbStudent1.setBounds(102, 227, 364, 27);
	    frame.getContentPane().add(lbStudent1);
	    
	    JLabel lbStudent2 = new JLabel("Phạm Quang Dương     -    19110344 ");
	    lbStudent2.setHorizontalAlignment(SwingConstants.LEFT);
	    lbStudent2.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    lbStudent2.setBounds(102, 255, 408, 27);
	    frame.getContentPane().add(lbStudent2);
	    
	    JLabel lbStudent3 = new JLabel("Vũ Minh Nhật              -    19110418");
	    lbStudent3.setHorizontalAlignment(SwingConstants.LEFT);
	    lbStudent3.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    lbStudent3.setBounds(102, 283, 408, 27);
	    frame.getContentPane().add(lbStudent3);
	    
	    JLabel lbTeacher = new JLabel("Giảng viên hướng dẫn : Ths. Trần Văn Định");
	    lbTeacher.setHorizontalAlignment(SwingConstants.LEFT);
	    lbTeacher.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    lbTeacher.setBounds(27, 160, 408, 27);
	    frame.getContentPane().add(lbTeacher);
	    frame.setVisible(true); 
	}

}
