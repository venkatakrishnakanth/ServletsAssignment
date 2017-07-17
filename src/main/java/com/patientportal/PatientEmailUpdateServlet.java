package com.patientportal;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.patientportal.repository.PRRepository;

/**
 * Servlet implementation class PatientEmailUpdateServlet
 */
public class PatientEmailUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS ="Successfully Updated your Email Adress"; 
	private static final String FAILED ="Something Went Wrong!!!!!!!!. Please try again"; 
	String message=null;
       
   
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("Username");
		String password = request.getParameter("Password");
		String newemail = request.getParameter("Email");
		PRRepository pr = new PRRepository();
		boolean result = pr.resetEmail(request.getSession(false).getAttribute("USERNAME").toString(), newemail);
		
		if(result){
	    	 message  = SUCCESS ;
	     }else{
	    	 message = FAILED ;
	     }
		
		request.setAttribute("status", message);
	     RequestDispatcher rd = request.getRequestDispatcher("update-success.jsp");
	     rd.forward(request, response);
		
	}

}
