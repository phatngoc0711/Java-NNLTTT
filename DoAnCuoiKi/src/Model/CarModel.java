package Model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DAO.DBConnection;
import Entity.Car;

public class CarModel {
	private Car car = new Car();
	
	
	public CarModel() {
		
	}
	
	public ArrayList<String> getAllBrandCar() throws ClassNotFoundException {
		ArrayList<String> listID = new ArrayList<String>();
		Connection con = null;
		try {
			String sql = "SELECT doan.brandcar.BrandName FROM doan.brandcar";
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
	public boolean addBrandCar(String brandname) throws ClassNotFoundException, SQLException {
		Connection con = null;
		con = DBConnection.getMySQLConnection();
		String qString = "INSERT doan.brandcar(BrandName) VALUES (?)";
		PreparedStatement pstmt = con.prepareStatement(qString);
		pstmt.setString(1, brandname);
		return pstmt.executeUpdate() > 0;
	}
	public boolean addCar(Car car) throws ClassNotFoundException, SQLException {
		Connection con = null;
			con = DBConnection.getMySQLConnection();
			String qString = "INSERT doan.car(ID, Brand, Decription, Color, Status, DateIn, DateOut) VALUES (?,?,?,?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(qString);
			pstmt.setString(1, car.getID());
			pstmt.setString(2, car.getBrand());
			pstmt.setString(3, car.getDecription());
			pstmt.setString(4, car.getColor());
			pstmt.setString(5, car.getStatus());
			pstmt.setDate(6, car.getDateIn());
			pstmt.setDate(7, car.getDateOut());
			return pstmt.executeUpdate() > 0;
	}
	public boolean deleteCar(String id) throws ClassNotFoundException, SQLException {
		Connection con = null;
		con = DBConnection.getMySQLConnection();
		String qString = "DELETE FROM doan.car WHERE ID = ?";
		PreparedStatement pstmt = con.prepareStatement(qString);
		pstmt.setString(1, id);
		return pstmt.executeUpdate() > 0;
	}
	public boolean deleteBrandCar(String brandname) throws ClassNotFoundException, SQLException {
		Connection con = null;
		con = DBConnection.getMySQLConnection();
		String qString = "DELETE FROM doan.brandcar WHERE BrandName = ?";
		PreparedStatement pstmt = con.prepareStatement(qString);
		pstmt.setString(1, brandname);
		return pstmt.executeUpdate() > 0;
	}
	public ArrayList<Car> getAllCar() throws ClassNotFoundException{
		ArrayList<Car> listcar = new ArrayList<Car>();
		Connection con = null;
		try {
			String sql = "SELECT * FROM doan.car";
			con = DBConnection.getMySQLConnection();
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				car = new Car(rs.getString(1),rs.getString(2),rs.getString(3)
						,rs.getString(4),rs.getString(5),rs.getDate(6),rs.getDate(7));
				listcar.add(car);
			}
			con.close();
			return listcar;
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
