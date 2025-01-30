package com.UST.Product_Service.contoller;

import com.UST.Product_Service.model.Product;
import com.UST.Product_Service.model.ProductDto;
import com.UST.Product_Service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductService productService;


    @PostMapping("/add")
    public ResponseEntity<Product> saveProducts(@RequestBody Product product) {
        Product savedProducts = productService.saveProduct(product);
        return ResponseEntity.ok(savedProducts);
    }

    @GetMapping("/get-products/{companyId}")
    public ResponseEntity<List<Product>> getAllProducts(@PathVariable String companyId) {
        List<Product> products = productService.getAllProducts(companyId);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/getproductById/{id}")
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

    @PostMapping("/save-data-csv")

    public ResponseEntity<String> uploadCSV(@RequestParam("products_data") MultipartFile file,@RequestParam String companyId) {
        try {
            Path tempFile = Files.createTempFile("products_data", ".csv");
            Files.copy(file.getInputStream(), tempFile, StandardCopyOption.REPLACE_EXISTING);
            productService.saveProductDataFromCSV(tempFile.toString(),companyId);
            Files.delete(tempFile);
            return ResponseEntity.ok("Products data successfully saved!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error occurred while processing the file");
        }
    }
}
