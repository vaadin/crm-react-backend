package com.vaadin.tutorial.crm.repository;

import com.vaadin.tutorial.crm.entity.DealContact;
import com.vaadin.tutorial.crm.entity.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import javax.transaction.Transactional;

@Transactional
public interface DealContactRepository extends JpaRepository<DealContact, Long> {
  Long deleteByDeal(Deal deal);
}
