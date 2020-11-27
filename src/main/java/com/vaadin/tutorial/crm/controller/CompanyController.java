package com.vaadin.tutorial.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaadin.tutorial.crm.service.CompanyService;

@RestController
public class CompanyController {
  @Autowired
  CompanyService companyService;

  @GetMapping("/company-info")
  public ResponseEntity<Object> getCompanyInfo() {
    return new ResponseEntity<>(companyService.getStats(), HttpStatus.OK);
  }

  @GetMapping("/companies")
  public ResponseEntity<Object> getCompanies() {
    return new ResponseEntity<>(companyService.findAll(), HttpStatus.OK);
  }
}
