package com.revature.trms2;

import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;
import com.revature.beans.Status;
import com.revature.dao.EmployeeDAO;
import com.revature.dao.ReimbursementDAO;
import com.revature.dao.StatusDAO;

import java.util.List;

public class App 
{
    public static void main( String[] args ) {
    	ReimbursementDAO rdao = new ReimbursementDAO();
    	List<Reimbursement> reimbursements = rdao.getAllReimbursements();
        System.out.println(reimbursements.get(0).getR_submitDate());
    	StatusDAO sdao = new StatusDAO();
        List<Status> statuses = sdao.getAllStatuses();
        System.out.println(statuses.get(0).getInfo());
        EmployeeDAO dao = new EmployeeDAO();
        List<Employee> employees = dao.getAllEmployees();
        System.out.println(employees.get(0).getE_firstName());
        
    }
}
