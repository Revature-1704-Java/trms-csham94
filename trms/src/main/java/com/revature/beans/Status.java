package com.revature.beans;

public class Status {
	private int id;
	private String info;
	
	public Status(int id, String info) {
		super();
		this.id = id;
		this.info = info;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	
	
}