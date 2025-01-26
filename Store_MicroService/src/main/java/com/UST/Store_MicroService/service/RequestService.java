package com.UST.Store_MicroService.service;

import com.UST.Store_MicroService.model.Request;
import com.UST.Store_MicroService.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {
    @Autowired
    private RequestRepository requestRepository;

    public String raiseRequest(Request request) {
        if (requestRepository.save(request) == null) {
            throw new RuntimeException("Error while Raising request");
        }
        return "Request raised Successfully";

    }
    public List<Request> getAllRequests(String companyId) {
        return requestRepository.getAllRequests(companyId);
    }
}
