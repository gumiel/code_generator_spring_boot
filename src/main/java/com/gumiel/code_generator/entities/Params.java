package com.gumiel.code_generator.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "config")
public class Params {
  @Id
  private Integer id;
  @Column(length = 30, nullable = false)
  private String code;
  @Column(name = "param_value", length = 100, nullable = false)
  private String paramValue;
  @Column(length = 500, nullable = false)
  private String description;
  @Column(name = "active")
  private Boolean active;
}