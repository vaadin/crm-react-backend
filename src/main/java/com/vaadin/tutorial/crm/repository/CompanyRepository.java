package com.vaadin.tutorial.crm.repository;

import com.vaadin.tutorial.crm.entity.Company;
import com.vaadin.tutorial.crm.model.CompanyDealSummaryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Query("SELECT NEW "
            + "com.vaadin.tutorial.crm.model.CompanyDealSummaryDTO("
            + "c.id, c.name, c.country, c.address, c.zipcode, c.state, count(d.id), sum(d.price)) "
            + "FROM Company c LEFT JOIN c.deals d "
            + "WHERE (:searchTerm is null) or (lower(c.name) like lower(concat('%', :searchTerm, '%'))) "
            + "GROUP BY c.id"
        )
    List<CompanyDealSummaryDTO> search(@Param("searchTerm") String searchTerm);
}
