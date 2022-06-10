package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DAO.DBConnection;
import Entity.Bill;

public class BillModel {
	

	
	
	public boolean insertBill(Bill bill) throws ClassNotFoundException, SQLException { 
		Connection con = null;
		con = DBConnection.getMySQLConnection();
		String qString = "INSERT doan.bill(ID, IDCus, IDCar, TotalPrice) VALUES (?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(qString);
		pstmt.setString(1, bill.getID());
		pstmt.setString(2, bill.getIDCustomer());
		pstmt.setString(3, bill.getIDCar());
		pstmt.setFloat(4, bill.getTotalPrice());
		return pstmt.executeUpdate() > 0;
	}
	public boolean insertInfoBill(String idBill, String idJob, int amount, float discount, float Price ) throws ClassNotFoundException, SQLException { 
		Connection con = null;
		con = DBConnection.getMySQLConnection();
		String qString = "INSERT doan.billinfo(IDBill, IDJob, Amount, Discount, Price) VALUES (?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(qString);
		pstmt.setString(1, idBill);
		pstmt.setString(2, idJob);
		pstmt.setInt(3, amount);
		pstmt.setFloat(4, discount);
		pstmt.setFloat(5, Price);
		return pstmt.executeUpdate() > 0;
	}
	public ArrayList<Bill> getAllBills() throws ClassNotFoundException{
		ArrayList<Bill> listBills = new ArrayList<Bill>();
		Connection con = null;
		try {
			String sql = "SELECT * FROM doan.bill";
			con = DBConnection.getMySQLConnection();
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				Bill bill = new Bill(rs.getString(1),rs.getString(2),rs.getString(3),rs.getFloat(4));
				listBills.add(bill);
			}
			con.close();
			return listBills;
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
	public String[][] listInfoBill(String idBill) throws ClassNotFoundException, SQLException{
		Connection con = null;
		int countRow = this.getCountListinfoBill(idBill);
		String[][] listInfoBills = new String[countRow][6];
		try {
			String sql = "SELECT billinfo.IDJob, job.JobName, billinfo.Amount, billinfo.Discount, billinfo.Price FROM billinfo LEFT JOIN job ON billinfo.IDJob = job.ID WHERE billinfo.IDBill = '" + idBill +"'";
			con = DBConnection.getMySQLConnection();
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			int i = 0;
			while (rs.next()) {
				String[] row = {String.valueOf(i+1),rs.getString(1),rs.getString(2),String.valueOf(rs.getInt(3)),String.valueOf(rs.getFloat(4)), String.valueOf(rs.getFloat(5))};
				listInfoBills[i] = row;
				i++;
			}
			con.close();
			return listInfoBills;
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
	public int getCountListinfoBill(String idBill) throws ClassNotFoundException, SQLException {
		Connection con = null;
		int size = 0;
		String sql = "SELECT * FROM doan.billinfo WHERE doan.billinfo.IDBill = '" + idBill + "'";
		con = DBConnection.getMySQLConnection();
		Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		while(rs.next()) size ++;
	    return size;
	}
	public boolean deleteBill(String id) throws ClassNotFoundException, SQLException {
		Connection con = null;
		con = DBConnection.getMySQLConnection();
		String qString = "DELETE FROM doan.bill WHERE ID = ?";
		PreparedStatement pstmt = con.prepareStatement(qString);
		pstmt.setString(1, id);
		return pstmt.executeUpdate() > 0;
	}
	public boolean deleteBillInfo(String id) throws ClassNotFoundException, SQLException {
		Connection con = null;
		con = DBConnection.getMySQLConnection();
		String qString = "DELETE FROM doan.billinfo WHERE IDBill = ?";
		PreparedStatement pstmt = con.prepareStatement(qString);
		pstmt.setString(1, id);
		return pstmt.executeUpdate() > 0;
	}
}
