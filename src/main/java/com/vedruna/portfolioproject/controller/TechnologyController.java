package com.vedruna.portfolioproject.controller;

import com.vedruna.portfolioproject.persistance.models.Technology;
import com.vedruna.portfolioproject.services.TechnologyServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar operaciones relacionadas con tecnologías.
 * Permite insertar y eliminar tecnologías a través de endpoints expuestos.
 */
@RestController
@RequestMapping("/api/v1/technologies")
@CrossOrigin // Permite la interacción entre el cliente (frontend) y el servidor (backend).
public class TechnologyController {

    /**
     * Servicio para manejar la lógica de negocio relacionada con tecnologías.
     */
    @Autowired
    private TechnologyServiceI technologyService;

    /**
     * Endpoint para insertar una nueva tecnología.
     *
     * @param technology el objeto Technology que se desea insertar.
     * @return una ResponseEntity con:
     *         - La tecnología creada (HTTP 200) si los datos son válidos.
     *         - Un error (HTTP 400 o 409) si los datos son inválidos o ya existe un conflicto.
     */
    @PostMapping("/insert")
    public ResponseEntity<Technology> createTechnology(@RequestBody Technology technology) {
        return technologyService.saveTechnology(technology);
    }

    /**
     * Endpoint para eliminar una tecnología existente por su ID.
     *
     * @param id el ID de la tecnología que se desea eliminar.
     * @return una ResponseEntity con:
     *         - Código HTTP 204 si la tecnología se elimina correctamente.
     *         - Un error (HTTP 400 o 404) si el ID no es válido o la tecnología no existe.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTechnology(@PathVariable int id) {
        return technologyService.deleteTechnology(id);
    }
}
