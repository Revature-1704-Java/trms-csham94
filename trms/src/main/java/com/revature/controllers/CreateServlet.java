package com.revature.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.Employee;
import com.revature.dao.ReimbursementDAO;

public class CreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CreateServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.getRequestDispatcher("create.html").include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession mySession = request.getSession();
		int type = Integer.parseInt(request.getParameter("type"));
	    double cost = Double.parseDouble(request.getParameter("cost"));
	    String info = request.getParameter("info");
	    String location = request.getParameter("location");
	    int id = (int) mySession.getAttribute("employee");
	    ReimbursementDAO rdao = new ReimbursementDAO();
	    rdao.createRequest(type, cost, id, info, location);
		//System.out.println((int) mySession.getAttribute("employee"));
		response.sendRedirect("display");
	}

}
