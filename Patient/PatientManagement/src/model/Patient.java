package model;

import java.sql.*;

public class Patient {
	
	public Connection connect() {
		Connection con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con= DriverManager.getConnection("jdbc:mysql://localhost:3306/new_schema", "root", "admin");    
		 
			//For testing
			System.out.print("Successfully connected");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}	
	
	//insert patient to database
	public String insertPatient(String code, String name, String age, String gender, String address, String mobile) {
		String output = ""; 
	 
		try {
			Connection con = connect(); 
	 
			if (con == null) {
				return "Error while connecting to the database";
			} 
	 
			//create a prepared statement 
			String query = "insert into patients(pid, pcode, pname, age, gender, address, mobile)"  + " values (?, ?, ?, ?, ?, ?, ?)";
				 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setInt(2, Integer.parseInt(code));
			preparedStmt.setString(3, name);
			preparedStmt.setInt(4, Integer.parseInt(age));
			preparedStmt.setString(5, gender);
			preparedStmt.setString(6, address);
			preparedStmt.setInt(7, Integer.parseInt(mobile));
		  
			//execute the statement 
			preparedStmt.execute();   
			con.close(); 
	  
			output = "Inserted successfully";
		}
		catch (Exception e) {
			output = "Error while inserting";
			System.err.println(e.getMessage());
		} 
	 
		return output;
	} 
	 
	//search patients from database
	public String readPatients() {
		String output = ""; 
	 
		try  {
			Connection con = connect(); 
			 
			if (con == null) {
				return "Error while connecting to the database for reading.";   
			} 
				 
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Patient Code</th>"
					+ "<th>Patient Name </th>"
					+ "<th>Patient Age</th>"
					+ "<th>Patient Gender</th>"
					+ "<th>Patient Address</th>"
					+ "<th>Patient Mobile</th>"
					+ "<th>Update</th><th>Remove</th></tr>";
			
			String query = "select * from patients";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query); 
	 
			// iterate through the rows in the result set
			while (rs.next()) {
				String pid = Integer.toString(rs.getInt("pid"));
				String pcode = Integer.toString(rs.getInt("pcode"));
				String pname = rs.getString("pname");
				String age = Integer.toString(rs.getInt("age"));
				String gender = rs.getString("gender");
				String address = rs.getString("address"); 
				String mobile = Integer.toString(rs.getInt("mobile"));
	  
				// Add into the html table
				output += "<tr><td>" + pcode + "</td>";
				output += "<td>" + pname + "</td>";
				output += "<td>" + age + "</td>";
				output += "<td>" + gender + "</td>";
				output += "<td>" + address + "</td>";
				output += "<td>" + mobile + "</td>";
	  
				// buttons
				output += "<td><input name=\"btnUpdate\" " 
						+ " type=\"button\" value=\"Update\"></td>" 
						+ "<td><form method=\"post\" action=\"patients.jsp\">"
						+ "<input name=\"btnRemove\" "     
						+ " type=\"submit\" value=\"Remove\">"
						+ "<input name=\"pId\" type=\"hidden\" value=\"" + pid + "\">" 
						+ "</form></td></tr>";   
			} 
	 
			con.close(); 
	 
			// Complete the html table
			output += "</table>";
		}
		catch (Exception e) {
			output = "Error while reading the patients.";
			System.err.println(e.getMessage());  
		} 
	  
		return output; 
	}
	
	//delete patients from database
	public String deletePatient(String pid) {
		String output = ""; 
	 
		try {
			Connection con = connect(); 
	 
			if (con == null) {
				return "Error while connecting to the database for deleting."; 
			} 
	 
			// create a prepared statement
			String query = "delete from patients where pid = ?"; 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(pid));
			
			// execute the statement
			preparedStmt.execute();
			
			con.close(); 
	 
			output = "Deleted successfully";
		}  
		catch (Exception e) {
			output = "Error while deleting the patient.";
			System.err.println(e.getMessage());
		} 
	 
		return output;
	} 
	
	// update patient	 
	public String updatePatient(String pid, String code, String name, String age, String gender, String address, String mobile) {
		String output = ""; 
	
		try {
			Connection con = connect(); 
				
		if (con == null) {
			return "Error while connecting to the database for updating."; 
		} 
		
		// create a prepared statement    
		String query = "UPDATE items SET pcode=?, pname=?, age=?, gender=?, address=?, mobile=? WHERE pid=?"; 
 
		PreparedStatement preparedStmt = con.prepareStatement(query); 
 
		// binding values
		preparedStmt.setInt(1, Integer.parseInt(code));
		preparedStmt.setString(2, name);
		preparedStmt.setInt(3, Integer.parseInt(age));
		preparedStmt.setString(4, gender);
		preparedStmt.setString(5, address);
		preparedStmt.setInt(6, Integer.parseInt(mobile));
		preparedStmt.setInt(7, Integer.parseInt(pid));
		
		// execute the statement
		preparedStmt.execute();
		con.close(); 
 
		output = "Updated successfully";
		}
		catch (Exception e) {
			output = "Error while updating the item.";
			System.err.println(e.getMessage());
		} 
		
		return output;
	}
	 
}
