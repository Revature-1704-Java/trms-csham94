package com.revature.beans;

public class Employee {
	private int E_id;
	private String E_firstName;
	private String E_lastName;
	private String E_password;
	private String E_email;
	
	public Employee(int e_id, String e_firstName, String e_lastName, String e_password, String e_email) {
		super();
		E_id = e_id;
		E_firstName = e_firstName;
		E_lastName = e_lastName;
		E_password = e_password;
		E_email = e_email;
	}

	/*public Employee(int e_id, String e_firstName, String e_lastName, String e_email) {
		super();
		E_id = e_id;
		E_firstName = e_firstName;
		E_lastName = e_lastName;
		E_email = e_email;
	}*/
	
	public Employee() {
		super();
	}

	public int getE_id() {
		return E_id;
	}

	public void setE_id(int e_id) {
		E_id = e_id;
	}

	public String getE_firstName() {
		return E_firstName;
	}

	public void setE_firstName(String e_firstName) {
		E_firstName = e_firstName;
	}

	public String getE_lastName() {
		return E_lastName;
	}

	public void setE_lastName(String e_lastName) {
		E_lastName = e_lastName;
	}

	public String getE_password() {
		return E_password;
	}

	public void setE_password(String e_password) {
		E_password = e_password;
	}

	public String getE_email() {
		return E_email;
	}

	public void setE_email(String e_email) {
		E_email = e_email;
	}
}