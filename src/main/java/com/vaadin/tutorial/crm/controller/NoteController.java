package com.vaadin.tutorial.crm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.Map;

import com.vaadin.tutorial.crm.entity.Note;
import com.vaadin.tutorial.crm.service.NoteService;

@RestController
public class NoteController {
  NoteService noteService;

  NoteController(NoteService noteService) {
    this.noteService = noteService;
  }

  @GetMapping("/notes")
  public ResponseEntity<Object> getNotes(@RequestParam(required=false, value="deal") String deal) {
    return new ResponseEntity<>(noteService.findByDeal(deal), HttpStatus.OK);
  }

  @PostMapping("/note")
  public ResponseEntity<String> addNote(@RequestBody Note note, @RequestHeader (name="Authorization") String token) {
    try {
      noteService.save(note, token);
      return new ResponseEntity<>("success", HttpStatus.OK);
    } catch (IOException e) {
      return new ResponseEntity<>("failure", HttpStatus.BAD_REQUEST);
    }
  }
}
