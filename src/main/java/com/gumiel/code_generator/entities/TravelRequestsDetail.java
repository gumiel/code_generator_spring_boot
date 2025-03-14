package com.gumiel.code_generator.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class TravelRequestsDetail{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  // Datos presupuesto
  @ManyToOne
  @JoinColumn(name = "external_budget_id", nullable = false)
  @JsonIgnore
  @Schema(description = "Presupuesto")
  private Object budget;

  @ManyToOne
  @JoinColumn(name = "external_concept_id", nullable = false)
  @JsonIgnore
  @Schema(description = "Concepto")
  private Object externalConcept;

  @Schema(description = "Indica si entra o no al solicitante", example = "true")
  private Boolean deliverAmountApplicant;

  // Datos generales
  @Column(name = "quantity")
  @Schema(description = "Cantidad solicitada", nullable = false)
  private BigDecimal quantity;

  @Column(name = "requested_amount")
  @Schema(description = "Importe solicitado", nullable = false)
  private BigDecimal requestedAmount;

  @Column(name = "requested_amount")
  @Schema(description = "Observación")
  private String observation;

  // Datos pasaje
  @ManyToOne
  @JoinColumn(name = "type_transport_id")
  @Schema(description = "ID del tipo de transporte como por ejemplo Aéreo, Ninguno, Terrestre o fluvial marítimo")
  private Object typeTransport;

  // Datos de viáticos
  @Schema(description = "Via o forma de transporte", example = "aéreo o terrestre")
  private String way;

  @ManyToOne
  @JoinColumn(name = "destination_type_id")
  private Object destinationType;

  @ManyToOne
  @JoinColumn(name = "coverage_id")
  private Object coverage;

  @Schema(description = "Fecha de inicio")
  private LocalDate startDate;

  @Schema(description = "Fecha de finalización")
  private LocalDate endDate;

  @ManyToOne
  @JoinColumn(name = "travel_requests_id", nullable = false)
  private Object travelRequests;

}
