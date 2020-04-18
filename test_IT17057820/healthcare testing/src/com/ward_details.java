package com;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import java.sql.PreparedStatement;
import java.sql.Statement;

public class ward_details {
	
		Connection con = null;            


	public Connection connect() 
	{  
		Connection con = null;            
		
		
		try        
		{         
			Class.forName("com.mysql.jdbc.Driver");         
			con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare","root", "");    

			System.out.print("\nSuccessfully connected");  
	   }        
	   catch(Exception e)   
	   {        
			System.out.print("\nNot connected");  

		   e.printStackTrace();        
	   }              
	   return con;
	}

	public String insertWard_Details(String ward_name,String no_of_wards,  String active_time, String facilities) {

		String output = ""; 
		 
		 try  
		 {
			 Connection con = connect(); 
			 if (con == null) 
			 { 
				 return "\nError while connecting to the database";  
			 } 
		 
		  // create a prepared statement  
		String query = "insert into ward values(?, ?, ?, ?,?)"; 
		 
		PreparedStatement preparedStmt =  con.prepareStatement(query); 
		 
		  // binding values  
		  preparedStmt.setInt(1, 0);  
		  preparedStmt.setString(2, ward_name);
		  preparedStmt.setString(3, no_of_wards);
		  preparedStmt.setString(4, active_time);
		  preparedStmt.setString(5, facilities); 

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

	public String readWard_Details()
	{
		String output = "";  
		
		try
		{  
			Connection con = connect(); 
			if (con == null) 
			{  
				return "Error while connecting to the database for reading.";  
			}
			
			output="<table border=\"1\"><tr>"
					+ "<th>ward name</th>"
					+ "<th>no of wards</th>"
					+ "<th>active time</th>"
					
					+ "<th>facilities</th>"
					+ "<th>Update</th><th>Remove</th>"
					+ "</tr>";
			

			String query = "select * from ward"; 
			Statement stmt = (Statement) con.createStatement();  
			ResultSet rs = stmt.executeQuery(query); 
			
			// iterate through the rows in the result set 
			while (rs.next())
			{   
				String ward_Id = Integer.toString(rs.getInt("ward_Id")); 
				String ward_name =rs.getString("ward_name"); 
				String no_of_wards = rs.getString("no_of_wards"); 
				String active_time = rs.getString("active_time");    
				 
				String facilities = rs.getString("facilities");
			
			

		    // Add into the html table  
			
			output += "<td>" + ward_name + "</td>"; 
			output += "<td>" + no_of_wards + "</td>";	  
			output += "<td>" + active_time + "</td>";  	  
			 
			output += "<td>" + facilities + "</td>";
			
			// buttons    
			output += "<td><input name=\"btnUpdate\" "     
			+ " type=\"button\" value=\"Update\"></td>"     
			+ "<td><form method=\"post\" action=\"ward.jsp\">"     
			+ "<input name=\"btnRemove\" "     
			+ " type=\"submit\" value=\"Remove\">"     
			+ "<input name=\"ward_Id\" type=\"hidden\" "    
			+ " value=\"" +ward_Id + "\">" 
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

	public String deleteward_Details(String ward_ID) 
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
			String query = "delete from ward where ward_id=?"; 
			 
			PreparedStatement preparedStmt =(PreparedStatement) con.prepareStatement(query);
			 
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(ward_ID));    
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

