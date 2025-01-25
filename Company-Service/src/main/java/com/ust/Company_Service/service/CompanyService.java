package com.ust.Company_Service.service;

import com.ust.Company_Service.model.Company;
import com.ust.Company_Service.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public Company addCompany(Company company){
//        company.setCompanyId(UUID.randomUUID().toString());

        return companyRepository.save(company);
    }
}
