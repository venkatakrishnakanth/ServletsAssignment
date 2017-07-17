package com.patientportal.repository;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.patientportal.modal.PatientInfo;

public class PRRepository {
	


	private static final String CREATE_QUERY = "create table Patient_info(name character varying(40) NOT NULL, password character varying(40) NOT NULL, email character varying(40) NOT NULL,username character varying(40) NOT NULL,gender character varying(40) NOT NULL,dob character varying(40) NOT NULL,specialization character varying(40) NOT NULL,numofdays character varying(40) NOT NULL)";
	private static final String INSERT_QUERY = "INSERT INTO Patient_info (name, password, email, username, gender, dob, specialization, numofdays) values(?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_DATA = "SELECT * FROM Patient_info";

	private Connection con = null;

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
			ResultSet rs = md.getTables(null, null, "Patient_info", null);
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

	public int insertCustInfo(PatientInfo info) {
		int result = 0;
		PreparedStatement ps = null;
		Statement s = null;
		boolean flag = false;
		createRegistration();
		getConnection();
		try {
			s = con.createStatement();
			String sql = "SELECT * FROM Patient_info WHERE name='" + info.getName() + "' AND email='" + info.getEmail()
					+ "' AND username='"+info.getUsername()+"'";
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				flag = true;

			}
			if (flag)
				System.err.println("User already exists. Try logging in or resetting password");
			else {
				BillRepository br = new BillRepository();
				br.insertCustbill(info.getUsername(),info.getEmail(),info.getSpecialization());
				ps = con.prepareStatement(INSERT_QUERY);
				ps.setString(1, info.getName());
				ps.setString(2, info.getPassword());
				ps.setString(3, info.getEmail());
				ps.setString(4, info.getUsername());
				ps.setString(5, info.getGender());
				ps.setString(6, info.getDob());
				ps.setString(7, info.getSpecialization());
				ps.setString(8, info.getNumofdays());

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

	public void getAllInfo() {
		PreparedStatement ps = null;
		getConnection();

		try {
			ps = con.prepareStatement(GET_ALL_DATA);
			ResultSet rs = ps.executeQuery();
			System.out.println("\nUsername************ Gender ************* Age ********* Email\n");
			while (rs.next()) {
				System.out.println(rs.getString("name") + "----------- " +rs.getString("email"));
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

	}

	public void deleteuser(String name, String email) {
		Statement s = null;
		getConnection();

		try {
			s = con.createStatement();
			String qry = "DELETE FROM register WHERE name='" + name + "' AND email='" + email + "'";
			int n = s.executeUpdate(qry);
			if (n > 0) {
				System.out.println("Deleted rows: " + n);
				System.out.println("User " + name + " Deleted Successfully\n");
			} else {
				System.err.println("Something went wrong!!!...Please check name and email");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				s.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean login(String name, String password) {
		Statement s = null;
		getConnection();
		boolean n = false;
		try {
			s = con.createStatement();
			String qry = "SELECT * FROM Patient_info WHERE username='" + name + "' AND password='" + password + "'";
			ResultSet rs = s.executeQuery(qry);
			while (rs.next()) {
				n = true;
			}
			if(!n)
				System.out.println("Login Error");
			return n;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				s.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return n;
	}

	public boolean resetEmail(String name, String email) {

		Statement s = null;
		getConnection();
		boolean n = false;
		int k = 0;
		try {
			s = con.createStatement();
			String qry = "UPDATE Patient_info SET email='" + email + "' WHERE username='" + name + "'";
			k = s.executeUpdate(qry);
			if (k > 0)
				n = true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				s.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return n;
	}

	public boolean resetpassword(String name, String password) {

		Statement s = null;
		getConnection();
		boolean n = false;
		int k = 0;
		try {
			s = con.createStatement();
			String qry = "UPDATE register SET password='" + password + "' WHERE name='" + name + "'";
			k = s.executeUpdate(qry);
			if (k > 0)
				n = true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				s.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return n;

	}

	public boolean resetGender(String name, String gender) {

		Statement s = null;
		getConnection();
		boolean n = false;
		int k = 0;
		try {
			s = con.createStatement();
			String qry = "UPDATE register SET gender='" + gender + "' WHERE name='" + name + "'";
			k = s.executeUpdate(qry);
			if (k > 0)
				n = true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				s.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return n;

	}

	public boolean resetAge(String name, String age) {

		Statement s = null;
		getConnection();
		boolean n = false;
		int k = 0;
		try {
			s = con.createStatement();
			String qry = "UPDATE register SET age='" + age + "' WHERE name='" + name + "'";
			k = s.executeUpdate(qry);
			if (k > 0)
				n = true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				s.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return n;

	}

	public boolean resetName(String name, String newname) {

		Statement s = null;
		getConnection();
		boolean n = false;
		int k = 0;
		try {
			s = con.createStatement();
			String qry = "UPDATE register SET name='" + newname + "' WHERE name='" + name + "'";
			k = s.executeUpdate(qry);
			if (k > 0)
				n = true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				s.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return n;

	}



}
