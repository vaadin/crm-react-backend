package com.vaadin.tutorial.crm.repository;

import com.vaadin.tutorial.crm.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    Optional<Contact> findById(Long id);
    @Query("select c from Contact c " +
          "where lower(c.firstName) like lower(concat('%', :searchTerm, '%')) " +
          "or lower(c.lastName) like lower(concat('%', :searchTerm, '%'))")
    List<Contact> search(@Param("searchTerm") String searchTerm);
}
