package com.tw.apistackbase.repository;

import com.tw.apistackbase.Entity.Company;
import com.tw.apistackbase.Entity.CompanyProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyProfileRepository extends JpaRepository <CompanyProfile, Long> {
    Company findNameById(Long id);
}
