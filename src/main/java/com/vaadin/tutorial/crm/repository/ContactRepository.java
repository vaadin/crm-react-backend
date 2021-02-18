package com.vaadin.tutorial.crm.repository;

import com.vaadin.tutorial.crm.entity.Contact;
import com.vaadin.tutorial.crm.model.ContactCompanyDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    @Query("SELECT NEW "
            + "com.vaadin.tutorial.crm.model.ContactCompanyDTO("
            + "c.id, c.firstName, c.lastName, c.email, c.status, cp.id, cp.name) "
            + "FROM Contact c LEFT JOIN c.company cp "
            + "WHERE (:searchTerm is null or lower(concat(c.firstName, ' ', c.lastName)) like lower(concat('%', :searchTerm, '%'))) "
            + "and (:company is null or CAST(cp.id as string) = :company)"
    )
    List<ContactCompanyDTO> search(
        @Param("company") String company,
        @Param("searchTerm") String searchTerm
    );
    boolean existsByEmail(String email);
}
