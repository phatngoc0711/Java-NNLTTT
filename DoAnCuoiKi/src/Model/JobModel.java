package Model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DAO.DBConnection;
import Entity.Job;

public class JobModel {
	public JobModel() {
		
	}
	
	public boolean insertJob(Job job) throws ClassNotFoundException, SQLException {
		Connection con = null;
		con = DBConnection.getMySQLConnection();
		String qString = "INSERT doan.job(ID, JobName, Value) VALUES (?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(qString);
		pstmt.setString(1, job.getJobID());
		pstmt.setString(2, job.getJobName());
		pstmt.setFloat(3, job.getValue());
		return pstmt.executeUpdate() > 0;
	}
	public boolean insertJobInfo(String idjob, String iddevice, int amount) throws ClassNotFoundException, SQLException {
		Connection con = null;
		con = DBConnection.getMySQLConnection();
		String qString = "INSERT doan.jobinfo(IDJob, IDDevice, Amount) VALUES (?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(qString);
		pstmt.setString(1, idjob);
		pstmt.setString(2, iddevice);
		pstmt.setInt(3, amount);
		return pstmt.executeUpdate() > 0;
	}
	public boolean editJob(Job job) throws ClassNotFoundException, SQLException, IOException {
		Connection con = null;
		con = DBConnection.getMySQLConnection();
		String qString = "UPDATE doan.job set JobName = ? , Value = ? WHERE ID = ?";
		PreparedStatement pstmt = con.prepareStatement(qString);
		pstmt.setString(3, job.getJobID());
		pstmt.setString(1, job.getJobName());
		pstmt.setFloat(2, job.getValue());
		return pstmt.executeUpdate() > 0 ;
	}
	public boolean editJobInfo(String idjob, String iddevice, int amount) throws ClassNotFoundException, SQLException, IOException {
		Connection con = null;
		con = DBConnection.getMySQLConnection();
		String qString = "UPDATE doan.jobinfo set Amount = ? WHERE IDJob = ? AND IDDevice = ? ";
		PreparedStatement pstmt = con.prepareStatement(qString);
		pstmt.setString(2, idjob);
		pstmt.setString(3, iddevice);
		pstmt.setFloat(1, amount);
		return pstmt.executeUpdate() > 0 ;
	}
	public boolean deleteJob(String id) throws ClassNotFoundException, SQLException {
		Connection con = null;
		con = DBConnection.getMySQLConnection();
		String qString = "DELETE FROM doan.job WHERE ID = ?";
		PreparedStatement pstmt = con.prepareStatement(qString);
		pstmt.setString(1, id);
		return pstmt.executeUpdate() > 0;
	}
	public boolean deleteJobInfo(String idjob, String idDevice) throws ClassNotFoundException, SQLException {
		Connection con = null;
		con = DBConnection.getMySQLConnection();
		String qString = "DELETE FROM doan.jobinfo WHERE IDJob = ? AND IDDevice = ?";
		PreparedStatement pstmt = con.prepareStatement(qString);
		pstmt.setString(1, idjob);
		pstmt.setString(2, idDevice);
		return pstmt.executeUpdate() > 0;
	}
	public ArrayList<Job> getAllJob() throws ClassNotFoundException{
		ArrayList<Job> listJobs = new ArrayList<Job>();
		Connection con = null;
		try {
			String sql = "SELECT * FROM doan.job";
			con = DBConnection.getMySQLConnection();
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				Job job = new Job(rs.getString(1),rs.getString(2),rs.getFloat(3));
				listJobs.add(job);
			}
			con.close();
			return listJobs;
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
	public String[][] listInfojob() throws ClassNotFoundException, SQLException{
		Connection con = null;
		int countRow = this.getCountListinfoJob();
		String[][] listInfoJobs = new String[countRow][6];
		try {
			String sql = "SELECT ID, JobName, Value, IDDevice, Amount FROM job LEFT JOIN jobinfo ON job.ID = jobinfo.IDJob";
			con = DBConnection.getMySQLConnection();
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			int i = 0;
			while (rs.next()) {
				String amount = "";
				if(rs.getInt(5) != 0)
					amount = String.valueOf(rs.getInt(5));
				String[] row = {rs.getString(1),rs.getString(2),String.valueOf(rs.getFloat(3)), rs.getString(4),amount};
				listInfoJobs[i] = row;
				i++;
			}
			con.close();
			return listInfoJobs;
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
	public int getCountListinfoJob() throws ClassNotFoundException, SQLException {
		Connection con = null;
		int size = 0;
		String sql = "SELECT ID, JobName, Value, IDDevice, Amount FROM job LEFT JOIN jobinfo ON job.ID = jobinfo.IDJob";
		con = DBConnection.getMySQLConnection();
		Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		while(rs.next()) size ++;
	    return size;
	}
}
