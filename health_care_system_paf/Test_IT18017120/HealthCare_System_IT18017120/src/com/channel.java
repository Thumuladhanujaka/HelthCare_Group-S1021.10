package com;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import java.sql.PreparedStatement;
import java.sql.Statement;

public class channel {

	Connection con = null;            


public Connection connect() 
{  
	Connection con = null;            
	
	
	try        
	{         
		Class.forName("com.mysql.jdbc.Driver");         
		con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/health_care","root", "");    

		System.out.print("\nSuccessfully connected");  
   }        
   catch(Exception e)   
   {        
		System.out.print("\nNot connected");  

	   e.printStackTrace();        
   }              
   return con;
}

public String insertchannels(String doctor_name, String hospital_name, String date) {

	String output = ""; 
	 
	 try  
	 {
		 Connection con = connect(); 
		 if (con == null) 
		 { 
			 return "\nError while connecting to the database";  
		 } 
	 
	  // create a prepared statement  
	String query = "insert into channels values(?, ?, ?, ?)"; 
	 
	PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	  // binding values  
	  preparedStmt.setInt(1, 0);  
	  preparedStmt.setString(2, doctor_name);  
	  preparedStmt.setString(3, hospital_name); 
	  //preparedStmt.setDouble(4, Double.parseDouble(price)); 
	  preparedStmt.setString(4, date); 

	//execute the statement
	  preparedStmt.execute();
	  con.close(); 
	  
	  output = "\nInserted successfully";  
		 System.out.print("inserted");  

	} 
	catch(Exception e) 
	{  
		 output= "\nError while inserting"; 
		 System.err.println(e.getMessage()); 
		 System.out.println("not inserted");  

	} 
	 
	 return output; 
	 
	
}

public String readchannels()
{
	String output = "";  
	
	try
	{  
		Connection con = connect(); 
		if (con == null) 
		{  
			return "Error while connecting to the database for reading.";  
		}
		
		output="<table border=\"1\"><tr><th>Hospital Name</th>"
				+ "<th>Hospital City</th>"
				+ "<th>Date</th>"
				+ "<th>Update</th><th>Remove</th>"
				+ "</tr>";
		

		String query = "select * from channels"; 
		Statement stmt = (Statement) con.createStatement();  
		ResultSet rs = stmt.executeQuery(query); 
		
		// iterate through the rows in the result set 
		while (rs.next())
		{   
			String channeling_id = Integer.toString(rs.getInt("channeling_id"));
			String doctorName = rs.getString("doctor_name");
			String hospitalName = rs.getString("hospital_name"); 
			String date = rs.getString("date");
		
		

	    // Add into the html table  
		output += "<tr><td>" + doctorName + "</td>";
		output += "<tr><td>" + hospitalName + "</td>"; 	  
		output += "<td>" + date + "</td>";  	  
		
		
		// buttons    
		output += "<td><input name=\"btnUpdate\" "     
		+ " type=\"button\" value=\"Update\"></td>"     
		+ "<td><form method=\"post\" action=\"channeling.jsp\">"     
		+ "<input name=\"btnRemove\" "     
		+ " type=\"submit\" value=\"Remove\">"     
		+ "<input name=\"channeling_id\" type=\"hidden\" "    
		+ " value=\"" + channeling_id + "\">" 
		+ "</form></td></tr>";   } 
		 
		con.close(); 
		 
	// Complete the html table  
		output += "</table>"; 
		 
		

	} 
	catch(Exception e) 
	{  
		output = "Error while reading the items.";
		System.err.println(e.getMessage()); 
	}
	return output; 
}

public String deletechannels(String channeling_id) 
{ 
	String output = ""; 
	
	try
	{ 
		Connection con = connect();
		if (con == null)
		{  
			return "Error while connecting to the database for deleting."; 
		} 

		// create a prepared statement 
		String query = "delete from channels where channeling_id=?"; 
		 
		PreparedStatement preparedStmt =(PreparedStatement) con.prepareStatement(query);
		 
		// binding values
		preparedStmt.setInt(1, Integer.parseInt(channeling_id));    
		// execute the statement preparedStmt.execute(); con.close(); 
		 
		// execute the statement   
		preparedStmt.execute(); 
		con.close(); 
		
		output = "Deleted successfully"; 
	}
	catch (Exception e) 
	{  
		output = "Error while deleting the item.";  
		System.err.println(e.getMessage()); 
	}
	return output;
} 




}