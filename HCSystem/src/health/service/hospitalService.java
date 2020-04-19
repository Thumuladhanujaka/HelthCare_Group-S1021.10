package health.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import health.util.DBConnect;
import health.model.*;


public class hospitalService {

private int success;
	
	public void addHospitals(Hospital hospital) {
		Connection connection;
		PreparedStatement preparedStatement;
		String name=null;
		
		try {
			connection = DBConnect.getDBConnection();
			
			//check hospital name
			preparedStatement = connection.prepareStatement("select * from hospital where name=?");
			preparedStatement.setString(1, hospital.getName());
			ResultSet rs = preparedStatement.executeQuery();
			 
			while(rs.next())
			{
				name = rs.getString(2);	
			}
			
			if(name==null) {
				
				//insert value
				preparedStatement = connection.prepareStatement("insert into hospital (name,beds,wards,rooms) values (?,?,?,?)");
				preparedStatement.setString(1, hospital.getName());
				preparedStatement.setDouble(2, hospital.getBeds());
				preparedStatement.setInt(3,hospital.getWards());
				preparedStatement.setInt(4,hospital.getRooms());
				preparedStatement.execute();
				preparedStatement.close();
				connection.close();
				setSuccess(1);
				
			}else {
				setSuccess(0);
			}
		
		}catch (ClassNotFoundException | SQLException  e) {
			System.out.println(e.getMessage());
		}
	}

	public int getSuccess() {
		return success;
	}

	public void setSuccess(int success) {
		this.success = success;
	}
	
	public ArrayList<Hospital> getHospital() {
		
		ArrayList<Hospital> hospitalList = new ArrayList<Hospital>();
		Connection connection;
		PreparedStatement preparedStatement;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("select * from hospital");
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				Hospital hospital = new Hospital();
				
				hospital.setId(Integer.parseInt(resultSet.getString(1)));
				hospital.setName(resultSet.getString(2));
				hospital.setBeds(Integer.parseInt(resultSet.getString(3)));
				hospital.setWards(Integer.parseInt(resultSet.getString(4)));
				hospital.setRooms(Integer.parseInt(resultSet.getString(5)));
				
				hospitalList.add(hospital);
				
			}
			
			preparedStatement.close();
			connection.close();
			
		}catch (ClassNotFoundException | SQLException  e) {

			System.out.println(e.getMessage());
		}
		
		return hospitalList;
	}

	public void editHospitals(Hospital hospital) {
		Connection connection;
		PreparedStatement preparedStatement;
		String name=null;
		int id=0;
		
		try {
			connection = DBConnect.getDBConnection();
			
			//check hospital name
			preparedStatement = connection.prepareStatement("select * from hospital where name=?");
			preparedStatement.setString(1, hospital.getName());
			ResultSet rs = preparedStatement.executeQuery();
			 
			while(rs.next())
			{
				id= rs.getInt(1);
			}
			
			if(id==0||id==hospital.getId()) {
				
				//insert value
				preparedStatement = connection.prepareStatement("UPDATE hospital SET name=?,beds=?,wards=?,rooms=? where id=?");
				preparedStatement.setString(1, hospital.getName());
				preparedStatement.setDouble(2, hospital.getBeds());
				preparedStatement.setInt(3,hospital.getWards());
				preparedStatement.setInt(4,hospital.getRooms());
				preparedStatement.setInt(5,hospital.getId());
				preparedStatement.execute();
				preparedStatement.close();
				connection.close();
				setSuccess(1);
				
			}else {
				setSuccess(0);
			}
		
		}catch (ClassNotFoundException | SQLException  e) {
			System.out.println(e.getMessage());
		}
	}

	public void deleteHospital(int hospital) {
		Connection connection;
		PreparedStatement preparedStatement;
		
		try {
			connection = DBConnect.getDBConnection();
			
			//delete hospital
			preparedStatement = connection.prepareStatement("DELETE FROM hospital WHERE id=?");
			preparedStatement.setInt(1, hospital);
			preparedStatement.execute();
			
			setSuccess(1);
		
		}catch (ClassNotFoundException | SQLException  e) {
			setSuccess(0);
		}
	}
	
	public Hospital getHospital(int id) {
		Connection connection;
		PreparedStatement preparedStatement;
		Hospital hospital = new Hospital();
		
		try {
			connection = DBConnect.getDBConnection();
			
			preparedStatement = connection.prepareStatement("select * from hospital where id=?");
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next())
			{
				hospital.setId(rs.getInt(1));
				hospital.setName(rs.getString(2));
				hospital.setBeds(rs.getInt(3));
				hospital.setWards(rs.getInt(4));
				hospital.setRooms(rs.getInt(5));
			}
			
		}catch (ClassNotFoundException | SQLException  e) {
			setSuccess(0);
		}
		return hospital;
	}
	
}
