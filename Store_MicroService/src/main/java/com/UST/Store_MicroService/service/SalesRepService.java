package com.UST.Store_MicroService.service;

import com.UST.Store_MicroService.model.SalesRep;
import com.UST.Store_MicroService.repository.SalesRepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalesRepService {
    @Autowired
    private SalesRepRepository salesRepRepository;

    public  String addSalesRep(SalesRep salesRep) {
        if(salesRepRepository.save(salesRep)==null) throw  new RuntimeException("Error while adding Sales Representative");
        return "Sales Representative Added Successfully";
    }

}
