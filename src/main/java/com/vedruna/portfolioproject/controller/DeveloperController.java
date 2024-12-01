package com.vedruna.portfolioproject.controller;

import com.vedruna.portfolioproject.persistance.models.Developer;
import com.vedruna.portfolioproject.services.DeveloperServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar operaciones relacionadas con desarrolladores.
 * Permite insertar y eliminar desarrolladores a través de endpoints expuestos.
 */
@RestController
@RequestMapping("/api/v1/developers")
@CrossOrigin // Permite la interacción entre el cliente (frontend) y el servidor (backend).
public class DeveloperController {

    /**
     * Servicio para manejar la lógica de negocio relacionada con desarrolladores.
     */
    @Autowired
    private DeveloperServiceI developerService;

    /**
     * Endpoint para insertar un nuevo desarrollador.
     *
     * @param developer el objeto Developer que se desea insertar.
     * @return una ResponseEntity con:
     *         - El desarrollador creado (HTTP 200) si los datos son válidos.
     *         - Un error (HTTP 400 o 409) si los datos son inválidos o ya existe un conflicto.
     */
    @PostMapping("/insert")
    public ResponseEntity<Developer> createDeveloper(@RequestBody Developer developer) {
        return developerService.saveDeveloper(developer);
    }

    /**
     * Endpoint para eliminar un desarrollador existente por su ID.
     *
     * @param id el ID del desarrollador que se desea eliminar.
     * @return una ResponseEntity con:
     *         - Código HTTP 204 si el desarrollador se elimina correctamente.
     *         - Un error (HTTP 400 o 404) si el ID no es válido o el desarrollador no existe.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeveloper(@PathVariable int id) {
        return developerService.deleteDeveloper(id);
    }
}
