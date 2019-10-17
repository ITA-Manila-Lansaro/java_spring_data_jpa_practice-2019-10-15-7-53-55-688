package com.tw.apistackbase.controller;

import com.tw.apistackbase.Entity.Company;
import com.tw.apistackbase.repository.CompanyRepository;
import com.tw.apistackbase.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping(value = "/all" , produces = {"application/json"})
    public Iterable<Company> list(@RequestParam (required = false, defaultValue = "0") Integer page,
                                    @RequestParam (required = false, defaultValue = "5") Integer pageSize) {
        return companyService.findAllWithPagination(page,pageSize);
    }

    @PostMapping(produces = {"application/json"})
    public HttpEntity add(@RequestBody List<Company> companyList) {
        return companyService.addAll(companyList);
    }

    @GetMapping(value= "/{id}", produces = {"application/json"})
    public Company getCompanyById (@PathVariable Long id){
        return companyService.findCompanyById(id);
    }

    @PatchMapping(produces = {"application/json"})
    public HttpEntity updateCompany (@RequestBody Company company){
        return companyService.updateCompany(company);
    }

    @DeleteMapping(value = "/{id}" , produces = {"application/json"})
    public HttpEntity deleteCompany (@PathVariable Long id){
        return companyService.deleteCompany(id);
    }

    @GetMapping(value = "/byQuery/{id}")
    public Company getCompanyUsingQuery (@PathVariable Long id){
        return companyService.getCompanyUsingQuery(id);
    }

    @GetMapping(produces = {"application/json"})
    public Iterable<Company> getCompanyById (@RequestParam String name){
        return companyService.findCompanyByNameContaining(name);
    }

}
