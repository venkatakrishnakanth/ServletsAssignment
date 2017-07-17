package com.patientportal;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.patientportal.repository.PRRepository;

/**
 * Servlet implementation class PatientLoginServlet
 */
public class PatientLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS ="Successfully Logged into Children's Nation Hospital page"; 
	private static final String FAILED ="Login FAILED. Please try again"; 
	String rcontact = null,hosName=null,message=null;
	
	public void init(ServletConfig sc){
		rcontact = sc.getInitParameter("Log-contact");
		ServletContext serContext = sc.getServletContext();
		hosName = serContext.getInitParameter("hospital-name");
		
	}
       
   
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("Username");
		String password = request.getParameter("Password");
		PRRepository pr = new PRRepository();
		boolean result=pr.login(username, password);
		if(result){
	    	 message  = SUCCESS + hosName;
	    	 request.setAttribute("status", message);
		     RequestDispatcher rd = request.getRequestDispatcher("login-success.jsp");
		     rd.forward(request, response);	    	 
		     HttpSession ses = request.getSession(true);
		     ses.setAttribute("USERNAME",username);
	     }else{
	    	 message = FAILED + rcontact;
	    	 RequestDispatcher rd = request.getRequestDispatcher("login-failed.jsp");
		     rd.forward(request, response);	
	    	 
	     }
		
		
		
	}
	

}
