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

        Company  company = new Company();
        company.setGSTIN(companyDto.getGSTIN());
        company.setUIN(companyDto.getUIN());
        company.setName(companyDto.getName());
        company.setEmail(companyDto.getEmail());
        Company savedCompany = companyRepository.save(company);

        // Send welcome email
        try {
            emailService.sendWelcomeEmail(savedCompany.getEmail(), savedCompany.getCompanyId());
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
        }

        return savedCompany;
    }
    public Company getCompanyById(String companyId) {
        return companyRepository.findById(companyId).get();
    }
}
