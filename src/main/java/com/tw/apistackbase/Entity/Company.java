package com.tw.apistackbase.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name ="tb_company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO  ,generator = "seq")

    private Long id;

    @Column(name ="tb_name")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private CompanyProfile profile;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Employees> employees;


    public List<Employees> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employees> employees) {
        this.employees = employees;
    }

    public CompanyProfile getProfile() {
        return profile;
    }

    public void setProfile(CompanyProfile companyProfile) {
        this.profile = companyProfile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Company() {
    }

    public Company(String name) {
        this.name = name;
    }
}
