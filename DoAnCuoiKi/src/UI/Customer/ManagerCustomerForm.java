package UI.Customer;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

import Entity.Car;
import Entity.Customer;
import Entity.Employee;
import Model.CarModel;
import Model.CustomerModel;
import Model.EmployeeModel;
import Model.GlobalModel;
import Model.ImageHelper;
import UI.BillForm.ManagerBill;

public class ManagerCustomerForm {

	private JFrame frame;
	private JTable table;
	private JTextField tfIDCustomer;
	private JTextField tfNameCustomer;
	private JTextField tfAddressCustomer;
	private JTextField tfPhoneEmployee;
	
	private CustomerModel customerModel = new CustomerModel();
	private ArrayList<Customer> listCustomers = new ArrayList<Customer>();
	private EmployeeModel employeeModel = new EmployeeModel();
	private CarModel carModel = new CarModel();
	private ArrayList<Employee> listEmployee = new ArrayList<Employee>();
	private ArrayList<Car> listCars = new ArrayList<Car>();
	private byte[] personalImgae = null;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerCustomerForm window = new ManagerCustomerForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public ManagerCustomerForm() throws ClassNotFoundException, IOException {
		frame = new JFrame();
		frame.setBounds(100, 100, 850, 451);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Qu???n l?? Kh??ch h??ng");
		
		JLabel Picture = new JLabel("");
		Picture.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Picture.setBounds(645, 11, 140, 160);
		frame.getContentPane().add(Picture);
		
		ImageIcon icon = new ImageIcon(getClass().getResource("image//user.jpg"));
		Picture.setIcon(icon);

		JLabel lbInfoCustomer = new JLabel("Th??ng tin Kh??ch h??ng");
		lbInfoCustomer.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbInfoCustomer.setBounds(28, 22, 200, 28);
		frame.getContentPane().add(lbInfoCustomer);
		
		JLabel lbIDCustomer = new JLabel("M?? Kh??ch h??ng");
		lbIDCustomer.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbIDCustomer.setBounds(28, 75, 89, 20);
		frame.getContentPane().add(lbIDCustomer);
		
		JLabel lbNameCustomer = new JLabel("T??n Kh??ch h??ng");
		lbNameCustomer.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbNameCustomer.setBounds(28, 106, 107, 20);
		frame.getContentPane().add(lbNameCustomer);
		
		JLabel lbGenderCustomer = new JLabel("Gi???i t??nh");
		lbGenderCustomer.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbGenderCustomer.setBounds(28, 137, 89, 20);
		frame.getContentPane().add(lbGenderCustomer);
		
		JLabel lbAddressCustomer = new JLabel("?????a ch???");
		lbAddressCustomer.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbAddressCustomer.setBounds(317, 106, 100, 20);
		frame.getContentPane().add(lbAddressCustomer);
		
		JLabel lbPhoneEmployee = new JLabel("??i???n tho???i");
		lbPhoneEmployee.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbPhoneEmployee.setBounds(317, 75, 100, 20);
		frame.getContentPane().add(lbPhoneEmployee);
		
		JPanel panel = new JPanel();
		panel.setBounds(126, 129, 146, 28);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		JRadioButton rdMale = new JRadioButton("Nam");
		JRadioButton rdFemale = new JRadioButton("N???");
		rdMale.setBounds(6, 7, 66, 23);
		panel.add(rdMale);
		rdFemale.setBounds(74, 7, 73, 23);
		panel.add(rdFemale);
		
		listCars = carModel.getAllCar();
		ArrayList<String> listCarID = new ArrayList<String>();
		for (Car car : listCars) {
				listCarID.add(car.getID());
		}
		JComboBox cbIDCar = new JComboBox(listCarID.toArray());
		cbIDCar.setBounds(397, 137, 146, 22);
		frame.getContentPane().add(cbIDCar);
		
		JButton btnUpload = new JButton("T???i l??n");
		btnUpload.setBounds(645,173,140,20);
		frame.getContentPane().add(btnUpload);
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
					Picture.setIcon(resizedIcon);
					personalImgae = ImageHelper.toByteArray(img, "jpg");
				} catch (Exception e2) {
				}
        	}
        });
		
		ButtonGroup bg = new ButtonGroup();
        bg.add(rdMale);
        bg.add(rdFemale);
        
		
		tfIDCustomer = new JTextField();
		tfIDCustomer.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfIDCustomer.setBounds(127, 76, 146, 20);
		frame.getContentPane().add(tfIDCustomer);
		tfIDCustomer.setColumns(10);
		
		tfNameCustomer = new JTextField();
		tfNameCustomer.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfNameCustomer.setColumns(10);
		tfNameCustomer.setBounds(127, 107, 146, 20);
		frame.getContentPane().add(tfNameCustomer);
		
		tfAddressCustomer = new JTextField();
		tfAddressCustomer.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfAddressCustomer.setColumns(10);
		tfAddressCustomer.setBounds(397, 106, 146, 20);
		frame.getContentPane().add(tfAddressCustomer);
		
		tfPhoneEmployee = new JTextField();
		tfPhoneEmployee.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfPhoneEmployee.setColumns(10);
		tfPhoneEmployee.setBounds(397, 75, 146, 20);
		frame.getContentPane().add(tfPhoneEmployee);
		
		String[] columnNames = { "M?? Kh??ch h??ng", "T??n Kh??ch h??ng", "Gi???i t??nh", "??i???n tho???i", "?????a ch???","M?? xe"};
		DefaultTableModel dtm = new DefaultTableModel(columnNames,0);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 217, 814, 183);
		frame.getContentPane().add(scrollPane);
		table = new JTable(dtm);
		listCustomers = customerModel.getAllCustomers(); 
		for (Customer customer : listCustomers) {
			Object[] row = {customer.getID(), customer.getName(), customer.getGender(), customer.getPhone(),customer.getAddress(),customer.getiDcar()};
			dtm.addRow(row);
		}
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				tfIDCustomer.setText(table.getValueAt(index, 0).toString());
				tfNameCustomer.setText(table.getValueAt(index, 1).toString());
				tfPhoneEmployee.setText(table.getValueAt(index, 3).toString());
				tfAddressCustomer.setText(table.getValueAt(index, 4).toString());
				cbIDCar.setSelectedItem(table.getValueAt(index, 5).toString());
				byte[] pic = customerModel.getPictureByID(table.getValueAt(index, 0).toString());
				if(pic != null) {
					Image img;
					try {
						img = ImageHelper.createImageFromByteArray(pic, "jpg");
						Picture.setIcon(new ImageIcon(img));
						personalImgae = ImageHelper.toByteArray(img, "jpg");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				else {
					ImageIcon icon = new ImageIcon(getClass().getResource("image//user.jpg"));
					Picture.setIcon(icon);
				}
				if(table.getValueAt(index, 2).toString().equals("Nam")) {
					rdMale.doClick();
				}
				else {
					rdFemale.doClick();
				}
			}
		});
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		scrollPane.setViewportView(table);
		
		JLabel lbPicture = new JLabel("H??nh ???nh");
		lbPicture.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbPicture.setBounds(561, 41, 100, 20);
		frame.getContentPane().add(lbPicture);
		
		JButton btnAddEmployee = new JButton("Th??m");
		btnAddEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfIDCustomer.getText().equals("")
						||tfNameCustomer.getText().equals("")
						||tfAddressCustomer.getText().equals("")
						||tfPhoneEmployee.getText().equals("")){
					JOptionPane.showMessageDialog(null, "C??c m???c kh??ng ???????c b??? tr???ng",
		  	                  "L???i", JOptionPane.ERROR_MESSAGE);
				}
				else {
					String gender = "Nam";
					if(rdFemale.isSelected()) {
						gender = "N???";
					}
					Customer customer = new Customer(tfIDCustomer.getText(),
							tfNameCustomer.getText(),
							gender,
							tfPhoneEmployee.getText(),
							tfAddressCustomer.getText(),
							personalImgae,
							cbIDCar.getSelectedItem().toString());
					try {
						if(customerModel.insertCustomer(customer)) {
							JOptionPane.showMessageDialog(null, "Th??m Kh??ch h??ng th??nh c??ng",
						              "Th??m Kh??ch h??ng", JOptionPane.INFORMATION_MESSAGE);
							Object[] row = {customer.getID(), customer.getName(), customer.getGender(), customer.getPhone(),customer.getAddress()};
							dtm.addRow(row);
						}
						else {
							JOptionPane.showMessageDialog(null, "Th??m Kh??ch h??ng kh??ng th??nh c??ng",
						              "Th??m Kh??ch h??ng", JOptionPane.ERROR_MESSAGE);
						}
							
					} catch (ClassNotFoundException | SQLException e1) {
						JOptionPane.showMessageDialog(null, "Th??m kh??ng th??nh c??ng\n"
								+ "M?? kh??ch h??ng c?? th??? ???? t???n t???i\n",
					              "Th??m kh??ch h??ng", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnAddEmployee.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAddEmployee.setBounds(63, 168, 72, 28);
		frame.getContentPane().add(btnAddEmployee);
		JButton btnEditEmployee = new JButton("S???a");
		btnEditEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfIDCustomer.getText().equals("")
						||tfNameCustomer.getText().equals("")
						||tfAddressCustomer.getText().equals("")
						||tfPhoneEmployee.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "C??c m???c kh??ng ???????c b??? tr???ng",
		  	                  "L???i", JOptionPane.ERROR_MESSAGE);
				}
				else {
					String gender = "Nam";
					if(rdFemale.isSelected()) {
						gender = "N???";
					}
					Customer customer = new Customer(tfIDCustomer.getText(),
							tfNameCustomer.getText(),
							gender,
							tfPhoneEmployee.getText(),
							tfAddressCustomer.getText(),
							personalImgae,cbIDCar.getSelectedItem().toString());
					try {
						if(customerModel.editCustomer(customer)) {
							for(int i = 0; i < table.getRowCount(); i++) {
								if(tfIDCustomer.getText().equals(table.getValueAt(i, 0))) {
									dtm.removeRow(i);
									Object[] row = {customer.getID(), customer.getName(), customer.getGender(), customer.getPhone(),customer.getAddress(),customer.getiDcar()};
									dtm.insertRow(i, row);
								}
							}
							JOptionPane.showMessageDialog(null, "C???p nh???t th??nh c??ng",
						              "S???a th??ng tin", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(null, "C???p nh???t kh??ng th??nh c??ng",
						              "S???a th??ng tin", JOptionPane.ERROR_MESSAGE);
						}
					} catch (HeadlessException | ClassNotFoundException | SQLException | IOException e1) {
						JOptionPane.showMessageDialog(null, "S???a kh??ng th??nh c??ng\n"
								+ "H??y ki???m tra l???i m?? kh??ch h??ng ho???c h??a ????n\n",
					              "S???a kh??ch h??ng", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnEditEmployee.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnEditEmployee.setBounds(248, 167, 79, 30);
		frame.getContentPane().add(btnEditEmployee);
		JButton btnDeleteEmployee = new JButton("X??a");
		btnDeleteEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfIDCustomer.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "M?? Kh??ch h??ng kh??ng ???????c b??? tr???ng",
		  	                  "L???i", JOptionPane.ERROR_MESSAGE);
				}
				else {
					if(JOptionPane.showConfirmDialog(null, "X??a kh??ch h??ng") == 0) {
						try {
							if(customerModel.deleteCustomer(tfIDCustomer.getText())) {
								for(int i = 0; i < table.getRowCount(); i++) {
									if(tfIDCustomer.getText().equals(table.getValueAt(i, 0))) {
										dtm.removeRow(i);
									}
								}
								JOptionPane.showMessageDialog(null, "???? X??a",
							              "X??a kh??ch h??ng", JOptionPane.INFORMATION_MESSAGE);
							}
							else {
								JOptionPane.showMessageDialog(null, "X??a kh??ng th??nh c??ng",
							              "X??a kh??ch h??ng", JOptionPane.ERROR_MESSAGE);
							}
						} catch (HeadlessException | ClassNotFoundException | SQLException e1) {
							JOptionPane.showMessageDialog(null, "X??a kh??ng th??nh c??ng\n"
									+ "H??y ki???m tra l???i m?? kh??ch h??ng ho???c h??a ????n\n",
						              "X??a kh??ch h??ng", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
		btnDeleteEmployee.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDeleteEmployee.setBounds(445, 168, 72, 28);
		frame.getContentPane().add(btnDeleteEmployee);
		
		JLabel lbAddressCustomer_1 = new JLabel("M?? xe");
		lbAddressCustomer_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbAddressCustomer_1.setBounds(317, 137, 100, 20);
		frame.getContentPane().add(lbAddressCustomer_1);
		
		frame.setVisible(true);
	}

}
