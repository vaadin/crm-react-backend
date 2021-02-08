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
import java.util.List;
import java.util.Arrays;
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
    List<String> companies = company == null ? null : Arrays.asList(company.split(","));
    String contact = all.get("contact");
    List<String> contacts = contact == null ? null : Arrays.asList(contact.split(","));
    String user = all.get("user");
    List<String> users = user == null ? null : Arrays.asList(user.split(","));
    String min = all.get("min");
    Double minPrice = (min == null) ? 0 : Double.parseDouble(min);
    String max = all.get("max");
    Double maxPrice = (max == null) ? -1 : Double.parseDouble(max);
    String isActive = all.get("active");
    return new ResponseEntity<>(dealService.findAll(companies, contacts, users, minPrice, maxPrice, isActive), HttpStatus.OK);
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
      Map<String, Object> updatedDeal = dealService.update(id, deal);
      if (updatedDeal.get("id") != "0") {
        return new ResponseEntity<>(updatedDeal, HttpStatus.OK);
      }
      return new ResponseEntity<>("No such Item", HttpStatus.BAD_REQUEST);
    } catch (IOException e) {
      return new ResponseEntity<>("failure", HttpStatus.BAD_REQUEST);
    }
  }
}
