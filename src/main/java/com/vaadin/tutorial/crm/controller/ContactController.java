package com.vaadin.tutorial.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaadin.tutorial.crm.service.ContactService;

@RestController
public class ContactController {
  @Autowired
  ContactService contactService;

  @GetMapping("/contacts")
  public ResponseEntity<Object> getContacts() {
    return new ResponseEntity<>(contactService.findAll(), HttpStatus.OK);
  }
}
