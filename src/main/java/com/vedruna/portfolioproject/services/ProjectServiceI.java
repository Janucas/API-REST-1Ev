package com.vedruna.portfolioproject.services;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.vedruna.portfolioproject.dto.ProjectDTO;
import com.vedruna.portfolioproject.persistance.models.Project;

public interface ProjectServiceI {

    ResponseEntity<List<Project>> getAllProjects();
    ResponseEntity<ProjectDTO> getProjectByWord(String word);//Busca un proyecto por palabra clave
    ResponseEntity<Project> saveProject(Project project);
    ResponseEntity<Project> updateProject(int id, Project project);
    ResponseEntity<Void> deleteProject(int id);
    
}
