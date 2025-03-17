package com.gumiel.code_generator.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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
  private String lastaName;
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

}
