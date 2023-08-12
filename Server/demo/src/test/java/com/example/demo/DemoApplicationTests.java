package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.product.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;



class DemoApplicationTests {

	@Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testGetProducts() {
        List<Product> productList = new ArrayList<>();
        // Add products to the list

        when(productService.getProducts()).thenReturn(productList);

        List<Product> result = productController.getProducts();

        // Assert the result
        assertEquals(productList, result);
    }

    @Test
    public void testUpdateStockValidTransaction() {
        Long productId = 1L;
        UpdateStockRequest request = new UpdateStockRequest();
        request.setNewStockValue(10);
        request.setTransaction(true);

        when(productService.decreaseProductStock(productId)).thenReturn(ResponseEntity.ok("Stock updated."));

        ResponseEntity<String> response = productController.updateStock(productId, request);

        // Assert the response
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Stock updated.", response.getBody());
    }

    @Test
    public void testUpdateStockInvalidTransaction() {
        Long productId = 1L;
        UpdateStockRequest request = new UpdateStockRequest();
        request.setNewStockValue(10);

        ResponseEntity<String> response = productController.updateStock(productId, request);

        // Assert the response
        
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("JSON body lacks required fields", response.getBody());
    }

    @Test
    public void testUpdatePriceValidRaise() {
        Long productId = 1L;
        PriceRequest request = new PriceRequest();
        request.setPrice("+100");

        when(productService.raisePrice(productId, request)).thenReturn(ResponseEntity.ok("Price raised."));

        ResponseEntity<String> response = productController.updatePrice(productId, request);

        // Assert the response
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Price raised.", response.getBody());
    }

    @Test
    public void testUpdatePriceInvalidPrice() {
        Long productId = 1L;
        PriceRequest request = new PriceRequest();
        request.setPrice("");

        ResponseEntity<String> response = productController.updatePrice(productId, request);

        // Assert the response
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("JSON body lacks required fields", response.getBody());
    }

}

