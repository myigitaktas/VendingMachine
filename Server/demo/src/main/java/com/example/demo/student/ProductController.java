package com.example.demo.student;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path="api/product")

public class ProductController {
	private final ProductRepository productRepository;
	@Autowired
	public ProductController(ProductRepository productRepository)
	{
		
		this.productRepository=productRepository;
	}
	
	
	
	@GetMapping
	public List<Product> getProducts() {
		return productRepository.findAll();
		
	}
	@PutMapping("/{productId}/updateStock")
    public ResponseEntity<String> updateProductStock(
            @PathVariable Long productId,
            @RequestBody UpdateStockRequest request
    ) {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setStock(request.getNewStockValue()+product.getStock());
            productRepository.save(product);
            return ResponseEntity.ok("Stock updated successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	@PutMapping("/{productId}/DecreaseStock")
    public ResponseEntity<String> DecreaseProductStock(
            @PathVariable Long productId
           
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
            return ResponseEntity.ok("Stock updated successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	
	

}
