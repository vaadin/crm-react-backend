package com.vaadin.tutorial.crm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

  public Note() {
  }

  public Note(String text, Timestamp created_at, Deal deal) {
    setText(text);
    setCreatedAt(created_at);
    setDeal(deal);
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

  @JsonIgnore
  public Deal getDeal() {
    return deal;
  }

  public void setDeal(Deal deal) {
    this.deal = deal;
  }
}
