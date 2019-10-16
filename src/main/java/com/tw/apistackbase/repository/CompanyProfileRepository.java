package com.tw.apistackbase.repository;

import com.tw.apistackbase.core.Company;
import com.tw.apistackbase.core.CompanyProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyProfileRepository extends JpaRepository <CompanyProfile, Long> {
    Company findNameById(Long id);
}
