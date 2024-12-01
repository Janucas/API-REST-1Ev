package com.vedruna.portfolioproject.controller;

import com.vedruna.portfolioproject.dto.ProjectDTO;
import com.vedruna.portfolioproject.persistance.models.Project;
import com.vedruna.portfolioproject.services.ProjectServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Controlador REST para gestionar operaciones relacionadas con proyectos.
 * Permite realizar operaciones CRUD y buscar proyectos de forma paginada.
 */
@RestController
@RequestMapping("/api/v1/projects")
@CrossOrigin // Permite la interacción entre el cliente (frontend) y el servidor (backend).
public class ProjectController {

    /**
     * Servicio para manejar la lógica de negocio relacionada con proyectos.
     */
    @Autowired
    private ProjectServiceI projectService;

    /**
     * Endpoint para obtener todos los proyectos de forma paginada.
     *
     * @param page número de la página solicitada (por defecto 0).
     * @param size tamaño de la página (por defecto 8).
     * @return una ResponseEntity que contiene una página de objetos ProjectDTO.
     */
    @GetMapping("/show")
    public ResponseEntity<Page<ProjectDTO>> getAllProjects(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "8") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProjectDTO> projects = projectService.getAllProjects(pageable);
        return ResponseEntity.ok(projects);
    }

    /**
     * Endpoint para buscar proyectos por palabra clave, de forma paginada.
     *
     * @param page número de la página solicitada (por defecto 0).
     * @param size tamaño de la página (por defecto 8).
     * @param word palabra clave para buscar en los nombres de los proyectos.
     * @return una ResponseEntity que contiene una página de objetos ProjectDTO.
     */
    @GetMapping("/{word}")
    public ResponseEntity<Page<ProjectDTO>> getMethodName(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "8") int size, @PathVariable String word) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProjectDTO> projects = projectService.getProjectByWord(pageable, word);
        return ResponseEntity.ok(projects);
    }

    /**
     * Endpoint para insertar un nuevo proyecto.
     *
     * @param project el objeto Project que se desea insertar.
     * @return una ResponseEntity que contiene el proyecto creado.
     */
    @PostMapping("/insert")
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        return projectService.saveProject(project);
    }

    /**
     * Endpoint para actualizar un proyecto existente por su ID.
     *
     * @param id el ID del proyecto que se desea actualizar.
     * @param project el objeto Project con los datos actualizados.
     * @return una ResponseEntity que contiene el proyecto actualizado.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable int id, @RequestBody Project project) {
        return projectService.updateProject(id, project);
    }

    /**
     * Endpoint para eliminar un proyecto existente por su ID.
     *
     * @param id el ID del proyecto que se desea eliminar.
     * @return una ResponseEntity con:
     *         - Código HTTP 204 si el proyecto se elimina correctamente.
     *         - Un error (HTTP 400 o 404) si el ID no es válido o el proyecto no existe.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable int id) {
        return projectService.deleteProject(id);
    }
}
