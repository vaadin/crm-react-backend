package com.vaadin.tutorial.crm.repository;

import com.vaadin.tutorial.crm.entity.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface DealRepository extends JpaRepository<Deal, Long> {
  @Query("select d from Deal d " +
        "where ((?1) is null or d.company4d in (?1)) " +
        "and ((?2) is null or d.user in (?2)) " +
        "and (d.price >= ?3) and (?4 = -1.0 or d.price <= ?4) " +
        "and (?5 is null or d.status in ('New', 'ProposalSent'))")
  List<Deal> search(List<String> companies, List<String>users, Double minPrice, Double maxPrice, String isActive);
}
