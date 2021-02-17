package com.vaadin.tutorial.crm.model;

import com.vaadin.tutorial.crm.entity.User;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;

public class NoteDTO {
  public Long id;
  public String text;
  public Date createdAt;
  public Map<String, Object> user;

  public NoteDTO(
    final Long id,
    final String text,
    final Date createdAt,
    final Long userId,
    final String userName
  ) {
    this.id = id;
    this.text = text;
    this.createdAt = createdAt;

    this.user = new HashMap();
    this.user.put("id", userId);
    this.user.put("name", userName);
  }
}
