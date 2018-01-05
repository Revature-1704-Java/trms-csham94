package com.revature.beans;

public class Reimbursement {
	private int R_id;
	private int R_type;
	private double R_cost;
	private int R_status;
	private int R_empId;
	private String R_information;
	private String R_submitDate;
	private String R_location;
	//private String R_finalDate;
	private String R_finalGrade;
	private int R_formatId;
	private String R_denialReason;
	
	public Reimbursement(int r_id, int r_type, double r_cost, int r_status, int r_empId, String r_information, String r_submitDate, String r_location, String r_finalGrade, int r_formatId,
			String r_denialReason) {
		super();
		R_id = r_id;
		R_type = r_type;
		R_cost = r_cost;
		R_status = r_status;
		R_empId = r_empId;
		R_information = r_information;
		R_submitDate = r_submitDate;
		R_location = r_location;
		//R_finalDate = r_finalDate;
		R_finalGrade = r_finalGrade;
		R_formatId = r_formatId;
		R_denialReason = r_denialReason;
	}
	
	public Reimbursement() {
		super();
	}
	
	public int getR_id() {
		return R_id;
	}
	
	public void setR_id(int r_id) {
		R_id = r_id;
	}
	
	public int getR_type() {
		return R_type;
	}
	
	public void setR_type(int r_type) {
		R_type = r_type;
	}
	
	public double getR_cost() {
		return R_cost;
	}
	
	public void setR_cost(double r_cost) {
		R_cost = r_cost;
	}
	
	public int getR_status() {
		return R_status;
	}
	
	public void setR_status(int r_status) {
		R_status = r_status;
	}
	
	public int getR_empId() {
		return R_empId;
	}
	
	public void setR_empId(int r_empId) {
		R_empId = r_empId;
	}
	
	public String getR_information() {
		return R_information;
	}
	
	public void setR_information(String r_information) {
		R_information = r_information;
	}
	
	public String getR_submitDate() {
		return R_submitDate;
	}
	
	public void setR_submitDate(String r_submitDate) {
		R_submitDate = r_submitDate;
	}
	
	public String getR_location() {
		return R_location;
	}
	
	public void setR_location(String r_location) {
		R_location = r_location;
	}
	
	/*public String getR_finalDate() {
		return R_finalDate;
	}
	
	public void setR_finalDate(String r_finalDate) {
		R_finalDate = r_finalDate;
	}*/
	
	public String getR_finalGrade() {
		return R_finalGrade;
	}
	
	public void setR_finalGrade(String r_finalGrade) {
		R_finalGrade = r_finalGrade;
	}
	
	public int getR_formatId() {
		return R_formatId;
	}
	
	public void setR_formatId(int r_formatId) {
		R_formatId = r_formatId;
	}
	
	public String getR_denialReason() {
		return R_denialReason;
	}
	
	public void setR_denialReason(String r_denialReason) {
		R_denialReason = r_denialReason;
	}
}
