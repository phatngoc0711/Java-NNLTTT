package UI.DeviceForm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import com.mysql.cj.x.protobuf.MysqlxCrud.Update;

import Entity.Device;
import Entity.Employee;
import Model.DeviceModel;
import Model.RevenueModel;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.awt.event.ItemEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.HierarchyEvent;

public class SetAmountDevice {

	private JFrame frame;
	private JTextField tfNameDevice;
	private JTextField tfAmount;
	private JTextField tfUpdateAmount;
	private JTextField tfValueDevice;
	private DeviceModel deviceModel = new DeviceModel();
	private ArrayList<Device> listDevices = new ArrayList<Device>();
	private RevenueModel revenueModel = new RevenueModel();
	private JTextField tfValueSys;
	private int index = -1;

	public SetAmountDevice() throws ClassNotFoundException {
		frame = new JFrame();
		frame.setTitle("Nhập / Xuất thiết bị");
		frame.setBounds(300, 100, 450, 315);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nhập và xuất thiết bị");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(130, 11, 242, 20);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lbIDDevice = new JLabel("Mã thiết bị");
		lbIDDevice.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbIDDevice.setBounds(73, 60, 68, 14);
		frame.getContentPane().add(lbIDDevice);
		
		JLabel lbNameDevice = new JLabel("Tên thiết bị");
		lbNameDevice.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbNameDevice.setBounds(73, 85, 68, 14);
		frame.getContentPane().add(lbNameDevice);
		
		JLabel lbAmount = new JLabel("Số thiết bị trong kho");
		lbAmount.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbAmount.setBounds(25, 110, 116, 18);
		frame.getContentPane().add(lbAmount);
		
		JLabel lbAddAmount = new JLabel("Số lượng");
		lbAddAmount.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbAddAmount.setBounds(73, 168, 68, 20);
		frame.getContentPane().add(lbAddAmount);
		
		JLabel lbValue = new JLabel("Giá trị");
		lbValue.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbValue.setBounds(83, 199, 58, 14);
		frame.getContentPane().add(lbValue);
		
		tfNameDevice = new JTextField();
		tfNameDevice.setEnabled(false);
		tfNameDevice.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfNameDevice.setBounds(151, 83, 221, 20);
		frame.getContentPane().add(tfNameDevice);
		tfNameDevice.setColumns(10);
		
		tfAmount = new JTextField();
		tfAmount.setEnabled(false);
		tfAmount.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfAmount.setColumns(10);
		tfAmount.setBounds(151, 108, 221, 20);
		frame.getContentPane().add(tfAmount);
		
		tfUpdateAmount = new JTextField();
		tfUpdateAmount.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfUpdateAmount.setColumns(10);
		tfUpdateAmount.setBounds(151, 168, 221, 20);
		frame.getContentPane().add(tfUpdateAmount);
		
		tfValueDevice = new JTextField();
		tfValueDevice.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfValueDevice.setColumns(10);
		tfValueDevice.setBounds(151, 196, 221, 20);
		frame.getContentPane().add(tfValueDevice);
		
		JLabel lbValueSys = new JLabel("Giá thiết bị");
		lbValueSys.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbValueSys.setBounds(73, 143, 116, 14);
		frame.getContentPane().add(lbValueSys);
		
		tfValueSys = new JTextField();
		tfValueSys.setEnabled(false);
		tfValueSys.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfValueSys.setEnabled(false);
		tfValueSys.setColumns(10);
		tfValueSys.setBounds(151, 137, 221, 20);
		frame.getContentPane().add(tfValueSys);
		
		listDevices = deviceModel.getAllDevices();
		ArrayList<String> listIDDevice = new ArrayList<String>();
		for (Device device: listDevices) {
				listIDDevice.add(device.getID());
		}
		JComboBox comboBox = new JComboBox(listIDDevice.toArray());
		comboBox.addHierarchyListener(new HierarchyListener() {
			public void hierarchyChanged(HierarchyEvent e) {
				for(Device device: listDevices) {
					if(device.getID().equals(comboBox.getSelectedItem().toString())) {
						tfNameDevice.setText(device.getName());
						tfAmount.setText(String.valueOf(device.getAmount()));
						tfValueSys.setText(String.valueOf(device.getValue()));
					}
				}
			}
		});
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(Device device: listDevices) {
					if(device.getID().equals(comboBox.getSelectedItem().toString())) {
						tfNameDevice.setText(device.getName());
						tfAmount.setText(String.valueOf(device.getAmount()));
						tfValueSys.setText(String.valueOf(device.getValue()));
					}
				}
			}
		});
		comboBox.setBounds(151, 57, 221, 22);
		frame.getContentPane().add(comboBox);
		
		JButton btnAddDevice = new JButton("Nhập");
		btnAddDevice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfUpdateAmount.getText().equals("")
						||tfValueDevice.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Các mục không được bỏ trống",
		  	                  "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
				else{
					Device dvc = new Device(comboBox.getSelectedItem().toString(),"",Integer.parseInt(tfAmount.getText()),"",Float.parseFloat(tfValueDevice.getText()));
					try {
						if(revenueModel.insertRevenue("Nhập thiết bị",Float.parseFloat(tfValueDevice.getText())*Float.parseFloat(tfUpdateAmount.getText())*(-1))
								&&deviceModel.editAmountDevices(dvc.getID(),Integer.parseInt(tfAmount.getText()) + Integer.parseInt(tfUpdateAmount.getText()))) {
							JOptionPane.showMessageDialog(null, "Cập nhật số lượng thành công",
				  	                  "Cập nhật thiết bị", JOptionPane.INFORMATION_MESSAGE);
							index = 0;
							for(Device device: listDevices) {
								if(device.getID().equals(comboBox.getSelectedItem().toString())) {
									device.setAmount(Integer.parseInt(tfAmount.getText()) + Integer.parseInt(tfUpdateAmount.getText()));
									listDevices.set(index, device);
								}
								index ++;
							}
						}
						else
							JOptionPane.showMessageDialog(null, "Cập nhật số lượng không thành công",
				  	                  "Cập nhật thiết bị", JOptionPane.ERROR_MESSAGE);
					} catch (ClassNotFoundException | SQLException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnAddDevice.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAddDevice.setBounds(51, 224, 89, 41);
		frame.getContentPane().add(btnAddDevice);
		
		JButton btnRemoveDevice = new JButton("Xuất");
		btnRemoveDevice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfUpdateAmount.getText().equals("")
						||tfValueDevice.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Các mục không được bỏ trống",
		  	                  "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
				else if(Integer.parseInt(tfAmount.getText()) < Integer.parseInt(tfUpdateAmount.getText())){
					JOptionPane.showMessageDialog(null, "Không đủ thiết bị",
		  	                  "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
				else {
					Device dvc = new Device(comboBox.getSelectedItem().toString(),"",Integer.parseInt(tfAmount.getText()),"",Float.parseFloat(tfValueDevice.getText()));
					try {
						if(revenueModel.insertRevenue("Bán thiết bị",Float.parseFloat(tfValueDevice.getText())*Float.parseFloat(tfUpdateAmount.getText()))
								&&deviceModel.editAmountDevices(dvc.getID(),Integer.parseInt(tfAmount.getText()) - Integer.parseInt(tfUpdateAmount.getText()))) {
							JOptionPane.showMessageDialog(null, "Bán thiết bị thành công",
				  	                  "Bán thiết bị", JOptionPane.INFORMATION_MESSAGE);
							index = 0;
							for(Device device: listDevices) {
								if(device.getID().equals(comboBox.getSelectedItem().toString())) {
									device.setAmount(Integer.parseInt(tfAmount.getText()) - Integer.parseInt(tfUpdateAmount.getText()));
									listDevices.set(index, device);
								}
								index ++;
							}
						}
						else
							JOptionPane.showMessageDialog(null, "Cập nhật số lượng không thành công",
				  	                  "Cập nhật thiết bị", JOptionPane.ERROR_MESSAGE);
					} catch (ClassNotFoundException | SQLException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnRemoveDevice.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnRemoveDevice.setBounds(185, 224, 89, 41);
		frame.getContentPane().add(btnRemoveDevice);
		
		JButton btnExit = new JButton("Thoát");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnExit.setBounds(312, 224, 89, 41);
		frame.getContentPane().add(btnExit);
	
		frame.setVisible(true);
	}
}
