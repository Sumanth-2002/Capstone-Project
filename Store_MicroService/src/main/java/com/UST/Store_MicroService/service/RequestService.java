package com.UST.Store_MicroService.service;

import com.UST.Store_MicroService.dto.ProductDto;
import com.UST.Store_MicroService.dto.UpdateProductDto;
import com.UST.Store_MicroService.model.Request;
import com.UST.Store_MicroService.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
public class RequestService {
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private StoreService storeService;
    @Autowired
    private WebClient.Builder webClientBuilder;

    public String raiseRequest(Request request) {
        if (requestRepository.save(request) == null) {
            throw new RuntimeException("Error while Raising request");
        }
        return "Request raised Successfully";

    }
    public List<Request> getAllRequests(String companyId) {
        return requestRepository.getAllRequests(companyId);
    }
    public List<Request> getAllRequestsForStore(String storeId ) {
        return requestRepository.getAllRequestsByStore(storeId);
    }

    public Request updateRequest(Request request) {
        Request exs = requestRepository.getById(request.getRequestId());
        exs.setStatus("Restocked");
        UpdateProductDto updateProductDto = new UpdateProductDto();
        updateProductDto.setProductId(request.getProductId());
        updateProductDto.setQuantity(request.getQuantity());
        updateProductDto.setProductName(request.getProductName());
        updateProductDto.setStoreId(request.getStoreId());
        updateProductDto.setStoreName(request.getStoreName());
        storeService.updateQuantity(updateProductDto);
        ProductDto productDto = new ProductDto();
        productDto.setProductId(request.getProductId());
        productDto.setProductName(request.getProductName());
        productDto.setQuantity(Long.valueOf(request.getQuantity()));
        Optional<Object> object = WebClient.builder()
                .baseUrl("http://localhost:9091")
                .build()
                .put()
                .uri("/api/products/UpdateQuantityById") // Direct URI without query parameters
                .bodyValue(productDto) // Attach the DTO as the body
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Optional<Object>>() {})
                .block();
        return requestRepository.save(exs);
    }
}
