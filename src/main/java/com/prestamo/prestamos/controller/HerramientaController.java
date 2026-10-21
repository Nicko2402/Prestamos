package com.prestamo.prestamos.controller;

import com.prestamo.prestamos.model.Herramienta;
import com.prestamo.prestamos.service.HerramientaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/herramientas")
public class HerramientaController {

    private final HerramientaService herramientaService;

    public HerramientaController(HerramientaService herramientaService) {
        this.herramientaService = herramientaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Herramienta registrar(@Valid @RequestBody Herramienta herramienta) {
        return herramientaService.registrar(herramienta);
    }

    @GetMapping
    public List<Herramienta> listar() {
        return herramientaService.listar();
    }
}