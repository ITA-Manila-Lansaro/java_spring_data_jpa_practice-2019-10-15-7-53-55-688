package com.tw.apistackbase.repository;

import com.tw.apistackbase.core.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CompanyRepository extends JpaRepository<Company, Long>{
    Company findCompanyById(Long id);

    @Query("SELECT c from Company c where c.id = :id")
    Company findCompanyUsingQuery (@Param("id") Long id);

    Iterable<Company> findCompanyByNameContaining(String name);
}
