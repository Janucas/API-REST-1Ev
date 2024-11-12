package com.vedruna.portfolioproject.persistance.models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "technologies")
public class Technology implements Serializable {

    @Id
    @Column(name = "tech_id")
    private int techId;

    @Column(name = "tech_name")
    private String techName;

    //RELACIONES N:M CON PROJECTS
    
}
