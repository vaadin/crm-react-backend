package com.vaadin.tutorial.crm.repository;

import com.vaadin.tutorial.crm.entity.Note;
import com.vaadin.tutorial.crm.model.NoteDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
  @Query("SELECT DISTINCT NEW "
        + "com.vaadin.tutorial.crm.model.NoteDTO("
        + "n.id, n.text, n.created_at, n.user.id, n.user.name) "
        + "FROM Note n "
        + "WHERE :deal is null or CAST(n.deal.id as string) = :deal "
        + "ORDER BY n.created_at DESC"
  )
  List<NoteDTO> search(@Param("deal") String deal);
}
