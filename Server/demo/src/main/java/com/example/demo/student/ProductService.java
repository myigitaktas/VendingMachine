package com.example.demo.student;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	public ResponseEntity<String> updateProductStock(
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
            return ResponseEntity.notFound().build();
        }
    }
	public ResponseEntity<String> DecreaseProductStock(
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
            return ResponseEntity.notFound().build();
        }
    }
	public ResponseEntity<String> RaisePrice(
		    Long productId,
		    RaisePriceRequest request
		) {
		    Optional<Product> optionalProduct = productRepository.findById(productId);
		    if (optionalProduct.isPresent()) {
		        Product product = optionalProduct.get();
		        double percentage = (double) request.getRaisePercentage() / 100;
		        double newPrice = product.getPrice() * (1 + percentage);
		        product.setPrice(newPrice);
		        productRepository.save(product);
		        return ResponseEntity.ok("Raise operation completed successfully.");
		    } else {
		        return ResponseEntity.notFound().build();
		    }
		}
	
	public ResponseEntity<String> discountPrice(
		    Long productId,
		    DiscountPriceRequest request
		) {
		    Optional<Product> optionalProduct = productRepository.findById(productId);
		    if (optionalProduct.isPresent()) {
		        Product product = optionalProduct.get();
		        double percentage = (double) request.getDiscountPercentage() / 100;
		        double newPrice = product.getPrice() * (1 - percentage);
		        product.setPrice(newPrice);
		        productRepository.save(product);
		        return ResponseEntity.ok("Discount operation  completedsuccessfully.");
		    } else {
		        return ResponseEntity.notFound().build();
		    }
		}

	

}
