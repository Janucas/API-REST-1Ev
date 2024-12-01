package com.vedruna.portfolioproject.persistance.models;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "projects")//nombre de la tabla en la BD
public class Project implements Serializable {
       
    //Declaracion de los distintos atributos siguiendo el modelo en la bd con algunas de sus restricciones
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="project_id", nullable = true )
    private int projectId;

    @Column(name="project_name", nullable = true, unique = true)
    private String projectName;

    @Column(name="description")
    private String projectDescription;

    @Column(name="start_date")
    private Date startDate;

    @Column(name="end_date")
    private Date endDate;

    @Column(name="repository_url")
    private String repositoryUrl;

    @Column(name="demo_url")
    private String demoUrl;

    @Column(name="picture", nullable = true)
    private String picture;

    //relacion N:1 con status
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_status_id", referencedColumnName ="status_id")
    private Status statusProject;

    //relacion N:M con technologies
    @ManyToMany(cascade= {CascadeType.ALL}, mappedBy="projectsTechnologies")
    private List<Technology> technologies;

    //relacion N:M con developers
    @ManyToMany(cascade = {CascadeType.ALL}, mappedBy="projectsDeveloping")
    private List<Developer> developersWorkingOnProjects;
    
    


}
