package com.vedruna.portfolioproject.services;

import java.net.http.HttpClient;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return ResponseEntity.ok(projects);
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
    public ResponseEntity<Project> saveProject(Project project) {
        Project savedProject = projectRepository.save(project);
        return ResponseEntity.ok(savedProject);
    }

    @Override
    public ResponseEntity<Project> updateProject(int id, Project project) {
        if (projectRepository.existsById(id)) {
            project.setProjectId(id);
            Project updatedProject = projectRepository.save(project);
            return ResponseEntity.ok(updatedProject);
        } else {
            return ResponseEntity.notFound().build();
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
   
  
  
   
