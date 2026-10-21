package com.prestamo.prestamos.service;

import com.prestamo.prestamos.dto.PrestamoRequest;
import com.prestamo.prestamos.exception.RecursoNoEncontradoException;
import com.prestamo.prestamos.exception.ReglaNegocioException;
import com.prestamo.prestamos.model.Herramienta;
import com.prestamo.prestamos.model.Prestamo;
import com.prestamo.prestamos.repository.HerramientaRepository;
import com.prestamo.prestamos.repository.PrestamoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final HerramientaRepository herramientaRepository;

    public PrestamoService(PrestamoRepository prestamoRepository,
                           HerramientaRepository herramientaRepository) {
        this.prestamoRepository = prestamoRepository;
        this.herramientaRepository = herramientaRepository;
    }

    @Transactional
    public Prestamo registrar(PrestamoRequest request) {
        Herramienta herramienta = herramientaRepository.findById(request.getHerramientaId())
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe la herramienta con id " + request.getHerramientaId()));

        int cantidad = request.getCantidad();

        if (cantidad <= 0) {
            throw new ReglaNegocioException("La cantidad debe ser mayor que cero");
        }
        if (cantidad > herramienta.getCantidad()) {
            throw new ReglaNegocioException("Unidades insuficientes. Disponibles: "
                    + herramienta.getCantidad());
        }

        herramienta.setCantidad(herramienta.getCantidad() - cantidad);
        herramientaRepository.save(herramienta);

        Prestamo prestamo = new Prestamo();
        prestamo.setHerramienta(herramienta);
        prestamo.setResponsable(request.getResponsable());
        prestamo.setFechaPrestamo(request.getFechaPrestamo());
        prestamo.setCantidad(cantidad);

        return prestamoRepository.save(prestamo);
    }

    public List<Prestamo> listar() {
        return prestamoRepository.findAll();
    }

    public List<Prestamo> listarPorHerramienta(Long herramientaId) {
        return prestamoRepository.findByHerramientaId(herramientaId);
    }
}