package com.vaadin.tutorial.crm.repository;

import com.vaadin.tutorial.crm.entity.DealContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealContactRepository extends JpaRepository<DealContact, Long> {
}
