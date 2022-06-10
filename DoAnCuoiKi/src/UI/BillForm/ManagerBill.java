package UI.BillForm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Entity.Bill;
import Entity.Car;
import Entity.Customer;
import Entity.Job;
import Model.BillModel;
import Model.CarModel;
import Model.CustomerModel;
import Model.JobModel;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManagerBill {

	private JFrame frame;
	private JTextField tfIDBill;
	private JTextField tfNameCus;
	private JTextField tfBrandCar;
	private JTextField tfDecriptionCar;
	private ArrayList<Job> listJobs = new ArrayList<Job>();
	private ArrayList<Customer> listCustomers = new ArrayList<Customer>();
	private ArrayList<Car> listCars = new ArrayList<Car>();
	private JTable table;
	private JobModel jobModel = new JobModel();
	private CustomerModel customerModel = new CustomerModel();
	private CarModel carModel = new CarModel();
	private BillModel billModel = new BillModel();
	static int index = 0;
	static float temp = 0;
	private JTextField tfIDCar;

	public ManagerBill() throws ClassNotFoundException {
		frame = new JFrame();
		frame.setTitle("Hóa đơn");
		frame.setBounds(300, 100, 650, 400);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lbInfoBill = new JLabel("Tạo hóa đơn");
		lbInfoBill.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbInfoBill.setBounds(10, 11, 201, 19);
		frame.getContentPane().add(lbInfoBill);
		
		JLabel lbBill = new JLabel("Mã Hóa đơn");
		lbBill.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbBill.setBounds(20, 41, 89, 19);
		frame.getContentPane().add(lbBill);
		
		JLabel lbIDCus = new JLabel("Mã khách hàng");
		lbIDCus.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbIDCus.setBounds(20, 65, 89, 19);
		frame.getContentPane().add(lbIDCus);
		
		JLabel lbIDCar = new JLabel("Mã Xe");
		lbIDCar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbIDCar.setBounds(355, 41, 89, 19);
		frame.getContentPane().add(lbIDCar);
		
		JLabel lbNameCus = new JLabel("Tên khách hàng");
		lbNameCus.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbNameCus.setBounds(20, 90, 100, 19);
		frame.getContentPane().add(lbNameCus);
		
		JLabel lbBrandCar = new JLabel("Hãng");
		lbBrandCar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbBrandCar.setBounds(355, 65, 89, 19);
		frame.getContentPane().add(lbBrandCar);
		
		JLabel lbDeciptionCar = new JLabel("Mô tả");
		lbDeciptionCar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbDeciptionCar.setBounds(355, 90, 89, 19);
		frame.getContentPane().add(lbDeciptionCar);
		
		tfIDBill = new JTextField();
		tfIDBill.setBounds(125, 41, 201, 20);
		frame.getContentPane().add(tfIDBill);
		tfIDBill.setColumns(10);
		
		tfNameCus = new JTextField();
		tfNameCus.setEditable(false);
		tfNameCus.setColumns(10);
		tfNameCus.setBounds(125, 90, 201, 20);
		frame.getContentPane().add(tfNameCus);
		
		tfBrandCar = new JTextField();
		tfBrandCar.setEditable(false);
		tfBrandCar.setColumns(10);
		tfBrandCar.setBounds(417, 65, 201, 20);
		frame.getContentPane().add(tfBrandCar);
		
		tfDecriptionCar = new JTextField();
		tfDecriptionCar.setEditable(false);
		tfDecriptionCar.setColumns(10);
		tfDecriptionCar.setBounds(417, 90, 201, 20);
		frame.getContentPane().add(tfDecriptionCar);
		
		listCustomers = customerModel.getAllCustomers();
		ArrayList<String> listCusID = new ArrayList<String>();
		for (Customer customer : listCustomers) {
				listCusID.add(customer.getID());
		}
		listCars = carModel.getAllCar();
		ArrayList<String> listCarID = new ArrayList<String>();
		for (Car car : listCars) {
				listCarID.add(car.getID());
		}
		JComboBox cbIDCus = new JComboBox(listCusID.toArray());
		cbIDCus.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				for (Customer customer : listCustomers) {
					if(customer.getID().equals(cbIDCus.getSelectedItem().toString())) {
						tfNameCus.setText(customer.getName());
						for (Car car : listCars) {
							if(car.getID().equals(customer.getiDcar())) {
								tfIDCar.setText(car.getID());
								tfBrandCar.setText(car.getBrand());
								tfDecriptionCar.setText(car.getDecription());
								break;
							}
						}
						break;
					}
				}
			}
		});
		cbIDCus.setBounds(124, 64, 202, 22);
		frame.getContentPane().add(cbIDCus);
		
		String[] columnNames = {"STT","Mã công việc", "Tên Công việc", "Khối lượng", "Giảm giá (%)", "Tổng"};
		DefaultTableModel dtm = new DefaultTableModel(columnNames,0);
		listJobs = jobModel.getAllJob();
		ArrayList<String> listJobID = new ArrayList<String>();
		for (Job job: listJobs) {
				listJobID.add(job.getJobID());
		}
		JComboBox com = new JComboBox(listJobID.toArray());
		com.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				temp = 0;
				for (Job job: listJobs) {
					if(job.getJobID().equals(com.getSelectedItem().toString())) {
						table.setValueAt(job.getJobName(), index, 2);
						temp = job.getValue();
						break;
					}
					}
			}
		});
		Object[] row = {"1",""};
		dtm.addRow(row);
		DefaultCellEditor de=new DefaultCellEditor(com);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 150, 599, 155);
		frame.getContentPane().add(scrollPane);
		table = new JTable(dtm);
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(table.getValueAt(index, 1)==null||table.getValueAt(index, 2)==null||table.getValueAt(index, 3) == null || table.getValueAt(index, 4) == null) {
						JOptionPane.showMessageDialog(null, "Nhập đủ các trường trước khi ENTER",
					              "Thêm công việc", JOptionPane.ERROR_MESSAGE);
					}
					else {
						float price = temp* Float.parseFloat(table.getValueAt(index, 3).toString()) - temp* Float.parseFloat(table.getValueAt(index, 3).toString())*Float.parseFloat(table.getValueAt(index, 4).toString())/100;
						table.setValueAt(price, index, 5);
						index = index + 1;
						String[] item = {String.valueOf(index + 1),""};
						dtm.addRow(item);
					}
				} else if (e.getKeyCode() == KeyEvent.VK_DELETE){
					if(table.getSelectedRow() != -1 && dtm.getRowCount() > 1) {
			               dtm.removeRow(table.getSelectedRow());
					}
				}
			}
		});
		table.getColumnModel().getColumn(1).setCellEditor(de);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Tạo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfIDBill.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Mã Hóa đơn không được bỏ trống",
				              "Tạo Hóa đơn", JOptionPane.ERROR_MESSAGE);
				}else {
					float totalPrice = 0;
					for(int i = 0 ; i < index; i++) {
						totalPrice += Float.parseFloat(table.getValueAt(i, 5).toString());
					}
					Bill bill = new Bill(tfIDBill.getText(), cbIDCus.getSelectedItem().toString(), tfIDCar.getText(), totalPrice);
					try {
						if(billModel.insertBill(bill)) {
							for(int i = 0 ; i < index; i++) {
								billModel.insertInfoBill(tfIDBill.getText(), table.getValueAt(i, 1).toString(),Integer.parseInt(table.getValueAt(i,3).toString()), Float.parseFloat(table.getValueAt(i,4).toString()), Float.parseFloat(table.getValueAt(i,5).toString()));
							}
							JOptionPane.showMessageDialog(null, "Đã tạo hóa đơn",
						              "Tạo Hóa đơn", JOptionPane.INFORMATION_MESSAGE);
						}
					} catch (HeadlessException e1) {
						System.out.print("1");
					} catch (ClassNotFoundException e1) {
						System.out.print("2");
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Hóa đơn đã tồn tại",
					              "Tạo Hóa đơn", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(269, 314, 89, 36);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lbInfoJob = new JLabel("Danh sách công việc");
		lbInfoJob.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbInfoJob.setBounds(20, 120, 122, 19);
		frame.getContentPane().add(lbInfoJob);
		
		tfIDCar = new JTextField();
		tfIDCar.setEditable(false);
		tfIDCar.setColumns(10);
		tfIDCar.setBounds(417, 41, 201, 20);
		frame.getContentPane().add(tfIDCar);
		frame.setVisible(true);
	}
}
