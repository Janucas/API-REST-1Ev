package com.vedruna.portfolioproject.services;

import java.net.http.HttpClient;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.NotFound;

import com.vedruna.portfolioproject.dto.ProjectDTO;
import com.vedruna.portfolioproject.persistance.models.Project;
import com.vedruna.portfolioproject.persistance.repository.ProjectRepositoryI;

@Service
public class ProjectServiceImpl implements ProjectServiceI {

    @Autowired
    private ProjectRepositoryI projectRepository;
  
    @Override
    public Page<ProjectDTO> getAllProjects(Pageable pageable) {
        Page <Project> projects = projectRepository.findAll(pageable);

        List<ProjectDTO> projectsDTO = new ArrayList<>();
        for (Project project : projects) {
            ProjectDTO projectDTO = new ProjectDTO(project);
            projectsDTO.add(projectDTO);
        }
        return new PageImpl<>(projectsDTO, pageable, projects.getTotalElements());
    }
    
    @Override
    public Page<ProjectDTO> getProjectByWord(Pageable pageable, String word) {

        Page<Project> projects = projectRepository.findByProjectNameContaining(word, pageable);

        List<ProjectDTO> projectsDTO = new ArrayList<>();
        for (Project project : projects) {
            ProjectDTO projectDTO = new ProjectDTO(project);
            projectsDTO.add(projectDTO);
        }
        return new PageImpl<>(projectsDTO, pageable, projects.getTotalElements());

    }
   

    @Override
    public ResponseEntity<Project> saveProject(Project project) {
        Project savedProject = projectRepository.save(project);
        return ResponseEntity.ok(savedProject);
    }

    @Override
public ResponseEntity<Project> updateProject(int id, Project project) {
    try {
        Optional<Project> projectOptional = projectRepository.findById(id);
        if (projectOptional.isPresent()) {
            Project projectToUpdate = projectOptional.get();
            if (project.getProjectName() != null && !projectToUpdate.getProjectName().equals(project.getProjectName())) {
                Optional<Project> existingProject = projectRepository.findByProjectName(project.getProjectName());
                if (existingProject.isPresent()) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
                }
                projectToUpdate.setProjectName(project.getProjectName());
            }
            projectToUpdate.setProjectDescription(project.getProjectDescription());
            projectToUpdate.setStartDate(project.getStartDate());
            projectToUpdate.setEndDate(project.getEndDate());
            projectToUpdate.setRepositoryUrl(project.getRepositoryUrl());
            projectToUpdate.setDemoUrl(project.getDemoUrl());
            projectToUpdate.setPicture(project.getPicture());
            projectToUpdate.setStatusProject(project.getStatusProject());
            projectToUpdate.setTechnologies(project.getTechnologies());
            projectToUpdate.setDevelopersWorkingOnProjects(project.getDevelopersWorkingOnProjects());
            Project updatedProject = projectRepository.save(projectToUpdate); // Corregido aquí para usar `projectToUpdate`
            return ResponseEntity.ok(updatedProject);
        } else {
            return ResponseEntity.notFound().build();
        }
    } catch (DataIntegrityViolationException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }
}


    @Override
    public ResponseEntity<Void> deleteProject(int id) {
        if (projectRepository.existsById(id)) {
            projectRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
   

}
   
  
  
   
