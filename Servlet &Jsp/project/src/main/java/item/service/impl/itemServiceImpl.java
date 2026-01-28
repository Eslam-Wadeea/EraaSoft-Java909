package item.service.impl;


import java.sql.Connection;
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
			
			String query = "SELECT * FROM DIP.item order by id";
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
			
			String query = "INSERT INTO DIP.ITEM (NAME, PRICE, TOTAL_NUMBER) VALUES('" + 
								item.getName() + "', " + item.getPrice() +", " + item.getTotalNumber() + ")";
			
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
		Connection connection = null;
		Statement statement = null;
		
		try {
			connection = dataSource.getConnection();  // connection open
			statement = connection.createStatement(); // statement open
			
			String query = "UPDATE DIP.ITEM SET NAME='" + item.getName() + "', PRICE=" + item.getPrice() + ", TOTAL_NUMBER = " + item.getTotalNumber() + " WHERE ID=" + item.getId();
			
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
	public boolean removeItem(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
