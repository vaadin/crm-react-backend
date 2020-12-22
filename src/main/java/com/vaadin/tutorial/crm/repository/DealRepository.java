package com.vaadin.tutorial.crm.repository;

import com.vaadin.tutorial.crm.entity.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealRepository extends JpaRepository<Deal, Long> {
}
