package User.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import User.model.User;
import User.service.UserService;



public class UserServiceImpl implements UserService {
	
	private DataSource dataSource;
	public UserServiceImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	

	@Override
	public boolean login(String email, String password) {
	    String query = "SELECT * FROM DIP.USERS WHERE email = ? AND password = ?";
	    
	    try (Connection connection = dataSource.getConnection();
	         PreparedStatement pstmt = connection.prepareStatement(query)) {
	        
	        pstmt.setString(1, email);
	        pstmt.setString(2, password);
	        
	        ResultSet rs = pstmt.executeQuery();
	        
	        return rs.next(); 
	        
	    } catch (SQLException e) {
	        System.out.println("Login error: " + e.getMessage());
	        return false;
	    }
	}

	@Override
	public boolean signup(User user) {
	    String query = "INSERT INTO DIP.USERS (name, email, password) VALUES (?, ?, ?)";
	    
	    try (Connection connection = dataSource.getConnection();
	         PreparedStatement pstmt = connection.prepareStatement(query)) {
	        
	        pstmt.setString(1, user.getName());
	        pstmt.setString(2, user.getEmail());
	        pstmt.setString(3, user.getPassword());
	        
	        int rowsAffected = pstmt.executeUpdate();
	        return rowsAffected > 0;
	        
	    } catch (SQLException e) {
	        System.out.println("Signup error: " + e.getMessage());
	        return false; 
	    }
	}
	
}
