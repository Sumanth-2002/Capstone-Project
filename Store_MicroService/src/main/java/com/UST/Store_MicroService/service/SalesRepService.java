package com.UST.Store_MicroService.service;

import com.UST.Store_MicroService.model.SalesRep;
import com.UST.Store_MicroService.repository.SalesRepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesRepService {
    @Autowired
    private SalesRepRepository salesRepRepository;

    public  SalesRep addSalesRep(SalesRep salesRep) {
        SalesRep salesRep1 = salesRepRepository.save(salesRep);
        if(salesRep1==null) throw  new RuntimeException("Error while adding Sales Representative");
        return salesRep1;
    }
    public List<SalesRep> getAllSalesRep(String storeId) {
        return salesRepRepository.getAllSalesRep(storeId);
    }
}
