package com.vedruna.portfolioproject.persistance.models;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "developers")//nombre de la tabla en la BD
public class Developer implements Serializable{

    //Declaracion de los distintos atributos siguiendo el modelo en la bd con algunas de sus restricciones
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dev_id",  unique = true, nullable = true)
    private int devId;

    @Column(name = "dev_name")
    private String devName;
    
    @Column(name = "dev_surname")
    private String devSurname;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "linkedin_url", unique = true)
    private String linkedinUrl;

    @Column(name = "github_url", unique = true)
    private String githubUrl;

    //RELACIONES N:M CON PROJECTS
    @ManyToMany
    @JoinTable(name="developers_worked_on_projects", joinColumns={@JoinColumn(name="developers_dev_id")}, inverseJoinColumns={@JoinColumn(name="projects_project_id")})
    List<Project> projectsDeveloping;
    
    
    
}
