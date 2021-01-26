package com.vaadin.tutorial.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import java.io.IOException;

import com.vaadin.tutorial.crm.entity.Deal;
import com.vaadin.tutorial.crm.service.DealService;

@RestController
public class DealController {
  @Autowired
  DealService dealService;

  @GetMapping("/deals")
  public ResponseEntity<Object> getDeals(@RequestParam(required=false) Map<String, String> all) {
    String company = all.get("company");
    String contact = all.get("contact");
    String user = all.get("user");
    String min = all.get("min");
    String max = all.get("max");
    String isActive = all.get("active");
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
