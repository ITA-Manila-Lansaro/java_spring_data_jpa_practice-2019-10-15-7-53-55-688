package com.tw.apistackbase.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tw.apistackbase.Entity.Company;
import com.tw.apistackbase.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CompanyController.class)
@ActiveProfiles(profiles = "test")
class CompanyControllerTest {

    @Autowired
    private CompanyController companyController;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CompanyService companyService;

    @Autowired
    private ObjectMapper objectMapper;

    private List<Company> companyList = new ArrayList<>();

    @Test
    void should_return_list_of_company_when_list_triggered() throws Exception {
        companyList.add(new Company());
        companyList.add(new Company());

            when(companyService.findAllWithPagination( 0, 5)).thenReturn(companyList);

        ResultActions result = mvc.perform(get("/companies/all"));
        //then
        result.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void should_add_company_when_add_is_triggered() throws Exception {
        companyList.add(new Company());
        companyList.add(new Company());

        ResultActions result = mvc.perform(post("/companies")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(companyList)));
        //then
        result.andExpect(status().isCreated());
    }

    @Test
    void getCompanyById() throws Exception {
        Company company = new Company();
        company.setId(1L);

        companyList.add(company);
        companyList.add(new Company());

        when(companyService.findCompanyById(1L)).thenReturn(companyList.get(0));

        ResultActions result = mvc.perform(get("/companies/1"));
        //then
        result.andExpect(status().isOk());
    }

    @Test
    void updateCompany() throws Exception {
        Company company = new Company();
        company.setId(1L);

        ResultActions result = mvc.perform(patch("/companies")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(company)));
        //then
        result.andExpect(status().isOk());
    }

    @Test
    void deleteCompany() throws Exception {
        Company company = new Company();
        company.setId(1L);

        ResultActions result = mvc.perform(delete("/companies/1")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(company)));
        //then
        result.andExpect(status().isOk());
    }

    @Test
    void getCompanyUsingQuery() throws Exception {
        Company company = new Company();
        company.setId(1L);

        companyList.add(company);
        companyList.add(new Company());

        when(companyService.getCompanyUsingQuery(1L)).thenReturn(companyList.get(0));

        ResultActions result = mvc.perform(get("/companies/byQuery/1"));
        //then
        result.andExpect(status().isOk());
    }

    @Test
    void findCompanyByNameContaining() throws Exception{
        Company company = new Company();
        company.setId(1L);
        company.setName("najib");

        companyList.add(company);
        companyList.add(new Company());

        when(companyService.findCompanyByNameContaining("naj")).thenReturn(companyList);

        ResultActions result = mvc.perform(get("/companies?name=naj"));
        //then
        result.andExpect(status().isOk());
    }

}