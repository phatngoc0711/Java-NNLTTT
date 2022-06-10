package Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;


import DAO.DBConnection;
import Entity.Revenue;

public class RevenueModel {
	
	public RevenueModel() {
		
	}
	
	public boolean insertRevenue(String type, float value) throws ClassNotFoundException, SQLException {
		Connection con = null;
		Date date = new Date(Calendar.getInstance().getTime().getTime());
		con = DBConnection.getMySQLConnection();
		String qString = "INSERT doan.revenue(Date, Type, Value) VALUES (?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(qString);
		pstmt.setDate(1, date);
		pstmt.setString(2, type);
		pstmt.setFloat(3, value);
		return pstmt.executeUpdate() > 0;
	}
	public ArrayList<Revenue> getAllRevenues() throws ClassNotFoundException{
		ArrayList<Revenue> listRevenues = new ArrayList<Revenue>();
		Connection con = null;
		try {
			con = DBConnection.getMySQLConnection();
			String sql = "SELECT * FROM doan.revenue";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				Revenue revenue = new Revenue(rs.getDate(2),rs.getString(3),rs.getFloat(4));
				listRevenues.add(revenue);
			}
			con.close();
			return listRevenues;
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
}
