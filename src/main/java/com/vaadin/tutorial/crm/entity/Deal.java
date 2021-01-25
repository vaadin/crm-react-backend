package com.vaadin.tutorial.crm.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Deal extends AbstractEntity {

  public enum Status {
    New, ProposalSent, ClosedWon, ClosedLost
  }

  @NotNull
  @NotEmpty
  private String name;

  @NotNull
  private Double price;

  @ManyToOne()
  @JoinColumn(name = "deal_company")
  private Company company4d;

  @ManyToOne()
  @JoinColumn(name = "deal_user")
  private User user;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "deal")
  private List<Note> notes = new LinkedList<>();

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "deal4r")
  private List<Role> roles = new LinkedList<>();

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

  public Company getCompany() {
    return company4d;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public User getUser() {
    return user;
  }

  public List<Note> getNotes() {
    return notes;
  }

  public List<Role> getRoles() {
    return roles;
  }
}
