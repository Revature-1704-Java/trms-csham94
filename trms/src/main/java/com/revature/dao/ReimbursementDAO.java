package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Reimbursement;
import com.revature.util.ConnectionUtil;

public class ReimbursementDAO {
	
	public List<Reimbursement> getAllReimbursements() {
		PreparedStatement ps = null;
		Reimbursement re = null;
		List<Reimbursement> reimbursements = new ArrayList<>();
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM trms_user.Reimbursement";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				/*int id = rs.getInt("EmployeeID");
				String firstName = rs.getString("FirstName");
				String lastName = rs.getString("LastName");
				String password = rs.getString("EmpPassword");
				String email = rs.getString("Email");
				
				emp = new Employee(id, firstName, lastName, password, email);
				employees.add(emp);*/
			}
			rs.close();
			ps.close();
		} catch (Exception ex) {
			ex.getMessage();
		}
		
		return reimbursements;
	}
	
	public Reimbursement getReimbursements(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Reimbursement re = null;
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM trms_user.Reimbursement WHERE ReimbursementID = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				/*int eid = rs.getInt("EmployeeID");
				String firstName = rs.getString("FirstName");
				String lastName = rs.getString("LastName");
				String password = rs.getString("EmpPassword");
				String email = rs.getString("Email");
				
				e = new Employee(eid, firstName, lastName, password, email);*/
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
		return re;
	}
}