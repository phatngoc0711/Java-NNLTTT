package UI.EmployeeForm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Entity.Employee;
import Model.EmployeeModel;
import Model.RevenueModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class totalSalaryForm {

	private JFrame frame;
	private JTable table;
	private EmployeeModel employeeModel = new EmployeeModel();
	ArrayList<Employee> listEmployee = new ArrayList<Employee>();
	RevenueModel revenueModel = new RevenueModel();

	public totalSalaryForm() throws ClassNotFoundException {
		frame = new JFrame();
		frame.setBounds(300, 200, 450, 400);
		frame.setTitle("Bảng Lương");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 47, 414, 206);
		frame.getContentPane().add(scrollPane);
		String[] columnNames = { "Mã Nhân viên", "Tên Nhân viên", "Lương(nghìn đồng)"};
		DefaultTableModel dtm = new DefaultTableModel(columnNames,0);
		listEmployee = employeeModel.getAllEmployee(); 
		for (Employee employee : listEmployee) {
			Object[] row = {employee.getID(), employee.getName(), employee.getSalary()};
			dtm.addRow(row);
		}
		frame.getContentPane().add(scrollPane);
		table = new JTable(dtm);
		scrollPane.setViewportView(table);
		
		JButton btnPaySalary = new JButton("Phát Lương");
		btnPaySalary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Xác nhận phát lương") == 0) {
					try {
						if(revenueModel.insertRevenue("Phát lương",(-1)*totalPrice(dtm))) {
							JOptionPane.showMessageDialog(null, "Đã phát lương",
						              "Phát lương", JOptionPane.INFORMATION_MESSAGE);
						}
						else{
							JOptionPane.showMessageDialog(null, "Phát lương không thành công",
						              "Phát lương", JOptionPane.ERROR_MESSAGE);
						}
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnPaySalary.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnPaySalary.setBounds(40, 310, 116, 40);
		frame.getContentPane().add(btnPaySalary);
		
		JButton btnCancel = new JButton("Thoát");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancel.setBounds(267, 311, 116, 38);
		frame.getContentPane().add(btnCancel);
		
		JLabel lbTotalSalary = new JLabel("Tổng Lương : " +totalPrice(dtm) + " nghìn đồng");
		lbTotalSalary.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbTotalSalary.setBounds(96, 264, 235, 27);
		frame.getContentPane().add(lbTotalSalary);
		
		JLabel lblNewLabel = new JLabel("Bảng lương nhân viên");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(152, 11, 153, 27);
		frame.getContentPane().add(lblNewLabel);
		frame.setVisible(true);
	}
	public static float totalPrice(DefaultTableModel dtm) {
		float a = 0;
		for(int i = 0; i < dtm.getRowCount() ; i++) {
			a += Float.parseFloat(dtm.getValueAt(i, 2).toString());
		}
		return a;
	}
}
