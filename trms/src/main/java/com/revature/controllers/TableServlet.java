package com.revature.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Employee;

/**
 * Servlet implementation class TableServlet
 */
public class TableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    //public TableServlet() {
    //    super();
    //    // TODO Auto-generated constructor stub
    //}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String jsonString = "{\"name\":\"Mehrab Rahman\"}";
		
		String jsonString = "{\"e_id\":\"1\", \"e_firstName\":\"John\", \"e_lastName\":\"Doe\", \"e_password\":\"password\", \"e_email\":\"jokes@emai.com\"}";
		

	    response.setContentType("application/json");

	    ObjectMapper mapper = new ObjectMapper();
	    Employee me = mapper.readValue(jsonString, Employee.class);

	    //send back Person
	    mapper.writeValue(response.getOutputStream(), me);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	doGet(request, response);
	//}

}
