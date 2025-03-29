package com.gumiel.code_generator.filters;

import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.ToString;
import lombok.NoArgsConstructor;

@Builder
@ToString
@Getter
public class ProductFilter_bk {
private Integer id;
private String code;
private String name;
private String description;
private Integer unitMeasurementId;
private BigDecimal approximateUnitPrice;
private String search;
}
