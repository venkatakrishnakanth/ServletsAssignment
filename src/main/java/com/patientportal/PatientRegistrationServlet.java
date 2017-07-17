package com.patientportal;

import java.io.IOException;
import java.util.ServiceConfigurationError;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.patientportal.modal.PatientInfo;
import com.patientportal.repository.PRRepository;

/**
 * Servlet implementation class PatientRegistrationServlet
 */
public class PatientRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String rcontact = null,hosName=null,message=null;
	private static  final String SUCCESS ="Hey, Successfully Registred at ";
	private static final String FAILED ="Something went wrong, please contact at ";

	public void init(ServletConfig sc){
		rcontact = sc.getInitParameter("Reg-contact");
		ServletContext serContext = sc.getServletContext();
		hosName = serContext.getInitParameter("hospital-name");
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String name = request.getParameter("Fullname");
		String email = request.getParameter("Email");
		String password = request.getParameter("Password");
		String username = request.getParameter("Username");
		String gender = request.getParameter("Gender");
		System.out.println("Gender: "+gender);
		String dob = request.getParameter("dob");
		System.out.println("DOB: "+dob);
		String specialization = request.getParameter("Specialist");
		System.out.println("Specialist: "+specialization);
		String numofdays = request.getParameter("numdays");
		System.out.println("Num of Days: "+numofdays);
		//Setting patient object values
		PatientInfo pi = new PatientInfo(name,password, email, username, gender, dob, specialization, numofdays);
	
		PRRepository pr = new PRRepository();
	     int result = pr.insertCustInfo(pi);
	     
	   //Validating the status & return
	     if(result!=0){
	    	 message  = SUCCESS + hosName;
	     }else{
	    	 message = FAILED + rcontact;
	     }
	     
	     //Setting in to response and flush it
	     request.setAttribute("status", message);
	     RequestDispatcher rd = request.getRequestDispatcher("register-success.jsp");
	     rd.forward(request, response);
	}

	
}
