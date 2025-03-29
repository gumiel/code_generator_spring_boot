package com.gumiel.code_generator.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "product")
@Schema( name = "Entity Product (Producto)")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Column(length = 30, nullable = false, name = "code")
  private String code;
  @Column(length = 100, nullable = false, name = "name")
  private String name;
  @Column(length = 500, nullable = false, name = "description")
  private String description;
  @Column(name = "unit_measurement")
  private int unitMeasurement;
  @Column(name = "approximate_unit_price")
  private BigDecimal approximateUnitPrice;
}
