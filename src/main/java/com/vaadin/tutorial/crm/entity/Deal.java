package com.vaadin.tutorial.crm.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Deal extends AbstractEntity {

  public enum Status {
    New, ProoposalSent, ClosedWon, ClosedLost
  }

  @NotNull
  @NotEmpty
  private String name;

  @NotNull
  private Double price;

  @ManyToOne()
  @JoinColumn(name = "deal_id")
  private Company company4d;

  @Enumerated(EnumType.STRING)
  @NotNull
  private Deal.Status status;

  public Deal() {
  }

  public Deal(String name) {
    setName(name);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
      this.status = status;
  }

  public void setCompany4d(Company company4d) {
    this.company4d = company4d;
  }

  @JsonIgnore
  public Company getCompany() {
      return company4d;
  }
}
