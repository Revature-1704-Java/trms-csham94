package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Reimbursement;
import com.revature.util.ConnectionUtil;

public class ReimbursementDAO {
	
	public static List<Reimbursement> getAllReimbursements() {
		PreparedStatement ps = null;
		Reimbursement reim = null;
		List<Reimbursement> reimbursements = new ArrayList<>();
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM trms_user.REIMBURSEMENT";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int rid = rs.getInt("ReimbursementID");
				int rtype = rs.getInt("TypeID");
				double rcost = rs.getDouble("CostTotal");
				int rstatus = rs.getInt("StatusID");
				int reid = rs.getInt("EmployeeID");
				String rinfo = rs.getString("Information");
				String rsubmitdate = rs.getString("SubmitDate");
				String rlocation = rs.getString("EventLocation");
				String rfinalgrade = rs.getString("FinalGrade");
				int rformatid = rs.getInt("FormatID");
				String rdenialreason = rs.getString("DenialReason");
				
				reim = new Reimbursement(rid, rtype, rcost, rstatus, reid, rinfo, rsubmitdate, rlocation, rfinalgrade, rformatid,
						rdenialreason);
				
				reimbursements.add(reim);
			}
			rs.close();
			ps.close();
		} catch (Exception ex) {
			ex.getMessage();
		}
		
		return reimbursements;
	}
	
	public static Reimbursement getReimbursement(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Reimbursement reimb = null;
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM Reimbursement WHERE ReimbursementID = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				int rid = rs.getInt("ReimbursementID");
				int rtype = rs.getInt("TypeID");
				double rcost = rs.getDouble("CostTotal");
				int rstatus = rs.getInt("StatusID");
				int reid = rs.getInt("EmployeeID");
				String rinfo = rs.getString("Information");
				String rsubmitdate = rs.getString("SubmitDate");
				String rlocation = rs.getString("EventLocation");
				String rfinalgrade = rs.getString("FinalGrade");
				int rformatid = rs.getInt("FormatID");
				String rdenialreason = rs.getString("DenialReason");
				
				reimb = new Reimbursement(rid, rtype, rcost, rstatus, reid, rinfo, rsubmitdate, rlocation, rfinalgrade, rformatid,
						rdenialreason);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return reimb;
	}
	
	public static void createRequest(int type, double cost, int id, String info, String location) {
		CallableStatement cs = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "{CALL SP_Add_Reimbursement(?, ?, ?, ?, ?)}";
			cs = conn.prepareCall(sql);
			cs.setInt(1, type);
			cs.setDouble(2, cost);
			cs.setInt(3, id);
			cs.setString(4, info);
			cs.setString(5, location);
			
			Boolean result = cs.execute();
			if (!result)
				System.out.println("Request Added");
			else
				System.out.println("Failed");
			
			cs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

	/*public String getStatus(int statusID) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String t = "";
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT StatusName FROM Status WHERE StatusID = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, statusID);
		
			rs = ps.executeQuery();
			t = rs.getString("StatusName");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return t;
	}
}*/
	
	/*public static String getType(int typeID) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String t = "";
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT TypeDesc FROM EventType WHERE TypeID = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, typeID);
		
			rs = ps.executeQuery();
			t = rs.getString("TypeDesc");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return t;
	}
}*/