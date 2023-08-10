package com.example.demo.student;

import java.util.List;

import org.springframework.stereotype.Service;
@Service
public class ProductService {
	public List<Product> getProducts()
	{
		return List.of(
				new Product(
						1L,
						"Soda",
						20,
						"abc",
						Double.valueOf(45)));
				
	}

}
