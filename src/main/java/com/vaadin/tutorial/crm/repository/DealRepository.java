package com.vaadin.tutorial.crm.repository;

import com.vaadin.tutorial.crm.entity.Deal;
import com.vaadin.tutorial.crm.model.ComplexDealDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface DealRepository extends JpaRepository<Deal, Long> {
  @Query("SELECT DISTINCT NEW "
        + "com.vaadin.tutorial.crm.model.ComplexDealDTO("
        + "d.id, d.name, d.price, d.status, d.user, c.id, c.name) "
        + "FROM Deal d LEFT JOIN d.company c LEFT JOIN d.dealContacts dc "
        + "where ((:companies) is null or d.company.id in (:companies)) "
        + "and ((:contacts) is null or dc.contact.id in (:contacts)) "
        + "and ((:users) is null or d.user.id in (:users)) "
        + "and (d.price >= :minPrice) and (:maxPrice = -1.0 or d.price <= :maxPrice) "
        + "and (:isActive is null or d.status in ('New', 'ProposalSent'))"
    )
  List<ComplexDealDTO> search(
    @Param("companies") List<Long> companies,
    @Param("contacts") List<Long> contacts,
    @Param("users") List<Long>users,
    @Param("minPrice") Double minPrice,
    @Param("maxPrice") Double maxPrice,
    @Param("isActive") String isActive);
}
