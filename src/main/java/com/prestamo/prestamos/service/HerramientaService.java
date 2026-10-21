package com.prestamo.prestamos.service;

import com.prestamo.prestamos.exception.ReglaNegocioException;
import com.prestamo.prestamos.model.Herramienta;
import com.prestamo.prestamos.repository.HerramientaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HerramientaService {

    private final HerramientaRepository herramientaRepository;

    public HerramientaService(HerramientaRepository herramientaRepository) {
        this.herramientaRepository = herramientaRepository;
    }

    public Herramienta registrar(Herramienta herramienta) {
        if (herramienta.getCantidad() < 0) {
            throw new ReglaNegocioException("La cantidad no puede ser negativa");
        }
        herramienta.setId(null);
        return herramientaRepository.save(herramienta);
    }

    public List<Herramienta> listar() {
        return herramientaRepository.findAll();
    }
}