package com.example.demo.student;

public class PriceRequest {
	private String price;

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	public Double getPriceDouble()
	{
		return Double.parseDouble(price.substring(1));
	}
	public boolean isRaise()
	{
		return (price.charAt(0)=='+');
	}

}
