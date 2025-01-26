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
    @Autowired
    private EmailService emailService;

    public Company addCompany(Company company) {
        // Save the company
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
