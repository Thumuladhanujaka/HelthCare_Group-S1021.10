package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Visit {

	public Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare", "root", "");

			System.out.print("\nSuccessfully connected");
		} catch (Exception e) {
			System.out.print("\nNot connected");

			e.printStackTrace();
		}
		return con;
	}

	public String insertVisits(String hospital_name, String hospital_city, String date, String time,
			String noPatients) {

		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				return "\nError while connecting to the database";
			}

			// create a prepared statement
			String query = "insert into doctor_visiting values(?, ?, ?, ?, ?,?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, hospital_name);
			preparedStmt.setString(3, hospital_city);
			// preparedStmt.setDouble(4, Double.parseDouble(price));
			preparedStmt.setString(4, date);
			preparedStmt.setString(5, time);
			preparedStmt.setString(6, noPatients);

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "\nInserted successfully";
			System.out.print("inserted");

		} catch (Exception e) {
			output = "\nError while inserting";
			System.err.println(e.getMessage());
			System.out.println("not inserted");

		}

		return output;

	}

	public String updateVisits(String visiting_id, String hospital_name, String hospital_city, String date, String time,
			String noPatients) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "\nError while connecting to the database";
			}

			// create a prepared statement
			String query = "update doctor_visiting set hospital_name=?, hospital_city=?, date=?,time= ?,noPatients= ? where visiting_id=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, hospital_name);
			preparedStmt.setString(2, hospital_city);
			preparedStmt.setString(3, date);
			preparedStmt.setString(4, time);
			preparedStmt.setInt(5, Integer.parseInt(noPatients));
			preparedStmt.setInt(6, Integer.parseInt(visiting_id));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "\nUpdated successfullyyyy";
			System.out.print("Updated");

		} catch (Exception e) {
			output = "\nError while updatinaaag";
			System.err.println(e.getMessage());
			System.out.println("not ups");

		}

		return output;

	}

	public String readVisits() {
		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			output = "<table border=\"1\"><tr><th>Hospital Name</th>" + "<th>Hospital City</th>"
					+ "<th>Date</th><th>Time</th>" + "<th>No. of patients</th>" + "<th>Update</th><th>Remove</th>"
					+ "</tr>";

			String query = "select * from doctor_visiting";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				String visiting_id = Integer.toString(rs.getInt("visiting_id"));
				String hospitalName = rs.getString("hospital_name");
				String city = rs.getString("hospital_city");
				String date = rs.getString("date");
				String time = rs.getString("time");
				String noPatients = rs.getString("noPatients");

				// Add into the html table *************************************************************************************
				output += "<tr><td><input id=\"hidItemIDUpdate\" name=\"hidItemIDUpdate\"     type=\"hidden\" value=\""
						+ visiting_id + "\">" + hospitalName + "</td>";
				//output += "<tr><td>" + hospitalName + "</td>";
				output += "<td>" + city + "</td>";
				output += "<td>" + date + "</td>";
				output += "<td>" + time + "</td>";
				output += "<td>" + noPatients + "</td>";

				// buttons (remove update ) *************************************************************************************
				output += "<td><input name=\"btnUpdate\" type=\"button\"    value=\"Update\" "
						+ "class=\"btnUpdate btn btn-secondary\"></td>"
						//+ "<td><input name=\"btnUpdate\" " + " type=\"button\" value=\"Update\"></td>"
						+ "<td><form method=\"post\" action=\"visits.jsp\">" + "<input name=\"btnRemove\" "
						+ " type=\"submit\" value=\"Remove\" class=\"btn btn-danger\"> >" 
						//+ "<input name=\"visiting_id\" type=\"hidden\" "
					//	+ " value=\"" + visiting_id + "\">" 
				+ "<input name=\"hidItemIDDelete\" type=\"hidden\" value=\"" + visiting_id + "\">" + "</form></td></tr>"; 
			}

			con.close();

			// Complete the html table
			output += "</table>";

		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteVisits(String visiting_id) {
		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from doctor_visiting where visiting_id=?";

			PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(visiting_id));
			// execute the statement preparedStmt.execute(); con.close();

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
