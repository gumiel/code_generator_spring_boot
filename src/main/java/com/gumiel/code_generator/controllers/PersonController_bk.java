package com.gumiel.code_generator.controllers;

import com.gumiel.code_generator.commons.util.PagePojo;
import com.gumiel.code_generator.dtos.PersonDto_bk;
import com.gumiel.code_generator.entities.Person;
import com.gumiel.code_generator.filters.PersonFilter_bk;
import com.gumiel.code_generator.pojos.PersonPojo_bk;
import com.gumiel.code_generator.services.PersonService_bk;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/person_bk")
@Tag(name = "Person_bk", description = "Gestión de productos. Los productos son los unicos que podran ingresar o salir a un almacen")
public class PersonController_bk {

  private final PersonService_bk personService_bk;

  @Operation(summary = "Obtener todos los registros")
  @GetMapping
  public ResponseEntity<List<PersonPojo_bk>> getAll() {

    return ResponseEntity.status(HttpStatus.OK).body(
        personService_bk.getAll()
    );

  }

  @Operation(summary = "Creación del registro")
  @PostMapping
  public ResponseEntity<PersonPojo_bk> create(@Valid @RequestBody PersonDto_bk dto) {

    return ResponseEntity.status(HttpStatus.CREATED).body(
        personService_bk.create(dto)
    );

  }

  @Operation(summary = "Edición del registro")
  @PutMapping("/{id}")
  public ResponseEntity<PersonPojo_bk> update(@PathVariable Integer id,
      @Valid @RequestBody PersonDto_bk dto) {

    return ResponseEntity.status(HttpStatus.CREATED).body(
        personService_bk.update(id, dto)
    );

  }

  @Operation(summary = "Obtención de los datos del registro por el identificador")
  @GetMapping("/{id}")
  public ResponseEntity<PersonPojo_bk> getById(@PathVariable Integer id) {

    return ResponseEntity.status(HttpStatus.OK).body(
        personService_bk.getById(id)
    );

  }

  @Operation(summary = "Eliminación del registro por el identificador")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {

    personService_bk.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

  }

  @Operation(summary = "Deshabilitar del registro por el identificador")
  @DeleteMapping("/disabled/{id}")
  public ResponseEntity<Void> disabled(@PathVariable Integer id) {

    personService_bk.disabled(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

  }

  @Operation(summary = "Paginador y buscador de registros por atributos")
  @GetMapping("/pageable")
  public ResponseEntity<PagePojo<PersonPojo_bk>> pageable(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "5") int size,
      @RequestParam(defaultValue = "id") String sortField,
      @RequestParam(defaultValue = "asc") String sortOrder,
      @RequestParam(required = false) String search,
      @RequestParam(required = false) Integer id,
      @RequestParam(required = false) String lastaName,
      @RequestParam(required = false) String firstName,
      @RequestParam(required = false) Integer age,
      @RequestParam(required = false) String gender,
      @RequestParam(required = false) Boolean disability,
      @RequestParam(required = false) LocalDate birthdate,
      @RequestParam(required = false) LocalDate registrationDate,
      @RequestParam(required = false) LocalTime registrationTime,
      @RequestParam(required = false) LocalDateTime endDateTime,
      @RequestParam(required = false) Boolean active  ) {

    PersonFilter_bk filter = PersonFilter_bk.builder()
            .id(id)
            .lastaName(lastaName)
            .firstName(firstName)
            .age(age)
            .gender(gender)
            .disability(disability)
            .birthdate(birthdate)
            .registrationDate(registrationDate)
            .registrationTime(registrationTime)
            .endDateTime(endDateTime)
            .active(active)
            .search(search)
            .build();
    return ResponseEntity.status(HttpStatus.OK).body(
        personService_bk.pageable(page, size, sortField, sortOrder, filter)
    );

  }

}
