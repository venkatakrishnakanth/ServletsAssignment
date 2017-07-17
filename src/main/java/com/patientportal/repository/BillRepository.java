package com.patientportal.repository;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import com.patientportal.modal.PatientInfo;

public class BillRepository {
	
	private Connection con = null;
	private static final String CREATE_QUERY = "create table billing(username character varying(40) NOT NULL, bill character varying(40) NOT NULL,email character varying(40) NOT NULL,specialization character varying(40) NOT NULL, PRIMARY KEY (username))";
	private static final String INSERT_QUERY = "INSERT INTO billing (username, bill,email,specialization) values(?,?,?,?)";
	private void getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/b6", "postgres", "3333");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Couldn't able to connect 1");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Couldn't able to connect 2");
		}
	}
	
	
	
	public boolean createRegistration() {
		boolean result = false;
		PreparedStatement ps = null;
		Statement st = null;
		getConnection();
		try {
			st = con.createStatement();
			DatabaseMetaData md = con.getMetaData();
			ResultSet rs = md.getTables(null, null, "billing", null);
			boolean exits = false;
			if(rs.next())
				exits=true;
			
			if(exits){
				ps = con.prepareStatement(CREATE_QUERY);
				System.out.println("Table already created...");
			}else{
				ps = con.prepareStatement(CREATE_QUERY);
				result = ps.execute();
				System.out.println("Table Successfully Created");
			}
			
		} catch (SQLException e) {
			System.out.println("Table already exists......Skipping it");
			
		}
		try {
			ps.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error in database connection");
		}
		return result;}
	
	

	public int insertCustbill(String username, String email,String specialization) {
		int result = 0;
		PreparedStatement ps = null;
		Statement s = null;
		boolean flag = false;
		createRegistration();
		getConnection();
		try {
			s = con.createStatement();
			String sql = "SELECT * FROM billing WHERE username='" + username+"'";
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				flag = true;

			}
			if (flag)
				System.err.println("User already exists. Try logging in or resetting password");
			else {
				ps = con.prepareStatement(INSERT_QUERY);
				ps.setString(1, username);
				
				ps.setString(2, Integer.toString(new Random().nextInt(100000)));
				ps.setString(3, email);
				ps.setString(4, specialization);
				
				result = ps.executeUpdate();
				System.out.println("inserted successfully");
			}
		} catch (SQLException e) {
			System.out.println("exception in insertion");
			e.printStackTrace();
		} finally {

			try {
				if (ps != null) {
					ps.close();

				}
				s.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
	}
	
	
	
	
	
	public String getAllInfo(String name) {
		PreparedStatement ps = null;
		getConnection();
		String ret = null;

		try {
			ps = con.prepareStatement("SELECT * FROM billing WHERE username='" + name + "'");
			ResultSet rs = ps.executeQuery();
			System.out.println("\nUsername************ Gender ************* Age ********* Email\n");
			while (rs.next()) {
				ret=rs.getString(2)+":"+rs.getString(1)+":"+rs.getString(3)+":"+rs.getString(4);
				System.out.println(ret);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ret;

	}

}
