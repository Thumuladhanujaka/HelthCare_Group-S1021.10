package health.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import health.util.DBConnect;
import health.model.*;


public class labService {

private int success;
	
	public void addTest(Lab lab) {
		Connection connection;
		PreparedStatement preparedStatement;
		
		try {
			connection = DBConnect.getDBConnection();
			
			//insert value
			preparedStatement = connection.prepareStatement("insert into lab (name,date,description,test) values (?,?,?,?)");
			preparedStatement.setString(1, lab.getName());
			preparedStatement.setString(2,lab.getDate());
			preparedStatement.setString(3,lab.getDescription());
			preparedStatement.setString(4, lab.getTest());
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
	
	public ArrayList<Lab> getLabs() {
		
		ArrayList<Lab> testList = new ArrayList<Lab>();
		Connection connection;
		PreparedStatement preparedStatement;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM lab");
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				Lab lab = new Lab();
				
				lab.setId(Integer.parseInt(resultSet.getString(1)));
				lab.setName(resultSet.getString(2));
				lab.setDate(resultSet.getString(3));
				lab.setDescription(resultSet.getString(4));
				lab.setTest(resultSet.getString(5));
				
				testList.add(lab);
				
			}
			
			preparedStatement.close();
			connection.close();
			
		}catch (ClassNotFoundException | SQLException  e) {

			System.out.println(e.getMessage());
		}
		
		return testList;
	}

	public void editLabTest(Lab lab) {
		Connection connection;
		PreparedStatement preparedStatement;
		
		try {
			connection = DBConnect.getDBConnection();
			
				//insert value
				preparedStatement = connection.prepareStatement("UPDATE lab SET name=?,date=?,description=?,test=? where id=?");
				preparedStatement.setString(1, lab.getName());
				preparedStatement.setString(2,lab.getDate());
				preparedStatement.setString(3,lab.getDescription());
				preparedStatement.setString(4, lab.getTest());
				preparedStatement.setInt(5,lab.getId());
				preparedStatement.execute();
				preparedStatement.close();
				connection.close();
				setSuccess(1);
				
		
		}catch (ClassNotFoundException | SQLException  e) {
			setSuccess(0);
			System.out.println(e.getMessage());
		}
	}

	public void deleteTest(int test) {
		Connection connection;
		PreparedStatement preparedStatement;
		
		try {
			connection = DBConnect.getDBConnection();
			
			//delete hospital
			preparedStatement = connection.prepareStatement("DELETE FROM lab WHERE id=?");
			preparedStatement.setInt(1, test);
			preparedStatement.execute();
			
			setSuccess(1);
		
		}catch (ClassNotFoundException | SQLException  e) {
			setSuccess(0);
		}
	}
	
	public Lab getLabTest(int id) {
		Connection connection;
		PreparedStatement preparedStatement;
		Lab lab = new Lab();
		
		try {
			connection = DBConnect.getDBConnection();
			
			preparedStatement = connection.prepareStatement("select * from lab where id=?");
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next())
			{
				lab.setId(rs.getInt(1));
				lab.setName(rs.getString(2));
				lab.setDate(rs.getString(3));
				lab.setDescription(rs.getString(4));
				lab.setTest(rs.getString(5));
			}
			
		}catch (ClassNotFoundException | SQLException  e) {
			setSuccess(0);
		}
		return lab;
	}
	
}
