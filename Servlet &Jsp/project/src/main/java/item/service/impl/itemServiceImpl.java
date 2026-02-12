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
	    // استخدام LEFT JOIN لضمان ظهور المنتجات حتى لو لم يكن لها تفاصيل بعد
	    String sql = "SELECT i.ID, i.NAME, i.PRICE, i.TOTAL_NUMBER, d.DESCRIPTION, d.EXPIRY_DATE " +
	                 "FROM DIP.ITEM i " +
	                 "LEFT JOIN DIP.ITEM_DETAILS d ON i.ID = d.ITEM_ID " +
	                 "WHERE i.IS_DELETED = 0"; // المهمة 10 من المستوى الأول

	    try (Connection conn = dataSource.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            Item item = new Item();
	            item.setId(rs.getInt("ID"));
	            item.setName(rs.getString("NAME"));
	            item.setPrice(rs.getDouble("PRICE"));
	            item.setTotalNumber(rs.getInt("TOTAL_NUMBER"));
	            
	            // قراءة البيانات الجديدة من نتيجة الاستعلام
	            item.setDescription(rs.getString("DESCRIPTION"));
	            item.setExpiryDate(rs.getDate("EXPIRY_DATE"));
	            
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
	    boolean isSaved = false;
	    // الاستعلام الأول للجدول الأساسي
	    String sqlItem = "INSERT INTO DIP.ITEM (NAME, PRICE, TOTAL_NUMBER, IS_DELETED) VALUES (?, ?, ?, 0)";
	    String sqlDetails = "INSERT INTO DIP.ITEM_DETAILS (ID, DESCRIPTION, ISSUE_DATE, EXPIRY_DATE, ITEM_ID) VALUES (DIP.SEQ_DETAILS.NEXTVAL, ?, SYSDATE, ?, ?)";
	    try (Connection conn = dataSource.getConnection()) {
	        // 1. بدء الـ Transaction يدوياً
	        conn.setAutoCommit(false); 

	        try (PreparedStatement ps1 = conn.prepareStatement(sqlItem, new String[]{"ID"})) {
	            ps1.setString(1, item.getName());
	            ps1.setDouble(2, item.getPrice());
	            ps1.setInt(3, item.getTotalNumber());
	            
	            int rows1 = ps1.executeUpdate();

	            // 2. التحقق من نجاح الإدخال الأول والحصول على الـ ID المولد
	            if (rows1 > 0) {
	                ResultSet rs = ps1.getGeneratedKeys();
	                if (rs.next()) {
	                    int generatedItemId = rs.getInt(1);

	                    try (PreparedStatement ps2 = conn.prepareStatement(sqlDetails)) {
	                        ps2.setString(1, item.getDescription());
	                        
	                        // معالجة التاريخ لمنع خطأ الـ Null الذي ظهر لك في الكونسول
	                        if (item.getExpiryDate() != null) {
	                            ps2.setDate(2, new java.sql.Date(item.getExpiryDate().getTime()));
	                        } else {
	                            ps2.setNull(2, java.sql.Types.DATE);
	                        }
	                        
	                        ps2.setInt(3, generatedItemId); // الربط عبر الـ Foreign Key
	                        
	                        int rows2 = ps2.executeUpdate();
	                        
	                        // 3. إذا نجح الحفظ في الجدولين، نقوم بالـ Commit
	                        if (rows2 > 0) {
	                            conn.commit(); 
	                            isSaved = true;
	                        }
	                    }
	                }
	            }
	            
	            // 4. إذا فشل أي جزء، نقوم بعمل تراجع (Rollback)
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
	    // We check for the name but EXCLUDE the current ID
	    String query = "SELECT COUNT(*) FROM DIP.ITEM WHERE NAME = ? AND ID != ? AND IS_DELETED = 0";
	    
	    try (Connection connection = dataSource.getConnection();
	         PreparedStatement pstmt = connection.prepareStatement(query)) {
	        
	        // Bind the parameters to the ? placeholders
	        pstmt.setString(1, name);
	        pstmt.setLong(2, currentId);
	        
	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                // If count > 0, the name is already taken by someone else
	                return rs.getInt(1) > 0;
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Database Error (isNameExistsForOtherId): " + e.getMessage());
	    }
	    return false;
	}
	


}
