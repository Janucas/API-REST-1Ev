package com.vedruna.portfolioproject.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vedruna.portfolioproject.dto.ProjectDTO;
import com.vedruna.portfolioproject.persistance.models.Project;
import com.vedruna.portfolioproject.persistance.repository.ProjectRepositoryI;

/**
 * Implementación de la interfaz ProjectServiceI.
 * Proporciona métodos para gestionar proyectos, incluyendo operaciones de
 * creación, actualización, eliminación, y búsqueda paginada.
 */
@Service
public class ProjectServiceImpl implements ProjectServiceI {

    /**
     * Repositorio para interactuar con los datos de Project en la capa de persistencia.
     */
    @Autowired
    private ProjectRepositoryI projectRepository;

    /**
     * Obtiene todos los proyectos de forma paginada.
     *
     * @param pageable objeto de paginación que define el tamaño de página y el número de página.
     * @return una página de objetos ProjectDTO que representa los proyectos encontrados.
     * @throws IllegalArgumentException si los parámetros de paginación no son válidos.
     */
    @Override
    public Page<ProjectDTO> getAllProjects(Pageable pageable) {
        if (pageable == null || pageable.getPageSize() <= 0) {
            throw new IllegalArgumentException("La paginación no es válida.");
        }
        
        Page<Project> projects = projectRepository.findAll(pageable);
        List<ProjectDTO> projectsDTO = new ArrayList<>();
        for (Project project : projects) {
            projectsDTO.add(new ProjectDTO(project));
        }
        return new PageImpl<>(projectsDTO, pageable, projects.getTotalElements());
    }

    /**
     * Busca proyectos que contengan una palabra clave en su nombre, de forma paginada.
     *
     * @param pageable objeto de paginación que define el tamaño de página y el número de página.
     * @param word la palabra clave para buscar en los nombres de los proyectos.
     * @return una página de objetos ProjectDTO que representa los proyectos encontrados.
     * @throws IllegalArgumentException si la palabra clave o los parámetros de paginación no son válidos.
     */
    @Override
    public Page<ProjectDTO> getProjectByWord(Pageable pageable, String word) {
        if (word == null || word.trim().isEmpty()) {
            throw new IllegalArgumentException("La palabra clave no puede ser nula o vacía.");
        }
        if (pageable == null || pageable.getPageSize() <= 0) {
            throw new IllegalArgumentException("La paginación no es válida.");
        }

        Page<Project> projects = projectRepository.findByProjectNameContaining(word.trim(), pageable);
        List<ProjectDTO> projectsDTO = new ArrayList<>();
        for (Project project : projects) {
            projectsDTO.add(new ProjectDTO(project));
        }
        return new PageImpl<>(projectsDTO, pageable, projects.getTotalElements());
    }

    /**
     * Guarda un nuevo proyecto en la base de datos.
     *
     * @param project el objeto Project a guardar.
     * @return una ResponseEntity con:
     *         - El proyecto guardado (HTTP 200) si los datos son válidos.
     *         - Un error (HTTP 400 o 409) si los datos son inválidos o ya existe un proyecto con el mismo nombre.
     */
    @Override
    public ResponseEntity<Project> saveProject(Project project) {
        if (project == null) {
            return ResponseEntity.badRequest().header("Error", "El proyecto no puede ser nulo.").build();
        }
        if (project.getProjectName() == null || project.getProjectName().trim().isEmpty()) {
            return ResponseEntity.badRequest().header("Error", "El nombre del proyecto es obligatorio.").build();
        }
        if (projectRepository.findByProjectName(project.getProjectName()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).header("Error", "El nombre del proyecto ya existe.").build();
        }

        Project savedProject = projectRepository.save(project);
        return ResponseEntity.ok(savedProject);
    }

    /**
     * Actualiza un proyecto existente por su ID.
     *
     * @param id el ID del proyecto a actualizar.
     * @param project el objeto Project con los datos actualizados.
     * @return una ResponseEntity con:
     *         - El proyecto actualizado (HTTP 200) si la operación es exitosa.
     *         - Un error (HTTP 400, 404, o 409) si el ID no es válido, el proyecto no existe, o los datos son conflictivos.
     */
    @Override
    public ResponseEntity<Project> updateProject(int id, Project project) {
        if (id <= 0) {
            return ResponseEntity.badRequest().header("Error", "El ID debe ser positivo.").build();
        }
        if (project == null) {
            return ResponseEntity.badRequest().header("Error", "El proyecto no puede ser nulo.").build();
        }

        try {
            Optional<Project> projectOptional = projectRepository.findById(id);
            if (projectOptional.isPresent()) {
                Project projectToUpdate = projectOptional.get();
                if (project.getProjectName() != null && !project.getProjectName().trim().equals(projectToUpdate.getProjectName())) {
                    if (projectRepository.findByProjectName(project.getProjectName()).isPresent()) {
                        return ResponseEntity.status(HttpStatus.CONFLICT).header("Error", "El nombre del proyecto ya existe.").build();
                    }
                    projectToUpdate.setProjectName(project.getProjectName().trim());
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

                Project updatedProject = projectRepository.save(projectToUpdate);
                return ResponseEntity.ok(updatedProject);
            } else {
                return ResponseEntity.notFound().header("Error", "No se encontró el proyecto con ID " + id).build();
            }
        } catch (DataIntegrityViolationException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).header("Error", "Violación de integridad de datos.").build();
        }
    }

    /**
     * Elimina un proyecto existente por su ID.
     *
     * @param id el ID del proyecto a eliminar.
     * @return una ResponseEntity con:
     *         - Código HTTP 204 si el proyecto se elimina correctamente.
     *         - Un error (HTTP 400 o 404) si el ID no es válido o el proyecto no existe.
     */
    @Override
    public ResponseEntity<Void> deleteProject(int id) {
        if (id <= 0) {
            return ResponseEntity.badRequest().header("Error", "El ID debe ser positivo.").build();
        }
        if (!projectRepository.existsById(id)) {
            return ResponseEntity.notFound().header("Error", "No se encontró el proyecto con ID " + id).build();
        }

        projectRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
