package com.vaadin.tutorial.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaadin.tutorial.crm.service.DealService;

@RestController
public class DealController {
  @Autowired
  DealService dealService;

  @GetMapping("/deals")
  public ResponseEntity<Object> getDeals() {
    return new ResponseEntity<>(dealService.findAll(), HttpStatus.OK);
  }
}
