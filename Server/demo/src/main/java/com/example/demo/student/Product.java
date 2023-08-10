package com.example.demo.student;

public class Product {
	private long id;
	private String name;
	private int stock;
	private String base_64;
	private double price;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getBase_64() {
		return base_64;
	}
	public void setBase_64(String base_64) {
		this.base_64 = base_64;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Product(long id, String name, int stock, String base_64, double price) {
		this.id = id;
		this.name = name;
		this.stock = stock;
		this.base_64 = base_64;
		this.price = price;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", stock=" + stock + ", base_64=" + base_64 + ", price=" + price
				+ "]";
	}
	

}
