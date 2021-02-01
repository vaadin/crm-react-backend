package com.vaadin.tutorial.crm.model;

public class CompanyDealSummaryDTO {
  public Long id;
  public String name;
  public String country;
  public String address;
  public String zipcode;
  public String state;
  public Long dealCount;
  public Double dealTotal;

  public CompanyDealSummaryDTO(
    final Long id,
    final String name,
    final String country,
    final String address,
    final String zipcode,
    final String state,
    final Long dealCount,
    final Double dealTotal
  ) {
    this.id = id;
    this.name = name;
    this.country = country;
    this.address = address;
    this.zipcode = zipcode;
    this.state = state;
    this.dealCount = dealCount;
    this.dealTotal = dealTotal;
  }
}
