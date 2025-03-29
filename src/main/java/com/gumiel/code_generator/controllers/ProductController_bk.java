package com.gumiel.code_generator.controllers;

import com.gumiel.code_generator.commons.util.PagePojo;
import com.gumiel.code_generator.dtos.ProductDto_bk;
import com.gumiel.code_generator.entities.Product;
import com.gumiel.code_generator.filters.ProductFilter_bk;
import com.gumiel.code_generator.pojos.ProductPojo_bk;
import com.gumiel.code_generator.services.ProductService_bk;
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
@RequestMapping("/product_bk")
@Tag(name = "Product_bk", description = "Gestión de productos. Los productos son los unicos que podran ingresar o salir a un almacen")
public class ProductController_bk {

  private final ProductService_bk productService_bk;

  @Operation(summary = "Obtener todos los registros")
  @GetMapping
  public ResponseEntity<List<ProductPojo_bk>> getAll() {

    return ResponseEntity.status(HttpStatus.OK).body(
        productService_bk.getAll()
    );

  }

  @Operation(summary = "Creación del registro")
  @PostMapping
  public ResponseEntity<ProductPojo_bk> create(@Valid @RequestBody ProductDto_bk dto) {

    return ResponseEntity.status(HttpStatus.CREATED).body(
        productService_bk.create(dto)
    );

  }

  @Operation(summary = "Edición del registro")
  @PutMapping("/{id}")
  public ResponseEntity<ProductPojo_bk> update(@PathVariable Integer id,
      @Valid @RequestBody ProductDto_bk dto) {

    return ResponseEntity.status(HttpStatus.CREATED).body(
        productService_bk.update(id, dto)
    );

  }

  @Operation(summary = "Obtención de los datos del registro por el identificador")
  @GetMapping("/{id}")
  public ResponseEntity<ProductPojo_bk> getById(@PathVariable Integer id) {

    return ResponseEntity.status(HttpStatus.OK).body(
        productService_bk.getById(id)
    );

  }

  @Operation(summary = "Eliminación del registro por el identificador")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {

    productService_bk.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

  }

  @Operation(summary = "Deshabilitar del registro por el identificador")
  @DeleteMapping("/disabled/{id}")
  public ResponseEntity<Void> disabled(@PathVariable Integer id) {

    productService_bk.disabled(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

  }

  @Operation(summary = "Paginador y buscador de registros por atributos")
  @GetMapping("/pageable")
  public ResponseEntity<PagePojo<ProductPojo_bk>> pageable(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "5") int size,
      @RequestParam(defaultValue = "id") String sortField,
      @RequestParam(defaultValue = "asc") String sortOrder,
      @RequestParam(required = false) String search,
      @RequestParam(required = false) Integer id,
      @RequestParam(required = false) String code,
      @RequestParam(required = false) String name,
      @RequestParam(required = false) String description,
      @RequestParam(required = false) Integer unitMeasurementId,
      @RequestParam(required = false) BigDecimal approximateUnitPrice  ) {

    ProductFilter_bk filter = ProductFilter_bk.builder()
            .id(id)
            .code(code)
            .name(name)
            .description(description)
            .unitMeasurementId(unitMeasurementId)
            .approximateUnitPrice(approximateUnitPrice)
            .search(search)
            .build();
    return ResponseEntity.status(HttpStatus.OK).body(
        productService_bk.pageable(page, size, sortField, sortOrder, filter)
    );

  }

}
