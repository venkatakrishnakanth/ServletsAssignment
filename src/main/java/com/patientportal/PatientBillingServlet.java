package com.patientportal;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.patientportal.repository.BillRepository;


public class PatientBillingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	
	
	protected void doGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("status", request.getSession(false).getAttribute("USERNAME"));
	     RequestDispatcher rd = request.getRequestDispatcher("newone.jsp");
	     rd.forward(request, response);
		response.sendRedirect("newone.jsp");
	}
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("status", request.getSession(false).getAttribute("USERNAME"));
	     BillRepository br = new BillRepository();
	    String ret= br.getAllInfo(request.getSession(false).getAttribute("USERNAME").toString());
	    String[] details = ret.split(":");
	    request.setAttribute("bill_amount", details[0]);
	    request.setAttribute("email", details[2]);
	    request.setAttribute("specialization", details[3]);
	    int b=Integer.parseInt(details[0]);
	    int i= b+150;
	    request.setAttribute("total_bill", Integer.toString(i));
	    RequestDispatcher rd = request.getRequestDispatcher("display-bill.jsp");
	     rd.forward(request, response);
	    
	}

}
