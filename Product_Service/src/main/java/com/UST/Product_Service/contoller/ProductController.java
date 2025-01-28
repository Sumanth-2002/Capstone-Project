package com.UST.Product_Service.contoller;

import com.UST.Product_Service.model.Product;
import com.UST.Product_Service.model.ProductDto;
import com.UST.Product_Service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductService productService;


    @PostMapping("/bulk")
    public ResponseEntity<List<Product>> saveProducts(@RequestBody List<Product> products) {
        List<Product> savedProducts = productService.saveProducts(products);
        return ResponseEntity.ok(savedProducts);
    }

    @GetMapping("/get-products/{companyId}")
    public ResponseEntity<List<Product>> getAllProducts(@PathVariable String companyId) {
        List<Product> products = productService.getAllProducts(companyId);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") String productId) {
        Product product = productService.getProductById(productId);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable("id") String productId) {
        String response = productService.deleteProductById(productId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/UpdateQuantityById")
    public Product updateQuantityById(@RequestBody ProductDto productDto){
        return productService.updateQuantityById(productDto);
    }

    @PutMapping("/restock-product")
    public Product restockProduct(@RequestBody ProductDto productDto){
        return productService.restockProduct(productDto);
    }

    @GetMapping("/get-total-purchases/{companyId}")
    public Map<String,Double> getTotalPurchase(@PathVariable String companyId){
        return productService.getTotalPurchases(companyId);
    }
}
