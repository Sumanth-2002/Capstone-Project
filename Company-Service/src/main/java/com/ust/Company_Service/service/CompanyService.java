package com.ust.Company_Service.service;

import com.ust.Company_Service.model.Company;
import com.ust.Company_Service.model.CompanyDto;
import com.ust.Company_Service.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private EmailService emailService;

    public Company addCompany(CompanyDto companyDto) {
        // Save the company
        CompanyDto companyDto1 = new CompanyDto();
        Company  company = new Company();
        company.setGSTIN(companyDto1.getGSTIN());
        company.setUIN(companyDto1.getUIN());
        company.setName(companyDto1.getName());
        company.setEmail(companyDto1.getEmail());
        Company savedCompany = companyRepository.save(company);

        // Send welcome email
        try {
            emailService.sendWelcomeEmail(savedCompany.getEmail(), savedCompany.getCompanyId());
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
        }

        return savedCompany;
    }
}
