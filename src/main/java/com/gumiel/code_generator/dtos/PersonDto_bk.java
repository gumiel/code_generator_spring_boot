package com.gumiel.code_generator.dtos;

import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto_bk {
private Integer id;
private String lastaName;
private String firstName;
private Integer age;
private String gender;
private Boolean disability;
private LocalDate birthdate;
private LocalDate registrationDate;
private LocalTime registrationTime;
private LocalDateTime endDateTime;
private Boolean active;
}
