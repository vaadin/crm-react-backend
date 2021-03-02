package com.vaadin.tutorial.crm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import java.util.List;
import java.util.stream.*;
import java.io.IOException;

import com.vaadin.tutorial.crm.entity.Deal;
import com.vaadin.tutorial.crm.service.DealService;

@RestController
public class DealController {
  DealService dealService;

  DealController(DealService dealService) {
    this.dealService = dealService;
  }

  @GetMapping("/deals")
  public ResponseEntity<Object> getDeals(@RequestParam(required=false) Map<String, String> all) {
    String company = all.get("company");
    List<Long> companies = company == null ? null : Stream.of(company.split(",")).map(Long::valueOf).collect(Collectors.toList());
    String contact = all.get("contact");
    List<Long> contacts = contact == null ? null : Stream.of(contact.split(",")).map(Long::valueOf).collect(Collectors.toList());
    String user = all.get("user");
    List<Long> users = user == null ? null : Stream.of(user.split(",")).map(Long::valueOf).collect(Collectors.toList());
    String min = all.get("min");
    Double minPrice = (min == null) ? 0 : Double.parseDouble(min);
    String max = all.get("max");
    Double maxPrice = (max == null) ? -1 : Double.parseDouble(max);
    String isActive = all.get("active");
    return new ResponseEntity<>(dealService.findAll(companies, contacts, users, minPrice, maxPrice, isActive), HttpStatus.OK);
  }

  @GetMapping("/deal-contacts")
  public ResponseEntity<Object> getDealContacts(@RequestParam(required=false, value="deal") String deal) {
    return new ResponseEntity<>(dealService.findDealContacts(deal), HttpStatus.OK);
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

  @DeleteMapping("/deal/{id}")
  public ResponseEntity<Object> deleteDeal(@PathVariable(value="id") Long id) {
    try {
      dealService.delete(id);
      return new ResponseEntity<>("success", HttpStatus.OK);
    } catch (IOException e) {
      return new ResponseEntity<>("failure", HttpStatus.BAD_REQUEST);
    }
  }
}
