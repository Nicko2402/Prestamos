package com.prestamo.prestamos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class PrestamoRequest {

    @NotNull(message = "La herramienta es obligatoria")
    private Long herramientaId;

    @NotBlank(message = "El responsable es obligatorio")
    private String responsable;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fechaPrestamo;

    @NotNull(message = "La cantidad es obligatoria")
    private Integer cantidad;

    public Long getHerramientaId() { return herramientaId; }
    public void setHerramientaId(Long herramientaId) { this.herramientaId = herramientaId; }
    public String getResponsable() { return responsable; }
    public void setResponsable(String responsable) { this.responsable = responsable; }
    public LocalDate getFechaPrestamo() { return fechaPrestamo; }
    public void setFechaPrestamo(LocalDate fechaPrestamo) { this.fechaPrestamo = fechaPrestamo; }
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
}