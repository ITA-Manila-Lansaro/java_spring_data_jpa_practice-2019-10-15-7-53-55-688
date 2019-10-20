package com.tw.apistackbase.controller;

import com.tw.apistackbase.Entity.Company;
import com.tw.apistackbase.service.CompanyService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    public static final String COMPANY_NOT_FOUND = "Company Not found";
    @Autowired
    private CompanyService companyService;

    @GetMapping(value = "/all" , produces = {"application/json"})
    public Iterable<Company> list(@RequestParam (required = false, defaultValue = "0") Integer page,
                                    @RequestParam (required = false, defaultValue = "5") Integer pageSize) {
        return companyService.findAllWithPagination(page,pageSize);
    }

    @PostMapping(produces = {"application/json"})
    public HttpEntity add(@RequestBody List<Company> companyList) {
         companyService.addAll(companyList);
         return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping(value= "/{id}", produces = {"application/json"})
    public Company getCompanyById (@PathVariable Long id){
        return companyService.findCompanyById(id);
    }

    @PatchMapping(produces = {"application/json"})
    public HttpEntity updateCompany (@RequestBody Company company){
        companyService.updateCompany(company);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}" , produces = {"application/json"})
    public HttpEntity deleteCompany (@PathVariable Long id) throws NotFoundException {
        if (companyService.deleteCompany(id)){
            return new ResponseEntity(HttpStatus.OK);
        }
        throw new NotFoundException(COMPANY_NOT_FOUND);

    }

    @GetMapping(value = "/byQuery/{id}")
    public Company getCompanyUsingQuery (@PathVariable Long id) throws NotFoundException {
        if (companyService.checkIfExisting(id)){
        return companyService.getCompanyUsingQuery(id);
        }
        throw new NotFoundException(COMPANY_NOT_FOUND);
    }

    @GetMapping(produces = {"application/json"})
    public Iterable<Company> getCompanyByContaining (@RequestParam String name){
        return companyService.findCompanyByNameContaining(name);
    }

}
