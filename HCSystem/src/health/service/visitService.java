package health.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import health.util.DBConnect;
import health.model.*;


public class visitService {

private int success;
	
	public void addVisits(Visit visit) {
		Connection connection;
		PreparedStatement preparedStatement;
		
		try {
			connection = DBConnect.getDBConnection();
			
			//insert value
			preparedStatement = connection.prepareStatement("insert into visit (name,date,time,hospital) values (?,?,?,?)");
			preparedStatement.setString(1, visit.getName());
			preparedStatement.setString(2,visit.getDate());
			preparedStatement.setString(3,visit.getTime());
			preparedStatement.setInt(4, visit.getHospital());
			preparedStatement.execute();
			preparedStatement.close();
			connection.close();
			setSuccess(1);
		
		}catch (ClassNotFoundException | SQLException  e) {
			setSuccess(0);
			System.out.println(e.getMessage());
		}
	}

	public int getSuccess() {
		return success;
	}

	public void setSuccess(int success) {
		this.success = success;
	}
	
	public ArrayList<Visit> getVisit() {
		
		ArrayList<Visit> visitList = new ArrayList<Visit>();
		Connection connection;
		PreparedStatement preparedStatement;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("SELECT v.*,h.name FROM visit v,hospital h where h.id=v.hospital");
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				Visit visit = new Visit();
				
				visit.setId(Integer.parseInt(resultSet.getString(1)));
				visit.setName(resultSet.getString(2));
				visit.setDate(resultSet.getString(3));
				visit.setTime(resultSet.getString(4));
				visit.setHname(resultSet.getString(6));
				
				visitList.add(visit);
				
			}
			
			preparedStatement.close();
			connection.close();
			
		}catch (ClassNotFoundException | SQLException  e) {

			System.out.println(e.getMessage());
		}
		
		return visitList;
	}

	public void editVisits(Visit visit) {
		Connection connection;
		PreparedStatement preparedStatement;
		
		try {
			connection = DBConnect.getDBConnection();
			
				//insert value
				preparedStatement = connection.prepareStatement("UPDATE visit SET name=?,date=?,time=?,hospital=? where id=?");
				preparedStatement.setString(1, visit.getName());
				preparedStatement.setString(2,visit.getDate());
				preparedStatement.setString(3,visit.getTime());
				preparedStatement.setInt(4, visit.getHospital());
				preparedStatement.setInt(5,visit.getId());
				preparedStatement.execute();
				preparedStatement.close();
				connection.close();
				setSuccess(1);
				
		
		}catch (ClassNotFoundException | SQLException  e) {
			setSuccess(0);
			System.out.println(e.getMessage());
		}
	}

	public void deleteVisit(int visit) {
		Connection connection;
		PreparedStatement preparedStatement;
		
		try {
			connection = DBConnect.getDBConnection();
			
			//delete hospital
			preparedStatement = connection.prepareStatement("DELETE FROM visit WHERE id=?");
			preparedStatement.setInt(1, visit);
			preparedStatement.execute();
			
			setSuccess(1);
		
		}catch (ClassNotFoundException | SQLException  e) {
			setSuccess(0);
		}
	}
	
	public Visit getVisit(int id) {
		Connection connection;
		PreparedStatement preparedStatement;
		Visit visit = new Visit();
		
		try {
			connection = DBConnect.getDBConnection();
			
			preparedStatement = connection.prepareStatement("select v.*,h.name from visit v,hospital h WHERE v.hospital=h.id and v.id=?");
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next())
			{
				visit.setId(rs.getInt(1));
				visit.setName(rs.getString(2));
				visit.setDate(rs.getString(3));
				visit.setTime(rs.getString(4));
				visit.setHospital(rs.getInt(5));
				visit.setHname(rs.getString(6));
			}
			
		}catch (ClassNotFoundException | SQLException  e) {
			setSuccess(0);
		}
		return visit;
	}
	
	public Visit getVisits(int id) {
		Connection connection;
		PreparedStatement preparedStatement;
		Visit visit = new Visit();
		
		try {
			connection = DBConnect.getDBConnection();
			
			preparedStatement = connection.prepareStatement("SELECT v.*,h.name FROM visit v,hospital h where h.id=v.hospital and v.id=?");
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next())
			{
				visit.setId(rs.getInt(1));
				visit.setName(rs.getString(2));
				visit.setDate(rs.getString(3));
				visit.setTime(rs.getString(4));
				visit.setHname(rs.getString(6));
			}
			
		}catch (ClassNotFoundException | SQLException  e) {
			setSuccess(0);
		}
		return visit;
	}
	
}
