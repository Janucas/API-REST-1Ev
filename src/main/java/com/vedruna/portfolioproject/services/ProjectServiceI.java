package com.vedruna.portfolioproject.services;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.vedruna.portfolioproject.dto.ProjectDTO;
import com.vedruna.portfolioproject.persistance.models.Project;

public interface ProjectServiceI {

    Page<ProjectDTO> getAllProjects(Pageable pageable); //Devuelve todos los proyectos pajeados
    Page<ProjectDTO> getProjectByWord(Pageable pageable, String word);//Busca un proyecto por palabra clave
    ResponseEntity<Project> saveProject(Project project); //Crea un nuevo proyecto
    ResponseEntity<Project> updateProject(int id, Project project); //Actualiza un proyecto por su id 
    ResponseEntity<Void> deleteProject(int id); //Borra un proyecto por su id
    
}
