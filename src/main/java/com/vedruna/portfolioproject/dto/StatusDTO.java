package com.vedruna.portfolioproject.dto;

import com.vedruna.portfolioproject.persistance.models.Status;

import lombok.Data;

@Data

public class StatusDTO {
    
    //Declarcion de variables
    private int status_id;
    private String status_name;
    
    public StatusDTO(Status status) {
        if (status != null) {
            this.status_id = status.getStatusId();
            this.status_name = status.getStatusName();
        } else {
            this.status_id = (Integer) null;
            this.status_name = "Unknown"; // O algún valor por defecto
        }
    }
    
}
