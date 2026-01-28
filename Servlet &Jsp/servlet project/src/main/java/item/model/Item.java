package item.model;

public class Item {
	private Long id;
    private String name;
    private double price;
    private int total_Number;

    
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

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", price=" + price + ", totalNumber=" + total_Number + "]";
	}
    
    

    
    
	

}
