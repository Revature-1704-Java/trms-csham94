package com.revature.trms;

import com.revature.beans.Employee;
import com.revature.dao.EmployeeDAO;

import java.util.List;

public class App 
{
    public static void main( String[] args ) {
    	EmployeeDAO dao = new EmployeeDAO();
        List<Employee> employees = dao.getAllEmployees();
        System.out.println(employees.get(0));
    }
}