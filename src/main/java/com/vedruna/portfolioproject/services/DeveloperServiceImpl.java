package com.vedruna.portfolioproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.vedruna.portfolioproject.persistance.models.Developer;
import com.vedruna.portfolioproject.persistance.repository.DeveloperRepositoryI;

/**
 * Implementación de la interfaz DeveloperServiceI.
 * Proporciona métodos para crear y eliminar entidades de tipo Developer con validaciones simples.
 */
@Service
public class DeveloperServiceImpl implements DeveloperServiceI {

    /**
     * Repositorio para interactuar con los datos de Developer en la capa de persistencia.
     */
    @Autowired
    private DeveloperRepositoryI developerRepository;

    /**
     * Guarda una nueva entidad de tipo Developer, realizando validaciones sobre los datos proporcionados.
     *
     * @param developer la entidad Developer a guardar
     * @return una ResponseEntity que contiene:
     *         - El Developer guardado (código HTTP 200) si los datos son válidos
     *         - Un mensaje de error (código HTTP 400) si los datos son inválidos
     */
    @Override
    public ResponseEntity<Developer> saveDeveloper(Developer developer) {
        if (developer == null) {
            return ResponseEntity.badRequest().header("Error", "El objeto Developer no puede ser nulo").build();
        }

        if (developer.getDevName() == null || developer.getDevName().trim().isEmpty()) { // Validación del nombre
            return ResponseEntity.badRequest().header("Error", "El nombre del desarrollador es obligatorio").build();
        }

        if (developer.getDevSurname() == null || developer.getDevSurname().trim().isEmpty()) {  // Validación del apellido
            return ResponseEntity.badRequest().header("Error", "El apellido del desarrollador es obligatorio").build();
        }

        if (developer.getEmail() == null || !developer.getEmail().contains("@")) {  // Validación del email 
            return ResponseEntity.badRequest().header("Error", "El correo electrónico no es válido").build();
        }

        Developer savedDeveloper = developerRepository.save(developer);
        return ResponseEntity.ok(savedDeveloper);
    }

    /**
     * Elimina una entidad de tipo Developer por su ID, realizando validaciones simples.
     *
     * @param id el ID del Developer a eliminar
     * @return una ResponseEntity con el estado HTTP correspondiente:
     *         - 204 (No Content) si el Developer se eliminó correctamente
     *         - 404 (Not Found) si no existe un Developer con el ID proporcionado
     *         - 400 (Bad Request) si el ID proporcionado no es válido
     */
    @Override
    public ResponseEntity<Void> deleteDeveloper(int id) {
        if (id <= 0) {
            return ResponseEntity.badRequest().header("Error", "El ID debe ser un número positivo").build();
        }

        if (!developerRepository.existsById(id)) {
            return ResponseEntity.notFound().header("Error", "No se encontró un desarrollador con el ID " + id).build();
        }

        developerRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
