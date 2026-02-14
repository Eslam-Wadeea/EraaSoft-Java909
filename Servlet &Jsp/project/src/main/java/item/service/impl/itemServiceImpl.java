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
		List<Item> list = new ArrayList<>();
	    String sql = "SELECT i.ID, i.NAME, i.PRICE, i.TOTAL_NUMBER, d.DESCRIPTION, d.EXPIRY_DATE " +
	                 "FROM DIP.ITEM i " +
	                 "LEFT JOIN DIP.ITEM_DETAILS d ON i.ID = d.ITEM_ID " +
	                 "WHERE i.IS_DELETED = 0";

	    try (Connection conn = dataSource.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            Item item = new Item();
	            item.setId(rs.getInt("ID"));
	            item.setName(rs.getString("NAME"));
	            item.setPrice(rs.getDouble("PRICE"));
	            item.setTotalNumber(rs.getInt("TOTAL_NUMBER"));
	            String desc = rs.getString("DESCRIPTION");
	            item.setDescription(desc);
	            item.setExpiryDate(rs.getDate("EXPIRY_DATE"));
	            
	     
	            
	            item.setHasDetails(desc != null);
	            
	            list.add(item);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
	

	@Override
	public Item getItem(Long id) {
		Connection connection = null;
		Statement statement = null;
		
		try {
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			
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
	    boolean isSaved = false;
	    String sqlItem = "INSERT INTO DIP.ITEM (NAME, PRICE, TOTAL_NUMBER, IS_DELETED) VALUES (?, ?, ?, 0)";
	    String sqlDetails = "INSERT INTO DIP.ITEM_DETAILS (ID, DESCRIPTION, ISSUE_DATE, EXPIRY_DATE, ITEM_ID) VALUES (DIP.SEQ_DETAILS.NEXTVAL, ?, SYSDATE, ?, ?)";
	    try (Connection conn = dataSource.getConnection()) {
	        conn.setAutoCommit(false); 

	        try (PreparedStatement ps1 = conn.prepareStatement(sqlItem, new String[]{"ID"})) {
	            ps1.setString(1, item.getName());
	            ps1.setDouble(2, item.getPrice());
	            ps1.setInt(3, item.getTotalNumber());
	            
	            int rows1 = ps1.executeUpdate();
	            if (rows1 > 0) {
	                ResultSet rs = ps1.getGeneratedKeys();
	                if (rs.next()) {
	                    int generatedItemId = rs.getInt(1);

	                    try (PreparedStatement ps2 = conn.prepareStatement(sqlDetails)) {
	                        ps2.setString(1, item.getDescription());
	                       
	                        if (item.getExpiryDate() != null) {
	                            ps2.setDate(2, new java.sql.Date(item.getExpiryDate().getTime()));
	                        } else {
	                            ps2.setNull(2, java.sql.Types.DATE);
	                        }
	                        
	                        ps2.setInt(3, generatedItemId);
	                        
	                        int rows2 = ps2.executeUpdate();
	                    
	                        if (rows2 > 0) {
	                            conn.commit(); 
	                            isSaved = true;
	                        }
	                    }
	                }
	            }
	            if (!isSaved) {
	                conn.rollback();
	            }

	        } catch (SQLException e) {
	            conn.rollback();
	            System.err.println("SQL Error: " + e.getMessage());
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return isSaved;
	}

	@Override
	public boolean updateItem(Item item) {
	    boolean isSaved = false;
	    String sqlItem = "UPDATE DIP.ITEM SET NAME = ?, PRICE = ?, TOTAL_NUMBER = ? WHERE ID = ?";
	    String sqlDetails = "UPDATE DIP.ITEM_DETAILS SET DESCRIPTION = ?, EXPIRY_DATE = ? WHERE ITEM_ID = ?";

	    try (Connection conn = dataSource.getConnection()) {
	        conn.setAutoCommit(false); 

	        try (PreparedStatement ps1 = conn.prepareStatement(sqlItem)) {
	            ps1.setString(1, item.getName());
	            ps1.setDouble(2, item.getPrice());
	            ps1.setInt(3, item.getTotalNumber());
	            ps1.setLong(4, item.getId());
	            
	            int rows1 = ps1.executeUpdate();

	            if (rows1 > 0) {
	                try (PreparedStatement ps2 = conn.prepareStatement(sqlDetails)) {
	                    ps2.setString(1, item.getDescription());
	                    if (item.getExpiryDate() != null) {
	                        ps2.setDate(2, new java.sql.Date(item.getExpiryDate().getTime()));
	                    } else {
	                        ps2.setNull(2, java.sql.Types.DATE);
	                    }
	                    
	                    ps2.setLong(3, item.getId());
	                    int rows2 = ps2.executeUpdate();

	                    if (rows2 > 0) {
	                        conn.commit(); 
	                        isSaved = true;
	                    }
	                }
	            }
	            if (!isSaved) {
	                conn.rollback();
	            }

	        } catch (SQLException e) {
	            conn.rollback(); 
	            System.err.println("SQL Error: " + e.getMessage());
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return isSaved;
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
	
	public boolean isNameExists(String name) {
	    try (Connection connection = dataSource.getConnection();
	         Statement statement = connection.createStatement()) {
	        
	        String query = "SELECT COUNT(*) FROM DIP.ITEM WHERE NAME = '" + name + "' AND IS_DELETED = 0";
	        ResultSet rs = statement.executeQuery(query);
	        if (rs.next()) {
	            return rs.getInt(1) > 0;
	        }
	    } catch (SQLException e) {
	        System.out.println("ex => " + e.getMessage());
	    }
	    return false;
	}
	public boolean isNameExistsForOtherId(String name, Long currentId) {
	    String query = "SELECT COUNT(*) FROM DIP.ITEM WHERE NAME = ? AND ID != ? AND IS_DELETED = 0";
	    
	    try (Connection connection = dataSource.getConnection();
	         PreparedStatement pstmt = connection.prepareStatement(query)) {
	        
	        pstmt.setString(1, name);
	        pstmt.setLong(2, currentId);
	        
	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                return rs.getInt(1) > 0;
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Database Error (isNameExistsForOtherId): " + e.getMessage());
	    }
	    return false;
	}
	
	@Override
	public void deleteItemDetails(int itemId) {
	    String sql = "DELETE FROM DIP.ITEM_DETAILS WHERE ITEM_ID = ?";
	    try (Connection conn = dataSource.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setInt(1, itemId);
	        ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	


}
