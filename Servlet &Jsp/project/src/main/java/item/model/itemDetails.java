package item.model;

import java.util.Date;

public class itemDetails {
	private int id ;
	private String description;
	private Date issue_date;
	private Date expiry_date;
	
	public itemDetails(int id ,  String description, Date issue_date, Date expiry_date) {
		this.id = id;
		this.description = description;
		this.issue_date = issue_date;
		this.expiry_date = expiry_date;
	}
	
	public itemDetails(String description, Date issue_date, Date expiry_date) {
		this.description = description;
		this.issue_date = issue_date;
		this.expiry_date = expiry_date;
	}

	public itemDetails() {
	}
	
	


	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public Date getIssue_date() {
		return issue_date;
	}



	public void setIssue_date(Date issue_date) {
		this.issue_date = issue_date;
	}



	public Date getExpiry_date() {
		return expiry_date;
	}



	public void setExpiry_date(Date expiry_date) {
		this.expiry_date = expiry_date;
	}

	@Override
	public String toString() {
		return "itemDetails [id=" + id + ", description=" + description + ", issue_date=" + issue_date
				+ ", expiry_date=" + expiry_date + "]";
	}
	



	
	
	

}
