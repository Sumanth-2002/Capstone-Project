package com.ust.Company_Service.service;

import com.ust.Company_Service.model.Company;
import com.ust.Company_Service.model.CompanyDto;
import com.ust.Company_Service.model.LoginDto;
import com.ust.Company_Service.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;
import java.util.UUID;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private WebClient.Builder webClientBuilder;


    public Company addCompany(CompanyDto companyDto) {

        Company  company = new Company();
        company.setGSTIN(companyDto.getGSTIN());

        company.setName(companyDto.getName());
        company.setEmail(companyDto.getEmail());
        Company savedCompany = companyRepository.save(company);
        LoginDto loginDto  = new LoginDto();
        loginDto.setPassword(companyDto.getPassword());
        loginDto.setUserId(company.getCompanyId());
        loginDto.setName(companyDto.getName());
        Map<String,Object> object = WebClient.builder()
                .baseUrl("http://localhost:9093")
                .build()
                .post()
                .uri("/api/login/register") // Direct URI without query parameters
                .bodyValue(loginDto) // Attach the DTO as the body
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String,Object>>() {})
                .block();
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
