package com.tw.apistackbase.repository;

import com.tw.apistackbase.Entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeesRepository extends JpaRepository<Employees, Long> {

}
