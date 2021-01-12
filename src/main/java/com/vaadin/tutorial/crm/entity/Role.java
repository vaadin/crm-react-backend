package com.vaadin.tutorial.crm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;

@Entity
public class Role extends AbstractEntity {

  public enum Roles {
    DecisionMaker, Consulted, Informed
  }

  @NotNull
  @NotEmpty
  private Long contact_id;

  @NotNull
  @NotEmpty
  private String contact_name;

  @Enumerated(EnumType.STRING)
  @NotNull
  private Role.Roles role;

  @ManyToOne()
  @JoinColumn(name = "deal_role")
  private Deal deal4r;

  public Role() {
  }

  public Role(Long contact_id, String contact_name, Role.Roles role, Deal deal4r) {
    setContactId(contact_id);
    setContactName(contact_name);
    setContactRole(role);
    setDeal(deal4r);
  }

  public Long getContactId() {
    return contact_id;
  }

  public void setContactId(Long contact_id) {
    this.contact_id = contact_id;
  }

  public String getContactName() {
    return contact_name;
  }

  public void setContactName(String contact_name) {
    this.contact_name = contact_name;
  }

  public Roles getContactRole() {
    return role;
  }

  public void setContactRole(Roles role) {
    this.role = role;
  }

  @JsonIgnore
  public Deal getDeal() {
    return deal4r;
  }

  public void setDeal(Deal deal4r) {
    this.deal4r = deal4r;
  }
}
