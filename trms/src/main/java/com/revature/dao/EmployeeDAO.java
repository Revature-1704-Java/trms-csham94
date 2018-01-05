package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Employee;
import com.revature.util.ConnectionUtil;

public class EmployeeDAO {
	
	public static List<Employee> getAllEmployees() {
		PreparedStatement ps = null;
		Employee emp = null;
		List<Employee> employees = new ArrayList<>();
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM trms_user.EMPLOYEE";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("EmployeeID");
				String firstName = rs.getString("FirstName");
				String lastName = rs.getString("LastName");
				String password = rs.getString("EmpPassword");
				String email = rs.getString("Email");
				int title = rs.getInt("TitleID");
				double availReim = rs.getDouble("AvailableReimbursement");
				Integer depHead = rs.getInt("DepartmentHead");
				if (depHead == null) {
					depHead = 0;
				}
				Integer sup = rs.getInt("Supervisor");
				if (sup == null) {
					sup = 0;
				}
				
				
				emp = new Employee(id, firstName, lastName, password, email, title, availReim, depHead, sup);
				
				employees.add(emp);
			}
			rs.close();
			ps.close();
		} catch (Exception ex) {
			ex.getMessage();
		}
		
		return employees;
	}
	
	public static Employee getEmployee(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Employee e = null;
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM Employee WHERE EmployeeID = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				int eid = rs.getInt("EmployeeID");
				String firstName = rs.getString("FirstName");
				String lastName = rs.getString("LastName");
				String password = rs.getString("EmpPassword");
				String email = rs.getString("Email");
				int title = rs.getInt("TitleID");
				double availReim = rs.getDouble("AvailableReimbursement");
				Integer depHead = rs.getInt("DepartmentHead");
				if (depHead == null) {
					depHead = 0;
				}
				Integer sup = rs.getInt("Supervisor");
				if (sup == null) {
					sup = 0;
				}
				
				e = new Employee(eid, firstName, lastName, password, email, title, availReim, depHead, sup);
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
		return e;
	}
	
	public static Employee getEmployeeByEmail(String email) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Employee e = null;
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM Employee WHERE Email = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("EmployeeID");
				String firstName = rs.getString("FirstName");
				String lastName = rs.getString("LastName");
				String password = rs.getString("EmpPassword");
				String eemail = rs.getString("Email");
				int title = rs.getInt("TitleID");
				double availReim = rs.getDouble("AvailableReimbursement");
				int depHead = rs.getInt("DepartmentHead");
				int sup = rs.getInt("Supervisor");
				
				
				e = new Employee(id, firstName, lastName, password, eemail, title, availReim, depHead, sup);
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
		return e;
	}
	
//	public static String getTitle(int titleID) {
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		String t = "";
//		try(Connection conn = ConnectionUtil.getConnection()) {
//			String sql = "SELECT TitleName FROM Title WHERE TitleID = ?";
//			ps = conn.prepareStatement(sql);
//			ps.setInt(1, titleID);
//			
//			rs = ps.executeQuery();
//			t = rs.getString("TitleName");
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		} finally {
//			if (ps != null) {
//				try {
//					ps.close();
//				} catch (SQLException ex) {
//					ex.printStackTrace();
//				}
//			}
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException ex) {
//					ex.printStackTrace();
//				}
//			}
//		}
//		return t;
//	}
}