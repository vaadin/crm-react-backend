package com.vaadin.tutorial.crm.model;

import com.vaadin.tutorial.crm.entity.Deal;
import com.vaadin.tutorial.crm.entity.User;
import java.util.Map;
import java.util.HashMap;

public class ComplexDealDTO {
  public Long id;
  public String name;
  public Double price;
  public Deal.Status status;
  public User user;
  public Map<String, Object> company;

  public ComplexDealDTO(
    final Long id,
    final String name,
    final Double price,
    final Deal.Status status,
    final User user,
    final Long companyID,
    final String companyName
  ) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.status = status;
    this.user = user;

    company = new HashMap();
    company.put("id", companyID);
    company.put("name", companyName);
  }
}
