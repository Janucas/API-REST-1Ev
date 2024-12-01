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

@RestController
@RequestMapping("/api/v1/projects")
@CrossOrigin
public class ProjectController {

    @Autowired
    private ProjectServiceI projectService;

    @GetMapping("/show")
    public ResponseEntity<Page<ProjectDTO>> getAllProjects(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "8") int size) {
        // Validación de la paginación
       
            // Crear el Pageable con los parámetros pasados
            Pageable pageable = PageRequest.of(page, size);
            Page<ProjectDTO> projects = projectService.getAllProjects(pageable);
              
            return ResponseEntity.ok(projects);
           
        }

   @GetMapping("/{word}")
    public ResponseEntity<Page<ProjectDTO>> getMethodName(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "8") int size,@PathVariable String word) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProjectDTO> projects = projectService.getProjectByWord(pageable, word);
        return ResponseEntity.ok(projects);
    }

    @PostMapping("/insert")
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        return projectService.saveProject(project);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable int id, @RequestBody Project project) {
        return projectService.updateProject(id, project);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable int id) {
        return projectService.deleteProject(id);
    }
}