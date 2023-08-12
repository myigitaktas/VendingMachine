
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
@RequestMapping(path="api/v1/product")

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
	@PutMapping("/{productId}/stocks")
	public ResponseEntity<String> updateStock(
			@PathVariable Long productId,
			@RequestBody UpdateStockRequest request)
	{
		if(request.getTransaction())
		{
			return productService.decreaseProductStock(productId);
		}
		else
		{
			return productService.increaseProductStock(productId, request);
		}
		
	}
	@PutMapping("/{productId}/prices")
	public ResponseEntity<String> updatePrice(
			@PathVariable Long productId,
			@RequestBody PriceRequest request)
	{
		if(request.isRaise())
		{
			return productService.raisePrice(productId, request);
		}
		else
		{
			return productService.discountPrice(productId, request);
		}
	}
	
	
	
	

}
