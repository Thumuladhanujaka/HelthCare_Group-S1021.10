package health.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import health.util.DBConnect;
import health.model.*;


public class appointmentService {

private int success;
	
	public void addAppointment(Appointment appointment) {
		Connection connection;
		PreparedStatement preparedStatement;
		
		try {
			connection = DBConnect.getDBConnection();
			
			//insert value
			preparedStatement = connection.prepareStatement("insert into appointment (name,age,gender,visitId) values (?,?,?,?)");
			preparedStatement.setString(1, appointment.getName());
			preparedStatement.setInt(2,appointment.getAge());
			preparedStatement.setString(3,appointment.getGender());
			preparedStatement.setInt(4, appointment.getVisitId());
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
	
	public ArrayList<Appointment> getAppointment() {
		
		ArrayList<Appointment> appointmentList = new ArrayList<Appointment>();
		Connection connection;
		PreparedStatement preparedStatement;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("SELECT a.*,v.date,v.time,h.name,v.name,v.pack FROM visit v,hospital h,appointment a where h.id=v.hospital and a.visitId=v.id");
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				Appointment appointment = new Appointment();
				
				appointment.setId(Integer.parseInt(resultSet.getString(1)));
				appointment.setName(resultSet.getString(2));
				appointment.setAge(resultSet.getInt(3));
				appointment.setGender(resultSet.getString(4));
				appointment.setDname(resultSet.getString(9));
				appointment.setDate(resultSet.getString(6));
				appointment.setTime(resultSet.getString(7));
				appointment.setHname(resultSet.getString(8));
				appointment.setPack(resultSet.getString(10));
				
				appointmentList.add(appointment);
				
			}
			
			preparedStatement.close();
			connection.close();
			
		}catch (ClassNotFoundException | SQLException  e) {

			System.out.println(e.getMessage());
		}
		
		return appointmentList;
	}

	public void editAppointments(Appointment appointment) {
		Connection connection;
		PreparedStatement preparedStatement;
		
		try {
			connection = DBConnect.getDBConnection();
			
				//insert value
				preparedStatement = connection.prepareStatement("UPDATE appointment SET name=?,age=?,gender=?,visitId=? where id=?");
				preparedStatement.setString(1, appointment.getName());
				preparedStatement.setInt(2,appointment.getAge());
				preparedStatement.setString(3,appointment.getGender());
				preparedStatement.setInt(4, appointment.getVisitId());
				preparedStatement.setInt(5,appointment.getId());
				preparedStatement.execute();
				preparedStatement.close();
				connection.close();
				setSuccess(1);
				
		
		}catch (ClassNotFoundException | SQLException  e) {
			setSuccess(0);
			System.out.println(e.getMessage());
		}
	}

	public void deleteAppointment(int appointment) {
		Connection connection;
		PreparedStatement preparedStatement;
		
		try {
			connection = DBConnect.getDBConnection();
			
			//delete hospital
			preparedStatement = connection.prepareStatement("DELETE FROM appointment WHERE id=?");
			preparedStatement.setInt(1, appointment);
			preparedStatement.execute();
			
			setSuccess(1);
		
		}catch (ClassNotFoundException | SQLException  e) {
			setSuccess(0);
		}
	}
	
	public Appointment getAppointments(int id) {
		Connection connection;
		PreparedStatement preparedStatement;
		Appointment appointment = new Appointment();
		
		try {
			connection = DBConnect.getDBConnection();
			
			preparedStatement = connection.prepareStatement("SELECT a.*,v.date,v.time,h.name,v.name,v.pack FROM visit v,hospital h,appointment a where h.id=v.hospital and a.visitId=v.id and a.id=?");
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next())
			{
				appointment.setId(rs.getInt(1));
				appointment.setName(rs.getString(2));
				appointment.setAge(rs.getInt(3));
				appointment.setGender(rs.getString(4));
				appointment.setDname(rs.getString(9));
				appointment.setDate(rs.getString(6));
				appointment.setTime(rs.getString(7));
				appointment.setVisitId(rs.getInt(5));
				appointment.setHname(rs.getString(8));
				appointment.setPack(rs.getString(10));
			}
			
		}catch (ClassNotFoundException | SQLException  e) {
			setSuccess(0);
		}
		return appointment;
	}
	
}
