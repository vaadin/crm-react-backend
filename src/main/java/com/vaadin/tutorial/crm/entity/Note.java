package com.vaadin.tutorial.crm.entity;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.sql.Timestamp;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;

@Entity
public class Note extends AbstractEntity {
  @NotEmpty
  private String text;

  @NotNull
  private Timestamp created_at;

  @ManyToOne()
  private Deal deal;

  @ManyToOne()
  private User user;

  public Note() {
  }

  public Note(String text, Timestamp created_at, Deal deal) {
    setText(text);
    setCreatedAt(created_at);
    setDeal(deal);
    setUser(user);
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Timestamp getCreatedAt() {
    return created_at;
  }

  public void setCreatedAt(Timestamp created_at) {
    this.created_at = created_at;
  }

  public Deal getDeal() {
    return deal;
  }

  public void setDeal(Deal deal) {
    this.deal = deal;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
