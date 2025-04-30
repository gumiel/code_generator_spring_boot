package com.gumiel.code_generator.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "configuration_parameter")
@Schema( name = "Entity Config (Configuraciones)")
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Column(length = 50, nullable = false)
  @Schema(description = "Nombres")
  private String lastName;
  @Schema(description = "Apellidos")
  @Column(length = 50, nullable = false)
  private String firstName;
  @Schema(description = "Edad")
  private Integer age;
  @Column(length = 100, nullable = false)
  @Schema(description = "Genero solo puede ser masculino o femenino")
  private String gender;
  @Schema(description = "Tiene discapacidad?")
  private Boolean disability;
  @Schema(description = "Fecha de nacimiento")
  private LocalDate birthdate;
  @Schema(description = "Fecha de registro")
  private LocalDate registrationDate;
  @Schema(description = "Hora de registro")
  private LocalTime registrationTime;
  @Schema(description = "Fecha y hora de finalizacion")
  private LocalDateTime endDateTime;
  @Schema(description = "valor para identificar si un registro esta eliminado de forma logica")
  private Boolean active;
  @Schema(description = "Estatura de la persona")
  private BigDecimal height;

}
