package com.UST.Product_Service.service;

import com.UST.Product_Service.model.Product;
import com.UST.Product_Service.model.ProductDto;
import com.UST.Product_Service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> saveProducts(List<Product> products){
        return productRepository.saveAll(products);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(String productId) {
        return productRepository.findById(productId).orElse(null);
    }

    public String deleteProductById(String productId) {
        productRepository.deleteById(productId);
        return "Product Deleted Successfully";
    }

    public Product updateQuantityById(String productId, ProductDto productDto) {
        return productRepository.findById(productId)
                .map(product -> {
                    product.setQuantity(productDto.getQuantity());
                    return productRepository.save(product);
                })
                .orElse(null);
    }

}
