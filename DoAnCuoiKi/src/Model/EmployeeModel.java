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
import Entity.Employee;

public class EmployeeModel {
	public EmployeeModel() {
		
	}
	public boolean checkUser(String username, String password) {
		Connection con = null;
		String type;
		String id;
		try {
			con = DBConnection.getMySQLConnection();
			Statement pstmt = con.createStatement();
			String qString = "SELECT login.id , employee.type FROM doan.login, doan.employee WHERE "
					+ "userName = '"+ username
					+ "' AND password ='"+ password 
					+"' AND login.id = employee.id ";
			ResultSet rs = pstmt.executeQuery(qString);
			if(rs.next() == true)
			{
				type = rs.getString(2);
				id = rs.getString(1);
				GlobalModel.setglobalTypeUser(type,id);
				return true;
			}
				 
		} catch (ClassNotFoundException | SQLException e2) {
			e2.printStackTrace();
		}
		return false;
	}
	public String getNameEmployeebyID() {
		String name = "";
		if(!GlobalModel.globalIDUser.equals(""))
		{
			Connection con = null;
			try {
				con = DBConnection.getMySQLConnection();
				Statement pstmt = con.createStatement();
				String qString = "SELECT Name FROM doan.employee WHERE ID = '"+ GlobalModel.globalIDUser +"'";
				ResultSet rs = pstmt.executeQuery(qString);
				if(rs.next() == true)
				{
					name = rs.getString(1);
				}
					 
			} catch (ClassNotFoundException | SQLException e2) {
				e2.printStackTrace();
			}
		}
		return name;
	}
	public Employee getInfoEmployee(String id) { 
		Employee employee = new Employee();
		Connection con = null;
		try {
			con = DBConnection.getMySQLConnection();
			Statement pstmt = con.createStatement();
			String qString = "SELECT * FROM doan.employee WHERE "
					+ "ID = '"+id+"'";
			ResultSet rs = pstmt.executeQuery(qString);
			if(rs.next() == true)
			{
				employee.setID(rs.getString(1));
				employee.setName(rs.getString(2));
				employee.setGender(rs.getString(3));
				employee.setPhone(rs.getString(4));
				employee.setAddress(rs.getString(5));
				employee.setSalary(rs.getFloat(6));
				employee.setType(rs.getString(7));
				Blob blob = (Blob) rs.getBlob(8);
				if(blob != null) {
					byte[] picture = blob.getBytes(1, (int) blob.length());
					employee.setPicture(picture);
				}
				else {
					byte[] picture = null;
					employee.setPicture(picture);
				}
			}
				 
		} catch (ClassNotFoundException | SQLException e2) {
			e2.printStackTrace();
		}
		return employee;
	}
	public String getUserNamebyID(String id) {
		Connection con = null;
		String username = "";
		try {
			con = DBConnection.getMySQLConnection();
			Statement pstmt = con.createStatement();
			String qString = "SELECT Username FROM doan.login WHERE "
					+ "ID = '"+id+"'";
			ResultSet rs = pstmt.executeQuery(qString);
			if(rs.next() == true)
			{
				username = rs.getString(1);
			}
				 
		} catch (ClassNotFoundException | SQLException e2) {
			e2.printStackTrace();
		}
		return username;
	}
	public void changePassword(String id, String pass) {
		Connection con = null;
		try {
			con = DBConnection.getMySQLConnection();
			String qString = "update doan.login set Password = '" + pass +"'  WHERE ID = '" + id + "'";
			Statement pstmt = con.prepareStatement(qString);
			pstmt.executeUpdate(qString);
		} catch (ClassNotFoundException | SQLException e2) {
			e2.printStackTrace();
		}
	}
	public boolean Update_Employee(Employee employee) throws ClassNotFoundException, SQLException, IOException {
		Connection con = null;
		con = DBConnection.getMySQLConnection();
		String qString = "UPDATE doan.employee set Name = ?, Gender = ? , Phone = ? , Address = ? , Salary = ?, Type = ?, Picture = ? WHERE ID = ?";
		PreparedStatement pstmt = con.prepareStatement(qString);
		pstmt.setString(8, employee.getID());
		pstmt.setString(1, employee.getName());
		pstmt.setString(2, employee.getGender());
		pstmt.setString(3, employee.getPhone());
		pstmt.setString(4, employee.getAddress());
		pstmt.setFloat(5, employee.getSalary());
		pstmt.setString(6, employee.getType());
		if(employee.getPicture()!= null) {
			Blob picture = new Blob(employee.getPicture(),null);
			pstmt.setBlob(7, picture);
		}
		else {
			Blob picture = null;
			pstmt.setBlob(7, picture);
		}
		return pstmt.executeUpdate() > 0 ;
	}
	public boolean addEmployee(Employee employee) throws ClassNotFoundException, SQLException {
		Connection con = null;
			con = DBConnection.getMySQLConnection();
			String qString = "INSERT doan.employee(ID, Name, Gender, Phone, Address, Salary, Type, Picture) VALUES (?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(qString);
			pstmt.setString(1, employee.getID());
			pstmt.setString(2, employee.getName());
			pstmt.setString(3, employee.getGender());
			pstmt.setString(4, employee.getPhone());
			pstmt.setString(5, employee.getAddress());
			pstmt.setFloat(6, employee.getSalary());
			pstmt.setString(7, employee.getType());
			if(employee.getPicture()!= null) {
				Blob picture = new Blob(employee.getPicture(), null);
				pstmt.setBlob(8, picture);
			}
			else {
				Blob picture = null;
				pstmt.setBlob(8, picture);
			}
			return pstmt.executeUpdate() > 0;
	}
	public boolean deleteEmployee(String id) throws ClassNotFoundException, SQLException {
		Connection con = null;
		con = DBConnection.getMySQLConnection();
		String qString = "DELETE FROM doan.employee WHERE ID = ?";
		PreparedStatement pstmt = con.prepareStatement(qString);
		pstmt.setString(1, id);
		return pstmt.executeUpdate() > 0;
	}
	public ArrayList<Employee> getAllEmployee() throws ClassNotFoundException{
		ArrayList<Employee> listEmployee = new ArrayList<Employee>();
		Connection con = null;
		try {
			String sql = "SELECT * FROM doan.employee";
			con = DBConnection.getMySQLConnection();
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				Blob blob = (Blob) rs.getBlob(8);
				Employee employee = new Employee();
				if(blob == null) {
					employee = new Employee(rs.getString(1),rs.getString(2),rs.getString(3)
							,rs.getString(4),rs.getString(5),rs.getFloat(6),rs.getString(7),null);
				}
				else {
					employee = new Employee(rs.getString(1),rs.getString(2),rs.getString(3)
							,rs.getString(4),rs.getString(5),rs.getFloat(6),rs.getString(7),blob.getBytes(1, (int) blob.length()));
				}
				listEmployee.add(employee);
			}
			con.close();
			return listEmployee;
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
	public boolean addAccountEmployee(String id, String uname, String pass) {
		Connection con = null;
		try {
			con = DBConnection.getMySQLConnection();
			String qString = "INSERT doan.login(ID, Username, Password) VALUES ('" + id
					+"', '"+ uname
					+"', '"+ pass +"')";
			Statement pstmt = con.prepareStatement(qString);
			pstmt.executeUpdate(qString);
			return true;
		} catch (ClassNotFoundException | SQLException e2) {
			e2.printStackTrace();
		}
		return false;
	}
	public ArrayList<String> getIDAccnonExist() throws ClassNotFoundException {
		ArrayList<String> listID = new ArrayList<String>();
		Connection con = null;
		try {
			String sql = "SELECT doan.employee.ID FROM doan.employee "
					+ "WHERE doan.employee.ID NOT IN (SELECT doan.login.ID FROM doan.login)";
			con = DBConnection.getMySQLConnection();
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				listID.add(rs.getString(1));
			}
			con.close();
			return listID;
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
	public byte[] getPictureByID(String id) {
		Connection con = null;
		byte[] pic = null;
		try {
			con = DBConnection.getMySQLConnection();
			Statement pstmt = con.createStatement();
			String qString = "SELECT Picture FROM doan.employee WHERE "
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
	public boolean removeUser(String id) throws ClassNotFoundException, SQLException {
		Connection con = null;
		con = DBConnection.getMySQLConnection();
		String qString = "DELETE FROM doan.login WHERE ID = ?";
		PreparedStatement pstmt = con.prepareStatement(qString);
		pstmt.setString(1, id);
		return pstmt.executeUpdate() > 0;
	}
}
