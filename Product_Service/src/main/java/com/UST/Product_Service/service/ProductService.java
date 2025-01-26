package com.UST.Product_Service.service;

import com.UST.Product_Service.model.Product;
import com.UST.Product_Service.model.ProductDto;
import com.UST.Product_Service.model.Purchases;
import com.UST.Product_Service.repository.ProductRepository;
import com.UST.Product_Service.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    public List<Product> saveProducts(List<Product> products){
        List<Product> savedProducts =   productRepository.saveAll(products);
        for(Product product : savedProducts){
            Purchases purchases = new Purchases();
            purchases.setCompanyId(product.getCompany_id());
            purchases.setProductId(product.getProductId());
            purchases.setQuantity(product.getQuantity());
            purchases.setProductName(product.getProductName());
            purchases.setVendorId(product.getVendorId());
            purchases.setVendorName(product.getVendorName());
            purchases.setPrice(product.getCost_Price());
            purchases.setTotalPrice(product.getQuantity()*product.getCost_Price());
            purchaseRepository.save(purchases);
        }
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
        purchases.setCompanyId(product.getCompany_id());
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

}
