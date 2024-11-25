package com.vedruna.portfolioproject.dto;

import com.vedruna.portfolioproject.persistance.models.Technology;

import lombok.Data;

@Data
public class TechnologyDTO {
    
    private int technology_id;
    private String technology_name;
    
    public TechnologyDTO(Technology technology) {
        this.technology_id = technology.getTechId();
        this.technology_name = technology.getTechName();
    }
    
    
    
}
