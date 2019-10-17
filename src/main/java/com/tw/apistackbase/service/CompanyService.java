package com.tw.apistackbase.service;

import com.tw.apistackbase.Entity.Company;
import com.tw.apistackbase.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    public Iterable<Company> findAllWithPagination (Integer page, Integer pageSize){
        return companyRepository.findAll(PageRequest.of(page, pageSize));
    }

    public HttpEntity addAll (List<Company> companyList){
        companyList.forEach(a -> companyRepository.save(a));
        return new ResponseEntity(HttpStatus.OK);
    }

    public Company findCompanyById (Long id){
        return companyRepository.findCompanyById(id);
    }


}
