package UI.DeviceForm;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import Entity.Device;
import Entity.Employee;
import Model.DeviceModel;
import Model.EmployeeModel;

public class ManagerDevice {

	private JFrame frame;
	private JTable table;
	private JTextField tfIDDevice;
	private JTextField tfNameDevice;
	private JTextField tfUnitDevice;
	private JTextField tfValueDevice;
	
	private DeviceModel deviceModel = new DeviceModel();
	private ArrayList<Device> listDevices = new ArrayList<Device>();
	
	public ManagerDevice() throws ClassNotFoundException, IOException {
			frame = new JFrame();
			frame.setBounds(300, 100, 600, 450);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			frame.setTitle("Quản lí Thiết bị");

			JLabel lbManagerDevice = new JLabel("Quản lý thiết bị");
			lbManagerDevice.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lbManagerDevice.setBounds(28, 22, 200, 28);
			frame.getContentPane().add(lbManagerDevice);
			
			JLabel lbIDDevice = new JLabel("Mã Thiết bị");
			lbIDDevice.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lbIDDevice.setBounds(28, 75, 89, 20);
			frame.getContentPane().add(lbIDDevice);
			
			JLabel lbDeviceName = new JLabel("Tên Thiết bị");
			lbDeviceName.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lbDeviceName.setBounds(28, 106, 89, 20);
			frame.getContentPane().add(lbDeviceName);
			
			JLabel lbUnitDevice = new JLabel("Đơn vị");
			lbUnitDevice.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lbUnitDevice.setBounds(317, 75, 100, 20);
			frame.getContentPane().add(lbUnitDevice);
			
			JLabel lbValueDevice = new JLabel("Giá trị");
			lbValueDevice.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lbValueDevice.setBounds(317, 106, 100, 20);
			frame.getContentPane().add(lbValueDevice);
	        
			tfIDDevice = new JTextField();
			tfIDDevice.setFont(new Font("Tahoma", Font.PLAIN, 13));
			tfIDDevice.setBounds(127, 76, 146, 20);
			frame.getContentPane().add(tfIDDevice);
			tfIDDevice.setColumns(10);
			
			tfNameDevice = new JTextField();
			tfNameDevice.setFont(new Font("Tahoma", Font.PLAIN, 13));
			tfNameDevice.setColumns(10);
			tfNameDevice.setBounds(127, 107, 146, 20);
			frame.getContentPane().add(tfNameDevice);
			
			tfUnitDevice = new JTextField();
			tfUnitDevice.setFont(new Font("Tahoma", Font.PLAIN, 13));
			tfUnitDevice.setColumns(10);
			tfUnitDevice.setBounds(396, 75, 146, 20);
			frame.getContentPane().add(tfUnitDevice);
			
			tfValueDevice = new JTextField();
			tfValueDevice.setFont(new Font("Tahoma", Font.PLAIN, 13));
			tfValueDevice.setColumns(10);
			tfValueDevice.setBounds(396, 106, 146, 20);
			frame.getContentPane().add(tfValueDevice);
			
			String[] columnNames = { "Mã Thiết bị", "Tên Thiết bị", "Số lượng", "Đơn vị", "Giá trị"};
			DefaultTableModel dtm = new DefaultTableModel(columnNames,0);
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 172, 564, 228);
			frame.getContentPane().add(scrollPane);
			table = new JTable(dtm);
			listDevices = deviceModel.getAllDevices();
			if(listDevices!=null) {
				for (Device device : listDevices) {
					Object[] row = {device.getID(), device.getName(), device.getAmount(), device.getUnit(),device.getValue()};
					dtm.addRow(row);
				}
			}
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int index = table.getSelectedRow();
					tfIDDevice.setText(table.getValueAt(index, 0).toString());
					tfNameDevice.setText(table.getValueAt(index, 1).toString());
					tfValueDevice.setText(table.getValueAt(index, 4).toString());
					tfUnitDevice.setText(table.getValueAt(index, 3).toString());
					}
			});
			table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			scrollPane.setViewportView(table);
			
			JButton btnAddDevice = new JButton("Thêm");
			btnAddDevice.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(tfIDDevice.getText().equals("")
							||tfNameDevice.getText().equals("")
							||tfUnitDevice.getText().equals("")
							||tfValueDevice.getText().equals("")){
						JOptionPane.showMessageDialog(null, "Các mục không được bỏ trống",
			  	                  "Lỗi", JOptionPane.ERROR_MESSAGE);
					}
					else {
						Device device = new Device(tfIDDevice.getText(),tfNameDevice.getText(),0,tfUnitDevice.getText(),Float.parseFloat(tfValueDevice.getText()));
						try {
							if(deviceModel.addDevice(device)) {
								JOptionPane.showMessageDialog(null, "Thêm thiết bị thành công",
							              "Thêm thiết bị", JOptionPane.INFORMATION_MESSAGE);
								Object[] row = {device.getID(), device.getName(), device.getAmount(), device.getUnit(),device.getValue()};
								dtm.addRow(row);
							}
							else {
								JOptionPane.showMessageDialog(null, "Thêm thiết bị không thành công",
							              "Thêm Thiết bị", JOptionPane.ERROR_MESSAGE);
							}
								
						} catch (ClassNotFoundException | SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			});
			btnAddDevice.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnAddDevice.setBounds(51, 137, 72, 28);
			frame.getContentPane().add(btnAddDevice);
			JButton btnEditDevice = new JButton("Sửa");
			btnEditDevice.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(tfIDDevice.getText().equals("")
							||tfNameDevice.getText().equals("")
							||tfUnitDevice.getText().equals("")
							||tfValueDevice.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Các mục không được bỏ trống",
			  	                  "Lỗi", JOptionPane.ERROR_MESSAGE);
					}
					else {
						Device device = new Device(tfIDDevice.getText(),tfNameDevice.getText(),0,tfUnitDevice.getText(),Float.parseFloat(tfValueDevice.getText()));
						try {
							if(deviceModel.editDevices(device)) {
								for(int i = 0; i < table.getRowCount(); i++) {
									if(tfIDDevice.getText().equals(table.getValueAt(i, 0))) {
										dtm.removeRow(i);
										Object[] row = {device.getID(),device.getName(),device.getAmount(),device.getUnit(),device.getValue()};
										dtm.insertRow(i, row);
									}
								}
								JOptionPane.showMessageDialog(null, "Cập nhật thành công",
							              "Sửa thiết bị", JOptionPane.INFORMATION_MESSAGE);
							}
							else {
								JOptionPane.showMessageDialog(null, "Cập nhật không thành công",
							              "Sửa tiết bị", JOptionPane.ERROR_MESSAGE);
							}
						} catch (HeadlessException | ClassNotFoundException | SQLException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}}
			});
			btnEditDevice.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnEditDevice.setBounds(186, 138, 79, 30);
			frame.getContentPane().add(btnEditDevice);
			JButton btnDeleteDevice = new JButton("Xóa");
			btnDeleteDevice.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(tfIDDevice.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Mã thiết bị không được bỏ trống",
			  	                  "Lỗi", JOptionPane.ERROR_MESSAGE);}
					else {
						if(JOptionPane.showConfirmDialog(null, "Xóa thiết bị") == 0) {
							try {
								for(int i = 0; i < table.getRowCount(); i++) {
									if(tfIDDevice.getText().equals(table.getValueAt(i, 0))) {
										if(!table.getValueAt(i, 2).equals(0)) {
											JOptionPane.showMessageDialog(null, "Thiết bị vẫn còn hàng. Không thể xóa",
										              "Xóa thiết bị", JOptionPane.ERROR_MESSAGE);
										}
										else if(deviceModel.deleteDevice(tfIDDevice.getText())) {
													dtm.removeRow(i);
													JOptionPane.showMessageDialog(null, "Đã Xóa",
												              "Xóa thiết bị", JOptionPane.INFORMATION_MESSAGE);
												}
										else {
											JOptionPane.showMessageDialog(null, "Xóa không thành công",
										              "Xóa thiết bị", JOptionPane.ERROR_MESSAGE);
										}
									}
								}
							} catch (HeadlessException | ClassNotFoundException | SQLException e1) {
								e1.printStackTrace();
							}
						}
					}
				}
			});
			btnDeleteDevice.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnDeleteDevice.setBounds(327, 137, 72, 28);
			frame.getContentPane().add(btnDeleteDevice);
			
			JButton btnExit = new JButton("Thoát");
			btnExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.dispose();
				}
			});
			btnExit.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnExit.setBounds(465, 137, 72, 28);
			frame.getContentPane().add(btnExit);
			
			frame.setVisible(true);
		}
	}
