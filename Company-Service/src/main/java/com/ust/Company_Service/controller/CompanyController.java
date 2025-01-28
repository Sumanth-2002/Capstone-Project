package com.ust.Company_Service.controller;

import com.ust.Company_Service.model.Company;
import com.ust.Company_Service.model.CompanyDto;
import com.ust.Company_Service.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/company")
@CrossOrigin("*")
public class CompanyController {

    @Autowired
    private CompanyService companyService;
    @PostMapping("/register")
    public ResponseEntity<Company> addCompany(@RequestBody CompanyDto companyDto) {
        return ResponseEntity.ok(companyService.addCompany(companyDto));
    }

    @GetMapping("/getCompanyDetails/{companyId}")
    public ResponseEntity<Company> getCompanyDetails(@PathVariable String companyId) {
        return ResponseEntity.ok(companyService.getCompanyById(companyId));
    }

}
