package models;

public class Gem {

	private long Id;
	 
	private String color;
	
	private String shine;
 
	private float price;
	
	private String description;
	
	private String rarity;
	
	private String faces;
	
	private boolean sold;
 
	public Gem(long id, String color, String shine, float price,String description,String rarity, String faces, boolean sold) {
		super();
		Id = id;
		this.color = color;
		this.shine = shine;
		this.price = price;
		this.description = description;
		this.rarity = rarity;
		this.faces = faces;
		this.sold = sold;
	}
	
	public Gem(){
		
	}
 
	public long getId() {
		return Id;
	}
 
	public void setId(long id) {
		Id = id;
	}
 
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getShine() {
		return shine;
	}

	public void setShine(String shine) {
		this.shine = shine;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getDescriptioin() {
		return description;
	}

	public void setDescriptioin(String descriptioin) {
		this.description = descriptioin;
	}

	public String getRarity() {
		return rarity;
	}

	public void setRarity(String rarity) {
		this.rarity = rarity;
	}

	public String getFaces() {
		return faces;
	}

	public void setFaces(String faces) {
		this.faces = faces;
	}

	public boolean isSold() {
		return sold;
	}

	public void setSold(boolean sold) {
		this.sold = sold;
	}

	public String toString(){
		return "(" + this.Id + ", " + this.color + ", " + this.shine 
				+ ", " + this.price + ", " + this.description +", " 
				+this.rarity +", " +this.faces+", " +this.sold + ")";
	}
}

