package health.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import health.model.User;
import health.util.DBConnect;

public class registerService {

	private int success;
	
	public void register(User user) {
		Connection connection;
		PreparedStatement preparedStatement;
		String email=null,phone=null;
		
		try {
			connection = DBConnect.getDBConnection();
			
			//check email
			preparedStatement = connection.prepareStatement("select * from users where email=?");
			preparedStatement.setString(1, user.getEmail());
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next())
			{
				email = rs.getString(4);	
			}
			
			if(email==null) {
				
				//check phone number
				preparedStatement = connection.prepareStatement("select * from users where phone=?");
				preparedStatement.setString(1, user.getPhone());
				rs = preparedStatement.executeQuery();
				
				while(rs.next())
				{
					phone = rs.getString(5);	
				}
				
				if(phone==null) {
				
					//insert value
					preparedStatement = connection.prepareStatement("insert into users (fname,lname,email,phone,password,admin) values (?,?,?,?,?,?)");
					preparedStatement.setString(1, user.getFname());
					preparedStatement.setString(2, user.getLname());
					preparedStatement.setString(3, user.getEmail());
					preparedStatement.setString(4, user.getPhone());
					preparedStatement.setString(5, user.getPassword());
					preparedStatement.setInt(6, 0);
					preparedStatement.execute();
					preparedStatement.close();
					connection.close();
					setSuccess(1);
				
				}else {
					setSuccess(2);
				}
				
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

	public void login(User user) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		String email=null,admin=null;
		
		try {
			connection = DBConnect.getDBConnection();
			
			preparedStatement = connection.prepareStatement("select * from users where email=? and password=?");
			preparedStatement.setString(1, user.getEmail());
			preparedStatement.setString(2, user.getPassword());
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next())
			{
				email = rs.getString(5);
				admin = rs.getString(7);
			}
			
			if(email==null) {
				setSuccess(0);
			}else {
				if(admin.toString().equals("0")) {
					setSuccess(1);
				}else if(admin.toString().equals("1")){
					setSuccess(2);
				}else if(admin.toString().equals("2")){
					setSuccess(3);
				}
			}
			
		}catch (ClassNotFoundException | SQLException  e) {
			System.out.println(e.getMessage());
		}
		
	}

	public ArrayList<User> getUser(String userEmail) {
		
		ArrayList<User> userList = new ArrayList<User>();
		Connection connection;
		PreparedStatement preparedStatement;
		
		try {
			
			connection = DBConnect.getDBConnection();
			preparedStatement = connection.prepareStatement("select * from users where email != ?");
			preparedStatement.setString(1, userEmail);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				User user = new User();
				
				user.setId(Integer.parseInt(resultSet.getString(1)));
				user.setFname(resultSet.getString(2));
				user.setLname(resultSet.getString(3));
				user.setPhone(resultSet.getString(4));
				user.setEmail(resultSet.getString(5));
				user.setAdmin(resultSet.getString(7));
				
				userList.add(user);
				
			}
			
			preparedStatement.close();
			connection.close();
			
		}catch (ClassNotFoundException | SQLException  e) {

			System.out.println(e.getMessage());
		}
		
		return userList;
		
	}

	public void createUser(User user) {
		Connection connection;
		PreparedStatement preparedStatement;
		
		try {
			connection = DBConnect.getDBConnection();
			
			preparedStatement = connection.prepareStatement("UPDATE users SET admin=? where id=?");
			preparedStatement.setInt(1, Integer.parseInt(user.getAdmin()));
			preparedStatement.setInt(2, user.getId());
			preparedStatement.execute();
			preparedStatement.close();
			connection.close();
			setSuccess(1);
				
		}catch (ClassNotFoundException | SQLException  e) {
			setSuccess(0);
		}
	}

	public void removeUser(User user) {
		
		Connection connection;
		PreparedStatement preparedStatement;
		
		try {
			connection = DBConnect.getDBConnection();
			
			//delete type
			preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id=?");
			preparedStatement.setInt(1, user.getId());
			preparedStatement.execute();
			
			setSuccess(1);
		
		}catch (ClassNotFoundException | SQLException  e) {
			setSuccess(0);
		}
		
	}
	
}
