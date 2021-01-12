package com.vaadin.tutorial.crm.repository;

import com.vaadin.tutorial.crm.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
