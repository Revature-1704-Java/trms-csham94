package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Status;
import com.revature.util.ConnectionUtil;

public class StatusDAO {
	
	public static List<Status> getAllStatuses() {
		PreparedStatement ps = null;
		Status st = null;
		List<Status> statuses = new ArrayList<>();
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM trms_user.STATUS";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int sid = rs.getInt("StatusID");
				String sInfo = rs.getString("StatusDesc");
				
				st = new Status(sid, sInfo);
				
				statuses.add(st);
			}
			rs.close();
			ps.close();
		} catch (Exception ex) {
			ex.getMessage();
		}
		
		return statuses;
	}
	
	public static Status getStatus(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Status st = null;
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM Status WHERE StatusID = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				int sid = rs.getInt("StatusID");
				String sInfo = rs.getString("StatusDesc");
				
				st = new Status(sid, sInfo);
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
		return st;
	}
}