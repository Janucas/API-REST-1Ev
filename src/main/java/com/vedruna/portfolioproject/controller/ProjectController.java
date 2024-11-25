package com.vedruna.portfolioproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vedruna.portfolioproject.dto.ProjectDTO;
import com.vedruna.portfolioproject.persistance.models.Project;
import com.vedruna.portfolioproject.services.ProjectServiceI;

@RestController
@RequestMapping("/api/v1")
public class ProjectController {
    @Autowired
    ProjectServiceI projectService;


     /**
     
Obtiene una lista paginada de proyectos.
@param page El número de página a obtener, 0.
@param size El tamaño de la página, 5.
@return ResponseEntity con una página de objetos ProjectDTO.
@throws ExceptionErrorPage Si el número de página o tamaño de página no es válido.*/
@GetMapping("/projects")
public ResponseEntity<Page<ProjectDTO>> getAllProjects(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "5") int size) {

    PageRequest pageable = PageRequest.of(page, size);
    Page<ProjectDTO> projects = projectService.getAllProjects(pageable);

    return ResponseEntity.ok(projects);
}
    
    //Obtener el proyecto que contenga la palabra introducida
@GetMapping("/projects/{word}")
public ResponseEntity<ProjectDTO> getMethodName(@PathVariable String word) {
    return projectService.getProjectByWord(word);
}

//Insertar un nuevo proyecto
@PostMapping("/projects")
public ResponseEntity<ProjectDTO> createProject(@RequestBody Project project) { 
    try {
        // Llama al servicio para crear el proyecto
        return projectService.createProject(project);
    } catch (Exception e) {
        return ResponseEntity.status(500).body(null);
    }
}



}


