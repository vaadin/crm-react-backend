package com.vaadin.tutorial.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vaadin.tutorial.crm.entity.Company;
import com.vaadin.tutorial.crm.service.CompanyService;

@RestController
public class CompanyController {
  @Autowired
  CompanyService companyService;

  @GetMapping("/countries")
  public ResponseEntity<Object> getCountries() {
    return new ResponseEntity<>(companyService.getCountries(), HttpStatus.OK);
  }

  @GetMapping("/company-info")
  public ResponseEntity<Object> getCompanyInfo() {
    return new ResponseEntity<>(companyService.getStats(), HttpStatus.OK);
  }

  @GetMapping("/companies")
  public ResponseEntity<Object> getCompanies(@RequestParam(required=false, value="search") String search) {
    return new ResponseEntity<>(companyService.findAll(search), HttpStatus.OK);
  }

  @PostMapping("/company")
  public void addCompany(@RequestBody Company company) {
    companyService.save(company);
  }

  @PutMapping("/company/{id}")
  public void updateCompany(@PathVariable(value="id") Long id, @RequestBody Company company) {
    companyService.update(id, company);
  }

  @DeleteMapping("/company/{id}")
  public void deleteCompany(@PathVariable(value="id") Long id) {
    companyService.delete(id);
  }
}
