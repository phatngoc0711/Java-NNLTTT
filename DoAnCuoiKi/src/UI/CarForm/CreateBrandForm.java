package UI.CarForm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import Entity.Employee;
import Model.CarModel;

import javax.swing.JRadioButton;
import javax.print.attribute.standard.PrinterInfo;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.spi.LocaleNameProvider;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ObjectInputFilter.Status;
import java.sql.SQLException;

import javax.swing.ListSelectionModel;

public class CreateBrandForm {

	private JFrame frame;
	private JTable table;
	private JTextField tfBrandName;
	private CarModel carModel = new CarModel();
	
	public CreateBrandForm() throws ClassNotFoundException {
		frame = new JFrame();
		frame.setTitle("Thêm hãng xe");
		frame.setBounds(200, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bảng hãng xe");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(30, 22, 143, 25);
		frame.getContentPane().add(lblNewLabel);
		
		String[] colName = {"Tên Hãng sản xuất"};
		DefaultTableModel dtm = new DefaultTableModel(colName,0);
		ArrayList<String> listBrand = new ArrayList<String>();
		listBrand = carModel.getAllBrandCar();
		for(String brandName : listBrand) {
			Object[] rowObjects = {brandName};
			dtm.addRow(rowObjects);
		}
		table = new JTable(dtm);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				tfBrandName.setText(table.getValueAt(index,0).toString());
			}
		});
		table.setFillsViewportHeight(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 13));
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 58, 208, 192);
		frame.getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);
		
		JLabel lbBrandCar = new JLabel("Hãng");
		lbBrandCar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbBrandCar.setBounds(307, 45, 56, 25);
		frame.getContentPane().add(lbBrandCar);
		
		tfBrandName = new JTextField();
		tfBrandName.setBounds(253, 75, 171, 20);
		frame.getContentPane().add(tfBrandName);
		tfBrandName.setColumns(10);
		
		JButton btnAddBrand = new JButton("Thêm");
		btnAddBrand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int status = 1;
				for(int i = 0; i < table.getRowCount() ; i++) {
					if(tfBrandName.getText().equals(table.getValueAt(i, 0))) {
						status = 0;
					}
				}
				if(tfBrandName.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Tên nhà sản xuất không được bỏ trống",
		  	                  "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
				else {
					try {
						if(status == 1) {
							if(carModel.addBrandCar(tfBrandName.getText())) {
								Object[] row = {tfBrandName.getText()};
								dtm.addRow(row);
								JOptionPane.showMessageDialog(null, "Tên nhà sản xuất đã được thêm",
					  	                  "Thêm hãng sản xuất", JOptionPane.INFORMATION_MESSAGE);
							}
							else {
								JOptionPane.showMessageDialog(null, "Tên nhà sản xuất không được thêm",
					  	                  "Thêm hãng sản xuất", JOptionPane.ERROR_MESSAGE);
							}
						}
						else if(status == 0){
							JOptionPane.showMessageDialog(null, "Nhà sản xuất đã có trong dữ liệu",
				  	                  "Thêm hãng sản xuất", JOptionPane.ERROR_MESSAGE);
						}
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnAddBrand.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAddBrand.setBounds(228, 120, 89, 52);
		frame.getContentPane().add(btnAddBrand);
		
		JButton btnRemoveBrand = new JButton("Xóa");
		btnRemoveBrand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfBrandName.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Tên Hãng không được bỏ trống",
		  	                  "Xóa hãng", JOptionPane.ERROR_MESSAGE);
				} else
					try {
						if(carModel.deleteBrandCar(tfBrandName.getText())) {
							JOptionPane.showMessageDialog(null, "Đã xóa hãng",
				  	                  "Xóa hãng", JOptionPane.INFORMATION_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(null, "Xóa không thành công",
				  	                  "Xóa hãng", JOptionPane.ERROR_MESSAGE);
						}
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Xóa không thành công vì tồn tại xe trong hãng",
			  	                  "Xóa hãng", JOptionPane.ERROR_MESSAGE);
					}
			}
		});
		btnRemoveBrand.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRemoveBrand.setBounds(335, 120, 89, 52);
		frame.getContentPane().add(btnRemoveBrand);
		
		JButton btnExit = new JButton("Thoát");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnExit.setBounds(277, 198, 89, 52);
		frame.getContentPane().add(btnExit);
		frame.setVisible(true);
	}
}
