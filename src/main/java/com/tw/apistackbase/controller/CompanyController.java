package com.tw.apistackbase.controller;

import com.tw.apistackbase.core.Company;
import com.tw.apistackbase.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.naming.CompositeName;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping(produces = {"application/json"})
    public Iterable<Company> list() {
        return companyRepository.findAll();
    }

    @PostMapping(produces = {"application/json"})
    public Company add(@RequestBody Company company) {
        return companyRepository.save(company);
    }

    @GetMapping(value= "/{id}", produces = {"application/json"})
    public Company getCompanyById (@PathVariable Long id){
        return companyRepository.findCompanyById(id);
    }

    @PutMapping(produces = {"application/json"})
    public Company updateCompany (@RequestBody Company company){
        return companyRepository.save(company);
    }

    @ResponseStatus(HttpStatus.GONE)
    @DeleteMapping(value = "/{id}" , produces = {"application/json"})
    public void deleteCompany (@PathVariable Long id){
        companyRepository.deleteById(id);
    }



}
