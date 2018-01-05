package com.revature.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.Reimbursement;
import com.revature.dao.ReimbursementDAO;

public class DisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DisplayServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession mySession = request.getSession();
	    int title = (int) mySession.getAttribute("title");
	    int empId = (int) mySession.getAttribute("employee");
		List<Reimbursement> reimbursements = null;

		if (title == 1) {
			reimbursements = ReimbursementDAO.getReimbursementsByStatus(3);
		} else if (title == 4) {
			reimbursements = ReimbursementDAO.getReimbursementsByStatus(2);
		} else if (title == 2) {
			reimbursements = ReimbursementDAO.getReimbursementsByStatus(4);
		} else {
			reimbursements = ReimbursementDAO.getReimbursementsByEmployee(empId);
		}
		out.println("<table>");		
		out.println("<form action=\"change\" method=\"GET\">");
		out.println("<tr>");
		out.println("<th>&nbsp;</th>");
		out.println("<th>Type</th>");
		out.println("<th>Total Cost</th>");
		out.println("<th>Status</th>");
		out.println("<th>Employee</th>");
		out.println("<th>Information</th>");
		out.println("<th>Submission Date</th>");
		out.println("<th>Event Location</th>");
		out.println("<th>Final Grade</th>");
		out.println("<th>Grading Format</th>");
		out.println("<th>Reason for Denial</th>");
		out.println("</tr>");
		for (Reimbursement re: reimbursements) {
			out.println("<tr>");
			out.println("<td>"
					+ "<input type=\"radio\" name=\"R_id\" value=\"" + re.getR_id() + "\" >"
					+ "</td>");
			out.println("<td>" + re.getR_type() + "</td>");
			out.println("<td>" + re.getR_cost() + "</td>");
			out.println("<td>" + re.getR_status() + "</td>");
			out.println("<td>" + re.getR_empId() + "</td>");
			out.println("<td>" + re.getR_information() + "</td>");
			out.println("<td>" + re.getR_submitDate() + "</td>");
			out.println("<td>" + re.getR_location() + "</td>");
			out.println("<td>" + re.getR_finalGrade() + "</td>");
			out.println("<td>" + re.getR_formatId() + "</td>");
			out.println("<td>" + re.getR_denialReason() + "</td>");
			out.println("</tr>");
		}
		out.println("<input type=\"submit\" name=\"change\" value=\"deny\">");
		out.println("<input type=\"submit\" name=\"change\" value=\"approve\">");
		out.println("</form>");
		out.println("</table>");
	    request.getRequestDispatcher("display.html").include(request, response);
		
		out.close();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("display.html");
		rd.forward(request, response);
	}

}
