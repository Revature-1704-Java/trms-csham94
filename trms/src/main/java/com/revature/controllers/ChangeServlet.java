package com.revature.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.ReimbursementDAO;

public class ChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ChangeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int r_id = Integer.parseInt(request.getParameter("R_id"));
		String r_change = request.getParameter("changeStatus");
		System.out.println(r_id);
		System.out.println(r_change);
		if (r_change.equals("approve") ) {
			ReimbursementDAO.giveApprove(r_id);
		} else {
			ReimbursementDAO.giveDeny(r_id);
		}
		
	    request.getRequestDispatcher("display.html").include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int r_id = Integer.parseInt(request.getParameter("R_id"));
		String r_change = request.getParameter("changeStatus");
		System.out.println(r_id);
		System.out.println(r_change);
		if (r_change.equals("approve") ) {
			ReimbursementDAO.giveApprove(r_id);
		} else {
			ReimbursementDAO.giveDeny(r_id);
		}
		
		response.sendRedirect("display");
	}

}
