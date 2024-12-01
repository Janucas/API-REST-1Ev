package com.vedruna.portfolioproject.persistance.models;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@Data
@Entity
@Table(name = "status") //nombre de la tabla en la BD
public class Status implements Serializable {

        //Declaracion de los distintos atributos siguiendo el modelo en la bd con algunas de sus restricciones
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id", nullable = false)
    private int statusId;

    @Column(name = "status_name", nullable = false, unique = true)
    private String statusName;  

    
    //RWLACIONES 1:N CON PROJECTS
    @OneToMany(fetch= FetchType.LAZY, mappedBy="statusProject")
    private List<Project> statusProject;


   
    
}
