package health.model;

public class Payment {
	
	private String name,type,hname;
	private int hospital,id;
	private double price;
	
	public String getHname() {
		return hname;
	}
	public void setHname(String hname) {
		this.hname = hname;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getHospital() {
		return hospital;
	}
	public void setHospital(int hopital) {
		this.hospital = hopital;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double prrice) {
		this.price = prrice;
	}
	
}
