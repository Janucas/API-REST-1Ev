package com.vedruna.portfolioproject.dto;

import com.vedruna.portfolioproject.persistance.models.Developer;

import lombok.Data;

@Data
public class DeveloperDTO {

    //Declaracion de variables
    private int developer_id;
    private String developer_name;
    private String developer_surname;
    private String email;
    private String linkedin_url;
    private String github_url;  

    public DeveloperDTO(Developer developer) {
        this.developer_id = developer.getDevId();
        this.developer_name = developer.getDevName();
        this.developer_surname = developer.getDevSurname();
        this.email = developer.getEmail();
        this.linkedin_url = developer.getLinkedinUrl();
        this.github_url = developer.getGithubUrl();
    }
    
}
