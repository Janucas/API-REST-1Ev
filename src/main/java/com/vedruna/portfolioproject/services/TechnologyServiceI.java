package com.vedruna.portfolioproject.services;

import org.springframework.http.ResponseEntity;

import com.vedruna.portfolioproject.persistance.models.Technology;

public interface TechnologyServiceI {
    ResponseEntity<Technology> saveTechnology(Technology technology); //Crea una nueva tecnologia
    ResponseEntity<Void> deleteTechnology(int id); //Borra una tecnologia por su id
}
