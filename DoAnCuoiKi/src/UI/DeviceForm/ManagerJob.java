package UI.DeviceForm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.xdevapi.UpdateParams;

import Entity.Device;
import Entity.Job;
import Model.DeviceModel;
import Model.JobModel;

import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.annotation.Target;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.beans.VetoableChangeListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ManagerJob {

	private JFrame frame;
	private JTextField tfIDJob;
	private JTextField tfNameJob;
	private JTextField tfValueJob;
	private JobModel jobModel = new JobModel();
	private DeviceModel deviceModel = new DeviceModel();
	private ArrayList<Job> listJobs = new ArrayList<Job>();
	private ArrayList<Device> listDevices = new ArrayList<Device>();
	private JTextField tfNameDevice;
	private JTable table;
	
	public ManagerJob() throws ClassNotFoundException, SQLException {
		frame = new JFrame();
		frame.setTitle("Quản lý công việc");
		frame.setBounds(200, 100, 800, 425);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lbinfo = new JLabel("Quản lý công việc");
		lbinfo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbinfo.setBounds(32, 11, 350, 41);
		frame.getContentPane().add(lbinfo);
		
		JLabel lbIDJob = new JLabel("Mã công việc");
		lbIDJob.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbIDJob.setBounds(32, 78, 78, 22);
		frame.getContentPane().add(lbIDJob);
		
		JLabel lbNameJob = new JLabel("Tên công việc");
		lbNameJob.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbNameJob.setBounds(32, 111, 91, 22);
		frame.getContentPane().add(lbNameJob);
		
		JLabel lbValueJob = new JLabel("Giá trị");
		lbValueJob.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbValueJob.setBounds(32, 144, 46, 22);
		frame.getContentPane().add(lbValueJob);
		
		JLabel lbIDJob1 = new JLabel("Mã công việc");
		lbIDJob1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbIDJob1.setBounds(418, 78, 91, 22);
		frame.getContentPane().add(lbIDJob1);
		
		JLabel lbIDDevice = new JLabel("Mã thiết bị");
		lbIDDevice.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbIDDevice.setBounds(418, 111, 78, 22);
		frame.getContentPane().add(lbIDDevice);
		
		JLabel lbAmount = new JLabel("Số lượng");
		lbAmount.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbAmount.setBounds(431, 173, 65, 22);
		frame.getContentPane().add(lbAmount);
		
		tfIDJob = new JTextField();
		tfIDJob.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfIDJob.setBounds(120, 80, 141, 22);
		frame.getContentPane().add(tfIDJob);
		tfIDJob.setColumns(10);
		
		tfNameJob = new JTextField();
		tfNameJob.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfNameJob.setColumns(10);
		tfNameJob.setBounds(120, 113, 141, 22);
		frame.getContentPane().add(tfNameJob);
		
		tfValueJob = new JTextField();
		tfValueJob.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfValueJob.setColumns(10);
		tfValueJob.setBounds(120, 146, 141, 22);
		frame.getContentPane().add(tfValueJob);
		
		JLabel lbIDDevice_1 = new JLabel("Tên thiết bị");
		lbIDDevice_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbIDDevice_1.setBounds(418, 144, 78, 22);
		frame.getContentPane().add(lbIDDevice_1);
		
		tfNameDevice = new JTextField();
		tfNameDevice.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfNameDevice.setBounds(506, 145, 141, 20);
		frame.getContentPane().add(tfNameDevice);
		tfNameDevice.setColumns(10);
		
		JSpinner spnAmountDevice = new JSpinner();
		spnAmountDevice.setFont(new Font("Tahoma", Font.PLAIN, 13));
		spnAmountDevice.setBounds(506, 174, 141, 20);
		
		frame.getContentPane().add(spnAmountDevice);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 206, 764, 169);
		frame.getContentPane().add(scrollPane);
		
		listJobs = jobModel.getAllJob();
		ArrayList<String> listJobID = new ArrayList<String>();
		for (Job job: listJobs) {
				listJobID.add(job.getJobID());
		}
		JComboBox cbIDJob = new JComboBox(listJobID.toArray());
		cbIDJob.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cbIDJob.setBounds(506, 79, 141, 22);
		frame.getContentPane().add(cbIDJob);
		
		listDevices = deviceModel.getAllDevices();
		ArrayList<String> listIDDevice = new ArrayList<String>();
		for (Device device: listDevices) {
				listIDDevice.add(device.getID());
		}
		JComboBox cbIDDevice = new JComboBox(listIDDevice.toArray());
		cbIDDevice.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cbIDDevice.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				for (Device device: listDevices) {
					if(device.getID().equals(cbIDDevice.getSelectedItem().toString())) {
						tfNameDevice.setText(device.getName());
					}
					}
			}
		});
		cbIDDevice.setBounds(506, 112, 141, 22);
		frame.getContentPane().add(cbIDDevice);
		
		String[] columnNames = { "Mã công việc", "Tên công việc", "Giá trị công việc", "Mã thiết bị","Số lượng"};
		DefaultTableModel dtm = new DefaultTableModel(columnNames,0);
		String[][] listInfoJob = jobModel.listInfojob();
		if(listInfoJob !=null) {
			for (int i = 0; i < listInfoJob.length; i++ ) {
				String[] row = listInfoJob[i];
				dtm.addRow(row);
			}
		}
		table = new JTable(dtm);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				tfIDJob.setText(table.getValueAt(index, 0).toString());
				tfNameJob.setText(table.getValueAt(index, 1).toString());
				tfValueJob.setText(table.getValueAt(index, 2).toString());
				cbIDJob.setSelectedItem(table.getValueAt(index, 0));
				if(table.getValueAt(index, 3) != null) {
					cbIDDevice.setSelectedItem(table.getValueAt(index, 3));
				}
				if(table.getValueAt(index, 4) != null && !table.getValueAt(index, 4).toString().equals("")) {
					spnAmountDevice.getModel().setValue(Integer.parseInt(table.getValueAt(index, 4).toString()));
				}else {
					spnAmountDevice.getModel().setValue(0);
				}
			}
		});
		table.setFont(new Font("Tahoma", Font.PLAIN, 13));
		scrollPane.setViewportView(table);
		
		JButton btnAddJob = new JButton("Thêm");
		btnAddJob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfIDJob.getText().equals("")
						||tfNameJob.getText().equals("")
						||tfValueJob.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Các mục không được bỏ trống",
		  	                  "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
				else {
					Job job = new Job(tfIDJob.getText(),tfNameJob.getText(),Float.parseFloat(tfValueJob.getText()));
					try {
						if(jobModel.insertJob(job)) {
							cbIDJob.addItem(tfIDJob.getText());
							Object[] row = {tfIDJob.getText(), tfNameJob.getText(), tfValueJob.getText()};
							dtm.addRow(row);
							JOptionPane.showMessageDialog(null, "Thêm công việc thành công",
						              "Thêm công việc", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(null, "Thêm công việc không thành công",
						              "Thêm công việc", JOptionPane.ERROR_MESSAGE);
						}
							
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnAddJob.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAddJob.setBounds(280, 79, 78, 23);
		frame.getContentPane().add(btnAddJob);
		
		JButton btnDeleteJob = new JButton("Xóa");
		btnDeleteJob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfIDJob.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Mã công việc không được bỏ trống",
		  	                  "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					try {
						if(jobModel.deleteJob(tfIDJob.getText())) {
							for(int i = 0; i < table.getRowCount(); i++) {
								if(tfIDJob.getText().equals(table.getValueAt(i, 0)))
									dtm.removeRow(i);
							}
							JOptionPane.showMessageDialog(null, "Xóa công việc thành công",
						              "Xóa công việc", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(null, "Xóa công việc không thành công",
						              "Xóa công việc", JOptionPane.ERROR_MESSAGE);
						}
							
					} catch (ClassNotFoundException | SQLException e1) {
						JOptionPane.showMessageDialog(null, "Công việc vẫn còn đang thực hiện \n"
								+ "Hãy thử với việc xóa các Bill chưa thanh toán\n"
								+ "Hoặc thử xóa thiết bị bên trong nó",
					              "Xóa công việc", JOptionPane.ERROR_MESSAGE);
					} 
				}
			}
		});
		btnDeleteJob.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDeleteJob.setBounds(280, 145, 78, 23);
		frame.getContentPane().add(btnDeleteJob);
		
		JButton btnEditJob = new JButton("Sửa");
		btnEditJob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfIDJob.getText().equals("")
						||tfNameJob.getText().equals("")
						||tfValueJob.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Các mục không được bỏ trống",
		  	                  "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
				else {
					Job job = new Job(tfIDJob.getText(),tfNameJob.getText(),Float.parseFloat(tfValueJob.getText()));
					try {
						if(jobModel.editJob(job)) {
							for(int i = 0; i < table.getRowCount(); i++) {
								if(tfIDJob.getText().equals(table.getValueAt(i, 0))) {
									dtm.removeRow(i);
									if(table.getValueAt(i, 3) == null) {
										Object[] row = {tfIDJob.getText(),tfNameJob.getText(),tfValueJob.getText(),null,table.getValueAt(i, 4)};
										dtm.insertRow(i, row);
									}else {
										Object[] row = {tfIDJob.getText(),tfNameJob.getText(),tfValueJob.getText(),table.getValueAt(i, 3),table.getValueAt(i, 4)};
										dtm.insertRow(i, row);
									}
								}
							}
							JOptionPane.showMessageDialog(null, "Sửa công việc thành công",
						              "Sửa công việc", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(null, "Sửa công việc không thành công",
						              "Sửa công việc", JOptionPane.ERROR_MESSAGE);
						}
							
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					} catch (HeadlessException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnEditJob.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnEditJob.setBounds(280, 112, 78, 23);
		frame.getContentPane().add(btnEditJob);
		
		JButton btnAddDeviceinJob = new JButton("Thêm");
		btnAddDeviceinJob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Integer.parseInt(spnAmountDevice.getValue().toString()) == 0) {
					JOptionPane.showMessageDialog(null, "Không thực hiện thêm vì số lượng thiết bị = 0",
		  	                  "Lỗi", JOptionPane.ERROR_MESSAGE);
				}else {
					int exist = 0;
					for(int i = 0; i<dtm.getRowCount();i++) {
						if(dtm.getValueAt(i, 0) == null) {
							break;
						}
						if(dtm.getValueAt(i, 0).toString().equals(cbIDJob.getSelectedItem().toString())) {
							if(dtm.getValueAt(i, 3) != null && dtm.getValueAt(i, 3).toString().equals(cbIDDevice.getSelectedItem().toString())){
								JOptionPane.showMessageDialog(null, "Không thực hiện thêm đã trùng",
					  	                  "Lỗi", JOptionPane.ERROR_MESSAGE);
								exist = 1;
								break;
							}
						}
					}
					if(exist == 0) {
						try {
							if(jobModel.insertJobInfo(cbIDJob.getSelectedItem().toString(), cbIDDevice.getSelectedItem().toString(), Integer.parseInt(spnAmountDevice.getValue().toString()))) {
								for(int i = 0; i < table.getRowCount(); i++) {
									if(tfIDJob.getText().equals(table.getValueAt(i, 0))) {
										if(table.getValueAt(i, 3) == null) {
											Object[] row = {table.getValueAt(i, 0),table.getValueAt(i, 1),table.getValueAt(i, 2),cbIDDevice.getSelectedItem().toString(),Integer.parseInt(spnAmountDevice.getValue().toString())};
											dtm.removeRow(i);
											dtm.insertRow(i, row);
											break;
										}else {
											Object[] row = {table.getValueAt(i, 0),table.getValueAt(i, 1),table.getValueAt(i, 2),cbIDDevice.getSelectedItem().toString(),Integer.parseInt(spnAmountDevice.getValue().toString())};
											dtm.insertRow(i, row);
											break;
										}
									}
								}
								JOptionPane.showMessageDialog(null, "Đã thêm",
					  	                  "Thêm thiết bị vào công việc", JOptionPane.INFORMATION_MESSAGE);
							}
							else {
								JOptionPane.showMessageDialog(null, "Thêm không thành công",
					  	                  "Thêm thiết bị vào công việc", JOptionPane.INFORMATION_MESSAGE);
							}
						} catch (NumberFormatException | ClassNotFoundException | SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btnAddDeviceinJob.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAddDeviceinJob.setBounds(660, 78, 78, 23);
		frame.getContentPane().add(btnAddDeviceinJob);
		
		JButton btnEditDeviceinJob = new JButton("Sửa");
		btnEditDeviceinJob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Integer.parseInt(spnAmountDevice.getValue().toString()) == 0) {
					JOptionPane.showMessageDialog(null, "Không thực hiện sửa vì số lượng thiết bị = 0",
		  	                  "Lỗi", JOptionPane.ERROR_MESSAGE);
				}else {
					int exist = 0;
					for(int i = 0; i< dtm.getRowCount();i++) {
						if(dtm.getValueAt(i, 0) == null) {
							break;
						}
						if(dtm.getValueAt(i, 0).toString().equals(cbIDJob.getSelectedItem().toString())) {
							if(dtm.getValueAt(i, 3) != null && dtm.getValueAt(i, 3).toString().equals(cbIDDevice.getSelectedItem().toString())){
								exist = 1;
								break;
							}
						}
					}
					if(exist == 1) {
						try {
							if(jobModel.editJobInfo(cbIDJob.getSelectedItem().toString(), cbIDDevice.getSelectedItem().toString(), Integer.parseInt(spnAmountDevice.getValue().toString()))) {
								for(int i = 0; i < table.getRowCount(); i++) {
									if(tfIDJob.getText().equals(table.getValueAt(i, 0))) {
											Object[] row = {table.getValueAt(i, 0),table.getValueAt(i, 1),table.getValueAt(i, 2),cbIDDevice.getSelectedItem().toString(),Integer.parseInt(spnAmountDevice.getValue().toString())};
											dtm.removeRow(i);
											dtm.insertRow(i, row);
											break;
									}
								}
								JOptionPane.showMessageDialog(null, "Đã Sửa",
					  	                  "Sửa thiết bị trong công việc", JOptionPane.INFORMATION_MESSAGE);
							}
							else {
								JOptionPane.showMessageDialog(null, "Thêm không thành công",
					  	                  "Sửa thiết bị trong công việc", JOptionPane.ERROR_MESSAGE);
							}
						} catch (NumberFormatException | ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (HeadlessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Không thực hiện sửa vì chưa có thiết bị",
			  	                  "Lỗi", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnEditDeviceinJob.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnEditDeviceinJob.setBounds(660, 111, 78, 23);
		frame.getContentPane().add(btnEditDeviceinJob);
		
		JButton btnDeleteDeviceinJob = new JButton("Xóa");
		btnDeleteDeviceinJob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int exist = 0;
				for(int i = 0; i< dtm.getRowCount();i++) {
					if(dtm.getValueAt(i, 0) == null) {
						break;
					}
					if(dtm.getValueAt(i, 0).toString().equals(cbIDJob.getSelectedItem().toString())) {
						if(dtm.getValueAt(i, 3) != null && dtm.getValueAt(i, 3).toString().equals(cbIDDevice.getSelectedItem().toString())){
							exist = 1;
							break;
						}
					}
				}
				if(exist == 1) {
					try {
						if(jobModel.deleteJobInfo(cbIDJob.getSelectedItem().toString(), cbIDDevice.getSelectedItem().toString())) {
							for(int i = 0; i < table.getRowCount(); i++) {
								if(tfIDJob.getText().equals(table.getValueAt(i, 0))) {
									dtm.removeRow(i);
									break;
								}
							}
							JOptionPane.showMessageDialog(null, "Đã Xóa",
				  	                  "Xóa thiết bị trong công việc", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(null, "Xóa không thành công",
				  	                  "Xóa thiết bị trong công việc", JOptionPane.ERROR_MESSAGE);
						}
					} catch (NumberFormatException | ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Không thực hiện xóa vì chưa có thiết bị",
		  	                  "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnDeleteDeviceinJob.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDeleteDeviceinJob.setBounds(660, 144, 78, 23);
		frame.getContentPane().add(btnDeleteDeviceinJob);
		
		JLabel lbinfo_1 = new JLabel("Yêu cầu công việc");
		lbinfo_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbinfo_1.setBounds(409, 11, 282, 41);
		frame.getContentPane().add(lbinfo_1);
		frame.setVisible(true);
		
	}
}
