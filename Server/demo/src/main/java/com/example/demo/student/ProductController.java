package com.example.demo.student;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path="api/product")

public class ProductController {
	private final ProductService productService;
	@Autowired
	public ProductController(ProductService productService)
	{
		
		this.productService=productService;
	}
	
	
	
	@GetMapping
	public List<Product> getProducts() {
		return productService.getProducts();
		
	}
	@PutMapping("/{productId}/updateStock")
    public ResponseEntity<String> updateProductStock(
            @PathVariable Long productId,
            @RequestBody UpdateStockRequest request
    ) {
        return productService.updateProductStock(productId, request);
    }
	@PutMapping("/{productId}/DecreaseStock")
    public ResponseEntity<String> DecreaseProductStock(
            @PathVariable Long productId
           
    ) {
        return productService.DecreaseProductStock(productId);
    }
	@PutMapping("/{productId}/raisePrice")
	public ResponseEntity<String> raisePrice(
			@PathVariable Long productId,
			@RequestBody RaisePriceRequest request)
	{
		return productService.RaisePrice(productId, request);
	}
	@PutMapping("/{productId}/discountPrice")
	public ResponseEntity<String>discountPrice(
			@PathVariable Long productId,
			@RequestBody DiscountPriceRequest request)
	{
		return productService.discountPrice(productId, request);
	}
	
	

}
