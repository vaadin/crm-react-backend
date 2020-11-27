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

@RestController
public class ContactController {
  @Autowired
  ContactService contactService;

  @GetMapping("/contacts")
  public ResponseEntity<Object> getContacts(@RequestParam(required=false, value="search") String search) {
    return new ResponseEntity<>(contactService.findAll(search), HttpStatus.OK);
  }

  @PostMapping("/contact")
  public void addContact(@RequestBody Contact contact) {
    contactService.save(contact);
  }

  @PutMapping("/contact/{id}")
  public void updateContact(@PathVariable(value="id") Long id, @RequestBody Contact contact) {
    contactService.update(id, contact);
  }

  @DeleteMapping("/contact/{id}")
  public void deleteContact(@PathVariable(value="id") Long id) {
    contactService.delete(id);
  }
}
