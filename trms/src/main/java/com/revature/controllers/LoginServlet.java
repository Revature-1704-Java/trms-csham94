package com.revature.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.beans.Employee;
import com.revature.dao.EmployeeDAO;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
	    String password = request.getParameter("pass");
		Employee emp = EmployeeDAO.getEmployeeByEmail(email);
		if (emp != null) {
			if(password.equals(emp.getE_password())) {
				request.getSession().setAttribute("employee", emp.getE_id());
				request.getSession().setAttribute("title", emp.getE_title());
				response.getWriter().append(emp.getE_firstName());
				response.sendRedirect("display");
			} else {
				response.getWriter().append("Incorrect Password");
			}
		} else {
			response.getWriter().append("Invalid Email");
		}
	}

}
