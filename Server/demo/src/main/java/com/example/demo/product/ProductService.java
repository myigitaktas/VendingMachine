package com.example.demo.product;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	private final ProductRepository productRepository;
	@Autowired
	public ProductService(ProductRepository productRepository)
	{
		this.productRepository=productRepository;
	}
	public List<Product> getProducts()
	{
		return productRepository.findAll();
				
	}
	public ResponseEntity<String> increaseProductStock(
             Long productId,
             UpdateStockRequest request
    ) {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setStock(request.getNewStockValue()+product.getStock());
            productRepository.save(product);
            return ResponseEntity.ok("Stocking operation completed successfully.");
        } else {
        	String errorMessage = "Product with ID " + productId + " not found.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }
	public ResponseEntity<String> decreaseProductStock(
             Long productId
           
    ) {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            if(product.getStock()<1)
            {
            	return ResponseEntity.badRequest().body("Product is out of stock.");
            }
            product.setStock(product.getStock()-1);
            productRepository.save(product);
            return ResponseEntity.ok("Transaction completed successfully.");
        } else {
        	String errorMessage = "Product with ID " + productId + " not found.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }
	public ResponseEntity<String> raisePrice(
		    Long productId,
		    PriceRequest request
		) {
		    Optional<Product> optionalProduct = productRepository.findById(productId);
		    if (optionalProduct.isPresent()) {
		        Product product = optionalProduct.get();
		        double percentage = (double) request.getPriceDouble() / 100;
		        double newPrice = product.getPrice() * (1 + percentage);
		        product.setPrice(newPrice);
		        productRepository.save(product);
		        return ResponseEntity.ok("Raise operation completed successfully.");
		    } else {
		    	String errorMessage = "Product with ID " + productId + " not found.";
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
		    }
		}
	
	public ResponseEntity<String> discountPrice(
		    Long productId,
		    PriceRequest request
		) {
		    Optional<Product> optionalProduct = productRepository.findById(productId);
		    if (optionalProduct.isPresent()) {
		        Product product = optionalProduct.get();
		        double percentage = (double) request.getPriceDouble() / 100;
		        double newPrice = product.getPrice() * (1 - percentage);
		        product.setPrice(newPrice);
		        productRepository.save(product);
		        return ResponseEntity.ok("Discount operation  completed successfully.");
		    } else {
		    	String errorMessage = "Product with ID " + productId + " not found.";
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
		        
		    }
		}

	

}
