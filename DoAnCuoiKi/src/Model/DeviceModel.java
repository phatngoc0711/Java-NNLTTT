package Model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DAO.DBConnection;
import Entity.Device;

public class DeviceModel {
	private Device device = new Device();
	
	public DeviceModel() {
		
	}
	public boolean addDevice(Device device) throws ClassNotFoundException, SQLException {
		Connection con = null;
			con = DBConnection.getMySQLConnection();
			String qString = "INSERT doan.device(ID, Name, Amount, Unit, Value) VALUES (?,?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(qString);
			pstmt.setString(1, device.getID());
			pstmt.setString(2, device.getName());
			pstmt.setInt(3, device.getAmount());
			pstmt.setString(4, device.getUnit());
			pstmt.setFloat(5, device.getValue());
			return pstmt.executeUpdate() > 0;
	}
	public boolean deleteDevice(String id) throws ClassNotFoundException, SQLException {
		Connection con = null;
		con = DBConnection.getMySQLConnection();
		String qString = "DELETE FROM doan.device WHERE ID = ?";
		PreparedStatement pstmt = con.prepareStatement(qString);
		pstmt.setString(1, id);
		return pstmt.executeUpdate() > 0;
	}
	public ArrayList<Device> getAllDevices() throws ClassNotFoundException{
		ArrayList<Device> listDevices = new ArrayList<Device>();
		Connection con = null;
		try {
			con = DBConnection.getMySQLConnection();
			String sql = "SELECT * FROM doan.device";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				device = new Device(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getFloat(5));
				listDevices.add(device);	
			}
			con.close();
			return listDevices;
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
	public boolean editDevices(Device device) throws ClassNotFoundException, SQLException, IOException {
		Connection con = null;
		con = DBConnection.getMySQLConnection();
		String qString = "UPDATE doan.device set Name = ? , Unit = ? , Value = ? WHERE ID = ?";
		PreparedStatement pstmt = con.prepareStatement(qString);
		pstmt.setString(1, device.getName());
		pstmt.setString(2, device.getUnit());
		pstmt.setFloat(3, device.getValue());
		pstmt.setString(4, device.getID());
		return pstmt.executeUpdate() > 0 ;
	}
	public boolean editAmountDevices(String id, int Amount) throws ClassNotFoundException, SQLException, IOException {
		Connection con = null;
		con = DBConnection.getMySQLConnection();
		String qString = "UPDATE doan.device set Amount = ? WHERE ID = ?";
		PreparedStatement pstmt = con.prepareStatement(qString);
		pstmt.setInt(1, Amount);
		pstmt.setString(2, id);
		return pstmt.executeUpdate() > 0 ;
	}
}
