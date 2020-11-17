package com.vaadin.tutorial.crm.repository;

import com.vaadin.tutorial.crm.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
