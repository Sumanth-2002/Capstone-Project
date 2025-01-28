package com.UST.Product_Service.service;

import com.UST.Product_Service.model.Product;
import com.UST.Product_Service.model.ProductDto;
import com.UST.Product_Service.model.Purchases;
import com.UST.Product_Service.repository.ProductRepository;
import com.UST.Product_Service.repository.PurchaseRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
        Product savedProduct = productRepository.save(product);
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
        String vendorId = savedProduct.getVendorId();
        Long totalQuantity = savedProduct.getQuantity();
        Map<String, Object> vendorDto = new HashMap<>();
        vendorDto.put("vendorId", vendorId);
        vendorDto.put("quantity", totalQuantity);
        Map<String,Object> response = WebClient.builder()
                .baseUrl("http://localhost:9094")
                .build()
                .put()
                .uri("/api/vendors/update-vendor")
                .bodyValue(vendorDto) // Attach the DTO as the body
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String,Object>>() {})
                .block();
        return savedProduct;
    }

    public List<Product> getAllProducts(String companyId) {
        return productRepository.findByCompanyId(companyId);
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
    public void saveProductDataFromCSV(String filePath,String companyId) {
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            // Read headers (assuming the first row contains column names)
            String[] headers = csvReader.readNext();
            String[] line;
            Long totalQuantity = 0l;
            // Process each line in the CSV file
            while ((line = csvReader.readNext()) != null) {
                // Map CSV fields to Product entity fields
                Product product = new Product();
//                product.setProductId(line[0]);       // Assuming productId is at column 0
                product.setProductName(line[0]);    // Assuming productName is at column 1
                product.setCategory(line[1]);       // Assuming category is at column 2
                product.setVendorId(line[2]);       // Assuming vendorId is at column 3
                product.setVendorName(line[3]);     // Assuming vendorName is at column 4
                product.setSelling_Price(Double.parseDouble(line[4])); // Assuming sellingPrice is at column 5
                product.setCost_Price(Double.parseDouble(line[5]));    // Assuming costPrice is at column 6
                product.setQuantity(Long.parseLong(line[6]));          // Assuming quantity is at column 7
                product.setDescription(line[7]);   // Assuming description is at column 8
                product.setCompanyId(companyId);     // Assuming companyId is at column 9

                productRepository.save(product);
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
                String vendorId = product.getVendorId();
                Long totalQuantityS = product.getQuantity();
                Map<String, Object> vendorDto = new HashMap<>();
                vendorDto.put("vendorId", vendorId);
                vendorDto.put("quantity", totalQuantityS);
                Map<String,Object> response = WebClient.builder()
                        .baseUrl("http://localhost:9094")
                        .build()
                        .put()
                        .uri("/api/vendors/update-vendor")
                        .bodyValue(vendorDto) // Attach the DTO as the body
                        .retrieve()
                        .bodyToMono(new ParameterizedTypeReference<Map<String,Object>>() {})
                        .block();
            }

        } catch (IOException | CsvValidationException | NumberFormatException e) {
            e.printStackTrace();
        }
    }


    private Date parseDate(String dateStr) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
