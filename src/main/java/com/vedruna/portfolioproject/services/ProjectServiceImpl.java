package com.vedruna.portfolioproject.services;

import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
    public ResponseEntity<ProjectDTO> getProjectByWord(String word) {
        Optional<Project> projectOptional = projectRepository.findByProjectName(word);
        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            ProjectDTO projectDTO = new ProjectDTO(project);
            
            return ResponseEntity.ok(projectDTO);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);        }
    }
    @Override
    public ResponseEntity<ProjectDTO> createProject(Project project) {
    try {
        Project savedProject = projectRepository.save(project);
        ProjectDTO projectDTO = new ProjectDTO(savedProject);
        return ResponseEntity.status(HttpStatus.CREATED).body(projectDTO);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
    @Override
    public ResponseEntity<ProjectDTO> updateProject(Project project, int id) {
        try {
            // Buscar el proyecto por el ID
            Optional<Project> existingProjectOptional = projectRepository.findById(id);
    
            // Si el proyecto no existe, retornar un error 404
            if (!existingProjectOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
    
            // Obtener el proyecto existente
            Project existingProject = existingProjectOptional.get();
    
            // Actualizar los campos del proyecto con los valores proporcionados
            existingProject.setProjectName(project.getProjectName());
            existingProject.setProjectDescription(project.getProjectDescription());
            existingProject.setStartDate(project.getStartDate());
            existingProject.setEndDate(project.getEndDate());
            existingProject.setRepositoryUrl(project.getRepositoryUrl());
            existingProject.setDemoUrl(project.getDemoUrl());
            existingProject.setPicture(project.getPicture());
            existingProject.setStatusProject(project.getStatusProject());
            existingProject.setTechnologies(project.getTechnologies());
            existingProject.setDevelopersWorkingOnProjects(project.getDevelopersWorkingOnProjects());
    
            // Guardar el proyecto actualizado
            Project updatedProject = projectRepository.save(existingProject);
    
            // Convertir el proyecto actualizado a DTO y devolverlo en la respuesta
            ProjectDTO projectDTO = new ProjectDTO(updatedProject);
            return ResponseEntity.ok(projectDTO);
    
        } catch (Exception e) {
            // Manejo de errores: si algo falla, retornamos un error 500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    
}
