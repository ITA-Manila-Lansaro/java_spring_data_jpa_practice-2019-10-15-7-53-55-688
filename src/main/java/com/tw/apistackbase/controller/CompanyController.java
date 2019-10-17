package com.tw.apistackbase.controller;

import com.tw.apistackbase.core.Company;
import com.tw.apistackbase.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.CompositeName;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping(value = "/all" , produces = {"application/json"})
    public Iterable<Company> list() {
        return companyRepository.findAll();
    }

    @PostMapping(produces = {"application/json"})
    public HttpEntity add(@RequestBody Company company) {
        companyRepository.save(company);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value= "/{id}", produces = {"application/json"})
    public Company getCompanyById (@PathVariable Long id){
        return companyRepository.findCompanyById(id);
    }

    @PatchMapping(produces = {"application/json"})
    public HttpEntity updateCompany (@RequestBody Company company){
        companyRepository.findById(company.getId()).ifPresent(a -> {
            a.setName(company.getName());
            companyRepository.save(a);
        });
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}" , produces = {"application/json"})
    public HttpEntity deleteCompany (@PathVariable Long id){
        companyRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/byQuery/{id}")
    public Company getCompanyUsingQuery (@PathVariable Long id){
        return companyRepository.findCompanyUsingQuery(id);
    }

    @GetMapping(produces = {"application/json"})
    public Iterable<Company> getCompanyById (@RequestParam String name){
        return companyRepository.findCompanyByNameContaining   (name);
    }

}
