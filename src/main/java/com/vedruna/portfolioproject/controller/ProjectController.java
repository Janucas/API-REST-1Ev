package com.vedruna.portfolioproject.controller;

import com.vedruna.portfolioproject.dto.ProjectDTO;
import com.vedruna.portfolioproject.persistance.models.Project;
import com.vedruna.portfolioproject.services.ProjectServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {

    @Autowired
    private ProjectServiceI projectService;

    @GetMapping("/show")
    public ResponseEntity<List<Project>> getAllProjects(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<Project> projects = projectService.getAllProjects().getBody();
        return ResponseEntity.ok(projects);
    }

   @GetMapping("/{word}")
    public ResponseEntity<ProjectDTO> getMethodName(@PathVariable String word) {
    return projectService.getProjectByWord(word);
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