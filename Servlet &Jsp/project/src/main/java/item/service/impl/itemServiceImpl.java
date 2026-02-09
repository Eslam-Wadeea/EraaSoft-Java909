package item.service.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.sql.DataSource;

import item.model.Item;
import item.service.itemService;

public class itemServiceImpl implements itemService {
	
	private DataSource dataSource;
	

	public itemServiceImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<Item> getItems() {
		Connection connection = null;
		Statement statement = null;
		
		try {
			connection = dataSource.getConnection();  // connection open
			statement = connection.createStatement(); // statement open
			
			String query = "SELECT ID, NAME, PRICE, TOTAL_NUMBER FROM DIP.ITEM WHERE IS_DELETED = 0";
			ResultSet resultSet = statement.executeQuery(query);
			
			List<Item> items = new ArrayList<Item>();
			while (resultSet.next()) {
				Item item = new Item(
						resultSet.getLong("id"),
						resultSet.getString("name"),
						resultSet.getDouble("price"),
						resultSet.getInt("total_Number")
				);
				items.add(item);
			}
			
			return items;
		} catch (Exception exception) {
			System.out.println("ex => " + exception.getMessage());
		} finally {
			try {
				if(Objects.nonNull(connection)) {
					connection.close();
				}
				
				if(Objects.nonNull(statement)) {
					statement.close();
				}
			} catch (SQLException exception) {
				System.out.println("ex => " + exception.getMessage());
			}
		}
		
		return null;
	}
	

	@Override
	public Item getItem(Long id) {
		Connection connection = null;
		Statement statement = null;
		
		try {
			connection = dataSource.getConnection();  // connection open
			statement = connection.createStatement(); // statement open
			
			String query = "SELECT * FROM DIP.item where id = " + id;
			ResultSet resultSet = statement.executeQuery(query);
			resultSet.next();
			
			return new Item(
					resultSet.getLong("id"),
					resultSet.getString("name"),
					resultSet.getDouble("price"),
					resultSet.getInt("TOTAL_NUMBER")
			);
			
		} catch (Exception exception) {
			System.out.println("ex => " + exception.getMessage());
		} finally {
			try {
				if(Objects.nonNull(connection)) {
					connection.close();
				}
				
				if(Objects.nonNull(statement)) {
					statement.close();
				}
			} catch (SQLException exception) {
				System.out.println("ex => " + exception.getMessage());
			}
		}
		
		return null;
	}

	@Override
	public boolean createItem(Item item) {
		Connection connection = null;
		Statement statement = null;
		
		try {
			connection = dataSource.getConnection();  // connection open
			statement = connection.createStatement(); // statement open
			
			String query = "INSERT INTO DIP.ITEM (NAME, PRICE, TOTAL_NUMBER , IS_DELETED) VALUES('" + 
								item.getName() + "', " + item.getPrice() +", " + item.getTotalNumber() + " , 0)";
			
			statement.execute(query);
			
			return true;
		} catch (Exception exception) {
			System.out.println("ex => " + exception.getMessage());
		} finally {
			try {
				if(Objects.nonNull(connection)) {
					connection.close();
				}
				
				if(Objects.nonNull(statement)) {
					statement.close();
				}
			} catch (SQLException exception) {
				System.out.println("ex => " + exception.getMessage());
			}
		}
		
		return false;
	}

	@Override
	public boolean updateItem(Item item) {
		
		if (isNameDuplicateForOtherId(item.getName(), item.getId())) {
	        return false; 
	    }
	    Connection connection = null;
	    PreparedStatement pstmt = null;

	    
	    String query = "UPDATE DIP.ITEM SET NAME = ?, PRICE = ?, TOTAL_NUMBER = ? WHERE ID = ?";

	    try {
	        connection = dataSource.getConnection();
	        pstmt = connection.prepareStatement(query);

	        
	        pstmt.setString(1, item.getName());
	        pstmt.setDouble(2, item.getPrice());
	        pstmt.setInt(3, item.getTotalNumber());
	        pstmt.setFloat(4, item.getId());

	        int rowsAffected = pstmt.executeUpdate();
	        return rowsAffected > 0;

	    } catch (Exception exception) {
	        System.out.println("ex => " + exception.getMessage());
	    } finally {
	        
	        try {
	            if (Objects.nonNull(pstmt)) pstmt.close();
	            if (Objects.nonNull(connection)) connection.close();
	        } catch (SQLException exception) {
	            System.out.println("ex => " + exception.getMessage());
	        }
	    }
	    return false;
	}

	@Override
	public boolean removeItem(Long id) {
	    Connection connection = null;
	    PreparedStatement pstmt = null;
	    String query = "UPDATE DIP.ITEM SET IS_DELETED = 1 WHERE ID = ?"; 

	    try {
	        connection = dataSource.getConnection();
	        pstmt = connection.prepareStatement(query);

	        
	        pstmt.setLong(1, id);

	        int rowsAffected = pstmt.executeUpdate();
	        return rowsAffected > 0;

	    } catch (Exception exception) {
	        System.out.println("ex => " + exception.getMessage());
	    } finally {
	        try {
	            
	            if (Objects.nonNull(pstmt)) pstmt.close();
	            if (Objects.nonNull(connection)) connection.close();
	        } catch (SQLException exception) {
	            System.out.println("ex => " + exception.getMessage());
	        }
	    }
	    return false;
	}

	@Override
	public Item getItemByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private boolean isNameExists(String name) {
	    String query = "SELECT COUNT(*) FROM DIP.ITEM WHERE NAME = ? AND IS_DELETED = 0";
	    try (Connection conn = dataSource.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(query)) {
	        pstmt.setString(1, name);
	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                return rs.getInt(1) > 0;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	
	private boolean isNameDuplicateForOtherId(String name, Long id) {
	    String sql = "SELECT COUNT(*) FROM DIP.ITEM WHERE NAME = ? AND ID != ? AND IS_DELETED = 0";
	    try (Connection conn = dataSource.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, name);
	        pstmt.setLong(2, id);
	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) return rs.getInt(1) > 0;
	        }
	    } catch (SQLException e) { e.printStackTrace(); }
	    return false;
	}
	


}
