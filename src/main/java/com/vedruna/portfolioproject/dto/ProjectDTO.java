package com.vedruna.portfolioproject.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.vedruna.portfolioproject.persistance.models.Developer;
import com.vedruna.portfolioproject.persistance.models.Project;
import com.vedruna.portfolioproject.persistance.models.Technology;

import lombok.Data;

@Data
public class ProjectDTO {
    private int project_id;
    private String project_name;
    private String description;
    private Date start_date;
    private Date end_date;
    private String repository_url;
    private String demo_url;
    private String picture; 
    private StatusDTO status_name;

    private List<TechnologyDTO> technologies = new ArrayList<>();
    private List<DeveloperDTO> developers = new ArrayList<>();
    
    public ProjectDTO(Project project) {
        this.project_id = project.getProjectId();
        this.project_name = project.getProjectName();
        this.description = project.getProjectDescription();
        this.start_date = project.getStartDate();
        this.end_date = project.getEndDate();
        this.repository_url = project.getRepositoryUrl();
        this.demo_url = project.getDemoUrl();
        this.picture = project.getPicture();
        StatusDTO statusDTO =  new StatusDTO(project.getStatusProject());
        this.status_name = statusDTO;
        List<TechnologyDTO> technologiesDTO = new ArrayList<>();
        List<DeveloperDTO> developersDTO = new ArrayList<>();

        for (Technology technology : project.getTechnologies()) {
            TechnologyDTO technologyDTO = new TechnologyDTO(technology);
            technologiesDTO.add(technologyDTO);
        }

        for (Developer developer : project.getDevelopersWorkingOnProjects()) {
            DeveloperDTO developerDTO = new DeveloperDTO(developer);
            developersDTO.add(developerDTO);
        }
    }



}
