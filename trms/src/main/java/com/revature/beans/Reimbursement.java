package com.revature.beans;

public class Reimbursement {
	private int R_id;
	private double R_cost;
	private int R_EmpId;
	private String R_information;
	
	public Reimbursement(int r_id, double r_cost, int r_EmpId, String r_information) {
		super();
		R_id = r_id;
		R_cost = r_cost;
		R_EmpId = r_EmpId;
		R_information = r_information;
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

	public double getR_cost() {
		return R_cost;
	}

	public void setR_cost(double r_cost) {
		R_cost = r_cost;
	}

	public int getR_EmpId() {
		return R_EmpId;
	}

	public void setR_EmpId(int r_EmpId) {
		R_EmpId = r_EmpId;
	}

	public String getR_information() {
		return R_information;
	}

	public void setR_information(String r_information) {
		R_information = r_information;
	}
	
	
}
