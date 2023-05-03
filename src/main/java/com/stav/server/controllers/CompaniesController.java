package com.stav.server.controllers;

import com.stav.server.beans.Company;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompaniesController {

    @PostMapping
    public void createCompany(@RequestBody Company company) {
        System.out.println(company);
    }

    @PutMapping
    public void updateCompany(@RequestBody Company company) {
        System.out.println(company);
    }

    @GetMapping("{companyId}")
    public Company getCompany(@PathVariable("companyId")int id) {
        System.out.println(id);
        Company company = new Company(123, "Coca Cola", "050-8887733", "Benei Brak");
        return company;
    }

    @GetMapping
    public List<Company> getAllCompanies() {

        Company company1 = new Company(123, "Coca Cola", "050-8887733", "Benei Brak");
        Company company2 = new Company(124, "Pepsi", "054-0011223", "Natanya");
        Company company3 = new Company(125, "Samsung", "050-0981241", "Petah Tikva");

        List<Company> companies = new ArrayList<>();
        companies.add(company1);
        companies.add(company2);
        companies.add(company3);
        
        return companies;
    }

    @DeleteMapping("{companyId}")
    public void deleteCompany(@PathVariable("companyId")int id) {

    }
}