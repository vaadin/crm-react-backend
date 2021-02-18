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

import com.vaadin.tutorial.crm.entity.Contact;
import com.vaadin.tutorial.crm.service.ContactService;

import java.util.Map;
import java.io.IOException;

@RestController
public class ContactController {
  @Autowired
  ContactService contactService;

  @GetMapping("/contacts")
  public ResponseEntity<Object> getContacts(@RequestParam(required=false)  Map<String, String> all) {
    String company = all.get("company");
    String name = all.get("search");
    return new ResponseEntity<>(contactService.findAll(company, name), HttpStatus.OK);
  }

  @PostMapping("/contact")
  public ResponseEntity<String> addContact(@RequestBody Contact contact) {
    try {
      contactService.save(contact);
      return new ResponseEntity<>("success", HttpStatus.OK);
    } catch (IOException e) {
      return new ResponseEntity<>("failure", HttpStatus.BAD_REQUEST);
    }
  }

  @PutMapping("/contact/{id}")
  public ResponseEntity<String> updateContact(@PathVariable(value="id") Long id, @RequestBody Contact contact) {
    try {
      contactService.update(id, contact);
      return new ResponseEntity<>("success", HttpStatus.OK);
    } catch (IOException e) {
      return new ResponseEntity<>("failure", HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping("/contact/{id}")
  public void deleteContact(@PathVariable(value="id") Long id) {
    contactService.delete(id);
  }
}
