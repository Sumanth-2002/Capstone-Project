package com.UST.Product_Service.service;

import com.UST.Product_Service.model.Product;
import com.UST.Product_Service.model.ProductDto;
import com.UST.Product_Service.model.Purchases;
import com.UST.Product_Service.repository.ProductRepository;
import com.UST.Product_Service.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private WebClient.Builder webClientBuilder;

    public List<Product> saveProducts(List<Product> products){
        List<Product> savedProducts =   productRepository.saveAll(products);
        int totalQuantity = 0;
        String vendorId = savedProducts.get(0).getVendorId();
        for(Product product : savedProducts){
            Purchases purchases = new Purchases();
            purchases.setCompanyId(product.getCompanyId());
            purchases.setProductId(product.getProductId());
            purchases.setQuantity(product.getQuantity());
            purchases.setProductName(product.getProductName());
            purchases.setVendorId(product.getVendorId());
            purchases.setVendorName(product.getVendorName());
            purchases.setPrice(product.getCost_Price());
            purchases.setTotalPrice(product.getQuantity()*product.getCost_Price());
            purchaseRepository.save(purchases);
            totalQuantity += product.getQuantity();
        }
        Map<String, Object> vendorDto = new HashMap<>();
        vendorDto.put("vendorId", vendorId);
        vendorDto.put("totalQuantity", totalQuantity);
Map<String,Object> response = WebClient.builder()
                .baseUrl("http://localhost:9094")
                .build()
                .put()
                .uri("/api/vendors/update-vendor")
                .bodyValue(vendorDto) // Attach the DTO as the body
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String,Object>>() {})
                .block();
        return savedProducts;

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

    public Product updateQuantityById( ProductDto productDto) {
        return productRepository.findById(productDto.getProductId())
                .map(product -> {
                    product.setQuantity(product.getQuantity()-productDto.getQuantity());
                    return productRepository.save(product);
                })
                .orElse(null);
    }
    public Product restockProduct(ProductDto productDto){
        Product product = productRepository.findById(productDto.getProductId()).get();
        product.setQuantity(product.getQuantity()+productDto.getQuantity());
        Purchases purchases = new Purchases();
        purchases.setCompanyId(product.getCompanyId());
        purchases.setProductId(product.getProductId());
        purchases.setQuantity(productDto.getQuantity());
        purchases.setProductName(product.getProductName());
        purchases.setVendorId(product.getVendorId());
        purchases.setVendorName(product.getVendorName());
        purchases.setPrice(product.getCost_Price());
        purchases.setTotalPrice(productDto.getQuantity()*product.getCost_Price());
        purchaseRepository.save(purchases);
        return productRepository.save(product);
    }

    public Map<String,Double> getTotalPurchases(String companyId){
        Map<String,Double> totalSale = new HashMap<>();
        totalSale.put("totalSale",  purchaseRepository.getTotalPurchases(companyId));
        return totalSale;
    }

}
