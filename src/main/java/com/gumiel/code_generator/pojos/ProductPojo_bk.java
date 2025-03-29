package com.gumiel.code_generator.pojos;

import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class ProductPojo_bk {
private Integer id;
private String code;
private String name;
private String description;
private Integer unitMeasurementId;
private BigDecimal approximateUnitPrice;
}
