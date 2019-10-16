package com.tw.apistackbase.repository;

import com.tw.apistackbase.core.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface CompanyRepository extends JpaRepository<Company, Long>{
    Company findCompanyById(Long id);
}
