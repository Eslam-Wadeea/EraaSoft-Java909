package item.model;

public class item {
	private int id;
    private String name;
    private int price;
    private double totalNumber;

    
    public item() {
    }
    
    public item( String name, int price, double totalNumber) {
        this.name = name;
        this.price = price;
        this.totalNumber = totalNumber;
    }
        
    public item(int id, String name, int price, double totalNumber) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.totalNumber = totalNumber;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(double totalNumber) {
        this.totalNumber = totalNumber;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", totalNumber=" + totalNumber +
                '}';
    }
	

}
