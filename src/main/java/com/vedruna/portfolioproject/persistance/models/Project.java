package com.vedruna.portfolioproject.persistance.models;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
@Table(name = "projects")
public class Project implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="project_id")
    private int projectId;

    @Column(name="project_name")
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

    @Column(name="picture")
    private String picture;

    //Falta hacer la columna de la CLAVES FORANEAS

    //relacion N:1 con status
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_status_id", referencedColumnName ="status_id")
    private Status statusProject;

    @ManyToMany(cascade= {CascadeType.ALL}, mappedBy="projects")
    private List<Technology> technologies;

    
    


}
