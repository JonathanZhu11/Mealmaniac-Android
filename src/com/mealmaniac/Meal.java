package com.mealmaniac;

public class Meal {
	
	private String restaurant;
	private String item;
	private String price;
	
	
	//If called with no arguments, then get a meal from the backend
	public Meal() {
		//TODO: Get a meal from the backend
		restaurant = "Restaurant";
		item = "item";
		price = "price";
	}
	
	//If called with the parameters set, then just store the info
	public Meal(String restaurant, String item, String price) {
		this.restaurant = restaurant;
		this.item = item;
		this.price = price;
	}
	
	//Getters
	public String getRestaurant() {
		return restaurant;
	}
	
	public String getItem() {
		return item;
	}
	
	public String getPrice() {
		return price;
	}
	
	//Setters
	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
	}
	
	public void setItem(String item) {
		this.item = item;
	}
	
	public void setPrice(String price) {
		this.price = price;
	}
}
