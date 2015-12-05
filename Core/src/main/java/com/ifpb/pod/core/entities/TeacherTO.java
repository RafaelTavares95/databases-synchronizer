/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ifpb.pod.core.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Classe que representa a entidade do professor
 * @author Rafael
 */
@Entity
@Table(name = "professor")
public class TeacherTO implements Serializable{
  @Id
  @Column(name = "codigo")
  private int code;
  @Column(name = "nome", nullable = false, length = 40)
  private String name;
  @Column(name = "abreviacao", nullable = false, length = 14)
  private String abbrev;
  @Column(name = "ativo")
  private boolean active;
  public int getCode() {
    return code;
  }
  public void setCode(int code) {
    this.code = code;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getAbbrev() {
    return abbrev;
  }
  public void setAbbrev(String abbrev) {
    this.abbrev = abbrev;
  }
  public boolean isActive() {
    return active;
  }
  public void setActive(boolean active) {
    this.active = active;
  }
  @Override
  public String toString() {
       return String.valueOf(this.code) + this.name + this.abbrev + String.valueOf(this.active);
  }
}
