package Model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.jdbc.Blob;

import DAO.DBConnection;
import Entity.Customer;

public class CustomerModel {
	
	public ArrayList<Customer> getAllCustomers() throws ClassNotFoundException{
		ArrayList<Customer> listCustomers = new ArrayList<Customer>();
		Connection con = null;
		try {
			String sql = "SELECT * FROM doan.customer";
			con = DBConnection.getMySQLConnection();
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				Blob blob = (Blob) rs.getBlob(6);
				Customer customer = new Customer();
				if(blob == null) {
					customer = new Customer(rs.getString(1),rs.getString(2),rs.getString(3)
							,rs.getString(4),rs.getString(5),null, rs.getString(7));
				}
				else {
					customer = new Customer(rs.getString(1),rs.getString(2),rs.getString(3)
							,rs.getString(4),rs.getString(5),blob.getBytes(1, (int) blob.length()),rs.getString(7));
				}
				listCustomers.add(customer);
			}
			con.close();
			return listCustomers;
		} catch (SQLException e) {
			try {
				if (con != null) {
					con.close();
				}
			} catch(SQLException ex) {
				ex.printStackTrace();
			}
			return null;
		} 
	}
	public boolean insertCustomer(Customer customer) throws ClassNotFoundException, SQLException {
		Connection con = null;
			con = DBConnection.getMySQLConnection();
			String qString = "INSERT doan.customer(ID, Name, Gender, Phone, Address, Picture, IDCar) VALUES (?,?,?,?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(qString);
			pstmt.setString(1, customer.getID());
			pstmt.setString(2, customer.getName());
			pstmt.setString(3, customer.getGender());
			pstmt.setString(4, customer.getPhone());
			pstmt.setString(5, customer.getAddress());
			pstmt.setString(7, customer.getiDcar());
			if(customer.getPicture()!= null) {
				Blob picture = new Blob(customer.getPicture(), null);
				pstmt.setBlob(6, picture);
			}
			else {
				Blob picture = null;
				pstmt.setBlob(6, picture);
			}
			return pstmt.executeUpdate() > 0;
	}
	public byte[] getPictureByID(String id) {
		Connection con = null;
		byte[] pic = null;
		try {
			con = DBConnection.getMySQLConnection();
			Statement pstmt = con.createStatement();
			String qString = "SELECT Picture FROM doan.customer WHERE "
					+ "ID = '"+id+"'";
			ResultSet rs = pstmt.executeQuery(qString);
			if(rs.next() == true)
			{
				Blob blob = (Blob) rs.getBlob(1);
				if(blob == null) {
					pic =null;
				}
				else {
					pic = blob.getBytes(1, (int) blob.length());
				}
			}
				 
		} catch (ClassNotFoundException | SQLException e2) {
			e2.printStackTrace();
		}
		return pic;
	}
	public boolean editCustomer(Customer customer) throws ClassNotFoundException, SQLException, IOException {
		Connection con = null;
		con = DBConnection.getMySQLConnection();
		String qString = "UPDATE doan.customer set Name = ?, Gender = ? , Phone = ? , Address = ? , Picture = ?, IDCar = ? WHERE ID = ?";
		PreparedStatement pstmt = con.prepareStatement(qString);
		pstmt.setString(7, customer.getID());
		pstmt.setString(1, customer.getName());
		pstmt.setString(2, customer.getGender());
		pstmt.setString(3, customer.getPhone());
		pstmt.setString(4, customer.getAddress());
		pstmt.setString(6, customer.getiDcar());
		if(customer.getPicture()!= null) {
			Blob picture = new Blob(customer.getPicture(),null);
			pstmt.setBlob(5, picture);
		}
		else {
			Blob picture = null;
			pstmt.setBlob(5, picture);
		}
		return pstmt.executeUpdate() > 0 ;
	}
	public boolean deleteCustomer(String id) throws ClassNotFoundException, SQLException {
		Connection con = null;
		con = DBConnection.getMySQLConnection();
		String qString = "DELETE FROM doan.customer WHERE ID = ?";
		PreparedStatement pstmt = con.prepareStatement(qString);
		pstmt.setString(1, id);
		return pstmt.executeUpdate() > 0;
	}
}
