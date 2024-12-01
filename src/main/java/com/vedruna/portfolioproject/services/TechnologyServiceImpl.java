package com.vedruna.portfolioproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vedruna.portfolioproject.persistance.models.Technology;
import com.vedruna.portfolioproject.persistance.repository.TechnologyRepositoryI;

/**
 * Implementación de la interfaz TechnologyServiceI.
 * Proporciona métodos para gestionar tecnologías, incluyendo la creación y eliminación con validaciones básicas.
 */
@Service
public class TechnologyServiceImpl implements TechnologyServiceI {

    /**
     * Repositorio para interactuar con los datos de Technology en la capa de persistencia.
     */
    @Autowired
    private TechnologyRepositoryI technologyRepository;

    /**
     * Guarda una nueva tecnología en la base de datos, realizando validaciones sobre los datos proporcionados.
     *
     * @param technology el objeto Technology a guardar.
     * @return una ResponseEntity con:
     *         - La tecnología guardada (HTTP 200) si los datos son válidos.
     *         - Un error (HTTP 400) si los datos son inválidos.
     */
    @Override
    public ResponseEntity<Technology> saveTechnology(Technology technology) {
        if (technology == null) {
            return ResponseEntity.badRequest().header("Error", "La tecnología no puede ser nula.").build();
        }
        if (technology.getTechName() == null || technology.getTechName().trim().isEmpty()) {
            return ResponseEntity.badRequest().header("Error", "El nombre de la tecnología es obligatorio.").build();
        }
        Technology savedTechnology = technologyRepository.save(technology);
        return ResponseEntity.ok(savedTechnology);
    }

    /**
     * Elimina una tecnología existente por su ID, realizando validaciones simples.
     *
     * @param id el ID de la tecnología a eliminar.
     * @return una ResponseEntity con:
     *         - Código HTTP 204 si la tecnología se elimina correctamente.
     *         - Un error (HTTP 400 o 404) si el ID no es válido o la tecnología no existe.
     */
    @Override
    public ResponseEntity<Void> deleteTechnology(int id) {
        if (id <= 0) {
            return ResponseEntity.badRequest().header("Error", "El ID debe ser un número positivo.").build();
        }
        if (!technologyRepository.existsById(id)) {
            return ResponseEntity.notFound().header("Error", "No se encontró una tecnología con el ID " + id).build();
        }

        technologyRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
