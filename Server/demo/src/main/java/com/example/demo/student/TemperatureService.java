package com.example.demo.student;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Random;
@Service
public class TemperatureService {
	private final ProductRepository productRepository;

    public TemperatureService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Scheduled(fixedRate = 60000) // Schedule to run every 30 seconds
    @Transactional // To ensure proper transaction management
    public void updateRandomProductColdField() {
        List<Product> products = productRepository.findAll();
        
        if (!products.isEmpty()) {
            int randomIndex = new Random().nextInt(products.size());
            Product randomProduct = products.get(randomIndex);
            randomProduct.setCold(0); // Set the "cold" field to 0
            productRepository.save(randomProduct);
        }
    }

}
