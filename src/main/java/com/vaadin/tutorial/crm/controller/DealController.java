package com.vaadin.tutorial.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vaadin.tutorial.crm.entity.Deal;
import com.vaadin.tutorial.crm.service.DealService;

import java.io.IOException;

@RestController
public class DealController {
  @Autowired
  DealService dealService;

  @GetMapping("/deals")
  public ResponseEntity<Object> getDeals() {
    return new ResponseEntity<>(dealService.findAll(), HttpStatus.OK);
  }

  @PostMapping("/deal")
  public ResponseEntity<String> addDeal(@RequestBody Deal deal) {
    try {
      dealService.save(deal);
      return new ResponseEntity<>("success", HttpStatus.OK);
    } catch (IOException e) {
      return new ResponseEntity<>("failure", HttpStatus.BAD_REQUEST);
    }
  }

  @PutMapping("/deal/{id}")
  public ResponseEntity<Object> updateDeal(@PathVariable(value="id") Long id, @RequestBody Deal deal) {
    try {
      dealService.update(id, deal);
      return new ResponseEntity<>(dealService.findAll(), HttpStatus.OK);
    } catch (IOException e) {
      return new ResponseEntity<>("failure", HttpStatus.BAD_REQUEST);
    }
  }
}
