package com.vaadin.tutorial.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vaadin.tutorial.crm.service.CompanyService;

@RestController
public class CompanyController {
  @Autowired
  CompanyService companyService;

  @RequestMapping(value="/company")
  public ResponseEntity<Object> getCompanies() {
    return new ResponseEntity<>(companyService.getStats(), HttpStatus.OK);
  }
}
