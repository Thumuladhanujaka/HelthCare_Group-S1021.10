package health.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import health.util.DBConnect;
import health.model.*;


public class paymentService {

private int success;
	
	public void addPayments(Payment payment) {
		Connection connection;
		PreparedStatement preparedStatement;
		String name=null;
		
		try {
			connection = DBConnect.getDBConnection();
			
			preparedStatement = connection.prepareStatement("select * from payment where name=?");
			preparedStatement.setString(1, payment.getName());
			ResultSet rs = preparedStatement.executeQuery();
			 
			while(rs.next())
			{
				name = rs.getString(2);	
			}
			
			if(name==null) {
				
				//insert value
				preparedStatement = connection.prepareStatement("insert into payment (name,hospital,type,price) values (?,?,?,?)");
				preparedStatement.setString(1, payment.getName());
				preparedStatement.setInt(2,payment.getHospital());
				preparedStatement.setString(3,payment.getType());
				preparedStatement.setDouble(4, payment.getPrice());
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
	
	public ArrayList<Payment> getPayments() {
		
		ArrayList<Payment> paymentList = new ArrayList<Payment>();
		Connection connection;
		PreparedStatement preparedStatement;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("SELECT p.*,h.name FROM payment p,hospital h where h.id=p.hospital");
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				Payment payment = new Payment();
				
				payment.setId(Integer.parseInt(resultSet.getString(1)));
				payment.setName(resultSet.getString(2));
				payment.setType(resultSet.getString(4));
				payment.setPrice(Double.parseDouble(resultSet.getString(5)));
				payment.setHname(resultSet.getString(6));
				
				paymentList.add(payment);
				
			}
			
			preparedStatement.close();
			connection.close();
			
		}catch (ClassNotFoundException | SQLException  e) {

			System.out.println(e.getMessage());
		}
		
		return paymentList;
	}

	public void editPayments(Payment payment) {
		Connection connection;
		PreparedStatement preparedStatement;
		String name=null;
		int id=0;
		
		try {
			connection = DBConnect.getDBConnection();
			
			//check hospital name
			preparedStatement = connection.prepareStatement("select * from payment where name=?");
			preparedStatement.setString(1, payment.getName());
			ResultSet rs = preparedStatement.executeQuery();
			 
			while(rs.next())
			{
				id= rs.getInt(1);
			}
			
			if(id==0||id==payment.getId()) {
				
				//insert value
				preparedStatement = connection.prepareStatement("UPDATE payment SET name=?,hospital=?,type=?,price=? where id=?");
				preparedStatement.setString(1, payment.getName());
				preparedStatement.setInt(2,payment.getHospital());
				preparedStatement.setString(3,payment.getType());
				preparedStatement.setDouble(4, payment.getPrice());
				preparedStatement.setInt(5,payment.getId());
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

	public void deletePayment(int pay) {
		Connection connection;
		PreparedStatement preparedStatement;
		
		try {
			connection = DBConnect.getDBConnection();
			
			//delete hospital
			preparedStatement = connection.prepareStatement("DELETE FROM payment WHERE id=?");
			preparedStatement.setInt(1, pay);
			preparedStatement.execute();
			
			setSuccess(1);
		
		}catch (ClassNotFoundException | SQLException  e) {
			setSuccess(0);
		}
	}
	
	public Payment getPayment(int id) {
		Connection connection;
		PreparedStatement preparedStatement;
		Payment payment = new Payment();
		
		try {
			connection = DBConnect.getDBConnection();
			
			preparedStatement = connection.prepareStatement("select * from payment where id=?");
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next())
			{
				payment.setId(rs.getInt(1));
				payment.setName(rs.getString(2));
				payment.setHospital(rs.getInt(3));
				payment.setType(rs.getString(4));
				payment.setPrice(rs.getDouble(5));
			}
			
		}catch (ClassNotFoundException | SQLException  e) {
			setSuccess(0);
		}
		return payment;
	}
	
}
