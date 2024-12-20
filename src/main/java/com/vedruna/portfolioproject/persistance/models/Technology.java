package com.vedruna.portfolioproject.persistance.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "technologies") //nombre de la tabla en la BD
public class Technology implements Serializable {

        //Declaracion de los distintos atributos siguiendo el modelo en la bd con algunas de sus restricciones
    @Id
    @Column(name = "tech_id", nullable = true)
    private int techId;

    @Column(name = "tech_name", unique = true)
    private String techName;

    //RELACIONES N:M CON PROJECTS
    @ManyToMany
    @JoinTable(name="technologies_used_in_projects", joinColumns={@JoinColumn(name="technologies_tech_id")}, inverseJoinColumns={@JoinColumn(name="projects_project_id")})
    private List<Project> projectsTechnologies = new ArrayList<>();
    
}
