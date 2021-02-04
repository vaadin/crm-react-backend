package com.vaadin.tutorial.crm.repository;

import com.vaadin.tutorial.crm.entity.Deal;
import com.vaadin.tutorial.crm.model.ComplexDealDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface DealRepository extends JpaRepository<Deal, Long> {
  @Query("SELECT DISTINCT NEW "
            + "com.vaadin.tutorial.crm.model.ComplexDealDTO("
            + "d.id, d.name, d.price, d.status, d.user, c.id, c.name) "
            + "FROM Deal d LEFT JOIN d.company c LEFT JOIN d.dealContacts dc "
            + "where ((?1) is null or d.company in (?1)) "
            + "and ((?2) is null or dc.contact.id in (?2)) "
            + "and ((?3) is null or d.user in (?3)) "
            + "and (d.price >= ?4) and (?5 = -1.0 or d.price <= ?5) "
            + "and (?6 is null or d.status in ('New', 'ProposalSent'))"
    )
  List<ComplexDealDTO> search(List<String> companies, List<String> contacts, List<String>users, Double minPrice, Double maxPrice, String isActive);
}
