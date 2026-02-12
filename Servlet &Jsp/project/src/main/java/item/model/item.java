package item.model;

import java.util.Date;

public class Item {
	private Long id;
    private String name;
    private double price;
    private int total_Number;
    private int is_deleted;
    private String description; 
    private Date expiryDate;
    private Date issueDate;
    
    
    

    
    public Item() {
    }
    
    public Item( String name, double price, int totalNumber) {
        this.name = name;
        this.price = price;
        this.total_Number = totalNumber;
    }
        
    public Item(Long id, String name, double price, int totalNumber) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.total_Number = totalNumber;
    }

	public Long getId() {
		return id;
	}

	public void setId(long i) {
		this.id = i;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getTotalNumber() {
		return total_Number;
	}

	public void setTotalNumber(int totalNumber) {
		this.total_Number = totalNumber;
	}
	
	public int getIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(int is_deleted) {
		this.is_deleted = is_deleted;
	}
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", price=" + price + ", totalNumber=" + total_Number + "]";
	}
    
    

    
    
	

}
