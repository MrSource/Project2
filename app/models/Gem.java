package models;

import java.util.Arrays;

public class Gem implements Comparable<Gem> {
	
	private String name;
	
	private String description;
	
	private int shine;
	
	private double price;
	
	private int rarity;
	
	private String color;
	
	private int faces;
	
	private String[] images;
	
	private Review review;
	
	
	
	public Gem(String name, String description, int shine, double price,
			int rarity, String color, int faces, String[] images, Review review) {
		super();
		this.name = name;
		this.description = description;
		this.shine = shine;
		this.price = price;
		this.rarity = rarity;
		this.color = color;
		this.faces = faces;
		this.images = images;
		this.review = review;
	}
	
	



	public Gem(String name, String description, int shine, double price,
			int rarity, String color, int faces, String[] images) {
		super();
		this.name = name;
		this.description = description;
		this.shine = shine;
		this.price = price;
		this.rarity = rarity;
		this.color = color;
		this.faces = faces;
		this.images = images;
	}





	public Gem(String name, String description, int shine, double price,
			int rarity, String color, int faces) {
		super();
		this.name = name;
		this.description = description;
		this.shine = shine;
		this.price = price;
		this.rarity = rarity;
		this.color = color;
		this.faces = faces;
	}
	
	public Gem(){
		
	}





	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public int getShine() {
		return shine;
	}



	public void setShine(int shine) {
		this.shine = shine;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	public int getRarity() {
		return rarity;
	}



	public void setRarity(int rarity) {
		this.rarity = rarity;
	}



	public String getColor() {
		return color;
	}



	public void setColor(String color) {
		this.color = color;
	}



	public int getFaces() {
		return faces;
	}



	public void setFaces(int faces) {
		this.faces = faces;
	}



	public String[] getImages() {
		return images;
	}



	public void setImages(String[] images) {
		this.images = images;
	}



	public Review getReview() {
		return review;
	}



	public void setReview(Review review) {
		this.review = review;
	}



	class Review{
		private int stars;
		private String body;
		private String author;
		private int createdOn;
		
		
		
		public Review(int stars, String body, String author, int createdOn){
			this.stars = stars;
			this.body=body;
			this.author=author;
			this.createdOn=createdOn;
		}



		public int getStars() {
			return stars;
		}



		public void setStars(int stars) {
			this.stars = stars;
		}



		public String getBody() {
			return body;
		}



		public void setBody(String body) {
			this.body = body;
		}



		public String getAuthor() {
			return author;
		}



		public void setAuthor(String author) {
			this.author = author;
		}



		public int getCreatedOn() {
			return createdOn;
		}



		public void setCreatedOn(int createdOn) {
			this.createdOn = createdOn;
		}	
		
		
	}

	@Override
	public int compareTo(Gem o) {
		// TODO Auto-generated method stub
		return 0;
	}





	@Override
	public String toString() {
		return "Gem [name=" + name + ", description=" + description
				+ ", shine=" + shine + ", price=" + price + ", rarity="
				+ rarity + ", color=" + color + ", faces=" + faces
				+ ", images=" + Arrays.toString(images) + ", review=" + review
				+ "]";
	}
	
	
	
}
