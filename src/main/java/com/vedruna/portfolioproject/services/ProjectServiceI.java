package com.vedruna.portfolioproject.services;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.vedruna.portfolioproject.dto.ProjectDTO;
import com.vedruna.portfolioproject.persistance.models.Project;

public interface ProjectServiceI {

    Page<ProjectDTO> getAllProjects(Pageable pageable);

    ResponseEntity<ProjectDTO> getProjectByWord(String word);
    ResponseEntity<ProjectDTO> createProject(Project project);
    ResponseEntity<ProjectDTO> updateProject(Project project, int id);


    
}
